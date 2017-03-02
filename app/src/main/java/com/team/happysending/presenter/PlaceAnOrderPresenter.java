package com.team.happysending.presenter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.team.happysending.R;
import com.team.happysending.utils.ToastUtils;
import com.team.happysending.views.activity.ContactsActivity;
import com.team.happysending.views.activity.PlaceAnOrderActivity;
import com.team.happysending.views.adapter.KindPopupAdapter;
import com.team.happysending.views.interfaces.PlaceAnOrderView;
import com.team.happysending.views.widget.MSeekBar;
import com.team.happysending.views.widget.RecordView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static android.support.v4.content.ContextCompat.checkSelfPermission;

/**
 * Created by 樊、先生 on 2017/2/24.
 * 立即下单的p层
 */

public class PlaceAnOrderPresenter extends BasePresenter<PlaceAnOrderView> {
    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;
    private PlaceAnOrderActivity mActivity;
    private AnimationDrawable mAnimationDrawable;

    public PlaceAnOrderPresenter(PlaceAnOrderActivity placeAnOrderActivity) {
        mActivity = placeAnOrderActivity;
    }

    private TimerTask timeTask;
    private Timer timeTimer = new Timer(true);

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int db = (int) (Math.random() * 100);
            mRecordView.setVolume(db);
        }
    };


    private PopupWindow mPopupWindow;
    private RecordView mRecordView;


    //语音操作对象
    private MediaPlayer mPlayer = null;
    private MediaRecorder mRecorder = null;
    private static final String LOG_TAG = "AudioRecordTest";
    private String FileName;


    /**
     * 停止录音
     */
    public void stopRecord() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    /**
     * 动态获取权限
     */
    public void getMyPermission(String fileName) {

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(mActivity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ToastUtils.showToast(mActivity,"没有获取权限！！！");
                return;

            }else {
                startRecord(fileName);
            }
        }else {
            startRecord(fileName);
        }
    }
    /**
     * 开始录音
     *
     * @param fileName
     */
    public void startRecord(String fileName) {

            FileName = fileName;
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(FileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }
            mRecorder.start();


    }


    //播放录音
    public void startPlayRecord(ImageView placeonorderImgYuYinLong) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(FileName);
            Log.d("TAG", FileName);
            mPlayer.prepare();
            mPlayer.start();
            /**
             * 播放是调用的帧动画
             */
            placeonorderImgYuYinLong.setImageResource(R.drawable.playrecord);
            mAnimationDrawable = (AnimationDrawable) placeonorderImgYuYinLong.getDrawable();
            mAnimationDrawable.start();

            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mAnimationDrawable.stop();
                    mAnimationDrawable = null;
                }
            });

        } catch (IOException e) {
            Log.e(LOG_TAG, "播放失败");
        }
    }

    //停止播放录音
    public void stopPlayListener() {
        mPlayer.release();
        mPlayer = null;
    }

    /**
     * 弹出popupWindow
     *
     * @param placeonorderEtFaName
     */
    public void showPop(EditText placeonorderEtFaName) {
        /**
         * 初始化控件
         */
        //获取屏幕高度
        /*WindowManager wm = mActivity.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        int wight = wm.getDefaultDisplay().getWidth();*/
        View v1 = View.inflate(mActivity, R.layout.show_yuyin_popup, null);
        mRecordView = (RecordView) v1.findViewById(R.id.recordView);
        mRecordView.start();

        timeTimer.schedule(timeTask = new TimerTask() {
            public void run() {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }, 20, 20);
        mRecordView.setOnCountDownListener(new RecordView.OnCountDownListener() {
            @Override
            public void onCountDown() {
                stopRecord();
                /**
                 *  动画结束，录音结束
                 */
                ToastUtils.showToast(mActivity, "录音结束~~");
            }
        });
        //创建对象
        mPopupWindow = new PopupWindow(v1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //  mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.alpha(200)));
        // 这里是位置显示方式
        mPopupWindow.showAtLocation(placeonorderEtFaName, Gravity.CENTER, 0, -250);
        // mPopupWindow.showAsDropDown(mPlaceonorderBackBtn, 300, 300);
        // mPopupWindow.showAtLocation(mPlaceanorderLlayout, Gravity.NO_GRAVITY,200,300);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
    }
    /**
     * 文备注的popup
     * @param placeonorderMoney
     */
    public void showWenZiBeiZhuPopup(TextView placeonorderMoney){

        View v2 = View.inflate(mActivity, R.layout.show_wenzi_popup, null);
        Button mWenzipopQuXiao = (Button) v2.findViewById(R.id.wenzipop_QuXiao);
        Button mWenzipopupQueDing = (Button) v2.findViewById(R.id.wenzipopup_QueDing);
        final EditText mWenzipopupEtBeiZhu = (EditText) v2.findViewById(R.id.wenzipopup_EtBeiZhu);
        mWenzipopQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                mPopupWindow = null;
            }
        });
        mWenzipopupQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击确定时，判断Edit的长度
                String str = mWenzipopupEtBeiZhu.getText().toString().trim();
                if (TextUtils.isEmpty(str)){
                    ToastUtils.showToast(mActivity,"还没有输入内容噢！！");
                }else {
                    getThisView().onGetTextBeiZhu(str);
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
            }
        });

        //创建对象
        mPopupWindow = new PopupWindow(v2, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //  mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.alpha(200)));
        // 这里是位置显示方式
        mPopupWindow.showAtLocation(placeonorderMoney, Gravity.CENTER, 0, 200);
        // mPopupWindow.showAsDropDown(mPlaceonorderBackBtn, 300, 300);
        // mPopupWindow.showAtLocation(mPlaceanorderLlayout, Gravity.NO_GRAVITY,200,300);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
    }



    public void dismiss() {

        mRecordView.cancel();
        mPopupWindow.dismiss();
        mPopupWindow = null;
    }

    /**
     * 跳转到ContactsActivity，调用通讯录
     *
     * @param i
     */
    public void toStartActivityForResult(int i){
        //跳转到通讯录界面
        Intent intent = new Intent(mActivity,ContactsActivity.class);
        mActivity.startActivityForResult(intent,i);
    }

    /**
     * 设置配送费用
     * @param placeonorderMoney
     */
    public void showMoneyPopup(TextView placeonorderMoney) {

        View v3 = View.inflate(mActivity, R.layout.show_money_popup, null);
        Button mWenzipopQuXiao = (Button) v3.findViewById(R.id.moneypop_QuXiao);
        Button mWenzipopupQueDing = (Button) v3.findViewById(R.id.moneypopup_QueDing);
        final MSeekBar  mSeekBar = (MSeekBar) v3.findViewById(R.id.moneypopup_Seekbar);

        mWenzipopQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                mPopupWindow = null;
            }
        });
        mWenzipopupQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getThisView().onGetMoney(mSeekBar.getProgress());
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
        });

        //创建对象
        mPopupWindow = new PopupWindow(v3, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //  mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.alpha(200)));
        // 这里是位置显示方式
        mPopupWindow.showAtLocation(placeonorderMoney, Gravity.CENTER, 0, 500);
        // mPopupWindow.showAsDropDown(mPlaceonorderBackBtn, 300, 300);
        // mPopupWindow.showAtLocation(mPlaceanorderLlayout, Gravity.NO_GRAVITY,200,300);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);

    }
    /**
     * 设置配送费用
     * @param mPlaceonorderTiJiaoBtn
     */
    public void showKindPopup(TextView mPlaceonorderTiJiaoBtn) {

        View v4 = View.inflate(mActivity, R.layout.show_kind_popup, null);
        Button mKindpopQuXiao = (Button) v4.findViewById(R.id.kindpop_QuXiao);
        Button mKindpopupQueDing = (Button) v4.findViewById(R.id.kindpopup_QueDing);

        RecyclerView recyclerView = (RecyclerView) v4.findViewById(R.id.kindpopup_re);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        final KindPopupAdapter goodsAdapter = new KindPopupAdapter(mActivity);

        recyclerView.setAdapter(goodsAdapter);
        //取消
        mKindpopQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                mPopupWindow = null;
            }
        });
        //确定按钮
        mKindpopupQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 条目点击事件
                 */
                getThisView().onGetKind(goodsAdapter.getTxt());

                mPopupWindow.dismiss();
                mPopupWindow = null;
            }
        });

        //创建对象
        mPopupWindow = new PopupWindow(v4, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //  mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.alpha(200)));
        // 这里是位置显示方式
        mPopupWindow.showAtLocation(mPlaceonorderTiJiaoBtn, Gravity.CENTER, 0, 500);
        // mPopupWindow.showAsDropDown(mPlaceonorderBackBtn, 300, 300);
        // mPopupWindow.showAtLocation(mPlaceanorderLlayout, Gravity.NO_GRAVITY,200,300);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);

    }
}
