package simplonweb.Controllers;

import java.util.ArrayList;
import java.util.List;

import simplonweb.Entities.StudentEntity;
import simplonweb.Models.StudentModel;

public class Student extends User {
  private Promo promo;

  public Student(String name, String email, String psswd, int id) {
    super(name, email, psswd, id);
  }

  public Student(Student student) {
    super(student.getName(), student.getEmail(), student.getPsswd(), student.getId());
  }

  public static List<StudentEntity> getAll() {
    // var students = new ArrayList<Student>();
    // students = StudentModel.getStudentsWithNoInstructor();
    // students.addAll(StudentModel.getStudentsWithNoPromo());
    // students.addAll(StudentModel.getStudentsWithIntructor());
    // return students;
    return StudentModel.getAllStudents();
  }

  public Promo getPromo() {
    return promo;
  }

  public void setPromo(Promo promo) {
    this.promo = promo;
  }

  public static void add(String name, String email, String psswd) {
    StudentModel.add(new Student(name, email, psswd, -1));
  }

  public static void delete(String studentId) {
    StudentModel.delete(Integer.parseInt(studentId));
  }

  public static ArrayList<Student> getStudentsByInstructor(int instructorId) {
    return StudentModel.getStudentsByInstructor(instructorId);
  }

  public static void addStudentToPromo(String studentId, String promoId) {
    StudentModel.addStudentToPromo(Integer.parseInt(studentId), Integer.parseInt(promoId));
  }
}
