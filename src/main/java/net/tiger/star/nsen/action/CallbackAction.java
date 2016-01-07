package net.tiger.star.nsen.action;

import java.sql.Timestamp;

import javax.annotation.Resource;

import net.tiger.star.nsen.entity.NsenUser;
import net.tiger.star.nsen.form.CallbackForm;
import net.tiger.star.nsen.service.NsenUserService;

import org.seasar.framework.log.Logger;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class CallbackAction extends AbstractBaseAction {

    private static Logger log = Logger.getLogger(CallbackAction.class);

    @ActionForm
    @Resource
    protected CallbackForm callbackForm;

    @Resource
    protected NsenUserService nsenUserService;

    @Execute(validator = false, redirect=true)
    public String auth() {
        if (StringUtil.isNotBlank(callbackForm.denied)) {
            return "/";
        }

        // ユーザー登録
        NsenUser nsenUser = getUser(callbackForm.oauth_verifier);

        // 登録できなかった場合はログインページへ飛ばす
        if (nsenUser == null) {
            log.debug("ログイン失敗");
            return "/";
        }

        // 最終ログイン日時を更新
        nsenUser.lastLogin = new Timestamp(System.currentTimeMillis());

        // 登録or更新
        if (nsenUser.userId == null) {
            nsenUserService.insert(nsenUser);
        } else {
             nsenUserService.updateExcludesNull(nsenUser);
        }

        // セッションにセット
        userDataDto.nsenUser = nsenUser;

        // チケット更新
        setTicket();

        return "/top";
    }


    /**
     * Oath認証を行い、ユーザー情報の更新/登録を行う
     * @param verifier
     * @return
     */
    private NsenUser getUser(String  verifier) {

        try {
            Twitter twitter = new TwitterFactory().getInstance();
            AccessToken accessToken = twitter.getOAuthAccessToken(userDataDto.requestToken, verifier);

            // TwitterIDで登録されているか
            NsenUser entity = nsenUserService.findByTwitterId(twitter.getId());

            // 登録されているか
            if (entity == null) {
                entity = new NsenUser();
                entity.accessToken = accessToken.getToken();
                entity.accessTokenSecret = accessToken.getTokenSecret();
                entity.screenName = twitter.getScreenName();
                entity.twitterUserId = twitter.getId();
            } else {
                entity.accessToken = accessToken.getToken();
                entity.accessTokenSecret = accessToken.getTokenSecret();
                entity.screenName = twitter.getScreenName();
            }

            return entity;
        } catch (IllegalStateException e) {
            log.warn("Twitter:" + e.getMessage());
            return null;
        } catch (TwitterException e) {
            log.warn("Twitter:" + e.getErrorMessage());
            return null;
        }
    }
}
