package com.ytd.user.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.ytd.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangjungis@126.com on 2016/7/24.
 */
@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  EbeanServer server;

  public List<User> getContent() {
//    EbeanServer server1 =  Ebean.getServer("db1");
//    logger.debug("server1 -> {}", server1.getName());

    return server.find(User.class).findList();
  }
}
