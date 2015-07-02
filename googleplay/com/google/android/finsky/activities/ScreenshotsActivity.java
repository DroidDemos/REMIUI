package com.google.android.finsky.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.ScreenshotView;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.play.image.BitmapLoader;
import java.util.List;

public class ScreenshotsActivity extends Activity {
    private Document mDocument;
    private ViewPager mPager;

    private static final class ImagePagerAdapter extends PagerAdapter {
        private final BitmapLoader mBitmapLoader;
        private final List<Image> mImages;
        private final LayoutInflater mInflater;

        public ImagePagerAdapter(List<Image> images, Context context, BitmapLoader bitmapLoader) {
            this.mImages = images;
            this.mInflater = LayoutInflater.from(context);
            this.mBitmapLoader = bitmapLoader;
        }

        public int getCount() {
            return this.mImages.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            ScreenshotView view = (ScreenshotView) this.mInflater.inflate(R.layout.screenshot, container, false);
            view.setImage((Image) this.mImages.get(position), this.mBitmapLoader);
            container.addView(view);
            return view;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ScreenshotView) object);
        }
    }

    public static void show(Context context, Document document, int index, int imageType) {
        Intent intent = new Intent(context, ScreenshotsActivity.class);
        intent.putExtra("document", document);
        intent.putExtra("index", index);
        intent.putExtra("imageType", imageType);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenshots_frame);
        this.mDocument = (Document) getIntent().getParcelableExtra("document");
        this.mPager = (ViewPager) findViewById(R.id.pager);
        this.mPager.setAdapter(new ImagePagerAdapter(this.mDocument.getImages(getIntent().getIntExtra("imageType", 1)), this, FinskyApp.get().getBitmapLoader()));
        if (savedInstanceState == null) {
            this.mPager.setCurrentItem(getIntent().getIntExtra("index", 0));
        }
        if (ActivityManager.isUserAMonkey()) {
            finish();
        }
    }
}
