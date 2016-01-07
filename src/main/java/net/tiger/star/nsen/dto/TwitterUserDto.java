package net.tiger.star.nsen.dto;

import twitter4j.PagableResponseList;
import twitter4j.User;

/**
 * フォロー フォロワー情報DTO
 * @author mikosu3
 *
 */
public class TwitterUserDto {

    public PagableResponseList<User> users;

    public Long next;

}
