package com.zxycloud.pipework.base.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.zxycloud.pipework.event.type.ShakeType;
import com.zxycloud.pipework.utils.Utils;

public abstract class FragmentModel<T extends ViewBinding> {
    protected T binding;
    protected BaseFragment fragment;

    public FragmentModel(T binding, BaseFragment fragment) {
        this.binding = binding;
        this.fragment = fragment;
    }

    public FragmentModel(T binding) {
        this.binding = binding;
    }

    public abstract void onResume();

    public abstract void onFragmentResult(int requestCode, int resultCode, Bundle data);

    public abstract void onDestroy();

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
