package net.tiger.star.nsen.dto;

import java.io.Serializable;

import net.tiger.star.nsen.entity.NsenUser;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import twitter4j.auth.RequestToken;

/**
 * ユーザー情報 情報クラス
 * ログインしたユーザーの情報を保持する
 *
 * @author mikosu3
 *
 */
@Component(instance = InstanceType.SESSION)
public class UserDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ユーザーデータ */
    public NsenUser nsenUser;

    /** リクエストトークン */
    public RequestToken requestToken;

}
