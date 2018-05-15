package com.nantian.utils;

/**
 * Created by guofeng on 2016/4/14.
 */
    import java.util.UUID;

public class MyTools {
    public static String getNewFilelName(String fileName){
        int beginIndex=fileName.lastIndexOf(".");
        String newFilename=UUID.randomUUID().toString()+fileName.substring(beginIndex,fileName.length());
        return newFilename;
    }
}
