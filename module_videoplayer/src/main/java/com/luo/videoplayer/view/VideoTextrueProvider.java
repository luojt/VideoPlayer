package com.luo.videoplayer.view;

import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;

/**
 * title：VideoTextrueProvider
 * description：ViewRound Radius
 * author：luojiongtian on 2020/4/17 10:40
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class VideoTextrueProvider extends ViewOutlineProvider {

    private float mRadius;

    public VideoTextrueProvider(float radius){
        this.mRadius = radius;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), mRadius);
        view.setClipToOutline(true);
    }
}