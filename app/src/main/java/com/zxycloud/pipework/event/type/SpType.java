package com.zxycloud.pipework.event.type;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SpType {
    public static final String PROJECT_ID = "projectId";// 项目ID
    // 网络请求路径
    public static final String URL = "url";
    public static final String PATROL = "patrol";
    public static final String STATISTICS = "statistics";
    public static final String FILE_UPLOAD = "fileUpload";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({PROJECT_ID
            ,PATROL
            ,STATISTICS
            ,FILE_UPLOAD
            ,URL})
    public @interface type {
    }
}
