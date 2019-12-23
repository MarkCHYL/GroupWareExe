package com.mark.sdklib.log;

import android.os.Build;
import android.os.Process;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mark.sdklib.BuildConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Desc 日志管理工具，可指定日志输出到自定的路径下
 * Android日志工具类  （支持打印重要日志，报错信息，到指定文件）
 *
 * @作者 Mark
 * @时间 2019-12-11
 * @EMail 2285581945@qq.com
 */
public class MarkLog implements Thread.UncaughtExceptionHandler {

    private static boolean isOpen = false; //是否打印日志
    private static boolean isSave = false; //是否保存日志到文件
    private static String logPath = null;  //log日志存放路径
    private static String logName = null;  //日志文件名
    private static String TAG = "MarkLog";

    //获取应用是否是处于 Debug 模式下
    private static boolean isDebug = BuildConfig.DEBUG;

    private static MarkLog instance;

    /**
     * 单列模式双层判断防死锁
     * @return
     */
    public static MarkLog getInstance() {
        if (instance == null) {
            synchronized (MarkLog.class){
                if (instance==null){
                    instance = new MarkLog();
                }
            }
        }
        return instance;
    }

    /**
     * 是否开启输出日志功能
     *
     * @param isOpen
     * @return MarkLog
     */
    public MarkLog isOpen(boolean isOpen) {
        MarkLog.isOpen = isOpen;
        return this;
    }

    /**
     * 是否保存日志到文件
     *
     * @param isSave
     * @return MarkLog
     */
    public MarkLog isSave(boolean isSave) {
        MarkLog.isSave = isSave;
        return this;
    }

    /**
     * 设置保存log的文件路径
     *
     * @param logPath
     * @return MarkLog
     */
    public MarkLog setLogPath(String logPath) {
        MarkLog.logPath = logPath;
        return this;
    }

    /**
     * 设置保存log的文件名称
     *
     * @param logName
     * @return MarkLog
     */
    public MarkLog setLogName(String logName) {
        MarkLog.logName = logName;
        return this;
    }

    /**
     * debug
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            if (isOpen) {
                Log.d(tag, msg);
            }
            if (isSave) {
                writeToFile(tag, msg);
            }
        }
    }

    /**
     * error
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isOpen) {
            Log.e(tag, msg);
        }
        if (isSave) {
            writeToFile(tag, msg);
        }
    }

    /**
     * info
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isOpen) {
            Log.i(tag, msg);
        }
        if (isSave) {
            writeToFile(tag, msg);
        }
    }

    /**
     * warn
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isOpen) {
            Log.w(tag, msg);
        }
        if (isSave) {
            writeToFile(tag, msg);
        }
    }

    /**
     * 直接写入到 log 文件中
     *
     * @param tag
     * @param msg
     */
    public static void f(String tag, String msg) {
        if (isSave) {
            writeToFile(tag, msg);
        }
    }

    /**
     * 将log信息处理
     *
     * @param tag
     * @param msg
     */
    private static void writeToFile(String tag, String msg) {
        String info = tag + "-----" + msg;
        writeInfoFile(info);
    }

    /**
     * 将log信息写入文件中
     *
     * @param info
     */
    private static void writeInfoFile(String info) {
        if (logPath == null) {
            return;
        }
        //log日志内容，可以自行定制
        String log = getNowTime() + " : " + info + "\n";
        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            //创建父路径
            boolean mkdirs = file.mkdirs();
            if (!mkdirs) {
                //没创建成功返回
                Log.d(TAG, "writeInfoFile Created folders failure");
                return;
            }else {
                Log.d(TAG, "writeInfoFile Created folders succefully");
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(logPath + File.separator + logName, true);
            fw.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();//关闭缓冲流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 初始化异常奔溃信息监听
     */
    public void initialize() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        if (logPath != null) {
            File file = new File(logPath);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    Log.d(TAG, "Created folders succefully");
                } else {
                    Log.d(TAG, "Created folders failure");
                }
            }

            try {
                String logName = "AppError" + getNowDay() + ".log";
                FileWriter fw = new FileWriter(logPath + File.separator + logName,
                        true);
                String model = Build.MODEL; //型号
                String brand = Build.BRAND; //品牌
                String release = Build.VERSION.RELEASE; //版本
                fw.write("设备型号：" + model + "\n");
                fw.write("设备品牌：" + brand + "\n");
                fw.write("设备系统版本" + release + "\n");
                fw.write(getNowTime() + "错误原因：");
                // 错误信息
                // 获取错误栈的错误信息
                StackTraceElement[] traceElements = throwable.getStackTrace();
                fw.write(throwable.getMessage() + "\n");
                for (StackTraceElement element :
                        traceElements) {
                    fw.write(element.getFileName() + " CLASS:"
                            + element.getClassName() + " METHOD:"
                            + element.getMethodName() + " LINE:"
                            + element.getLineNumber() + "\n");
                }
                fw.write("\n");
                fw.close();
                // 上传错误信息到服务器
                uploadToServer();
            } catch (IOException e) {
                Log.e("crash handler", "执行保存App运行报错信息加载文件失败...", e.getCause());
            }
        }
        throwable.printStackTrace();
        Process.killProcess(Process.myPid());
    }

    /**
     * 上传错误信息到服务器
     */
    private void uploadToServer() {
        //TODO 暂时不实现
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    private static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.US);//  这里时间选择中文显示会看起来很困扰，故选择美国标准
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return sdf.format(curDate);
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    private static String getNowDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.SIMPLIFIED_CHINESE);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return sdf.format(curDate);
    }
}
