/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gvm.Kashpad.config.localization;

import java.time.Duration;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 *
 * @author Gonçalo Monteiro
 */
@Configuration
public class LocaleConfig {
    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        
        localeResolver.setCookieHttpOnly(true);
        localeResolver.setCookieSecure(true);
        localeResolver.setCookieMaxAge(Duration.ofHours(1));
        localeResolver.setDefaultLocale(Locale.US);
        localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("Europe/Lisbon"));
        
        return localeResolver;
    }
}
