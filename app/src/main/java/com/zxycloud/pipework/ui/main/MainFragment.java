package com.zxycloud.pipework.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.zxycloud.pipework.R;
import com.zxycloud.pipework.base.fragment.BaseFragment;
import com.zxycloud.pipework.databinding.MainFragmentBinding;
import com.zxycloud.pipework.event.TabSelectedEvent;
import com.zxycloud.pipework.ui.home.HomeFragment;
import com.zxycloud.pipework.utils.Utils;
import com.zxycloud.pipework.widget.BottomBarTab.BottomBar;
import com.zxycloud.pipework.widget.BottomBarTab.BottomBarTab;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;

public class MainFragment extends BaseFragment<MainFragmentBinding, MainViewModel> {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    private SupportFragment[] mFragments = new SupportFragment[4];

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected void initView() {
        binding.bottomBar
                .addItem(new BottomBarTab(Utils.getContext(), R.mipmap.tab_home, "首页"))
                .addItem(new BottomBarTab(Utils.getContext(), R.mipmap.tab_statistics, "统计"))
                .addItem(new BottomBarTab(Utils.getContext(), R.mipmap.tab_service, "服务"))
                .addItem(new BottomBarTab(Utils.getContext(), R.mipmap.tab_me, "我的"));

        binding.bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                EventBusActivityScope.getDefault(getActivity()).post(new TabSelectedEvent(position));
            }
        });

        SupportFragment firstFragment = findChildFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = HomeFragment.newInstance();
            mFragments[THIRD] = HomeFragment.newInstance();
            mFragments[FOURTH] = HomeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(HomeFragment.class);
            mFragments[THIRD] = findChildFragment(HomeFragment.class);
            mFragments[FOURTH] = findChildFragment(HomeFragment.class);
        }
    }

    @Override
    protected MainFragmentBinding getBinding(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return MainFragmentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected MainViewModel getModel() {
        return new MainViewModel(binding,this);
    }
}
