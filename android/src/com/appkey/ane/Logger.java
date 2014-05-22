package com.appkey.ane;

import com.appkey.ane.appkeyanenative.BuildConfig;

import android.util.Log;

/**
 * Helper methods that make logging more consistent throughout the app.
 */
public class Logger {
	
    private final static String mTag="AppKeyANE";
    private final static boolean mLogd=false;

    public static void d(String message) {
        if (mLogd) {
            Log.d(mTag, message);
        }
    }

    public static void d(String message, Throwable cause) {
        if (mLogd) {
            Log.d(mTag, message, cause);
        }
    }

    public static void i(String message) {
        Log.i(mTag, message);
    }

    public static void i(String message, Throwable cause) {
        Log.i(mTag, message, cause);
    }
    
    public static void v(String message) {
        if (BuildConfig.DEBUG && Log.isLoggable(mTag, Log.VERBOSE)) {
            Log.v(mTag, message);
        }
    }

    public static void v(String message, Throwable cause) {
        if (BuildConfig.DEBUG && Log.isLoggable(mTag, Log.VERBOSE)) {
            Log.v(mTag, message, cause);
        }
    }

    public static void w(String message) {
        Log.w(mTag, message);
    }

    public static void w(String message, Throwable cause) {
        Log.w(mTag, message, cause);
    }

    public static void e(String message) {
        Log.e(mTag, message);
    }

    public static void e(String message, Throwable cause) {
        Log.e(mTag, message, cause);
    }

    private Logger() {
    }
}
