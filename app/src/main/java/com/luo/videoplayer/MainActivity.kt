package com.luo.videoplayer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.luo.videoplayer.base.BaseVideoPlayer
import com.luo.videoplayer.base.IMediaPlayer
import com.luo.videoplayer.bean.VideoParams
import com.luo.videoplayer.constants.VideoConstants
import com.luo.videoplayer.controller.DetailsCoverController
import com.luo.videoplayer.manager.VideoPlayerManager
import com.luo.videoplayer.utils.VideoUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //自动隐藏小窗口按钮倒计时
    val HIDE_VIEW_MILLISS = 3000
    //视频参数
    private var mVideoParams: VideoParams? = null
    private var mIsPlaying = true
    private var json = "{\"durtion\":248,\"lastTime\":1586183934000,\"nickName\":\"混乱博物馆\",\"previewCount\":6,\"userFront\":\"http://img.kaiyanapp.com/5947bb2e8c24673c340bfaf2c9cc9e42.png?imageMogr2/quality/60/format/jpg\",\"userSinger\":\"一档满足百科知识好奇心的短视频节目。\\n\\n每周一三五更新。\",\"videoCover\":\"http://img.kaiyanapp.com/ebf7ffc8258bbf40dd75f1a7e78194b5.png?imageMogr2/quality/60/format/jpg\",\"videoDesp\":\"限制了我的视力。\",\"videoTitle\":\"你的眼睛为何如此贫穷丨混乱博物馆\",\"videoUrl\":\"https://alivc-demo-vod.aliyuncs.com/9b2e0a6f142a4308ab96f9dad0b76eb9/4813ed0d0fbe5a234b4a7a25ce7791d6-fd.mp4\",\"videoiId\":\"190428\"}"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VideoPlayerManager.getInstance().videoDisplayType = VideoConstants.VIDEO_DISPLAY_TYPE_CUT
        initViews()
        val gson = Gson()
        mVideoParams = gson.fromJson(json, VideoParams::class.java)
        initVideoParams()
    }

    private fun initViews() { //播放器控件宽高
        val itemHeight = ScreenUtils.getScreenWidth() * 9 / 16
        video_player.layoutParams.height = itemHeight
        val coverController = DetailsCoverController(this@MainActivity)
        video_player.setVideoCoverController(coverController, false)
        video_player.setGlobaEnable(true)
        //退出播放器
        btn_back.setOnClickListener {
            onBackPressed()
        }
        //小窗口测试
        btn_tiny.setOnClickListener(View.OnClickListener {
            if (null != video_player) {
                val startY = (video_player.measuredHeight + VideoUtils.getInstance().dpToPxInt(this@MainActivity, 10f) + VideoUtils.getInstance().getStatusBarHeight(this@MainActivity))
                video_player.startMiniWindowToLocaion(Gravity.RIGHT, startY, 1280, 720, null)
            }
        })

    }

    /**
     * 播放器初始化
     * @param isCreate
     */
    private fun initVideoParams() {
        if (null != mVideoParams) {
            video_player.setDataSource(mVideoParams!!.videoUrl, mVideoParams!!.videoTitle, mVideoParams!!.videoiId)
            video_player.isPlayerWorking = true
            video_player.setParamsTag(mVideoParams)
            //封面
            if (null != video_player.coverController) {
                Glide.with(this@MainActivity)
                    .load(mVideoParams!!.videoCover)
                    .placeholder(R.drawable.ic_video_default_cover)
                    .error(R.drawable.ic_video_default_cover)
                    .dontAnimate()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(video_player.coverController.mVideoCover)
            }
            //无缝衔接外部播放任务
            if (mIsPlaying && null != IMediaPlayer.getInstance().textureView) {
                addTextrueViewToView(video_player)
                IMediaPlayer.getInstance().addOnPlayerEventListener(video_player)
                //手动检查播放器内部状态，同步常规播放器状态至全屏播放器
                IMediaPlayer.getInstance().checkedVidepPlayerState()
            } else { //开始全新播放任务
                video_player.startPlayVideo()
            }
        }
    }
    /**
     * 添加一个视频渲染组件至View
     * @param videoPlayer
     */
    private fun addTextrueViewToView(videoPlayer: BaseVideoPlayer<*, *, *>) { //先移除存在的TextrueView
        if (null != IMediaPlayer.getInstance().textureView) {
            val textureView = IMediaPlayer.getInstance().textureView
            if (null != textureView.parent) {
                (textureView.parent as ViewGroup).removeView(textureView)
            }
        }
        if (null != IMediaPlayer.getInstance().textureView) {
            videoPlayer.mSurfaceView.addView(
                IMediaPlayer.getInstance().textureView,
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER
                )
            )
        }
    }


    override fun onResume() {
        super.onResume()
        VideoPlayerManager.getInstance().onResume()
    }

    override fun onPause() {
        super.onPause()
        VideoPlayerManager.getInstance().onPause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        if (VideoPlayerManager.getInstance().isBackPressed) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        VideoPlayerManager.getInstance().onDestroy()
        if (null != video_player) {
            video_player.onDestroy()
        }
        mVideoParams = null
    }
}
