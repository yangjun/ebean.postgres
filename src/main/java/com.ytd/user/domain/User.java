package com.ytd.user.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yangjungis@126.com on 2016/7/24.
 */
@Entity
@Table(name="users")
public class User extends BaseModel {


  @Column(name="name")
  private String name;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
