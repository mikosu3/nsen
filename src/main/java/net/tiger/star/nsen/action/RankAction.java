package net.tiger.star.nsen.action;

import java.util.List;

import javax.annotation.Resource;

import net.tiger.star.nsen.dto.RankDto;
import net.tiger.star.nsen.form.RankForm;
import net.tiger.star.nsen.logic.TigerLogic;
import net.tiger.star.nsen.service.ChannelLogService;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class RankAction extends AbstractBaseAction {


    @ActionForm
    @Resource
    protected RankForm rankForm;

    @Resource
    protected ChannelLogService channelLogService;

    @Resource
    protected TigerLogic tigerLogic;

    public List<RankDto> rankItems;

    @Execute(validator = false)
    public String index() {

        // カテゴリ毎のランキング取得
        rankItems = tigerLogic.getRanking(10);

        return "index.jsp";
    }

}