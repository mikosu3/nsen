package net.tiger.star.nsen.util;

import java.math.BigDecimal;

import org.seasar.framework.util.StringUtil;


/**
 * Jspで使用する静的メソッドを記載します。
 *
 * @author mikosu3
 *
 */
public class JspTagUtil {

    /**
     * 現在ページのURIとリンク先が同じ場合class="active"を返す
     * 同じではない場合""を返す
     * ※スタイルシートのクラス名を想定
     *
     * @return
     */
    public static String getTabLink(String pageUri, String target) {

        if (StringUtil.equals(pageUri, target)) {
            return "class=\"active\"";
        } else {
            return "off2";
        }
    }

    /**
     * 文字列を結合して返す
     *
     * @return
     */
    public static String concat(String target1, String target2) {
        return target1 + target2;
    }

    /**
     * 小数点を切り捨てて返す
     *
     * @param input
     * @return
     */
    public static String floor(String input) {
        Double d = Double.parseDouble(input);
        return String.valueOf(Math.floor(d));
    }

    /**
     * 小数点2ケタまでのパーセンテージを取得する
     * @param s1 分子
     * @param s2 分母
     * @return パーセンテージ
     */
    public static String getPercentage(String s1, String s2) {

        if (StringUtil.isBlank(s1 + s2) || "0".equals(s1) || "0".equals(s2)) {
            return "0";
        }

        BigDecimal bi = new BigDecimal(String.valueOf(Double.parseDouble(s1) / Double.parseDouble(s2)));
        return String.valueOf(bi.setScale(2, BigDecimal.ROUND_DOWN).doubleValue() * 100);
    }

    /**
     * 現在年月日から年齢を取得する
     *
     * @param input
     * @return
     */
    public static String getAge(String input) {
        return String.valueOf(CommonUtil.getAge(input));
    }

    /**
     * アクション名からリンク先を取得する
     * @param input
     * @return
     */
    public static String getActionLink(String input) {
        return StringUtil.decapitalize(StringUtil.trimSuffix(input, "Action"));
    }

    /**
     * ページャーを取得する
     * @return
     */
    public static String getPager(String page, String total, String limit, String maxPages) {
        String ret = "総件数:" + total +"<br/>" ;

        // 前ページがあるか
        if(Integer.parseInt(page) - 1 > 0) {
            ret += "<li><a href=\"./" + (Integer.parseInt(page) - 1) + "\"> &laquo; </a></li>";
        }

        // 総ページ数
        int pageNum = (int) Math.ceil(Double.parseDouble(total) / Double.parseDouble(limit));

        // ページャーの最大件数から開始ページを設定
        int from = getFromPage(pageNum, Integer.parseInt(maxPages), Integer.parseInt(page));
        int to = getToPage(pageNum, Integer.parseInt(maxPages), Integer.parseInt(page));

        // 総件数からページ数割り出し
        for(int i = from; i <= to; i++) {
            if (!String.valueOf(i).equals(page)) {
                ret += "<li><a href=\"./"+ i + "\">" + i + "</a></li>";
            } else {
                ret += "<li><a href=\"./"+ i + "\" class=\"active\">" + i + "</a></li>";
            }
        }

        // 次ページがあるか
        if(Integer.parseInt(page) + 1 <= pageNum) {
            ret += "<li><a href=\"./" + (Integer.parseInt(page) + 1) + "\"> &raquo; </a></li>";
        }


        return "<div class=\"pagination pagination-right\"><ul>" + ret + "</ul></div>";
    }

    /**
     * ページャーの最大ページ番号を取得する
     * @param pageNum 総ページ数
     * @param maxPages 最大ページ数
     * @param nowPage 現在ページ数
     * @return
     */
    private static int getToPage(int pageNum, int maxPages, int nowPage) {
        int ret = pageNum;

        // 最大ページ数を超えているか
        if (pageNum > maxPages) {

            // 現在ページが最大ページ数の半分を超えているか
            if (nowPage > (maxPages/2)) {

                // 現在ページ + 最大ページ数の半分を加算して総ページ数を超えないか
                if (nowPage + (maxPages / 2) > pageNum) {
                    ret = pageNum;
                } else {
                    ret = nowPage + (maxPages / 2);
                }
            } else {
                ret = maxPages;
            }
        }

        return ret;
    }

    /**
     * ページャーの開始ページ番号を取得する
     * @param pageNum 総ページ数
     * @param maxPages 最大ページ数
     * @param nowPage 現在ページ数
     * @return
     */
    private static int getFromPage(int pageNum, int maxPages, int nowPage) {
        int ret = 1;
        int max = (int) Math.ceil(Double.valueOf(maxPages) / 2);

        // 最大ページ数を超えているか
        if (pageNum > maxPages) {

            // 現在ページが最大ページ数の半分を超えているか
            if (nowPage > max) {
                ret = nowPage - max + 1;
            } else {
                ret = 1;
            }
        }

        return ret;
    }

}
