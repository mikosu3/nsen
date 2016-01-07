package net.tiger.star.nsen.service;

import static net.tiger.star.nsen.entity.MonitorNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.ArrayList;
import java.util.List;

import net.tiger.star.nsen.entity.Monitor;

import org.seasar.extension.jdbc.JoinType;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.util.StringUtil;

public class MonitorWebService extends MonitorService {

    /**
     * マイデータを取得する
     * @param userId
     * @return
     */
    public List<Monitor> getMyData(Long userId) {
        return select().join(nsenUser(), JoinType.INNER).where(eq(nsenUser().userId(), userId)).getResultList();
    }

    /**
     * 監視対象を削除　登録する
     * @param userId
     * @return
     */
    public int[] updateMonitor(Long userId, List<String> videos) {

        // 自分のモニターデータを全削除
        deleteWhere(new SimpleWhere().eq(userId(), userId));

        // 画面入力値で再登録
        List<Monitor> entitys = new ArrayList<>();

        for (String s : videos) {
            if(StringUtil.isNotBlank(s)) {
                Monitor entity = new Monitor();
                entity.userId = userId;
                entity.video = s;
                entitys.add(entity);
            }
        }

        return insertBatch(entitys);
    }
}
