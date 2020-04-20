package com.luo.videoplayer.controller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.luo.videoplayer.R;
import com.luo.videoplayer.base.BaseCoverController;
import com.luo.videoplayer.utils.Logger;


/**
 * title：DefaultCoverController
 * description：默认封面（Cover）控制器
 * author：luojiongtian on 2020/4/17 10:33
 */
public class DefaultCoverController extends BaseCoverController {

    private static final String TAG = "DefaultCoverController";
    public ImageView mVideoCover;
    public TextView mPreCount;
    public TextView mPreDurtion;

    public DefaultCoverController(@NonNull Context context) {
        this(context,null);
    }

    public DefaultCoverController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DefaultCoverController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.video_default_cover_controller_layout,this);
        mVideoCover = (ImageView) findViewById(R.id.video_cover_icon);
        mPreCount = (TextView) findViewById(R.id.view_cover_count);
        mPreDurtion = (TextView) findViewById(R.id.view_cover_durtion);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Logger.d(TAG,"onFinishInflate");
    }

    public ImageView getVideoCover() {
        return mVideoCover;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null!=mVideoCover){
            mVideoCover.setImageResource(0);
            mVideoCover=null;
        }
        mPreCount=null;mPreDurtion=null;
    }
}