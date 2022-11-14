package simplonweb.Controllers;

import java.sql.Date;
import java.util.ArrayList;

import simplonweb.App;
import simplonweb.Models.BriefModel;
import simplonweb.Models.InstructorModel;
import simplonweb.Models.StudentModel;

public class Instructor extends User {

  private Promo currentPromo;

  public Promo getCurrentPromo() {
    return currentPromo;
  }

  public void setCurrentPromo(Promo currentPromo) {
    this.currentPromo = currentPromo;
  }

  public Instructor(String name, String email, String psswd, int id) {
    super(name, email, psswd, id);
  }

  public Instructor(Instructor instructor) {
    super(instructor.getName(), instructor.getEmail(), instructor.getPsswd(), instructor.getId());
  }

  public Instructor(String name, String email, String psswd, Promo currentPromo, int id) {
    super(name, email, psswd, id);
    this.currentPromo = currentPromo;
  }

  public void addStudent() {
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

  public void addStudentToPromo() {
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

  public void addBrief() {
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

  public void assignBriefToPromo() {
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

  public void listBriefs() {
    System.out.println("Briefs:");
    BriefModel.getAllBriefs().forEach(brief -> {
      System.out.println(brief.getId() + "- " + brief.getTitle());
    });
  }

  public static ArrayList<Instructor> getAll() {
    var instructors = new ArrayList<Instructor>();
    instructors = InstructorModel.getInstructorsWithNoPromo();
    instructors.addAll(InstructorModel.getInstructorsWithPromo());
    return instructors;
  }
}
