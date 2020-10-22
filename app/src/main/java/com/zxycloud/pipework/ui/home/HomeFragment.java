package com.zxycloud.pipework.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zxycloud.pipework.R;
import com.zxycloud.pipework.base.main.BaseMainFragment;
import com.zxycloud.pipework.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseMainFragment<FragmentHomeBinding, HomeViewModel> {
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void onPrepare() {

    }

    @Override
    protected FragmentHomeBinding getBinding(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentHomeBinding.inflate(inflater,container,false);
    }

    @Override
    protected HomeViewModel getModel() {
        return new HomeViewModel(binding, this);
    }
}
