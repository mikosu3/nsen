package net.tiger.star.nsen.form;

import java.io.Serializable;


/**
 * アクションフォーム基底クラス
 * @author mikosu3
 *
 */
public abstract class AbstractBaseForm implements Serializable {
    private static final long serialVersionUID = 1L;
    /** ページ番号 */
    public Integer page = 1;

    /** フォーム情報リセット */
    abstract void resetForm();
}
