/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gvm.Kashpad.config.logging;

import com.gvm.Kashpad.config.logging.utils.MDCUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author Gonçalo Monteiro
 */
@Component
@Slf4j
public class RequestLoggingInterceptor implements HandlerInterceptor {
    private static final String HEADER_CORRELATION_PROP = "X-Correlation-ID";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String correlationID;
        
        if ((correlationID = request.getHeader(HEADER_CORRELATION_PROP)) == null
                || correlationID.isEmpty()
                || correlationID.isBlank()) {
            correlationID = UUID.randomUUID().toString();
        }

        MDC.put(MDCUtils.CORRELATION_ID_KEY.getKey(), correlationID);
        response.setHeader(HEADER_CORRELATION_PROP, correlationID);
        MDC.put(MDCUtils.REQUEST_START_TIME_KEY.getKey(), String.valueOf(System.currentTimeMillis()));
        log.info("Received {} request at URI {}",
                request.getMethod(),
                request.getRequestURI(),
                MDC.get(MDCUtils.CORRELATION_ID_KEY.getKey()));

        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception execption) throws Exception {
        log.info("Completed {} request at URI {} - Completion Time (in ms): {}",
                request.getMethod(),
                request.getRequestURI(),
                System.currentTimeMillis() - Long.parseLong(MDC.get(MDCUtils.REQUEST_START_TIME_KEY.getKey())));
    }
}
