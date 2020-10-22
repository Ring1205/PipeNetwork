package com.zxycloud.pipework.ui.main;

import android.text.TextUtils;
import android.view.Menu;
import android.view.View;

import androidx.annotation.MenuRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;

import com.zxycloud.pipework.R;
import com.zxycloud.pipework.base.BaseModel;
import com.zxycloud.pipework.base.fragment.BaseFragment;
import com.zxycloud.pipework.base.main.BaseMainFragment;
import com.zxycloud.pipework.databinding.ToolbarBinding;
import com.zxycloud.pipework.event.type.ShakeType;
import com.zxycloud.pipework.utils.StrUtils;
import com.zxycloud.pipework.utils.Utils;

public class MainTitleModel {
    protected ToolbarBinding binding;
    protected BaseMainFragment fragment;

    public MainTitleModel(ToolbarBinding binding, BaseMainFragment fragment) {
        this.binding = binding;
        this.fragment = fragment;
    }

    /**
     * 设置工具栏
     *
     * @param title 标题
     */
    public void setToolbarTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title))
            binding.toolbar.setTitle(title);
    }

    public void setToolbarTitle(@StringRes int titleRes) {
        this.setToolbarTitle(StrUtils.getInstance().getString(titleRes));
    }

    /**
     * 设置工具栏菜单
     *
     * @param menuRes  菜单样式
     * @param listener 点击监听
     * @param addItem  是否可添加
     */
    public void setToolbarMenu(@MenuRes int menuRes, Toolbar.OnMenuItemClickListener listener, boolean addItem) {
        Menu menu = binding.toolbar.getMenu();
        if (addItem || null == menu || menu.size() == 0)
            binding.toolbar.inflateMenu(menuRes);
        binding.toolbar.setOnMenuItemClickListener(listener);
    }

    public void setToolbarMenu(@MenuRes int menuRes, Toolbar.OnMenuItemClickListener listener) {
        setToolbarMenu(menuRes, listener, true);
    }

}
