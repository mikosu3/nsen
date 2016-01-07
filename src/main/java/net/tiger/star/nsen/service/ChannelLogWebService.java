package net.tiger.star.nsen.service;

import static net.tiger.star.nsen.entity.ChannelLogNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.sql.Timestamp;

import net.tiger.star.nsen.entity.ChannelLog;

/**
 * {@link ChannelLog}のサービスクラスです。
 *
 */
public class ChannelLogWebService extends ChannelLogService {

    /**
     * 対象動画の最新1件を取得する
     * @return
     */
    public ChannelLog getLastOne(String video) {
        return select().orderBy(desc(channelLogId())).where(eq(video(), video)).limit(1).getSingleResult();
    }

    /**
     * 放映回数を取得する
     * @param video
     * @return
     */
    public Long getCount(String video) {
        return select().where(eq(video(), video)).getCount();
    }

    /**
     * 総件数を取得
     * @return
     */
    public Long getTotalCnt() {
        return select().where(isNotNull(video())).getCount();
    }

    /**
     * 最古の日付を取得
     * @return
     */
    public Timestamp getOldDate() {
        return select().orderBy(asc(createAt())).limit(1).getSingleResult().createAt;
    }

    /**
     * 最新の日付を取得
     * @return
     */
    public Timestamp getLatestDate() {
        return select().orderBy(desc(createAt())).limit(1).getSingleResult().createAt;
    }

}