package net.tiger.star.nsen.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tiger.star.nsen.dto.UserDataDto;
import net.tiger.star.nsen.logic.TigerLogic;


/**
 * アクション既定クラス
 * @author mikosu3
 *
 */
public abstract class AbstractBaseAction {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Resource
    protected UserDataDto userDataDto;

    @Resource
    protected TigerLogic tigerLogic;

    /** 1ページあたりの表示件数 */
    protected Integer limit = 50;

    /** ページャーの最大件数(何ページまでリンクを貼るか) */
    protected Integer maxPages = 10;

    /** 総件数 */
    protected Long total = 0L;

    /**
     * 開始行数の取得
     * @return
     */
    public int getOffset(int page) {
        int pageNum = 0;

        if  (page -1 > 0) {
            pageNum = page -1;
        }

        return pageNum  * limit;
    }

    /**
     * ページ数設定
     * @return
     */
    public List<String> getPages() {
        // ページ数設定
        List<String> pages = new ArrayList<String>();
        for(long i = 1L; i < total/limit; i++) {
            pages.add(String.valueOf(i));
        }
        return pages;
    }

    /**
     * @return limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @return maxPages
     */
    public Integer getMaxPages() {
        return maxPages;
    }

    /**
     * @return total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * ログインユーザー名
     * @return
     */
    public String getUserName() {
        try {
            return userDataDto.nsenUser.screenName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * ログインユーザーId
     * @return
     */
    public String getMyUserId() {
        try {
            return userDataDto.nsenUser.userId.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 自動ログイン用のランダム文字列をクッキーに設定する
     */
    protected void setTicket() {
        tigerLogic.setTicket(request, response, userDataDto.nsenUser);
    }

    /**
     * 自動ログイン用のチケットをクリアする
     */
    protected void clearTicket() {
        tigerLogic.clearTicket(request, response, userDataDto.nsenUser);
    }

}
