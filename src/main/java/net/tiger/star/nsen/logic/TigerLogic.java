package net.tiger.star.nsen.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.niconico.api.client.NiconicoApiClient;
import jp.niconico.api.entity.ThumbInfo;
import jp.niconico.api.exception.NiconicoException;
import net.tiger.star.nsen.constant.BaseConst;
import net.tiger.star.nsen.dto.ChannelLogDataDto;
import net.tiger.star.nsen.dto.MyDataDto;
import net.tiger.star.nsen.dto.RankDto;
import net.tiger.star.nsen.dto.RankRowDto;
import net.tiger.star.nsen.dto.VideoInfoDto;
import net.tiger.star.nsen.entity.Channel;
import net.tiger.star.nsen.entity.ChannelLog;
import net.tiger.star.nsen.entity.Monitor;
import net.tiger.star.nsen.entity.NsenUser;
import net.tiger.star.nsen.service.ChannelLogService;
import net.tiger.star.nsen.service.ChannelLogWebService;
import net.tiger.star.nsen.service.ChannelService;
import net.tiger.star.nsen.service.MonitorWebService;
import net.tiger.star.nsen.service.NsenUserService;
import net.tiger.star.nsen.util.CommonUtil;
import net.tiger.star.nsen.util.DateUtil;

import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.log.Logger;

/**
 * Webアプリ共通ロジック
 * @author mikosu3
 *
 */
public class TigerLogic {

    @Resource
    protected NsenUserService nsenUserService;

    @Resource
    protected MonitorWebService monitorWebService;

    @Resource
    protected ChannelService channelService;

    @Resource
    protected ChannelLogService channelLogService;

    @Resource
    protected ChannelLogWebService channelLogWebService;

    private static Logger log = Logger.getLogger(TigerLogic.class);

    /**
     * 自動ログイン用のランダム文字列をクッキーとDB両方にセットする
     *
     * @param request
     * @param response
     * @param nsenUser
     */
    public void setTicket(HttpServletRequest request, HttpServletResponse response, NsenUser nsenUser) {

        // チケット文字列取得
        String ticket = CommonUtil.getRandomSha256();

        // クッキーにチケットセット
        Cookie c = new Cookie(BaseConst.loginKey, ticket);
        c.setMaxAge(604800);
        c.setPath(request.getContextPath());
        response.addCookie(c);

        // DBに同じ値を設定
        nsenUser.ticket = ticket;
        nsenUserService.update(nsenUser);
    }

    /**
     * 自動ログイン用のチケットをクリアする
     *
     * @param request
     * @param response
     * @param player
     */
    public void clearTicket(HttpServletRequest request, HttpServletResponse response, NsenUser nsenUser) {
        // クッキー削除
        Cookie c = new Cookie(BaseConst.loginKey, null);
        c.setMaxAge(0);
        c.setPath(request.getContextPath());
        response.addCookie(c);

        // チケットクリア
        nsenUserService.clearTicket(nsenUser.userId);
    }

    /**
     * マイデータを取得する
     * @param userId
     * @return
     */
    public List<MyDataDto> getMyData(Long userId) {
        List<MyDataDto> ret = new ArrayList<>();

        // 動画情報を作る
        for (Monitor m : monitorWebService.getMyData(userId)) {
            ret.add(getMyDataDto(m.video));
        }

        return ret;
    }

    /**
     * チャンネルログを取得する
     * @param userId
     * @return
     */
    public List<ChannelLogDataDto> getChannelLogData(List<ChannelLog> logs) {
        List<ChannelLogDataDto> ret = new ArrayList<>();

        // ログ取得
        for (ChannelLog row : logs) {
            ret.add(Beans.createAndCopy(ChannelLogDataDto.class, getMyDataDto(row.video)).execute());
        }
        return ret;
    }

    /**
     * カテゴリ毎のランキングを取得する
     * @param limit
     * @return
     */
    public List<RankDto> getRanking(Integer limit) {
         List<RankDto> ret = new ArrayList<>();

         for (Channel ch : channelService.findAll()) {
             RankDto row = new RankDto();

             row.channelName = ch.channelName;

             // ランキング作成
             List<ChannelLogDataDto> channelLogDataDtoItems = new ArrayList<>();

             for (RankRowDto log : channelLogService.getRanking(ch.channelId, limit)) {
                 ChannelLogDataDto dto = new ChannelLogDataDto();
                 dto.video = log.video;
                 dto.onAirCnt = log.cnt;
                 dto.lastOnAir = DateUtil.formatDateHour(log.createAt);

                 // ニコニコから取得
                 VideoInfoDto videoInfo = getVideoInfo(log.video);
                 dto.thumbnailUrl = videoInfo.thumbnailUrl;
                 dto.title = videoInfo.title;


                 channelLogDataDtoItems.add(dto);
             }

             row.channelLogDataDtoItems = channelLogDataDtoItems;
             ret.add(row);
         }

        return ret;
    }

    /**
     * 動画IDから表示用のデータを取得する
     * @param video
     * @return
     */
    public MyDataDto getMyDataDto(String video) {
        MyDataDto ret = new MyDataDto();

        ret.video = video;

        VideoInfoDto videoInfo = getVideoInfo(video);
        ret.title = videoInfo.title;
        ret.thumbnailUrl = videoInfo.thumbnailUrl;

        // 最終放映日
        try {
            ret.lastOnAir = DateUtil.formatDateHour(channelLogWebService.getLastOne(video).createAt);
        } catch (NullPointerException e) {
            ret.lastOnAir = "";
        }

        // 放映回数
        ret.onAirCnt = channelLogWebService.getCount(video);

        return ret;
    }

    /**
     * ニコニコから動画情報取得
     * @param video
     * @return
     */
    public VideoInfoDto getVideoInfo(String video) {
        VideoInfoDto ret = new VideoInfoDto();
        NiconicoApiClient client = new NiconicoApiClient();

        try {
            // 動画サムネ情報
            ThumbInfo t = client.getThumbInfo(video);
            ret.title = t.title;
            ret.thumbnailUrl = t.thumbnailUrl;

        } catch (NiconicoException e) {
            log.warn("動画タイトル取得失敗", e);
        }

        return ret;
    }
}
