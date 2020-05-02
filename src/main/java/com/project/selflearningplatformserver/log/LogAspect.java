package com.project.selflearningplatformserver.log;

import com.project.selflearningplatformserver.dto.LoginUser;
import com.project.selflearningplatformserver.mapper.LogMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author itning
 * @date 2020/5/1 16:17
 * @see Log
 */
@Aspect
@Component
public class LogAspect {
    private final LogMapper logMapper;

    @Autowired
    public LogAspect(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Around("@annotation(log)")
    public Object advice(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        final Object[] methodArgs = joinPoint.getArgs();
        Optional<LoginUser> loginUserOptional = Arrays.stream(methodArgs)
                .filter(o -> o instanceof LoginUser)
                .map(o -> (LoginUser) o)
                .findAny();

        String result = null;
        Throwable th = null;
        Object proceed;
        try {
            proceed = joinPoint.proceed();
            if (proceed instanceof ResponseEntity) {
                ResponseEntity<?> responseEntity = (ResponseEntity<?>) proceed;
                result = log(responseEntity);
            }
        } catch (Throwable throwable) {
            th = throwable;
            throw throwable;
        } finally {
            log(loginUserOptional.orElse(null), log.value(), methodArgs, th, result);
        }
        return proceed;
    }

    private void log(@Nullable LoginUser loginUser,
                     @NonNull String methodEffect,
                     @Nullable Object[] methodArgs,
                     @Nullable Throwable th,
                     @Nullable String result) {
        if (Objects.isNull(loginUser)) {
            return;
        }
        StringBuilder sb = new StringBuilder()
                .append("╔═══════════════════════════════════")
                .append("\n")
                .append("║")
                .append(loginUser.getName())
                .append("(ID:")
                .append(loginUser.getId())
                .append(")")
                .append("\t")
                .append(methodEffect)
                .append("\t");
        if (Objects.nonNull(methodArgs)) {
            sb.append("\n").append("║").append("调用参数：");
            Arrays.stream(methodArgs)
                    .filter(o -> !(o instanceof LoginUser))
                    .forEach(itemArg -> sb.append("[").append(itemArg.toString()).append("]"));
        }
        sb.append("\n");
        if (Objects.nonNull(th)) {
            sb.append("║").append("捕获异常：").append(th.getMessage());
        }
        if (StringUtils.isNotBlank(result)) {
            sb.append("║").append("返回参数：").append(result);
        }
        sb.append("\n").append("╚═══════════════════════════════════");
        persistence(sb.toString(), loginUser.getId());
    }

    private String log(@NonNull ResponseEntity<?> responseEntity) {
        return "状态码：" +
                responseEntity.getStatusCode().value() +
                " " +
                "响应体：" +
                responseEntity.getBody();
    }

    private void persistence(@NonNull String content, @NonNull String userId) {
        Date date = new Date();
        com.project.selflearningplatformserver.entity.Log log = new com.project.selflearningplatformserver.entity.Log();
        log.setId(UUID.randomUUID().toString());
        log.setContent(content);
        log.setUserId(userId);
        log.setGmtCreate(date);
        log.setGmtModified(date);
        try {
            logMapper.insert(log);
        } catch (Exception e) {
            // ignore
        }
    }
}
