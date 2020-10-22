package com.zxycloud.pipework.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.zxycloud.pipework.base.main.BaseMainFragment;
import com.zxycloud.pipework.base.main.FragmentMainModel;
import com.zxycloud.pipework.databinding.FragmentHomeBinding;
import com.zxycloud.pipework.ui.main.MainTitleModel;

public class HomeViewModel extends FragmentMainModel<FragmentHomeBinding> {
    private MainTitleModel titleModel;

    public HomeViewModel(FragmentHomeBinding binding, BaseMainFragment fragment) {
        super(binding, fragment);
        titleModel = new MainTitleModel(binding.title,fragment);
        titleModel.setToolbarTitle("首页");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
