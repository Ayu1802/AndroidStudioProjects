package com.example.ayushib.menuapp.chat.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
/**
 * Created by Amritesh on 16-Jan-18.
 */

public class ImageUtils {

    public static final int AVATAR_WIDTH = 128;
        public static final int AVATAR_HEIGHT = 128;

        /**
         * round the avatar image
         * @param context
         * @param src bitmap images
         * @return RoundedBitmapDrawable is the input to the setImageDrawable () function.
         */
        public static RoundedBitmapDrawable roundedImage(Context context, Bitmap src){
        /*Be round avatar*/
            Resources res = context.getResources();
            RoundedBitmapDrawable dr =
                    RoundedBitmapDrawableFactory.create(res, src);
            dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);

            return dr;
        }

        /**
         *For rectangular images, the image should be cut in square and centered
         * The avatar will not be distorted
         * @param srcBmp
         * @return
         */
        public static Bitmap cropToSquare(Bitmap srcBmp){
            Bitmap dstBmp = null;
            if (srcBmp.getWidth() >= srcBmp.getHeight()){

                dstBmp = Bitmap.createBitmap(
                        srcBmp,
                        srcBmp.getWidth()/2 - srcBmp.getHeight()/2,
                        0,
                        srcBmp.getHeight(),
                        srcBmp.getHeight()
                );

            }else{
                dstBmp = Bitmap.createBitmap(
                        srcBmp,
                        0,
                        srcBmp.getHeight()/2 - srcBmp.getWidth()/2,
                        srcBmp.getWidth(),
                        srcBmp.getWidth()
                );
            }

            return dstBmp;
        }

        /**
         * Convert bitmap images to String base64
         * @param imgBitmap
         * @return
         */
        public static String encodeBase64(Bitmap imgBitmap){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        /**
         * Reduce the number of pixels to avoid Firebase Database OutOfMemory errors
         * @param is user entered
         * @param reqWidth size width after reduction
         * @param reqHeight size height after reduction
         * @return
         */
        public static Bitmap makeImageLite(InputStream is, int width, int height,
                                           int reqWidth, int reqHeight) {
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) >= reqHeight
                        && (halfWidth / inSampleSize) >= reqWidth) {
                    inSampleSize *= 2;
                }
            }

            // Calculate inSampleSize
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = inSampleSize;

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(is, null, options);
        }


        public static InputStream convertBitmapToInputStream(Bitmap bitmap){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
            return bs;
        }

}

