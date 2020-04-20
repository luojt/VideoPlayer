package com.luo.videoplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.luo.videoplayer.R;
import com.luo.videoplayer.base.BaseVideoPlayer;
import com.luo.videoplayer.controller.DefaultGestureController;
import com.luo.videoplayer.controller.DefaultVideoController;
import com.luo.videoplayer.controller.DetailsCoverController;


/**
 * title：VideoDetailsPlayerTrackView
 * description：
 * author：luojiongtian on 2020/4/17 10:39
 */
public class VideoDetailsPlayerTrackView extends BaseVideoPlayer<DefaultVideoController,
        DetailsCoverController, DefaultGestureController> {

    @Override
    protected int getLayoutID() {
        return R.layout.video_default_track_layout;
    }

    public VideoDetailsPlayerTrackView(@NonNull Context context) {
        this(context,null);
    }

    public VideoDetailsPlayerTrackView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VideoDetailsPlayerTrackView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}