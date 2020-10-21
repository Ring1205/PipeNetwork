package com.zxycloud.pipework.base.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.zxycloud.pipework.event.type.ShakeType;
import com.zxycloud.pipework.utils.Utils;

import me.yokeyword.fragmentation.SupportFragment;

public abstract class FragmentMainModel<T extends ViewBinding> {
    protected T binding;
    protected BaseMainFragment fragment;

    public FragmentMainModel(T binding, BaseMainFragment fragment) {
        this.binding = binding;
        this.fragment = fragment;
    }

    public FragmentMainModel(T binding) {
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
    /**
     * 主页面跳转到次级页面
     *
     * @param targetFragment
     */
    public void startFragment(SupportFragment targetFragment) {
        ((SupportFragment) fragment.getParentFragment()).start(targetFragment);
    }

    /**
     * 主页面跳转到次级页面并返回值
     *
     * @param toFragment
     * @param requestCode
     */
    public void startFragmentForResult(SupportFragment toFragment, int requestCode) {
        ((SupportFragment) fragment.getParentFragment()).startForResult(toFragment, requestCode);
    }
}
