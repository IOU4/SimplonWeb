package simplonclone.Controllers;

import java.util.LinkedHashMap;

import simplonclone.App;
import simplonclone.Models.AdminModel;
import simplonclone.Models.InstructorModel;
import simplonclone.Models.StudentModel;

public class Admin extends User {
  private LinkedHashMap<String, MenuHandler> menu;

  @FunctionalInterface
  private interface MenuHandler {
    public void run();
  }

  public Admin(String name, String email, int id) {
    super(name, email, id);
  }

  public Admin() {
    super();
  }

  public Admin(Admin admin) {
    super(admin.getName(), admin.getEmail(), admin.getId());
  }

  private void fillMenu() {
    menu = new LinkedHashMap<>();
    menu.put("add instructor", this::addInstructor);
    menu.put("add promo", this::addPromo);
    menu.put("assign instructor to promo", this::assignInstructorToPromo);
    menu.put("list instructors", this::listInstructors);
    menu.put("list promos", this::listPromos);
    menu.put("list students", this::liststudents);
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

  private void addInstructor() {
    System.out.printf("Instructor name: ");
    String name = App.scanner.nextLine();
    System.out.printf("Instructor email: ");
    String email = App.scanner.nextLine();
    if (InstructorModel.addInstructor(new Instructor(name, email, 0)))
      System.out.println("added instructor '" + name + "' successfully!");
    else
      System.out.println("failed to add instructor '" + name + "'!");
  }

  private void addPromo() {
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

  private void assignInstructorToPromo() {
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
