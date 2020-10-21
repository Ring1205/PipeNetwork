package com.zxycloud.pipework;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zxycloud.pipework.base.activity.BaseActivity;
import com.zxycloud.pipework.databinding.MainActivityBinding;
import com.zxycloud.pipework.ui.main.MainFragment;
import com.zxycloud.pipework.utils.SystemUtil;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(MainActivityBinding.inflate(getLayoutInflater()).getRoot());

        // 设置状态栏白底黑字
        SystemUtil.StatusBarLightMode(this);

        if (findFragment(MainFragment.class) == null)
            loadRootFragment(R.id.container, MainFragment.newInstance());
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
