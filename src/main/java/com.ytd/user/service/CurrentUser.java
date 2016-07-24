package com.ytd.user.service;

import com.avaje.ebean.config.CurrentUserProvider;
import org.springframework.stereotype.Component;

/**
 * Created by yangjungis@126.com on 2016/7/24.
 */

@Component
public class CurrentUser implements CurrentUserProvider {

  @Override
  public Object currentUser() {
    return "test";
  }
}
