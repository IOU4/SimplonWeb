package simplonweb.Controllers;

import java.sql.Date;
import java.util.LinkedHashMap;

import simplonweb.App;
import simplonweb.Models.BriefModel;
import simplonweb.Models.StudentModel;

public class Instructor extends User {
  private LinkedHashMap<String, MenuHandler> menu;

  public Instructor(String name, String email, String psswd, int id) {
    super(name, email, psswd, id);
  }

  public Instructor(Instructor instructor) {
    super(instructor.getName(), instructor.getEmail(), instructor.getPsswd(), instructor.getId());
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
    System.out.printf("Student password: ");
    String psswd = App.scanner.nextLine();
    if (StudentModel.addStudent(new Student(name, email, psswd, 0)))
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
  }

  private void listBriefs() {
    System.out.println("Briefs:");
    BriefModel.getAllBriefs().forEach(brief -> {
      System.out.println(brief.getId() + "- " + brief.getTitle());
    });
  }

  // send email to students using jakarta mail
}
