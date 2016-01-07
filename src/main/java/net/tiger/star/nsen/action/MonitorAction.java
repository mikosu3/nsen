package net.tiger.star.nsen.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.niconico.api.client.NiconicoApiClient;
import jp.niconico.api.exception.NiconicoException;
import net.tiger.star.nsen.dto.MyDataDto;
import net.tiger.star.nsen.form.MonitorForm;
import net.tiger.star.nsen.logic.TigerLogic;
import net.tiger.star.nsen.service.MonitorWebService;
import net.tiger.star.nsen.service.NsenUserService;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

/**
 * 監視対象メンテナンス
 *
 * @author admin
 *
 */
public class MonitorAction extends AbstractBaseAction {

    public List<MyDataDto> myDataList;

    @ActionForm
    @Resource
    protected MonitorForm monitorForm;

    @Resource
    protected MonitorWebService monitorWebService;

    @Resource
    protected NsenUserService nsenUserService;

    @Resource
    protected TigerLogic tigerLogic;

    @Execute(validator = false)
    public String index() {

        // マイデータ取得
        myDataList = tigerLogic.getMyData(userDataDto.nsenUser.userId);

        // 上限取得
        Integer max = new Integer(nsenUserService.findById(userDataDto.nsenUser.userId).targetLimit);

        // maxまで空白で埋める
        for (int i = myDataList.size(); i < max; i++) {
            myDataList.add(new MyDataDto());
        }

        return "list.jsp";
    }

    @Execute(input = "riseError",validate="validateInput", redirect = true)
    public String update() {

        // 最大数チェック
        int max = nsenUserService.findById(userDataDto.nsenUser.userId).targetLimit;
        if (monitorForm.videos.size() > max) {
            throw new ActionMessagesException("errors.orver.limit", max);
        }

        // 削除＆登録
        monitorWebService.updateMonitor(userDataDto.nsenUser.userId, monitorForm.videos);

        return "/monitor/";
    }

    /**
     * 入力チェック
     * @return
     */
    public ActionMessages validateInput() {
        ActionMessages errors = new ActionMessages();

        for (String video : monitorForm.videos) {
            if (StringUtil.isNotBlank(video)) {
                // 半角英数チェック
                if (!video.matches("^[0-9a-zA-Z]+$")) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.aplha.numeric.video", video));
                } else {
                    // 動画存在チェック
                    try {
                        NiconicoApiClient client = new NiconicoApiClient();
                        client.getThumbInfo(video);
                    } catch (NullPointerException | NiconicoException e) {
                        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.invalid.video", video));
                    }
                }
            }
        }

        return errors;
    }


    /**
     * 入力エラー時
     * @return
     */
    @Execute(validator = false)
    public String riseError() {

        myDataList = new ArrayList<>();

        // 入力値から動画情報を再取得
        for (String video : monitorForm.videos) {
            myDataList.add(tigerLogic.getMyDataDto(video));
        }
        // 上限取得
        Integer max = new Integer(nsenUserService.findById(userDataDto.nsenUser.userId).targetLimit);

        // maxまで空白で埋める
        for (int i = myDataList.size(); i < max; i++) {
            myDataList.add(new MyDataDto());
        }

        return "list.jsp";
    }
}