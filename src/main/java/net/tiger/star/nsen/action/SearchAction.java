package net.tiger.star.nsen.action;

import java.util.List;

import javax.annotation.Resource;

import net.tiger.star.nsen.dto.ChannelLogDataDto;
import net.tiger.star.nsen.form.SearchForm;
import net.tiger.star.nsen.logic.TigerLogic;
import net.tiger.star.nsen.service.ChannelLogService;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class SearchAction extends AbstractBaseAction {


    @ActionForm
    @Resource
    protected SearchForm searchForm;

    @Resource
    protected ChannelLogService channelLogService;

    @Resource
    protected TigerLogic tigerLogic;

    public List <ChannelLogDataDto> channelLogDataItems;

    @Execute(validator = false)
    public String index() {

        return "index.jsp";
    }

    @Execute(validator = false)
    public String search() {

        // ログ検索
        channelLogDataItems = tigerLogic.getChannelLogData(channelLogService.getVideo(searchForm.video));

        return "index.jsp";
    }

}