package com.base.givon.givonlibrary.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.List;

import io.nlopez.smartadapters.builders.BindableLayoutBuilder;
import io.nlopez.smartadapters.builders.DefaultBindableLayoutBuilder;
import io.nlopez.smartadapters.utils.Mapper;
import io.nlopez.smartadapters.utils.Reflections;
import io.nlopez.smartadapters.views.BindableLayout;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/26 上午10:41 - Guzhu
 * @email:muyi126@163.com
 */
public class BundleItemLayoutBuilder implements BindableLayoutBuilder {
    private Bundle mBundle;

    public BundleItemLayoutBuilder(Bundle object) {
        this.mBundle = object;
    }

    public View build(@NonNull ViewGroup parent, int viewType, Object item, @NonNull Mapper mapper) {
        Class viewClass = mapper.viewClassFromViewType(viewType);
        if (viewClass == null) {
            throw new IllegalArgumentException("viewType not present in the mapper");
        } else {
            try {
                Constructor e = Reflections.constructor(viewClass, new Class[]{Context.class, Bundle.class});
                return (ViewGroup) e.newInstance(new Object[]{parent.getContext(), mBundle});
            } catch (Exception var7) {
                throw new RuntimeException("Something went wrong creating the views. Please review your BindableLayout implementation.", var7);
            }
        }
    }

    public Class<? extends BindableLayout> viewType(@NonNull Object item, int position, @NonNull Mapper mapper) {
        List classes = mapper.get(item.getClass());
        if (classes == null) {
            throw new IllegalArgumentException("Object class " + item.getClass() + "not found in mapper");
        } else if (classes.size() == 1) {
            return (Class) classes.get(0);
        } else if (classes.size() > 1) {
            throw new RuntimeException("There are more than 1 view classes associated to the same object class. Please write a custom BindableLayoutBuilder for this case, and ensure allowsMultimapping returns true.");
        } else {
            throw new RuntimeException("There are no view classes associated to the object class.");
        }
    }

    public boolean allowsMultimapping() {
        return false;
    }
}
