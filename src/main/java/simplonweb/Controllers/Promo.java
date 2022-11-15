package simplonweb.Controllers;

import java.util.ArrayList;

import simplonweb.Models.InstructorModel;
import simplonweb.Models.PromoModel;

public class Promo {
  private int id;
  private String name;
  private Instructor instructor;
  private int studentsCount;

  public int getStudentsCount() {
    return studentsCount;
  }

  public void setStudentsCount(int studentsCount) {
    this.studentsCount = studentsCount;
  }

  public Promo(int id, String name, Instructor instructor) {
    this.id = id;
    this.name = name;
    this.instructor = instructor;
  }

  public Promo(int id, String name) {
    this.name = name;
    this.id = id;
  };

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Instructor getInstructor() {
    return instructor;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  public static ArrayList<Promo> getAll() {
    var promos = new ArrayList<Promo>();
    InstructorModel.getInstructorsWithPromo().forEach(instructor -> {
      var curr = instructor.getCurrentPromo();
      curr.setStudentsCount(PromoModel.getStudentsCount(curr.getId()));
      promos.add(instructor.getCurrentPromo());
    });
    PromoModel.getPromosWithNoIntructor().forEach(promo -> {
      promo.setStudentsCount(promo.getId());
      promos.add(promo);
    });
    return promos;
  }
}
