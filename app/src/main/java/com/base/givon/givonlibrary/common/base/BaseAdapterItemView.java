package com.base.givon.givonlibrary.common.base;



import com.base.givon.givonlibrary.common.qualifier.ClickType;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import butterknife.ButterKnife;
import io.nlopez.smartadapters.views.BindableFrameLayout;

public abstract class BaseAdapterItemView<T> extends BindableFrameLayout<T> {

    public BaseAdapterItemView(Context context) {
        super(context);
    }

    public BaseAdapterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseAdapterItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BaseAdapterItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onViewInflated() {
        ButterKnife.bind(this);
    }

    public void notifyItemAction(@ClickType int actionId, T theItem, View view) {
        if (this.viewEventListener != null) {
            this.viewEventListener.onViewEvent(actionId, theItem, this.position, view);
        }

    }

    public void notifyItemAction(@ClickType int actionId, View view) {
        this.notifyItemAction(actionId, this.item, view);
    }

    public void notifyItemAction(@ClickType int actionId) {
        this.notifyItemAction(actionId, this.item, this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}