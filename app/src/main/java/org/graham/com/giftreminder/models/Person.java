package org.graham.com.giftreminder.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Person extends RealmObject {
  public String name;
  public Date bithday;
  public int age;
  public RealmList<Gift> gifts;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBithday() {
    return bithday;
  }

  public void setBithday(Date bithday) {
    this.bithday = bithday;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public RealmList<Gift> getGifts() {
    return gifts;
  }

  public void setGifts(RealmList<Gift> gifts) {
    this.gifts = gifts;
  }
}
