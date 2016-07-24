package com.ytd.user.domain;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import com.avaje.ebean.annotation.WhoCreated;
import com.avaje.ebean.annotation.WhoModified;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Created by yangjungis@126.com on 2016/7/24.
 */
@MappedSuperclass
public class BaseModel extends Model {
  @Id
  private
  Long id;

  @Version
  private
  Long version;

  @WhenCreated
  @Column(name="whenCreated")
  private
  DateTime whenCreated;

  @WhenModified
  @Column(name="whenModified")
  private
  DateTime whenModified;

  @WhoCreated
  @Column(name="whoCreated")
  private
  String whoCreated;

  @WhoModified
  @Column(name="whoModified")
  private
  String whoModified;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public DateTime getWhenCreated() {
    return whenCreated;
  }

  public void setWhenCreated(DateTime whenCreated) {
    this.whenCreated = whenCreated;
  }

  public DateTime getWhenModified() {
    return whenModified;
  }

  public void setWhenModified(DateTime whenModified) {
    this.whenModified = whenModified;
  }

  public String getWhoCreated() {
    return whoCreated;
  }

  public void setWhoCreated(String whoCreated) {
    this.whoCreated = whoCreated;
  }

  public String getWhoModified() {
    return whoModified;
  }

  public void setWhoModified(String whoModified) {
    this.whoModified = whoModified;
  }
}
