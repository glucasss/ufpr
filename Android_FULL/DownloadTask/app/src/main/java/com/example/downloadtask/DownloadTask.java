package com.example.downloadtask;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;

public class DownloadTask extends AsyncTask<String, Void, Bitmap> {
    private Context ctx;
    private ImageView image;
    private ProgressDialog progressDialog;

    public DownloadTask(Context ctx, ImageView image){
        this.ctx = ctx;
        this.image = image;
    }



    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(ctx, "Por favor aguarde...", "Baixando imagem...");
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap imagemBitmap = null;
        try{
            imagemBitmap = Auxiliar.baixarImagem(params[0]);
        }catch (IOException e){
            e.printStackTrace();
        }
        return imagemBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        image.setImageBitmap(bitmap);
        progressDialog.dismiss();
    }

}
