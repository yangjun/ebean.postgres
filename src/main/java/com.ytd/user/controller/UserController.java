package com.ytd.user.controller;

import com.ytd.user.domain.User;
import com.ytd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yangjungis@126.com on 2016/7/24.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public List<User> index() {
    return userService.getContent();
  }
}
