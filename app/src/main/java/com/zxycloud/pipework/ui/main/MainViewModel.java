package com.zxycloud.pipework.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.zxycloud.pipework.base.fragment.FragmentModel;
import com.zxycloud.pipework.databinding.MainFragmentBinding;

public class MainViewModel extends FragmentModel<MainFragmentBinding> {

    public MainViewModel(MainFragmentBinding binding, MainFragment fragment) {
        super(binding, fragment);
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
