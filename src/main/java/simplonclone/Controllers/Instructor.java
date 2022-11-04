package simplonclone.Controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import simplonclone.App;
import simplonclone.Models.BriefModel;
import simplonclone.Models.StudentModel;

public class Instructor extends User {
  private LinkedHashMap<String, MenuHandler> menu;

  public Instructor(String name, String email, int id) {
    super(name, email, id);
  }

  public Instructor(Instructor instructor) {
    super(instructor.getName(), instructor.getEmail(), instructor.getId());
  }

  @FunctionalInterface
  private interface MenuHandler {
    public void run();
  }

  private void fillMenu() {
    menu = new LinkedHashMap<>();
    menu.put("add student", this::addStudent);
    menu.put("add student to promo", this::addStudentToPromo);
    menu.put("add brief", this::addBrief);
    menu.put("assign brief to promo", this::assignBriefToPromo);
    menu.put("list Briefs", this::listBriefs);
  }

  private void printMenu() {
    int i = 0;
    System.out.println("");
    for (String key : menu.keySet()) {
      System.out.println(++i + "- " + key);
    }
    System.out.println("(anything else will quit)");
  }

  public void handler() {
    fillMenu();
    System.out.println("hello : " + this.getName());
    while (true) {
      printMenu();
      System.out.printf("choose an option: ");
      try {
        var choice = App.scanner.nextInt();
        App.scanner.nextLine();
        for (var entry : menu.entrySet()) {
          if (choice > menu.size() && choice < 1)
            return;
          if (entry.getKey().equals(menu.keySet().toArray()[choice - 1])) {
            entry.getValue().run();
            break;
          }
        }
      } catch (Exception e) {
        System.out.println("err : " + e.getMessage());
        return;
      }
    }
  }

  private void addStudent() {
    System.out.printf("Student name: ");
    String name = App.scanner.nextLine();
    System.out.printf("Student email: ");
    String email = App.scanner.nextLine();
    if (StudentModel.addStudent(new Student(name, email, 0)))
      System.out.println("added student '" + name + "' successfully!");
    else
      System.out.println("failed to add student '" + name + "'!");
  }

  private void addStudentToPromo() {
    Admin admin = new Admin();
    admin.liststudents();
    int studentId = App.scanner.nextInt();
    App.scanner.nextLine();
    admin.listPromos();
    int promoId = App.scanner.nextInt();
    App.scanner.nextLine();
    if (StudentModel.addStudentToPromo(studentId, promoId))
      System.out.println("added student to promo successfully!");
    else
      System.out.println("failed to add student to promo!");
  }

  private void addBrief() {
    System.out.println("Brief title: ");
    var title = App.scanner.nextLine();
    System.out.println("Brief Contents: ");
    var contents = App.scanner.nextLine();
    System.out.println("Brief luanch date: ");
    var launchDate = App.scanner.nextLine();
    System.out.println("Brief deadline: ");
    var deadline = App.scanner.nextLine();
    try {
      if (BriefModel.addBrief(new Brief(0, title, contents, Date.valueOf(launchDate), Date.valueOf(deadline))))
        System.out.println("added brief '" + title + "' successfully!");
      else
        System.out.println("failed to add brief '" + title + "'!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void assignBriefToPromo() {
    Admin admin = new Admin();
    listBriefs();
    System.out.printf("Brief id: ");
    var briefId = App.scanner.nextInt();
    App.scanner.nextLine();
    admin.listPromos();
    System.out.printf("Promo id: ");
    var promoId = App.scanner.nextInt();
    App.scanner.nextLine();
    if (BriefModel.assignBriefToPromo(briefId, promoId))
      System.out.println("assigned brief to promo successfully!");
    else
      System.out.println("failed to assign brief to promo!");
    ArrayList<Student> students = StudentModel.getStudentsByPromo(promoId);
    if (sendEmail(students))
      System.out.println("emails sent successfully!");
    else
      System.out.println("failed to send emails!");
  }

  private void listBriefs() {
    System.out.println("Briefs:");
    BriefModel.getAllBriefs().forEach(brief -> {
      System.out.println(brief.getId() + "- " + brief.getTitle());
    });
  }

  // send email to students using jakarta mail
  private boolean sendEmail(ArrayList<Student> students) {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "465");
    props.put("mail.smtp.ssl.enable", "true");
    props.put("mail.smtp.auth", "true");
    Session session = Session.getInstance(props, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("ouchaibimad000@gmail.com", "bjwcpghcbelphjnb");
      }
    });
    try {
      var msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("ouchaibimad000@gmail.com"));
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
    }
    return false;
  }
}
