package net.tiger.star.nsen.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日付を扱うユーティリティクラス
 *
 * @author mikosu3
 *
 */
public class DateUtil {

    /**
     * yyyyMMddの形式で現在年月日を取得する
     *
     * @return 年月日
     */
    public static  String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * HH:mmの形式で現在時刻を取得する
     *
     * @return 時分秒
     */
    public static  String getNowHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }

    /**
     * HH:mm:ssの形式で現在時刻を取得する
     *
     * @return 時分秒
     */
    public static  String getNowHourSecond() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * yyyy-MM-dd HH:mm:ssの形式で現在年月日を取得する
     *
     * @return 年月日
     */
    public static  String getNowDateHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * yyyyMMddHHmmssの形式で現在年月日を取得する
     *
     * @return 年月日
     */
    public static  String getNowDateHourNoSeparate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * yyyy-MM-dd HH:mm:ssの文字列を日付に変換する
     * @param s
     * @return
     */
    public static Date parseDateHour(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ssの形式で日付を文字列へ変換する
     *
     * @return 年月日
     */
    public static  String formatDateHour(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * yyyy/MM/dd HH:mm:ssの形式で日付を文字列へ変換する
     *
     * @return 年月日
     */
    public static  String formatDateHour(Timestamp date) {
        if(date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }
}
