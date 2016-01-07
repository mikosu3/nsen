/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package net.tiger.star.nsen.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import net.tiger.star.nsen.constant.BaseConst;
import net.tiger.star.nsen.dto.RankDto;
import net.tiger.star.nsen.logic.TigerLogic;
import net.tiger.star.nsen.service.ChannelLogWebService;
import net.tiger.star.nsen.service.NsenUserService;
import net.tiger.star.nsen.util.DateUtil;

import org.seasar.framework.env.Env;
import org.seasar.struts.annotation.Execute;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class IndexAction extends AbstractBaseAction {

    @Resource
    protected Map<String, Cookie> cookie;

    @Resource
    protected NsenUserService nsenUserService;


    @Resource
    protected ChannelLogWebService channelLogWebService;

    @Resource
    protected TigerLogic tigerLogic;

    public List<RankDto> rankItems;

    public Long totalCnt;

    public String start;

    public String end;


    @Execute(validator = false)
    public String index() {

        // クッキーのチケットからユーザー情報取得
        try {
            userDataDto.nsenUser = nsenUserService.findByTicket(this.cookie.get(BaseConst.loginKey).getValue());
        } catch (Exception e) {
            userDataDto.nsenUser = null;
        }

        // カテゴリ毎のランキング取得
        rankItems = tigerLogic.getRanking(5);

        totalCnt = channelLogWebService.getTotalCnt();
        start = DateUtil.formatDateHour(channelLogWebService.getOldDate());
        end = DateUtil.formatDateHour(channelLogWebService.getLatestDate());

        // 開発用
        if("ct".equals(Env.getValue())) {
            userDataDto.nsenUser = nsenUserService.findById(1L);
        }

        // セッションがあればTOPへ移動
        try {
            if (userDataDto.nsenUser != null && nsenUserService.isActiveUser(userDataDto.nsenUser.userId)) {

                // 最終ログイン更新
                userDataDto.nsenUser.lastLogin = new Timestamp(System.currentTimeMillis());
                nsenUserService.update(userDataDto.nsenUser);

                // チケット更新
                setTicket();

                return "/top/?redirect=true";
            }
        } catch(Exception e) {
            return "index.jsp";
        }
        return "index.jsp";
    }

    @Execute(validator = false, redirect=true)
    public String login() {
        Twitter twitter = new TwitterFactory().getInstance();

        // RequestTokenとTwitterオブジェクトをセッションに保存
        try {
            userDataDto.requestToken = twitter.getOAuthRequestToken();
        } catch (TwitterException e) {
            return null;
        }
        return userDataDto.requestToken.getAuthorizationURL();
    }

}
