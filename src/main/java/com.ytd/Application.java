package com.ytd;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import com.ytd.utils.JodaDateTimeDeserializer;
import com.ytd.utils.JodaDateTimeSerializer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by yangjungis@126.com on 2016/7/24.
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.ytd"})
public class Application {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(Application.class, args);

    logger.debug("Let's inspect the beans provided by Spring Boot:");

    String[] beanNames = ctx.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String beanName : beanNames) {
      logger.debug(beanName);
    }
  }

  @Bean
  public CharacterEncodingFilter characterEncodingFilter() {
    final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);
    return characterEncodingFilter;
  }

  /**
   * 注入数据源
   * @return
   * @throws IOException
   */
  @Bean
  public DataSource dataSource() throws IOException {
    InputStream is = Application.class.getClassLoader().getResourceAsStream("hikari.properties");
    Properties ps = new Properties();
    ps.load(is);
    HikariConfig config = new HikariConfig(ps);
    HikariDataSource ds = new HikariDataSource(config);
    return ds;
  }

//  @Bean
//  public Module jodaModule() {
//    JodaModule jodaModule = new JodaModule();
//    jodaModule.addSerializer(DateTime.class, new JodaDateTimeSerializer());
//    jodaModule.addDeserializer(DateTime.class, new JodaDateTimeDeserializer());
//    return jodaModule;
//  }

}
