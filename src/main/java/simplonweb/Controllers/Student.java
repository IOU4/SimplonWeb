package simplonweb.Controllers;

import java.util.ArrayList;

import simplonweb.Models.StudentModel;

public class Student extends User {
  private Promo promo;

  public Student(String email, String name, String psswd, int id) {
    super(email, name, psswd, id);
  }

  public Student(Student student) {
    super(student.getEmail(), student.getName(), student.getPsswd(), student.getId());
  }

  public Student(String email, String name, String psswd, Promo promo, int id) {
    super(email, name, psswd, id);
    this.promo = promo;
  }

  public static ArrayList<Student> getAll() {
    var students = new ArrayList<Student>();
    students = StudentModel.getStudentsWithNoInstructor();
    students.addAll(StudentModel.getStudentsWithNoPromo());
    students.addAll(StudentModel.getStudentsWithIntructor());
    return students;
  }

  public Promo getPromo() {
    return promo;
  }

  public void setPromo(Promo promo) {
    this.promo = promo;
  }
}
