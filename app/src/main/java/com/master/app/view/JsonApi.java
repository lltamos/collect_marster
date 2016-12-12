package com.master.app.view;

import org.json.JSONObject;

/**
 * Created by vijay on 5/16/15.
 */
public interface JsonApi {
    JSONObject getStep(String stepName);

    void writeValue(String stepName, int key);



    String currentJsonState();

    String getCount();
}
