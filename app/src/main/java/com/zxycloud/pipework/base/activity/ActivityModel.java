package com.zxycloud.pipework.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.zxycloud.pipework.event.type.ShakeType;
import com.zxycloud.pipework.utils.Utils;

public abstract class ActivityModel<T extends ViewBinding>{
    protected T binding;
    protected BaseActivity activity;

    public ActivityModel(T binding, BaseActivity activity) {
        this.binding = binding;
        this.activity = activity;
    }

    public ActivityModel(T binding) {
        this.binding = binding;
    }

    public abstract void onResume();

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    public abstract void onPause();

    public abstract void onDestroy();

    public abstract void onRestart();

    public abstract void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    /**
     * 设置点击事件并防止重复执行点击事件
     *
     * @param listener 点击事件监听，若listener为空则设置点击事件为空
     * @param views
     */
    protected void setOnClickListener(View.OnClickListener listener, View... views) {
        for (View view : views)
            view.setOnClickListener((View v) -> {
                        if (listener != null && Utils.isTimeUp(ShakeType.TIME_UP_CAN_CLICK, System.currentTimeMillis()))
                            listener.onClick(v);
                    }
            );
    }
}
