package com.ranapromo.nara.ranapromo3.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ranapromo.nara.ranapromo3.R;
import com.ranapromo.nara.ranapromo3.comman.Util;

/**
 * Adapter for the viewPager in the first tab
 */


public class HomeViewPagerAdapter extends PagerAdapter {

    int[] mResources = {
            R.drawable.ad_banner,
            R.drawable.cheese_4,
            R.drawable.ad_banner

    };

    Context mContext;
    LayoutInflater mLayoutInflater;


    public HomeViewPagerAdapter(Context context) {

        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (LinearLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //imageView.setBackgroundResource(mResources[position]);
        position = position+1;
        String dest = mContext.getCacheDir() + "/" + "BAN_" +position+".jpg";
        new MyImageLoader().execute(new ParamHolder(imageView,position,dest));

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    class ParamHolder{
        public ParamHolder(ImageView v,int i,String desfile){
            theView =v;
            image = i;
            this.desfile = desfile;
        }
        ImageView theView;
        int image;
        String desfile;
    }

    class MyImageLoader extends AsyncTask<ParamHolder, Void, Bitmap> {

        ParamHolder container;
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Bitmap doInBackground(ParamHolder... params) {
            container = params[0];
            Bitmap btm = null;
            String url = mContext.getString(R.string.server_base_url)+"BAN_" + container.image + ".jpg";



            // if(Util.isMustDownload(container.desfile)){
            //     Log.d(Util.DEBUG_VAL, "Le fichier existe lecture du cache");
            //    btm = BitmapFactory.decodeFile(container.desfile);
            //} else
            //{
                Log.d(Util.DEBUG_VAL, "le fichier n'existe pas téléchargment depuis le serveur " + container.image);
                btm = Util.getBitmapFromURL(url, container.desfile);
            //}
            return btm;
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null){
                //imageView.setBackgroundResource(mResources[position]);
                container.theView.setImageBitmap(result);
            }
        }
    }



}
