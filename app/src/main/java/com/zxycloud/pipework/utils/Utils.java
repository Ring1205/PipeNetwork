package com.zxycloud.pipework.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.zxycloud.pipework.BuildConfig;
import com.zxycloud.pipework.event.type.ShakeType;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leiming
 * @date 2018/12/19.
 */
public class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        //是否开启打印日志
        KLog.init(BuildConfig.DEBUG);

        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }

    private static ExecutorService threadPoolExecutor;
    /*-------------------------------------------线程池--------------------------------------------*/

    /**
     * 启动线程池
     *
     * @param runnable 需要在子线程执行的程序
     */
    public static void threadPoolExecute(Runnable runnable) {
        if (threadPoolExecutor == null) {
            threadPoolExecutor = Executors.newFixedThreadPool(3);
        }
        threadPoolExecutor.execute(runnable);
    }

    /*-------------------------------------------长度判断-------------------------------------------*/

    /**
     * 判断集合的长度
     *
     * @param list 所要判断的集合
     * @return 集合的大小，若为空也则返回0
     */
    public static int judgeListNull(List list) {
        if (list == null || list.size() == 0) {
            return 0;
        } else {
            return list.size();
        }
    }

    /**
     * 判断集合的长度
     *
     * @param map 所要判断的集合
     * @return 集合的大小，若为空也则返回0
     */
    public static int judgeListNull(Map map) {
        if (map == null || map.size() == 0) {
            return 0;
        } else {
            return map.size();
        }
    }

    /**
     * 判断集合的长度
     *
     * @param list 索要获取长度的集合
     * @return 该集合的长度
     */
    public static <T> int judgeListNull(T[] list) {
        if (list == null || list.length == 0) {
            return 0;
        } else {
            return list.length;
        }
    }

    /*-------------------------------------------toast-------------------------------------------*/

    private static Toast toast;

    /**
     * 消息提示框
     *`
     * @param message 提示消息文本
     */
    @SuppressLint("ShowToast")
    public static void toast(String message) {
        try {
            if (toast != null) {
                toast.setText(message);
            } else {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            }
            toast.show();
        } catch (Exception e) {
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    /**
     * 消息提示框
     *
     * @param messageId 提示消息文本ID
     */
    @SuppressLint("ShowToast")
    public static void toast(int messageId) {
        try {
            if (toast != null) {
                toast.setText(messageId);
            } else {
                toast = Toast.makeText(context, messageId, Toast.LENGTH_SHORT);
            }
            toast.show();
        } catch (Exception e) {
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    /*-------------------------------------------防止频繁操作工具-------------------------------------------*/

    /**
     * 默认的时间间隔初始化
     */
    private static SparseArray<Long> defaultIntervalTimes;
    private static SparseArray<Long> lastSaveTimes;

    {
        defaultIntervalTimes = new SparseArray<>();
        defaultIntervalTimes.put(ShakeType.TIME_UP_PROJECT_CHANGED, 2000L);
        defaultIntervalTimes.put(ShakeType.TIME_UP_PUSH_DATA_REFRESH, 2000L);
    }

    private static long getDefaultIntervalTime(int type) {
        Long defaultIntervalTime = defaultIntervalTimes.get(type);
        if (null == defaultIntervalTime) {
            return 500L;
        } else {
            return defaultIntervalTime;
        }
    }

    /**
     * 时间间隔判断（默认500ms时间间隔）
     *
     * @param type      类型
     * @param judgeTime 当前时间
     * @return 是否在间隔时间内
     */
    public static boolean isTimeUp(@ShakeType.type int type, long judgeTime) {
        return isTimeUp(type, judgeTime, getDefaultIntervalTime(type));
    }

    /**
     * 时间间隔判断（默认500ms时间间隔）
     *
     * @param type      类型
     * @param judgeTime 当前时间
     * @return 是否在间隔时间内
     */
    public static boolean isTimeUp(@ShakeType.type int type, long judgeTime, boolean firstState) {
        return isTimeUp(type, judgeTime, getDefaultIntervalTime(type), firstState);
    }

    /**
     * 时间间隔判断
     *
     * @param type         类型
     * @param judgeTime    当前时间
     * @param intervalTime 间隔时间
     * @return 是否在间隔时间外
     */
    public static boolean isTimeUp(@ShakeType.type int type, long judgeTime, long intervalTime) {
        return isTimeUp(type, judgeTime, intervalTime, true);
    }

    /**
     * 时间间隔判断
     *
     * @param type         类型
     * @param judgeTime    当前时间
     * @param intervalTime 间隔时间
     * @return 是否在间隔时间外
     */
    public static boolean isTimeUp(@ShakeType.type int type, long judgeTime, long intervalTime, boolean firstState) {
        if (lastSaveTimes == null) {
            lastSaveTimes = new SparseArray<>();
        }
        Long tempTime = lastSaveTimes.get(type);
        if (tempTime == null) {
            lastSaveTimes.put(type, judgeTime);
            return firstState;
        } else {
            KLog.i(type + " 上次点击时间：" + tempTime + "  本次点击时间：" + judgeTime + "  时间间隔：" + (judgeTime - tempTime));
            if (judgeTime - tempTime > intervalTime) {
                lastSaveTimes.put(type, judgeTime);
                return true;
            } else {
                return false;
            }
        }
    }

    /*-------------------------------------------其它-------------------------------------------*/

    /**
     * 拼接数组
     *
     * @param first 第一个数组
     * @param rest  其他数组
     * @param <T>   数组类型
     * @return
     */
    public static <T> T[] concatArray(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /**
     * 判断APP是否在前台运行
     *
     * @param context
     * @return
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        return !TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName());
    }

}
