package com.zxycloud.pipework.event.listener;

import com.zxycloud.pipework.base.BaseBean;

public interface NetRequestListener<TT extends BaseBean> {
    void success(String action, TT baseBean, Object tag);
}
