package com.master.app.tools;


import com.google.gson.Gson;


/**
 * @param
 * @author Litao-pc on 2016/11/30.
 *         ~
 */

public class JsonUtils {


    public static <T> T toObject(String json, Class<T> clazz) {
        Gson g = new Gson();
        T t = g.fromJson(json, clazz);
        return t;
    }
}
