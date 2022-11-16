package simplonweb.Controllers;

import java.sql.Date;
import java.util.ArrayList;
import simplonweb.App;
import simplonweb.Models.BriefModel;
import simplonweb.Models.InstructorModel;

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

  public static void add(String name, String email, String psswd) {
    InstructorModel.add(new Instructor(name, email, psswd, 0));
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

  public void assignBriefToPromo(String briefId, String promoId) {
    BriefModel.assignBriefToPromo(Integer.parseInt(briefId), Integer.parseInt(promoId));
  }

  public static ArrayList<Instructor> getAll() {
    var instructors = new ArrayList<Instructor>();
    instructors = InstructorModel.getInstructorsWithNoPromo();
    instructors.addAll(InstructorModel.getInstructorsWithPromo());
    return instructors;
  }

  public static void delete(String id) {
    InstructorModel.delete(Integer.parseInt(id));
  }

  public static void assign(String instructorId, String promoId) {
    InstructorModel.assign(Integer.parseInt(instructorId), Integer.parseInt(promoId));
  }

}
