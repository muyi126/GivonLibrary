package com.base.givon.librarymaster.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 * <p/>
 * Copyright 2015 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @十月 15/10/4 下午3:31 - Guzhu
 * @email:muyi126@163.com
 */

public class StringUtil {

    /**
     * 是否是英文字
     *
     * @param c
     * @return
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 获取英文字长度
     *
     * @param s
     * @return
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        int length = c.length;
        for (int i = 0; i < length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    /**
     * 去掉换行符
     *
     * @param str
     * @return
     */
    public static String removeLineChar(String str) {
        if (str == null)
            return "";
        return str.replaceAll("\r\n", "").replaceAll("\n", "");
    }

    /**
     * 以中文字长度计算，截取字符串
     *
     * @param origin
     * @param len
     * @param c
     * @return
     */
    public static String substring(String origin, int len, String c) {
        if (origin == null || origin.equals("") || len < 1)
            return "";
        String temp = removeLineChar(origin);
        byte[] strByte = new byte[len];
        if (len > length(origin)) {
            return temp;
        }
        try {
            System.arraycopy(temp.getBytes("GBK"), 0, strByte, 0, len);
            int count = 0;
            for (int i = 0; i < len; i++) {
                int value = (int) strByte[i];
                if (value < 0) {
                    count++;
                }
            }
            if (count % 2 != 0) {
                len = (len == 1) ? ++len : --len;
            }
            return new String(strByte, 0, len, "GBK") + c;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static int StrToIntDef(String s, int defaultValue) {
        int res = defaultValue;
        try {
            res = Integer.parseInt(s, 10);
        } catch (Exception e) {
            res = defaultValue;
        }
        return res;
    }

    /**
     * 将数据集合转化拼成字符串
     *
     * @param collection 集合
     * @param delimiter  分隔符
     * @return
     */
    public static String join(Collection<?> collection, String delimiter) {
        StringBuilder builder = new StringBuilder();
        Iterator<?> iter = collection.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            if (iter.hasNext()) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }

    /**
     * 检查是否是正确的email
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 检查是否是数字
     *
     * @param phone
     * @return
     */
    public static boolean checkNum(String phone) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher match = pattern.matcher(phone);
        return match.matches();
    }

    /**
     * 检查是否是电话号码
     */
    public static boolean isMobileNo(String paramString) {
        return Pattern.compile("^1[3|4|5|8]\\d{9}$").matcher(paramString).matches();
    }

    /**
     * 检查用户输入账号格式是否正确,只能是邮箱或电话号码
     *
     * @param account
     * @return
     */
    public static boolean checkAccount(String account) {
        return (isMobileNo(account) || checkEmail(account));
    }

    // 校验Tag Alias 只能是数字,英文字母和中文
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_-]{0,}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static final String KEY_APP_KEY = "JPUSH_APPKEY";

    /**
     * 获取jpush AppKey
     *
     * @param context
     * @return
     */
    public static String getAppKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai)
                metaData = ai.metaData;
            if (null != metaData) {
                appKey = metaData.getString(KEY_APP_KEY);
                if ((null == appKey) || appKey.length() != 24) {
                    appKey = null;
                }
            }
        } catch (NameNotFoundException e) {

        }
        return appKey;
    }

    /**
     * 判断给定字符串是否空白串。
     * 空白串是指由空格、制表符、回车符、换行符组成的字符串
     * 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 将输入流转化为String
     *
     * @param is
     * @return
     */
    public static String readDataFromStream(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    /**
     * 补零操作
     * 如：01
     */
    public static String zerofill(Object obj) {
        String parten = "00";
        if (obj instanceof String)
            obj = Double.parseDouble(obj.toString());
        DecimalFormat decimal = new DecimalFormat(parten);
        return decimal.format(obj);
    }

    /**
     * 金币格式化
     *
     * @param str 金币字符串
     * @return 如:0.01
     */
    public static String formatMoney(String str) {
        try {
            Double value = Double.parseDouble(str);
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(value);
        } catch (Exception e) {
            return "0.00";
        }
    }

    /**
     * 正则HTML字符串中的img url 地址
     *
     * @param htmlStr HTML字符串内容
     * @return
     */
    public static ArrayList<String> getImageUrl(String htmlStr) {
        ArrayList<String> listImgUrl = new ArrayList<String>();
        Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(htmlStr);
        while (m.find()) {
            listImgUrl.add(m.group(1));
        }
        return listImgUrl;
    }

    /***
     * 去除HTML标签内容
     *
     * @param source
     * @return
     */
    public static String getHtmlString(String source) {
        Pattern p_html = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(source);
        return m_html.replaceAll(""); // 过滤html标签
    }

    public static String getDefultString(CharSequence text) {
        if (isEmpty(text)) {
            return "";
        } else {
            return text.toString().trim();
        }
    }

    public static String getDefultString(CharSequence text,CharSequence subText) {
        if (isEmpty(text)) {
            return "";
        } else {
            return text.toString().trim()+subText;
        }
    }
}
