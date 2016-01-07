package net.tiger.star.nsen.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * 共通ユーティリティクラス
 *
 * @author mikosu3
 *
 */
public class CommonUtil {

    /**
     * Integerの0 1でフラグを返す
     *
     * @return 0:false 1:true
     */
    public static Integer getIntegerFlag(boolean bool) {
        if (bool) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 5刻みで0-100までの数値を取得する
     * @return
     */
    public static List<Integer> getPercentageBy5() {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i <= 100; i = i + 5) {
            ret.add(i);
        }
        return ret;
    }

    /**
     * 文字列に変換を行う nullの場合はホワイトスペースを返す
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * int型配列の合計値を取得する
     * @return
     */
    public static int getIntArrayValue(int[] input) {
        int ret = 0;

        for(int i : input) {
            ret += i;
        }

        return ret;
    }

    /**
     * yyyyMMddの誕生日から現在の年齢を取得する
     * @param birthDay
     * @return
     */
    public static int getAge(String birthDay) {
        return (Integer.parseInt(DateUtil.getNowDate()) - Integer.parseInt(birthDay))/10000;
    }

    /**
     * ログファイル名をフルパスで取得する
     * @return
     */
    public static String getLogFileName() {
        String prop = "log4j.appender.file.File";
        if(ResourceBundle.getBundle("log4j").containsKey(prop)) {
            return ResourceBundle.getBundle("log4j").getString(prop);
        } else {
            return "";
        }
    }

    /**
     * アプリのURLルートを取得する
     * @return
     */
    public static String getRootUrl() {
        if(ResourceBundle.getBundle("page_conf").containsKey("url.root")) {
            return ResourceBundle.getBundle("page_conf").getString("url.root");
        } else {
            return "";
        }
    }

    /**
     * ランダムの文字列をSha256で暗号して返す
     * @return
     */
    public static String getRandomSha256() {
        return DigestUtils.sha256Hex(String.valueOf(System.currentTimeMillis() * System.currentTimeMillis() * (int)(Math.random()*10) + (int)(Math.random()*10)));
    }

}
