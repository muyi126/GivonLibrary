package com.base.givon.givonlibrary.common.utils;

import com.givon.lib.BuildConfig;
import com.givon.lib.MainApplication;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志工具类
 * 
 * @author givon 时间： 2013-8-20 上午10:50:14
 */
public final class LogUtil {
	public static String customTagPrefix = "giv_log";
	public static final boolean DEBUG = MainApplication.DEBUG;

	public static void v(String tag, String msg) {
		if (DEBUG)
			Log.v(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (DEBUG)
			Log.d(tag, msg);
	}

	public static void i(String tag, String msg) {
		if (DEBUG)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (DEBUG)
			Log.e(tag, msg);
	}

	public static void w(String tag, String msg) {
		if (DEBUG)
			Log.w(tag, msg);
	}

	public static void v(String tag, String msg, Throwable throwable) {
		if (DEBUG)
			Log.v(tag, msg, throwable);
	}

	public static void d(String tag, String msg, Throwable throwable) {
		if (DEBUG)
			Log.d(tag, msg, throwable);
	}

	public static void i(String tag, String msg, Throwable throwable) {
		if (DEBUG)
			Log.i(tag, msg, throwable);
	}

	public static void e(String tag, String msg, Throwable throwable) {
		if (DEBUG)
			Log.e(tag, msg, throwable);
	}

	public static void w(String tag, String msg, Throwable throwable) {
		if (DEBUG)
			Log.w(tag, msg, throwable);
	}


	private static String generateTag() {
		StackTraceElement caller = new Throwable().getStackTrace()[2];
		String tag = "%s.%s(L:%d)";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
		tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
		return tag;
	}

	public static void d(String content) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.d(tag, content);
	}

	public static void d(String content, Throwable tr) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.d(tag, content, tr);
	}

	public static void e(String content) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.e(tag, content);
	}

	public static void e(String content, Throwable tr) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.e(tag, content, tr);
	}

	public static void i(String content) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.i(tag, content);
	}

	public static void i(String content, Throwable tr) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.i(tag, content, tr);
	}

	public static void v(String content) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.v(tag, content);
	}

	public static void v(String content, Throwable tr) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.v(tag, content, tr);
	}

	public static void w(String content) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.w(tag, content);
	}

	public static void w(String content, Throwable tr) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.w(tag, content, tr);
	}

	public static void w(Throwable tr) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.w(tag, tr);
	}


	public static void wtf(String content) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.wtf(tag, content);
	}

	public static void wtf(String content, Throwable tr) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.wtf(tag, content, tr);
	}

	public static void wtf(Throwable tr) {
		if (DEBUG) return;
		String tag = generateTag();

		Log.wtf(tag, tr);
	}
}
