package com.ytd.user.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yangjungis@126.com on 2016/7/24.
 */
@Component
public class EbeanFactoryBean implements FactoryBean<EbeanServer>, EnvironmentAware {

  private static final Logger logger = LoggerFactory.getLogger(EbeanFactoryBean.class);

  @Autowired
  private CurrentUser currentUser;

  private Properties properties = new Properties();

  @Override
  public EbeanServer getObject() throws Exception {
    for (Object key : properties.keySet()) {
      logger.debug("{} -> {}", key, properties.get(key));
    }
    ServerConfig config = new ServerConfig();
    config.setName("db");
    config.setCurrentUserProvider(currentUser);
    config.loadFromProperties(properties);
    config.setDefaultServer(true);
    config.setRegister(true);

    EbeanServer ebeanServer = EbeanServerFactory.create(config);
    Ebean.register(ebeanServer, true);

//    config = new ServerConfig();
//    config.setName("db1");
//    config.setCurrentUserProvider(currentUser);
//    config.loadFromProperties(properties);
//
//    EbeanServer server1 = EbeanServerFactory.create(config);
//    Ebean.register(server1, false);
//
    return ebeanServer;
  }

  @Override
  public Class<?> getObjectType() {
    return EbeanServer.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public void setEnvironment(Environment environment) {
    loadProperties((AbstractEnvironment) environment);
  }

  private void loadProperties(AbstractEnvironment environment) {

    MutablePropertySources propertySources = environment.getPropertySources();

    // reverse the order of the property sources
    List<MapPropertySource> props = new ArrayList<>();
    for (PropertySource propertySource :propertySources) {
      if (propertySource instanceof MapPropertySource) {
        props.add(0, (MapPropertySource) propertySource);
      }
    }
    // merge them into the single Properties
    for (MapPropertySource propertySource : props) {
      properties.putAll(propertySource.getSource());
    }
  }

}
