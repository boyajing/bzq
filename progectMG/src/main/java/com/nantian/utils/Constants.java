package com.nantian.utils;

import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Constants {
    public static final String DISPOSE_ZHLX ="01";
    public static final String RECOVERY_ZHLX ="02";
    public static final int ONEDISPOSE =1;
    public static final int PACKAGEDISPOSE=2;
    public static final int NODISPOSE =0;

    public static <T extends Object> T getSessionObject(Class<T> type, HttpSession session, String key, boolean createNew) {
        Object obj = session.getAttribute(key);
        if (obj == null) {
            if (createNew) {
                try {
                    obj = type.newInstance();
                    session.setAttribute(key, obj);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return (T) obj;
    }

    /**
     * 用法：String temp = "HT0000";Constants.swiftNumber(temp, 2);
     *
     * @param maxNum 查询的流水号
     * @param index  流水号的开始索引
     * @return
     */
    public static String swiftNumber(String maxNum, Integer index) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Integer length = maxNum.length();
        Integer lastCharIndex = lastCharIndex(maxNum, index);
        /*检测maxNum中序列的第一个字符是否为字母*/
        if (fristCharIndex(maxNum, index) != -1) {
            /*取出数字部分进行计算*/
            String temp = maxNum.substring(lastCharIndex + 1, length);
            String tag = getNum(temp, 0);
            if (alphabet.indexOf(tag) > -1) {
                return maxNum.substring(0, index) + procCore(maxNum.substring(index, length - 1) + tag);
            } else {
                return maxNum.substring(0, lastCharIndex + 1) + getNum(temp, 0);
            }
        } else {
            return getNum(maxNum, index);
        }
    }

    private static String getNum(String maxNum, Integer index) {
        Integer length = maxNum.length();
        /*在序列中找出首个数字的index*/
        Integer fristDigitIndex = fristDigitIndex(maxNum, index);
        /*在序列中找出首个零的index*/
        Integer fristZeroIndex = fristZeroIndex(maxNum, index);
        /*在序列中找出首个非零数字的index*/
        Integer fristNoZeroIndex = fristNoZeroIndex(maxNum, index);

        if (fristNoZeroIndex != -1) {
            /*在序列中以数字开头的情况*/
            if (fristDigitIndex == fristNoZeroIndex) {
                String older = maxNum.substring(fristNoZeroIndex, length);
                String newer = String.valueOf(Integer.valueOf(older) + 1);
                if (older.length() == newer.length()) {
                    return maxNum.substring(0, fristNoZeroIndex) + newer;
                } else {
                    return maxNum.substring(0, fristNoZeroIndex) + "a" + newer.substring(2, newer.length());
                }
            }
            /*在序列中以零开头的情况*/
            else {
                String older = maxNum.substring(fristNoZeroIndex, length);
                String newer = String.valueOf(Integer.valueOf(older) + 1);
                if (newer.length() == older.length()) {
                    return maxNum.substring(0, fristNoZeroIndex) + newer;
                } else {
                    String temp = alternate(length - index - newer.length());
                    return maxNum.substring(0, fristDigitIndex) + temp + newer;
                }
            }
        } else {
            return maxNum.substring(0, length - 1) + "1";
        }
    }

    /*纯字母序列的进位处理*/
    private static String procCore(String letter) {
        Integer length = letter.length();
        /*末位的tag*/
        String tag = letter.substring(length - 1, length);
        /*tag每一次出现的位置*/
        Integer frist = letter.indexOf(tag);
        /*序列全是tag字母*/
        if (frist == 0) {
            return prevchar(tag) + alternate(length - frist - 1);
        }
        /*以tag结尾的任意纯字母序列*/
        else {
            return letter(letter);
        }
    }

    /*以tag结尾的任意纯字母序列*/
    private static String letter(String letter) {
        String temp = "";
        Integer length = letter.length();
        for (int i = length - 2; i < length; i--) {
            if (i == 0) {
                temp = prevchar(letter.substring(0, 1)) + alternate(length - 1);
                break;
            }
            String currchar = letter.substring(i, i + 1);
            String prevchar = letter.substring(i - 1, i);
            /*序列中当前字母和前一个字母不相等时进位*/
            if (!prevchar.equals(currchar)) {
                if (prevchar.equals(prevchar(currchar))) {
                    temp = letter.substring(0, i) + prevchar + alternate(length - i - 1);
                } else {
                    temp = letter.substring(0, i) + prevchar(currchar) + alternate(length - i - 1);
                }
                break;
            }
        }
        return temp;
    }

    private static String alternate(Integer length) {
        String temp = "";
        for (int i = 0; i < length; i++) {
            temp = temp + "0";
        }
        return temp;
    }

    private static Integer fristZeroIndex(String maxNum, Integer index) {
        String arabnumbers = "0123456789";
        Integer length = maxNum.length();
        Integer fristZeroIndex = -1;
        for (int i = index; i < length; i++) {
            if (arabnumbers.indexOf(maxNum.substring(i, i + 1)) == 0) {
                fristZeroIndex = i;
                break;
            }
        }
        return fristZeroIndex;
    }

    private static Integer fristNoZeroIndex(String maxNum, Integer index) {
        String arabnumbers = "0123456789";
        Integer length = maxNum.length();
        Integer fristNoZeroIndex = -1;
        Integer charIndex;
        for (int i = index; i < length; i++) {
            charIndex = arabnumbers.indexOf(maxNum.substring(i, i + 1));
            if (charIndex != 0 && charIndex != -1) {
                fristNoZeroIndex = i;
                break;
            }
        }
        return fristNoZeroIndex;
    }

    private static Integer fristDigitIndex(String maxNum, Integer index) {
        String arabnumbers = "0123456789";
        Integer length = maxNum.length();
        Integer fristDigitIndex = -1;
        for (int i = index; i < length; i++) {
            if (arabnumbers.indexOf(maxNum.substring(i, i + 1)) > -1) {
                fristDigitIndex = i;
                break;
            }
        }
        return fristDigitIndex;
    }

    private static Integer fristCharIndex(String maxNum, Integer index) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Integer length = maxNum.length();
        Integer fristCharIndex = -1;
        for (int i = index; i < length; i++) {
            if (alphabet.indexOf(maxNum.substring(i, i + 1)) > -1) {
                fristCharIndex = i;
                break;
            }
        }
        return fristCharIndex;
    }

    private static Integer lastCharIndex(String maxNum, Integer index) {
        return fristCharIndex(maxNum, index) == -1 ? -1 : fristDigitIndex(maxNum, index) - 1;
    }

    private static String prevchar(String letter) {
        int currchar = letter.charAt(0);
        int prevchar;
        if (letter.equals("A")) {
            prevchar = currchar + 1;
            return String.valueOf((char) prevchar);
        } else if (letter.equals("Z")) {
            prevchar = 124;
            return String.valueOf((char) prevchar);
        } else if (letter.equals("a")) {
            prevchar = currchar + 1;
            return String.valueOf((char) prevchar);
        } else if (letter.equals("z")) {
            prevchar = currchar - 57;
            return String.valueOf((char) prevchar);
        } else {
            prevchar = currchar + 1;
            return String.valueOf((char) prevchar);
        }
    }

    private static String followchar(String letter) {
        int currchar = letter.charAt(0);
        int prevchar;
        if (letter.equals("A")) {
            prevchar = 124;
            return String.valueOf((char) prevchar);
        } else if (letter.equals("Z")) {
            prevchar = currchar - 1;
            return String.valueOf((char) prevchar);
        } else if (letter.equals("a")) {
            prevchar = currchar - 57;
            return String.valueOf((char) prevchar);
        } else if (letter.equals("z")) {
            prevchar = currchar - 1;
            return String.valueOf((char) prevchar);
        } else {
            prevchar = currchar - 1;
            return String.valueOf((char) prevchar);
        }
    }

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }

}
