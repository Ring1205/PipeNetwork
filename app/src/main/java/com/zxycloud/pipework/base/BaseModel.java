package com.zxycloud.pipework.base;

import androidx.viewbinding.ViewBinding;

import com.zxycloud.pipework.base.fragment.BaseFragment;

public class BaseModel<T extends ViewBinding> {
    protected T binding;
    protected BaseFragment fragment;

    public BaseModel(T binding, BaseFragment fragment) {
        this.binding = binding;
        this.fragment = fragment;
    }

    public BaseModel(T binding) {
        this.binding = binding;
    }
}
