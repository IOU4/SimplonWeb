package simplonweb.Controllers;

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

  // public static ArrayList<Promo> getAll() {
  // var promos = new ArrayList<Promo>();
  // promos = PromoModel.getPromosWithNoInstructor();
  // promos.addAll(PromoModel.getPromosWithIntructor());
  // return promos;
  // }

}
