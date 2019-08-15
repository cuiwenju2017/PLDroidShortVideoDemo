package com.qiniu.pili.droid.shortvideo.demo.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.qiniu.pili.droid.shortvideo.PLAuthenticationResultCallback;
import com.qiniu.pili.droid.shortvideo.PLShortVideoEnv;
import com.qiniu.pili.droid.shortvideo.demo.BuildConfig;
import com.qiniu.pili.droid.shortvideo.demo.R;
import com.qiniu.pili.droid.shortvideo.demo.utils.PermissionChecker;
import com.qiniu.pili.droid.shortvideo.demo.utils.RecordSettings;
import com.qiniu.pili.droid.shortvideo.demo.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Spinner mPreviewSizeRatioSpinner;
    private Spinner mPreviewSizeLevelSpinner;
    private Spinner mEncodingModeLevelSpinner;
    private Spinner mEncodingSizeLevelSpinner;
    private Spinner mEncodingBitrateLevelSpinner;
    private Spinner mAudioChannelNumSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView versionInfoTextView = (TextView) findViewById(R.id.VersionInfoTextView);
        String info = "版本号：" + getVersionDescription() + "，编译时间：" + getBuildTimeDescription();
        versionInfoTextView.setText(info);

        mPreviewSizeRatioSpinner = (Spinner) findViewById(R.id.PreviewSizeRatioSpinner);
        mPreviewSizeLevelSpinner = (Spinner) findViewById(R.id.PreviewSizeLevelSpinner);
        mEncodingModeLevelSpinner = (Spinner) findViewById(R.id.EncodingModeLevelSpinner);
        mEncodingSizeLevelSpinner = (Spinner) findViewById(R.id.EncodingSizeLevelSpinner);
        mEncodingBitrateLevelSpinner = (Spinner) findViewById(R.id.EncodingBitrateLevelSpinner);
        mAudioChannelNumSpinner = (Spinner) findViewById(R.id.AudioChannelNumSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.PREVIEW_SIZE_RATIO_TIPS_ARRAY);
        mPreviewSizeRatioSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.PREVIEW_SIZE_LEVEL_TIPS_ARRAY);
        mPreviewSizeLevelSpinner.setAdapter(adapter);
        mPreviewSizeLevelSpinner.setSelection(3);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_MODE_LEVEL_TIPS_ARRAY);
        mEncodingModeLevelSpinner.setAdapter(adapter);
        mEncodingModeLevelSpinner.setSelection(0);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_SIZE_LEVEL_TIPS_ARRAY);
        mEncodingSizeLevelSpinner.setAdapter(adapter);
        mEncodingSizeLevelSpinner.setSelection(7);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.ENCODING_BITRATE_LEVEL_TIPS_ARRAY);
        mEncodingBitrateLevelSpinner.setAdapter(adapter);
        mEncodingBitrateLevelSpinner.setSelection(2);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RecordSettings.AUDIO_CHANNEL_NUM_TIPS_ARRAY);
        mAudioChannelNumSpinner.setAdapter(adapter);
        mAudioChannelNumSpinner.setSelection(0);

        PLShortVideoEnv.checkAuthentication(getApplicationContext(), new PLAuthenticationResultCallback() {
            @Override
            public void onAuthorizationResult(int result) {
                if (result == PLAuthenticationResultCallback.UnCheck) {
                    ToastUtils.s(MainActivity.this, "UnCheck");
                } else if (result == PLAuthenticationResultCallback.UnAuthorized) {
                    ToastUtils.s(MainActivity.this, "UnAuthorized");
                } else {
                    ToastUtils.s(MainActivity.this, "Authorized");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(this);
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
            ToastUtils.s(this, "Some permissions is not approved !!!");
        }
        return isPermissionOK;
    }

    public void onClickCapture(View v) {//视频拍摄
        if (isPermissionOK()) {
            jumpToCaptureActivity();
        }
    }

    public void onClickAudioCapture(View v) {//音频录制
        if (isPermissionOK()) {
            jumpToAudioCaptureActivity();
        }
    }

    public void onClickImport(View v) {//视频剪辑
        if (isPermissionOK()) {
            jumpToActivity(VideoTrimActivity.class);
        }
    }

    public void onClickMixRecord(View v) {//分屏录制
        if (isPermissionOK()) {
            jumpToActivity(VideoMixRecordConfigActivity.class);
        }
    }

    public void onClickTranscode(View v) {//视频转码
        if (isPermissionOK()) {
            jumpToActivity(VideoTranscodeActivity.class);
        }
    }

    public void onClickMakeGIF(View v) {//制作GIF动画
        if (isPermissionOK()) {
            jumpToActivity(MakeGIFActivity.class);
        }
    }

    public void onClickScreenRecord(View v) {//屏幕录制
        if (isPermissionOK()) {
            jumpToActivity(ScreenRecordActivity.class);
        }
    }

    public void onClickVideoCompose(View v) {//视频拼接
        if (isPermissionOK()) {
            jumpToActivity(VideoComposeActivity.class);
        }
    }

    public void onClickImageCompose(View v) {//图片合成
        if (isPermissionOK()) {
            jumpToActivity(ImageComposeActivity.class);
        }
    }

    public void onClickMultipleCompose(View v) {//图片视频混排
        if (isPermissionOK()) {
            jumpToActivity(MultipleComposeActivity.class);
        }
    }

    public void onClickAR(View v) {//AR特效
        if (isPermissionOK()) {
            jumpToActivity(ArRecordActivity.class);
        }
    }

    public void onClickTransitionMake(View v) {//视频分割
        if (isPermissionOK()) {
            jumpToActivity(VideoDivideActivity.class);
        }
    }

    public void onClickDraftBox(View v) {//草稿箱
        if (isPermissionOK()) {
            jumpToActivity(DraftBoxActivity.class);
        }
    }

    public void onClickExternalMediaRecord(View v) {//外部录制
        if (isPermissionOK()) {
            jumpToActivity(ExternalMediaRecordActivity.class);
        }
    }

    public void onClickVideoMix(View v) {//视频拼图
        if (isPermissionOK()) {
            jumpToActivity(VideoMixActivity.class);
        }
    }

    private void jumpToActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

    public void jumpToCaptureActivity() {
        Intent intent = new Intent(MainActivity.this, VideoRecordActivity.class);
        intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_RATIO, mPreviewSizeRatioSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_LEVEL, mPreviewSizeLevelSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.ENCODING_MODE, mEncodingModeLevelSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.ENCODING_SIZE_LEVEL, mEncodingSizeLevelSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.ENCODING_BITRATE_LEVEL, mEncodingBitrateLevelSpinner.getSelectedItemPosition());
        intent.putExtra(VideoRecordActivity.AUDIO_CHANNEL_NUM, mAudioChannelNumSpinner.getSelectedItemPosition());
        startActivity(intent);
    }

    public void jumpToAudioCaptureActivity() {
        Intent intent = new Intent(MainActivity.this, AudioRecordActivity.class);
        intent.putExtra(AudioRecordActivity.ENCODING_MODE, mEncodingModeLevelSpinner.getSelectedItemPosition());
        intent.putExtra(AudioRecordActivity.AUDIO_CHANNEL_NUM, mAudioChannelNumSpinner.getSelectedItemPosition());
        startActivity(intent);
    }

    private String getVersionDescription() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "未知";
    }

    protected String getBuildTimeDescription() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(BuildConfig.BUILD_TIMESTAMP);
    }
}
