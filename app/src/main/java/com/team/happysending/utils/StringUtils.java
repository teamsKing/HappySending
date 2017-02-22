package com.team.happysending.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class StringUtils {
    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isEmpty(CharSequence str) {

        return (str == null || str.length() == 0);
    }


    /**
     * encoded in utf-8
     *
     * @param str 字符串
     * @return 返回一个utf8的字符串
     */
    public static String utf8Encode(String str) {

        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }


    /**
     * 把给定的字符串用给定的字符分割
     *
     * @param string 给定的字符串
     * @param ch 给定的字符
     * @return 分割后的字符串数组
     */
    public static String[] split(String string, char ch) {
        ArrayList<String> stringList = new ArrayList<String>();
        char chars[] = string.toCharArray();
        int nextStart = 0;
        for (int w = 0; w < chars.length; w++) {
            if (ch == chars[w]) {
                stringList.add(new String(chars, nextStart, w - nextStart));
                nextStart = w + 1;
                if (nextStart ==
                        chars.length) {    //当最后一位是分割符的话，就再添加一个空的字符串到分割数组中去
                    stringList.add("");
                }
            }
        }
        if (nextStart <
                chars.length) {    //如果最后一位不是分隔符的话，就将最后一个分割符到最后一个字符中间的左右字符串作为一个字符串添加到分割数组中去
            stringList.add(new String(chars, nextStart,
                    chars.length - 1 - nextStart + 1));
        }
        return stringList.toArray(new String[stringList.size()]);
    }


    private static char[] getChars(char[] chars, int startIndex) {
        int endIndex = startIndex + 1;
        //如果第一个是数字
        if (Character.isDigit(chars[startIndex])) {
            //如果下一个是数字
            while (endIndex < chars.length &&
                    Character.isDigit(chars[endIndex])) {
                endIndex++;
            }
        }
        char[] resultChars = new char[endIndex - startIndex];
        System.arraycopy(chars, startIndex, resultChars, 0, resultChars.length);
        return resultChars;
    }



    /**
     * 是否全是数字
     */
    public static boolean isAllDigital(char[] chars) {
        boolean result = true;
        for (int w = 0; w < chars.length; w++) {
            if (!Character.isDigit(chars[w])) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 删除给定字符串中所有的旧的字符
     *
     * @param string 源字符串
     * @param ch 要删除的字符
     * @return 删除后的字符串
     */
    public static String removeChar(String string, char ch) {
        StringBuffer sb = new StringBuffer();
        for (char cha : string.toCharArray()) {
            if (cha != '-') {
                sb.append(cha);
            }
        }
        return sb.toString();
    }


    /**
     * 删除给定字符串中给定位置处的字符
     *
     * @param string 给定字符串
     * @param index 给定位置
     */
    public static String removeChar(String string, int index) {
        String result = null;
        char[] chars = string.toCharArray();
        if (index == 0) {
            result = new String(chars, 1, chars.length - 1);
        }
        else if (index == chars.length - 1) {
            result = new String(chars, 0, chars.length - 1);
        }
        else {
            result = new String(chars, 0, index) +
                    new String(chars, index + 1, chars.length - index);
            ;
        }
        return result;
    }

    /**
     * 将给定字符串中给定的区域的字符转换成小写
     *
     * @param str 给定字符串中
     * @param beginIndex 开始索引（包括）
     * @param endIndex 结束索引（不包括）
     * @return 新的字符串
     */
    public static String toLowerCase(String str, int beginIndex, int endIndex) {
        return str.replaceFirst(str.substring(beginIndex, endIndex),
                str.substring(beginIndex, endIndex)
                        .toLowerCase(Locale.getDefault()));
    }


    /**
     * 将给定字符串中给定的区域的字符转换成大写
     *
     * @param str 给定字符串中
     * @param beginIndex 开始索引（包括）
     * @param endIndex 结束索引（不包括）
     * @return 新的字符串
     */
    public static String toUpperCase(String str, int beginIndex, int endIndex) {
        return str.replaceFirst(str.substring(beginIndex, endIndex),
                str.substring(beginIndex, endIndex)
                        .toUpperCase(Locale.getDefault()));
    }


    /**
     * 将给定字符串的首字母转为小写
     *
     * @param str 给定字符串
     * @return 新的字符串
     */
    public static String firstLetterToLowerCase(String str) {
        return toLowerCase(str, 0, 1);
    }


    /**
     * 将给定字符串的首字母转为大写
     *
     * @param str 给定字符串
     * @return 新的字符串
     */
    public static String firstLetterToUpperCase(String str) {
        return toUpperCase(str, 0, 1);
    }


    /**
     * 将给定的字符串MD5加密
     *
     * @param string 给定的字符串
     * @return MD5加密后生成的字符串
     */
    public static String MD5(String string) {
        String result = null;
        try {
            char[] charArray = string.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }

            StringBuffer hexValue = new StringBuffer();
            byte[] md5Bytes = MessageDigest.getInstance("MD5")
                    .digest(byteArray);
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }

            result = hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 判断给定的字符串是否以一个特定的字符串开头，忽略大小写
     *
     * @param sourceString 给定的字符串
     * @param newString 一个特定的字符串
     */
    public static boolean startsWithIgnoreCase(String sourceString, String newString) {
        int newLength = newString.length();
        int sourceLength = sourceString.length();
        if (newLength == sourceLength) {
            return newString.equalsIgnoreCase(sourceString);
        }
        else if (newLength < sourceLength) {
            char[] newChars = new char[newLength];
            sourceString.getChars(0, newLength, newChars, 0);
            return newString.equalsIgnoreCase(String.valueOf(newChars));
        }
        else {
            return false;
        }
    }

}
