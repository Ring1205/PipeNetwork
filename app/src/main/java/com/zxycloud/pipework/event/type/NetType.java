package com.zxycloud.pipework.event.type;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NetType {
    /**
     * 通用业务
     */
    public static final int API_TYPE_NORMAL = 0x096;
    /**
     * 文件操作
     */
    public static final int API_TYPE_FILE_OPERATION = 0x095;
    /**
     * 统计
     */
    public static final int API_TYPE_STATISTICS = 0x094;
    /**
     * 登录设置
     */
    public static final int API_TYPE_LOGIN_SETTING = 0x093;
    /**
     * 登录设置
     */
    public static final int API_TYPE_PATROL = 0x092;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({API_TYPE_NORMAL, API_TYPE_FILE_OPERATION, API_TYPE_STATISTICS, API_TYPE_LOGIN_SETTING, API_TYPE_PATROL})
    public @interface type {
    }
}
