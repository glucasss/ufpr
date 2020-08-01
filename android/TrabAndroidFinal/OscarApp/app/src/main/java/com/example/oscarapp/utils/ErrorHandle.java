package com.example.oscarapp.utils;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ErrorHandle {

    public static final String parseVolleyError(VolleyError error){
        String msg = new String();

        try{
            String responseBody = new String(error.networkResponse.data, "utf-8");
            JSONObject data = new JSONObject(responseBody);
            msg = data.getString("message");
        } catch(JSONException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }

        return msg;
    }
}
