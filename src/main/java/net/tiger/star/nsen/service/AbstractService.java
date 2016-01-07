package net.tiger.star.nsen.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.annotation.Resource;

import net.tiger.star.nsen.dto.UserDataDto;
import net.tiger.star.nsen.entity.AbstractBaseEntity;

import org.seasar.extension.jdbc.Where;

/**
 * サービスの抽象クラスです。
 *
 * @param <ENTITY>
 *            エンティティの型
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.AbstServiceModelFactoryImpl"}, date = "2013/04/05 16:56:14")
public abstract class AbstractService<ENTITY extends AbstractBaseEntity> extends AbstractBaseService<ENTITY> {

    @Resource
    protected UserDataDto userDataDto;

    /**
    *
    */
   @Override
   protected void setEntityClass(Class<ENTITY> entityClass) {
         this.entityClass = entityClass;
         sqlFilePathPrefix = "";
   }
    /**
     * エンティティを挿入します。
     *
     * @param entity
     *            エンティティ
     * @return 更新した行数
     */
    @Override
    public int insert(ENTITY entity) {
        setRegistFootterData(entity);
        return super.insert(entity);
    }

    /**
     * エンティティを更新します。
     *
     * @param entity
     *            エンティティ
     * @return 更新した行数
     */
    @Override
    public int update(ENTITY entity) {
        setUpdateFootterData(entity);
        return jdbcManager.update(entity).execute();
    }

    /**
     * フッター情報をセットしてNull項目以外を更新する
     * @return
     */
    public int updateExcludesNull(ENTITY entity) {
        setUpdateFootterData(entity);
        return jdbcManager
                .update(entity)
                .excludesNull()
                .execute();
    }

    /**
     * エンティティのリストを一括で登録する
     * @param list エンティティリスト
     * @return 登録した行数
     */
    public int[] insertBatch(List<ENTITY>list) {
        if (list == null || list.isEmpty()) {
            return new int[0];
        } else {
            return jdbcManager.insertBatch(getRegistFootterDataList(list)).execute();
        }
    }

    /**
     * エンティティのリストを一括で登録する
     * フッター情報はつけない
     * @param list エンティティリスト
     * @return 登録した行数
     */
    public int[] insertBatchNonFooter(List<ENTITY>list) {
        if (list == null || list.isEmpty()) {
            return new int[0];
        } else {
            return jdbcManager.insertBatch(list).execute();
        }
    }

    /**
     * エンティティのリストを一括で更新する
     * @param list エンティティリスト
     * @return 更新した行数
     */
    public int[] updateBatch(List<ENTITY>list) {
        if (list == null || list.isEmpty()) {
            return new int[0];
        } else {
            return jdbcManager.updateBatch(getUpdateFootterDataList(list)).execute();
        }
    }

    /**
     * エンティティのリストを一括で更新する
     * @param list エンティティリスト
     * @return 更新した行数
     */
    public int[] updateExcludesNullBatch(List<ENTITY>list) {
        if (list == null || list.isEmpty()) {
            return new int[0];
        } else {
            int[]ret = new int[list.size()];

            for(int i = 0; i < ret.length ; i++) {
                ret[i] = updateExcludesNull(list.get(i));
            }
            return ret;
        }
    }

    /**
     * テーブル共通のフッター情報をセットしたエンティティリストを取得する
     * 更新日時 更新者ID
     * @return エンティティのリスト
     */
    protected List<ENTITY> getUpdateFootterDataList(List<ENTITY>list) {
        List<ENTITY> ret = new ArrayList<ENTITY>();
        for(ENTITY row : list) {
            setUpdateFootterData(row);
            ret.add(row);
        }
        return ret;
    }

    /**
     * テーブル共通のフッター情報をセットしたエンティティリストを取得する
     * 登録日時 登録者ID
     * @return エンティティのリスト
     */
    protected List<ENTITY> getRegistFootterDataList(List<ENTITY>list) {
        List<ENTITY> ret = new ArrayList<ENTITY>();
        for(ENTITY row : list) {
            setRegistFootterData(row);
            ret.add(row);
        }
        return ret;
    }

    /**
     * Where区を指定して削除を行う
     * @param simpleWhere
     * @return 削除件数
     */
    protected int deleteWhere(final Where simpleWhere) {
        int ret = 0;
        // 検索を実行して削除
        for (ENTITY t : select().where(simpleWhere).getResultList()) {
            delete(t);
            ret++;
        }
        return ret;
    }

    @Override
    public String getUserName() {
        try {
            return userDataDto.nsenUser.userId.toString();
        } catch(Exception e) {
            return "";
        }
    }
}