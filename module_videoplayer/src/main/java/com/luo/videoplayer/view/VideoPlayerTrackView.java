package com.luo.videoplayer.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.luo.videoplayer.R;
import com.luo.videoplayer.base.BaseVideoPlayer;
import com.luo.videoplayer.controller.DefaultCoverController;
import com.luo.videoplayer.controller.DefaultGestureController;
import com.luo.videoplayer.controller.DefaultVideoController;


/**
 * title：VideoPlayerTrackView
 * description：示例视频播放器
 * author：luojiongtian on 2020/4/17 10:39
 */
public class VideoPlayerTrackView extends BaseVideoPlayer<DefaultVideoController,
        DefaultCoverController, DefaultGestureController> {

    @Override
    protected int getLayoutID() {
        return R.layout.video_default_track_layout;
    }

    public VideoPlayerTrackView(@NonNull Context context) {
        this(context,null);
    }

    public VideoPlayerTrackView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VideoPlayerTrackView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}