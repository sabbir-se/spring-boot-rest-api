package com.ikubinfo.assignment.util;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by sabbir on 9/28/21.
 */
public class Utils {

    public static boolean isNullOrEmpty(String s){
        return s == null || s.isEmpty();
    }

    public static boolean isNullOrEmpty(List list){
        return list == null || list.size() == 0;
    }

    public static String messageResponse(String message) {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty(Constant.MESSAGE, message);
        return responseObj.toString();
    }
}
