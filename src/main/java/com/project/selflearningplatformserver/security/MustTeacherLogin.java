package com.project.selflearningplatformserver.security;

import java.lang.annotation.*;

/**
 * 必须是教师登录
 *
 * @author itning
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MustLogin(role = MustLogin.ROLE.TEACHER)
public @interface MustTeacherLogin {
}
