package com.zxycloud.pipework.event.type;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ShakeType {
    /**
     * 按键点击防抖
     */
    public static final int TIME_UP_CAN_CLICK = 50;
    /**
     * 页面跳转防抖
     */
    public static final int TIME_UP_JUMP = 51;
    /**
     * 数据加载防抖
     */
    public static final int TIME_UP_LOADING = 52;
    /**
     * 推送分发事件总线防抖
     */
    public static final int TIME_UP_PUSH_EVENT_SEND = 53;
    /**
     * 单位切换
     * 默认时间为2000 ms，后续如果有新的需求，可以直接调整
     */
    public static final int TIME_UP_PROJECT_CHANGED = 90;
    /**
     * 极光推送页面刷新
     * 默认时间为2000 ms，后续如果有新的需求，可以直接调整
     */
    public static final int TIME_UP_PUSH_DATA_REFRESH = 91;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TIME_UP_CAN_CLICK, TIME_UP_JUMP, TIME_UP_LOADING, TIME_UP_PROJECT_CHANGED, TIME_UP_PUSH_DATA_REFRESH, TIME_UP_PUSH_EVENT_SEND})
    public @interface type {
    }
}
