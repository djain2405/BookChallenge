package com.divya.readthemall;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by divya on 2/26/2018.
 */

public class DownloadImageTask extends AsyncTask <String, Void, Bitmap> {

    ImageView bgImage;

    public DownloadImageTask(ImageView imageView)
    {
        bgImage = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap img = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            img = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return img;
    }

    protected void onPostExecute(Bitmap result) {
        bgImage.setImageBitmap(result);
    }

}

