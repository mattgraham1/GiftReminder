package org.graham.com.giftreminder.models;

import java.util.Date;

import io.realm.RealmObject;

public class Gift extends RealmObject {
  public String name;
  public float cost;
  public String occasion;
  public String link;
  public Date datePurchased;
  public boolean giftedToMe;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }

  public String getOccasion() {
    return occasion;
  }

  public void setOccasion(String occasion) {
    this.occasion = occasion;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Date getDatePurchased() {
    return datePurchased;
  }

  public void setDatePurchased(Date datePurchased) {
    this.datePurchased = datePurchased;
  }

  public boolean isGiftedToMe() {
    return giftedToMe;
  }

  public void setGiftedToMe(boolean giftedToMe) {
    this.giftedToMe = giftedToMe;
  }
}
