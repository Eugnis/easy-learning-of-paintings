package com.eugnis.easylearningofpaintings.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class ImageGridHandler extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private Context context;

    public ImageGridHandler(Context context, ImageView img){
        imageViewReference = new WeakReference<ImageView>(img);
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        //return PixzelleUtilities.getThumbnail(this.context, BitmapFactory.decodeFile(params[0]) ,5);
        return ImageHelper.decodeSampledBitmapFromAssets(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]));
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        final ImageView imageView = imageViewReference.get();
        imageView.setImageBitmap(result);
    }
}
