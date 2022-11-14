package simplonweb.Controllers;

import java.sql.Date;
import java.util.ArrayList;

import simplonweb.Models.BriefModel;

public class Brief {
  int id;
  String title;
  String content;
  Date launchDate;
  Date deadline;
  Promo promo;

  public Promo getPromo() {
    return promo;
  }

  public void setPromo(Promo promo) {
    this.promo = promo;
  }

  public Brief(int id, String title, String content, Date launchDate, Date deadline) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.launchDate = launchDate;
    this.deadline = deadline;
  }

  public Brief() {
    this(0, "", "", new Date(0), new Date(0));
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getLaunchDate() {
    return launchDate;
  }

  public void setLaunchDate(Date launchDate) {
    this.launchDate = launchDate;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  public static ArrayList<Brief> getAll() {
    var briefs = new ArrayList<Brief>();
    briefs.addAll(BriefModel.getAllBriefs());
    return briefs;
  }
}
