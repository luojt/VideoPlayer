package com.luo.videoplayer.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * title：BaseCoverController
 * description：封面控制器基类
 * author：luojiongtian on 2020/4/17 10:29
 */
public abstract class BaseCoverController extends FrameLayout {

    public BaseCoverController(@NonNull Context context) {
        this(context,null);
    }

    public BaseCoverController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseCoverController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mOnStartListener){
                    mOnStartListener.onStartPlay();
                }
            }
        });
    }

    public interface OnStartListener{
        void onStartPlay();
    }

    protected OnStartListener mOnStartListener;

    public void setOnStartListener(OnStartListener onStartListener) {
        mOnStartListener = onStartListener;
    }

    public void onDestroy(){
        mOnStartListener=null;
    }
}