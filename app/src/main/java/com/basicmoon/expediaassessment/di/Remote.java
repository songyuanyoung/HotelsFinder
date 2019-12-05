package com.basicmoon.expediaassessment.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * A qualifier for remote data source
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Remote {
}
