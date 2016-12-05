package com.master.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.lody.turbodex.TurboDex;
import com.master.app.Manager.ConfigMAnager;
import com.master.app.tools.FileManager;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Litao-pc on 2016/9/7.
 */
public class SynopsisObj extends Application {
    public static Context sContext;
    public static boolean allow_anonymous = true;
    public static String chrootDir = Environment.getExternalStorageDirectory().toString();
    public static int portNum = 2121;
    public static boolean shouldTakeFullWakeLock = true;
    public static String username = "admin";
    public static String password = "admin";

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println(chrootDir);
        sContext = getApplicationContext();
        Logger.init()
                .setMethodCount(3)
                .hideThreadInfo()
                .setLogLevel(LogLevel.FULL);
        /**
         * 每次登陆检查，如不存在，重新创建
         */
        FileManager.init(this);

        ConfigMAnager.create().initVariablesCfg(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        TurboDex.enableTurboDex();
        super.attachBaseContext(base);
    }

    public static Context getAppContext() {
        if (sContext == null) {
            Log.e(TAG, "Global context not set");
        }
        return sContext;
    }

    public static String getVersion() {
        Context context = getAppContext();
        String packageName = context.getPackageName();
        try {
            PackageManager pm = context.getPackageManager();
            return pm.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            AirbrakeNotifier.notify(e);
            Log.e(TAG, "Unable to find the name " + packageName + " in the package");
            return null;
        }
    }


}
