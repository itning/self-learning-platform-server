package com.project.selflearningplatformserver.security;

import java.lang.annotation.*;

/**
 * 必须是学生登录
 *
 * @author itning
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MustLogin(role = MustLogin.ROLE.STUDENT)
public @interface MustStudentLogin {
}
