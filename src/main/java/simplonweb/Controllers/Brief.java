package simplonweb.Controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import simplonweb.Models.BriefModel;
import simplonweb.Models.StudentModel;

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

  public static ArrayList<Brief> getBriefsWithNoPromo() {
    var briefs = BriefModel.getAllBriefs();
    briefs.removeIf(brief -> brief.getPromo() != null);
    return briefs;
  }

  public static void add(String title, String content, String launchDate, String deadline) {
    BriefModel.addBrief(new Brief(0, title, content, Date.valueOf(launchDate), Date.valueOf(deadline)));
  }

  public static void assignBrief(String briefId, String promoId) {
    BriefModel.assignBriefToPromo(Integer.parseInt(briefId), Integer.parseInt(promoId));
    if (sendEmail(StudentModel.getStudentsByPromo(Integer.parseInt(promoId))))
      System.out.println("emails sent.");
    else
      System.out.println("failed to send emails.");
  }

  private static boolean sendEmail(ArrayList<Student> students) {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "465");
    props.put("mail.smtp.ssl.enable", "true");
    props.put("mail.smtp.auth", "true");
    Session session = Session.getInstance(props, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(System.getenv("FROM_EMAIL"), System.getenv("EMAIL_PASSWORD"));
      }
    });
    try {
      var msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(System.getenv("FROM_EMAIL")));
      ArrayList<InternetAddress> to = new ArrayList<>();
      students.forEach(student -> {
        try {
          to.add(new InternetAddress(student.getEmail()));
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      msg.setRecipients(MimeMessage.RecipientType.TO, to.toArray(new InternetAddress[students.size()]));
      msg.setSubject("new brief");
      msg.setSentDate(new java.util.Date());
      msg.setText("check your platform there is a new brief");
      Transport.send(msg);
      return true;
    } catch (MessagingException e) {
      System.out.println("err : " + e.getMessage());
      System.out.println("username: " + System.getenv("FROM_EMAIL"));
      System.out.println("psswd: " + System.getenv("EMAIL_PASSWORD"));
      e.printStackTrace();
    }
    return false;
  }
}
