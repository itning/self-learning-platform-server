package com.project.selflearningplatformserver.log;

import java.lang.annotation.*;

/**
 * @author itning
 * @date 2020/5/1 16:18
 * @see LogAspect
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 方法作用
     *
     * @return 方法作用
     */
    String value();
}
