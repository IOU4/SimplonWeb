package simplonweb.Controllers;

import simplonweb.App;
import simplonweb.Models.AdminModel;
import simplonweb.Models.InstructorModel;
import simplonweb.Models.StudentModel;

public class Admin extends User {
  public Admin(String name, String email, String psswd, int id) {
    super(name, email, psswd, id);
  }

  public Admin() {
    super();
  }

  public Admin(Admin admin) {
    super(admin.getName(), admin.getEmail(), admin.getPsswd(), admin.getId());
  }

  public void addInstructor() {
    System.out.printf("Instructor name: ");
    String name = App.scanner.nextLine();
    System.out.printf("Instructor email: ");
    String email = App.scanner.nextLine();
    System.out.printf("Instructor password: ");
    String psswd = App.scanner.nextLine();
    if (InstructorModel.add(new Instructor(name, email, psswd, 0)))
      System.out.println("added instructor '" + name + "' successfully!");
    else
      System.out.println("failed to add instructor '" + name + "'!");
  }

  public void addPromo() {
    System.out.printf("promo name :  ");
    String name = App.scanner.nextLine();
    if (AdminModel.addPromo(name))
      System.out.println("added promo '" + name + "' successfully!");
    else
      System.out.println("failed to add promo '" + name + "'!");
  }

  public void listInstructors() {
    System.out.println("Instructors:");
    InstructorModel.getAllInstructors().forEach(instructor -> {
      System.out.println(instructor.getId() + "- " + instructor.getName());
    });
  }

  public void listPromos() {
    System.out.println("Promos:");
    AdminModel.getAllPromos().forEach(System.out::println);
  }

  public void liststudents() {
    System.out.println("Students:");
    StudentModel.getAllStudents().forEach(student -> {
      System.out.println(student.getId() + "- " + student.getName());
    });
  }

  public void assignInstructorToPromo() {
    listInstructors();
    System.out.printf("choose an instructor: ");
    int instructorId = App.scanner.nextInt();
    App.scanner.nextLine();
    listPromos();
    System.out.printf("choose a promo: ");
    int promoId = App.scanner.nextInt();
    App.scanner.nextLine();
    if (AdminModel.assignInstructorToPromo(promoId, instructorId))
      System.out.println("assigned instructor to promo successfully!");
    else
      System.out.println("failed to assign instructor to promo!");
  }
}
