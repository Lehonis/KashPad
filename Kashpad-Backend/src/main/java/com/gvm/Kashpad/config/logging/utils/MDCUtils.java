/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.gvm.Kashpad.config.logging.utils;

import lombok.Getter;
import lombok.NonNull;

/**
 *
 * @author Gonçalo Monteiro
 */
public enum MDCUtils {
    URI_KEY("uri"),
    HTTP_METHOD_KEY("httpMethod"),
    REQUEST_START_TIME_KEY("requestStartTime"),
    CORRELATION_ID_KEY("correlationID");
    
    @Getter
    private final String key;
    
    private MDCUtils(final @NonNull String key) {
        this.key = key;
    }
}
