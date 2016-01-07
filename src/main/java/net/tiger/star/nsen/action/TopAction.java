package net.tiger.star.nsen.action;

import java.util.List;

import javax.annotation.Resource;

import net.tiger.star.nsen.dto.MyDataDto;
import net.tiger.star.nsen.form.TopForm;
import net.tiger.star.nsen.logic.TigerLogic;
import net.tiger.star.nsen.service.MonitorWebService;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class TopAction extends AbstractBaseAction {


    @ActionForm
    @Resource
    protected TopForm topForm;

    @Resource
    protected MonitorWebService monitorWebService;

    @Resource
    protected TigerLogic tigerLogic;

    public List<MyDataDto> myDataList;


    @Execute(validator = false)
    public String index() {

        // マイデータ取得
        myDataList = tigerLogic.getMyData(userDataDto.nsenUser.userId);

        return "index.jsp";
    }

}