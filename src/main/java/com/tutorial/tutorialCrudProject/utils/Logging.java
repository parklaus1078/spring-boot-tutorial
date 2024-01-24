package com.tutorial.tutorialCrudProject.utils;


import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logging {
        Level level() default Level.NONE;

        enum Level {
            NONE, INFO, DEBUG, ERROR, WARN, TRACE
        }
}
