package simplonweb.Controllers;

import java.util.ArrayList;
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
