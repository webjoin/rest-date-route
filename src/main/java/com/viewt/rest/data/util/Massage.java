package com.viewt.rest.data.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author tangyu
 * @since 2018-01-15 22:33
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Massage {

    /**
     * (Optional) The name of the table.
     * <p> Defaults to the entity name.
     */
    String value () default "";

}