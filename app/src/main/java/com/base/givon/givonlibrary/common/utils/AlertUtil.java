package com.base.givon.givonlibrary.common.utils;


import com.base.givon.givonlibrary.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

/**
 * 所有的弹窗封装,所有弹窗增加try{...}catch(Exception e) 防止未知错误
 * @description
 * @author givon
 * @date 2015年7月28日 上午11:49:31
 *
 */
public class AlertUtil {
	/**
	 * show Alert Dialog
	 * @param context
	 * @param titleId
	 * @param messageId
	 */
	//重复点击时差毫秒数
	private static int milliseconds=1000;
	//弹窗单例
	private static AlertDialog  alertDialog;
	public static void showAlert(Context context, int titleId, int messageId) {
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			try{
				if (alertDialog==null) {
					alertDialog = new AlertDialog.Builder(context)
						.setIcon(android.R.drawable.ic_dialog_alert).setTitle(titleId)
						.setPositiveButton(android.R.string.ok,null)
						.setMessage(messageId).create();
				}
				if (!alertDialog.isShowing())
					alertDialog.show();
				
				alertDialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						clearDialog();
					}
				});
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/** 
	 * @param context
	 * @param title
	 * @param message
	 */
	public static void showAlert(Context context, String title, String message) {
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			try{
				if (alertDialog==null) {
					alertDialog = new AlertDialog.Builder(context)
						.setPositiveButton(android.R.string.ok,null).create();
				}
				alertDialog.setTitle(title);
				alertDialog.setMessage(message);
				if (!alertDialog.isShowing())
					alertDialog.show();
				alertDialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						clearDialog();
					}
				});
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * Show Alert Dialog
	 * 
	 * @param context
	 * @param titleId
	 * @param messageId
	 * @param ok
	 * @param positiveListener
	 * @param cancle
	 * @param negativeListener
	 */
	public static void showAlert(Context context, int titleId, int messageId,
			int ok, OnClickListener positiveListener,
			int cancle, OnClickListener negativeListener) {
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			try{
				String title = titleId != 0 ? context.getResources().getString(titleId)
						: "";
				String msg = messageId != 0 ? context.getResources().getString(
						messageId) : "";
			if (alertDialog==null) {
				alertDialog = new AlertDialog.Builder(context).setTitle(title)
						.setPositiveButton(ok, positiveListener)
						.setNegativeButton(cancle, negativeListener).setMessage(msg)
						.setCancelable(false).create();
			}
			if (!alertDialog.isShowing())
				alertDialog.show();
			
			alertDialog.setOnDismissListener(new OnDismissListener() {
				@Override
				public void onDismiss(DialogInterface dialog) {
					clearDialog();
				}
			});
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * Show Alert Dialog 2
	 * 
	 * @param context
	 * @param titleId
	 * @param message
	 * @param ok
	 * @param positiveListener
	 * @param cancle
	 * @param negativeListener
	 */
	public static void showAlert(Context context, int titleId, String message,
			int ok, OnClickListener positiveListener,
			int cancle, OnClickListener negativeListener) {
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			try{
				String title = titleId != 0 ? context.getResources().getString(titleId)
						: "";
				if (alertDialog==null) {
					alertDialog = new AlertDialog.Builder(context).setTitle(title)
						.setPositiveButton(ok, positiveListener)
						.setNegativeButton(cancle, negativeListener)
						.setMessage(message).setCancelable(false).create();
				}
				if (!alertDialog.isShowing())
					alertDialog.show();
				
				alertDialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						clearDialog();
					}
				});
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Show Alert Dialog
	 * 
	 * @param context
	 * @param titleId
	 * @param messageId
	 * @param positiveButtontxt
	 * @param positiveListener
	 */
	public static void showAlert(Context context, int titleId, int messageId,
			CharSequence positiveButtontxt,
			OnClickListener positiveListener) {
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			try{
				if (alertDialog==null) {
					alertDialog = new AlertDialog.Builder(context)
						.setIcon(android.R.drawable.ic_dialog_alert).setTitle(titleId)
						.setPositiveButton(positiveButtontxt, positiveListener)
						.setMessage(messageId).setCancelable(false).create();
				}
				if (!alertDialog.isShowing())
					alertDialog.show();
				alertDialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						clearDialog();
					}
				});
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	static Toast mToast = null;
	/**
	 * show Toast
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, String msg) {
		if(null==msg || "".equals(msg))
			return;
		if (mToast==null) {
			mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		}
		
		mToast.setText(msg);
		mToast.show();
		mToast=null;
	}

	/**
	 * 自定义Toast
	 * @param context
	 * @param msg string资源文件
	 */
	public static void waringToast(Context context, String msg) {
		waringToast(context, R.mipmap.ic_launcher,msg);
	}
	
	/**
	 * 自定义Toast
	 * @param context
	 * @param res_icon 图片资源
	 * @param msg string资源文件
	 */
	public static  void waringToast(Context context, int res_icon,String msg) {
		
		ImageView img = new ImageView(context);
		img.setImageResource(res_icon);
		waringToast(context,img,msg);
	}
	
	/**
	 * 自定义Toast
	 * @param context
	 * @param view 自定义视图
	 * @param msg 消息
	 */
	public static void waringToast(Context context, View view,String msg) {
		if(null==msg || "".equals(msg))
			return;
		if (mToast==null) {
			mToast = Toast.makeText(context,
				msg, Toast.LENGTH_SHORT);
		}
		mToast.setText(msg);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout lay = (LinearLayout) mToast.getView();
		lay.getBackground().setAlpha(150);
		lay.addView(view, 0);
		mToast.show();
		mToast = null;
	}

	/**
	 * 自定义视图
	 * @param context
	 * @param res_layout 资源布局
	 * @return 返回当前装入的视图窗口
	 */
	public static AlertDialog showViewDialog(Context context,int res_layout) {
		return showViewDialog(context, LayoutInflater.from(context).inflate(res_layout, null));
	}

	/**
	 * 自定义视图 如果需要全屏通过dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT)设置;
	 * @param activity
	 * @param view 视图布局
	 * @return 返回当前装入的视图窗口
	 */
	public static AlertDialog showViewDialog(Context activity,View view) {
		
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			try{
				if (alertDialog==null) {
					alertDialog = new AlertDialog.Builder(activity).create();
				}
				alertDialog.setCanceledOnTouchOutside(true);
				if(!alertDialog.isShowing())
					alertDialog.show();
				alertDialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						clearDialog();
					}
				});
				Window window = alertDialog.getWindow();
				window.setContentView(view);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return alertDialog;
	}
	
	/**
	 * 自定义视图,可以自定义弹窗间距,如果需要全屏通过dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT)设置;
	 * @param activity
	 * @param layoutResID 视图布局，可以自定义间距
	 * @return 返回当前装入的视图窗口
	 */
	public static AlertDialog customDialog(Context activity,int layoutResID) {
		
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			try{
				if (alertDialog==null) {
					alertDialog = new AlertDialog.Builder(activity).create();
				}
				alertDialog.setCanceledOnTouchOutside(true);
				if(!alertDialog.isShowing())
					alertDialog.show();
				alertDialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						clearDialog();
					}
				});
				Window window = alertDialog.getWindow();
				window.setContentView(layoutResID);
				//获取编辑焦点
				window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return alertDialog;
	}
	
	/**
	 * 全屏显示Dialog
	 * @param activity
	 * @param layoutResID
	 * @return AlertDialog
	 */
	public static AlertDialog fullScreenDialog(Context activity,int layoutResID)
	{
		AlertDialog alertDialog = customDialog(activity,layoutResID);
		if(alertDialog==null)
			return null;
		alertDialog.getWindow().setLayout(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
	    return alertDialog;
	}

		
	/**
	 * 单选弹窗选择项
	 * 
	 * @param context
	 * @param title
	 *            弹窗选择标题
	 * @param index
	 *            当前选择的项
	 * @param title
	 *            []data 数据项
	 * @param onClickListener
	 */
	public static void showSingleChoiceDialog(Context context, String title,
			int index, String[] data, OnClickListener onClickListener) {
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			try{
				if (alertDialog==null) {
					Context dialogContext = new ContextThemeWrapper(context,
							android.R.style.Theme_Light);
					AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext).setTitle(title);
					alertDialog = builder.create();
					ListAdapter adapter = new ArrayAdapter<String>(dialogContext,
							android.R.layout.simple_list_item_single_choice, data);
				
					builder.setSingleChoiceItems(adapter, index, onClickListener);
					builder.setNegativeButton(
							context.getResources().getString(R.string.cancle),
							new OnClickListener() {
			
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									clearDialog();
								}
			
							});
				
				}
				if (!alertDialog.isShowing())
					alertDialog.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 列表对话框
	 * 
	 * @param context
	 * @param title
	 *            弹窗选择标题
	 * @param data
	 *            []data 数据项
	 * @param onClickListener
	 */
	public static void showListItemDialog(Context context, String title,
			String[] data, final OnClickListener onClickListener) {
		if (!ToolsUtil.isFastDoubleClick(milliseconds)) {
			if (alertDialog==null) {
				Context dialogContext = new ContextThemeWrapper(context,
						android.R.style.Theme_Light);
				AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext).setTitle(title);
				alertDialog = builder.create();
				builder.setItems(data, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						onClickListener.onClick(dialog, which);
					}
				});
			}
			if (!alertDialog.isShowing())
				alertDialog.show();
		}
	}

	private static ProgressDialog progressDialog;

	/**
	 * 显示进度条
	 * 
	 * @param context
	 * @param title
	 * @param message
	 */
	public synchronized static void showProgressDialog(Context context, String title,
			String message) {
		try {
			if(null != context && null==progressDialog) {
				progressDialog = ProgressDialog.show(context, title, message, true);
				progressDialog.setCancelable(true);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 改变进度条内容
	 * 
	 * @param text
	 */
	public synchronized static void showProgressDialogMsgs(final String text) {
		try{
			if (progressDialog != null) {
				progressDialog.setMessage(text);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭进度条
	 */
	public synchronized static void closeProgressDialog() {
		try{
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
				progressDialog = null;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 在Activity 销毁的时候调用该方法
	 */
	public static void clearDialog() {
		alertDialog = null;
	}
}
