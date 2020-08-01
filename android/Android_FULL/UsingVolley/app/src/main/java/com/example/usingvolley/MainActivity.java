package com.example.usingvolley;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener,
Response.ErrorListener{

    public static final String REQUEST_TAG = "MainVolleyActivity";
    private TextView mTextView;
    private Button mButton;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getmRequestQueue();
        String url = "https://dl.dropboxusercontent.com/s/d24im9i7e3tczls/carros.json?dl=0";
        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET, url,
                new JSONObject(),this,this);
        jsonRequest.setTag(REQUEST_TAG);

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mQueue.add(jsonRequest);
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(mQueue != null){
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error){
        mTextView.setText(error.getMessage());
    }

    @Override
    public void onResponse(Object response){
        try{
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray cars = jsonObject.getJSONArray("carros");
            JSONObject carObject = cars.getJSONObject(0);
            String c1 = carObject.getString("modelo") +
                    "\n" + carObject.getString("ano") +
                    "\n" + carObject.getString("cor") +
                    "\n" + carObject.getString("preco");
            JSONObject carObject2 = cars.getJSONObject(1);
            String c2 = carObject2.getString("modelo") +
                    "\n" + carObject2.getString("ano") +
                    "\n" + carObject2.getString("cor") +
                    "\n" + carObject2.getString("preco");
            mTextView.setText(c1 + "\n\n" + c2);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
