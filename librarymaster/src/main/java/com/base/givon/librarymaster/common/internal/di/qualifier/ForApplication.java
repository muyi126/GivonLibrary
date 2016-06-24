package com.base.givon.librarymaster.common.internal.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/20 下午11:38 - Guzhu
 * @email:muyi126@163.com
 */
@Qualifier
@Documented
@Retention(RUNTIME)
//在Android中，我们会需要不同类型的context，
// 所以我们就可以定义 qualifier注解“@ForApplication”和“@ForActivity”，
// 这样当注入一个context的时候，我们就可以告诉 Dagger我们想要哪种类型的context
public @interface ForApplication {
}
