package com.inet.cloud.service.horae.statistic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver

/**
 * Application
 *
 * @author Dzung Nguyen
 * @version $Id Application.groovy 2014-07-02 16:26:30z nguyen_dv $
 * @since 1.0
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
class Application extends SpringBootServletInitializer {
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class)
  }

  /**
   * The main entry point that used to boot the application.
   *
   * @param args the configuration parameters. However, currently we does not
   *        accept any parameters from console.
   */
  static void main(String[] args) {
    new SpringApplication(Application.class).run(args)
  }

  @Bean
  CharacterEncodingFilter characterEncodingFilter() {
    final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter()
    characterEncodingFilter.encoding = 'UTF-8'
    characterEncodingFilter.forceEncoding = true
    characterEncodingFilter
  }

  @Bean
  LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver()
    localeResolver.defaultLocale = new Locale("vi", "VN")
    localeResolver
  }

  @Bean
  LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor()
    localeChangeInterceptor.paramName = 'lang'
    localeChangeInterceptor
  }
}