package com.example.oscarapp.utils;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CallWeb extends AsyncTask<String, String, Drawable> {


    @Override
    protected Drawable doInBackground(String... strings) {
        String url = strings[0];

        Drawable src = null;
        try {
            src = Drawable.createFromStream((InputStream) new URL(url).getContent(), "src");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return src;

    }
}
