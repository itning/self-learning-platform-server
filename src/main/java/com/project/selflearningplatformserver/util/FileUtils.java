package com.project.selflearningplatformserver.util;

import com.project.selflearningplatformserver.exception.SecurityServerException;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * 文件工具类
 *
 * @author itning
 */
public final class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final String RANGE_SEPARATOR = "-";
    private static final String RANGE_CONTAINS = "bytes=";
    private static final int RANGE_BYTES_ALL = 2;
    private static final int RANGE_BYTES_ONE = 1;

    /**
     * 断点续传
     *
     * @param file        所需要下载的文件
     * @param contentType MIME类型
     * @param range       请求头
     * @param response    {@link HttpServletResponse}
     */
    public static void breakpointResume(File file, String contentType, String range, HttpServletResponse response) {
        long startByte = 0;
        long endByte = file.length() - 1;
        if (range != null && range.contains(RANGE_CONTAINS) && range.contains(RANGE_SEPARATOR)) {
            range = range.substring(range.lastIndexOf("=") + 1).trim();
            String[] ranges = range.split(RANGE_SEPARATOR);
            try {
                //判断range的类型
                if (ranges.length == RANGE_BYTES_ONE) {
                    if (range.startsWith(RANGE_SEPARATOR)) {
                        //类型一：bytes=-2343
                        endByte = Long.parseLong(ranges[0]);
                    } else if (range.endsWith(RANGE_SEPARATOR)) {
                        //类型二：bytes=2343-
                        startByte = Long.parseLong(ranges[0]);
                    }
                } else if (ranges.length == RANGE_BYTES_ALL) {
                    //类型三：bytes=22-2343
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }

            } catch (NumberFormatException e) {
                startByte = 0;
                endByte = file.length() - 1;
            }
        }
        long contentLength = endByte - startByte + 1;
        response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
        if (range == null) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + startByte + "-" + endByte + "/" + file.length());
        }
        response.setContentType(contentType);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + new String(file.getName().getBytes(), StandardCharsets.ISO_8859_1));
        response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength));
        long transmitted = 0;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
             BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startByte);
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
            }
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }
            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();
            logger.debug("下载完毕：{}-{}: {}", startByte, endByte, transmitted);
        } catch (ClientAbortException e) {
            logger.debug("用户停止下载：{}-{}: {}", startByte, endByte, transmitted);
        } catch (IOException e) {
            throw new SecurityServerException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
