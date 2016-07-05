package com.base.givon.givonlibrary.common.utils;



import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.App;

import android.content.Context;
import android.widget.Toast;




public class ToastUtils {
	private static Context mContext = App.getInstance();

	public static void showMessage(Context cont, int msg) {
		Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
		toast.getView().setBackgroundResource(R.mipmap.base_tip_bg);
		toast.getView().setPadding(50, 20, 50, 20);
		toast.show();
	}

	public static void showMessage(CharSequence text) {
		if(StringUtil.isEmpty(text)){
			return;
		}
		Toast toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
		toast.getView().setBackgroundResource(R.mipmap.base_tip_bg);
		toast.getView().setPadding(50, 20, 50, 20);
		toast.show();
	}
	public static void showMessage(CharSequence text,int type) {
		if(StringUtil.isEmpty(text)){
			return;
		}
		Toast toast = Toast.makeText(mContext, text, type);
		toast.getView().setBackgroundResource(R.mipmap.base_tip_bg);
		toast.getView().setPadding(50, 20, 50, 20);
		toast.show();
	}

	public static void showMessage(int resid) {
		if (resid < 1) {
			return;
		}
		Toast toast = Toast.makeText(mContext, mContext.getResources().getString(resid),
				Toast.LENGTH_SHORT);
		toast.getView().setBackgroundResource(R.mipmap.base_tip_bg);
		toast.getView().setPadding(50, 20, 50, 20);
		toast.show();
	}
}
