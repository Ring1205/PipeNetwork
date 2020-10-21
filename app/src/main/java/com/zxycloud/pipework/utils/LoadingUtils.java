package com.zxycloud.pipework.utils;

import android.view.View;

import com.zxycloud.pipework.event.type.ShakeType;
import com.zxycloud.pipework.event.type.ShowLoadType;
import com.zxycloud.pipework.utils.Utils;
import com.zxycloud.pipework.widget.LoadingLayout;

public class LoadingUtils {
    private LoadingLayout loading;
    private LoadingClickCallBack clickCallBack;

    public LoadingUtils(View parentLayout, int loadLayoutRes, LoadingClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
        View view = parentLayout.findViewById(loadLayoutRes);
        if (view instanceof LoadingLayout) {
            loading = (LoadingLayout) view;
        } else {
            throw new IllegalArgumentException("loadingLayout must extend from IllustrationLayout");
        }
    }

    public void loading(@ShowLoadType.showType final int nextStatus) {
        loading(nextStatus, true);
    }

    public void loading(@ShowLoadType.showType final int nextStatus, boolean isShowText) {
        if (loading != null) {
            switch (nextStatus) {
                case ShowLoadType.SHOW_LOADING:
                    loading.showLoading();
                    break;
                case ShowLoadType.SHOW_EMPTY:
                    loading.showEmpty();
                    break;
                case ShowLoadType.SHOW_ERROR:
                    loading.showError();
                    break;
                case ShowLoadType.SHOW_CONTENT:
                    loading.showContent();
                    break;
                default:
                    break;
            }
            if (nextStatus != ShowLoadType.SHOW_CONTENT) {
                loading.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != clickCallBack && Utils.isTimeUp(ShakeType.TIME_UP_LOADING, System.currentTimeMillis())) {
                            clickCallBack.onClick(nextStatus);
                        }
                    }
                });
            }
        }
    }

    /**
     * 缺省页点击回调
     */
    public interface LoadingClickCallBack {
        /**
         * 缺省页点击联动 请求成功显示数据不会调用该方法
         *
         * @param loadType 当前缺省页所处状态
         */
        void onClick(int loadType);
    }
}
