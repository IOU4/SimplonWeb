package simplonweb.Models;

import simplonweb.Controllers.Promo;
import simplonweb.Controllers.Student;
import simplonweb.Entities.StudentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import simplonweb.App;
import simplonweb.Config.Config;

public class StudentModel {
  private static Connection con = App.getConnection();

  public static Student find(String email) {
    try {
      Connection con = App.getConnection();
      PreparedStatement stmnt = con.prepareStatement("select * from students where email = ?");
      stmnt.setString(1, email);
      var rs = stmnt.executeQuery();
      if (rs.next())
        return new Student(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id"));
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  // get all students using hibernate
  public static List<StudentEntity> getAllStudents() {
    try {
      EntityManager em = Config.getInstance().getEm();
      em.getTransaction().begin();
      TypedQuery<StudentEntity> query = em.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class);
      List<StudentEntity> students = query.getResultList();
      em.getTransaction().commit();
      return students;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // get students with no promo
  public static ArrayList<Student> getStudentsWithNoPromo() {
    try {
      ArrayList<Student> students = new ArrayList<Student>();
      PreparedStatement stmnt = con
          .prepareStatement("select * from students where promo_id is null");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        students.add(new Student(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id")));
      }
      return students;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  // assign student to promo
  public static boolean addStudentToPromo(int studentId, int promoId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("update students set promo_id = ? where id = ?");
      stmnt.setInt(1, promoId);
      stmnt.setInt(2, studentId);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // add new student
  public static boolean add(Student student) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into students (name, email, psswd) values (?, ?,?)");
      stmnt.setString(1, student.getName());
      stmnt.setString(2, student.getEmail());
      stmnt.setString(3, student.getPsswd());
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // remove student
  public static boolean delete(int id) {
    try {
      PreparedStatement stmnt = con.prepareStatement("delete from students where id = ?");
      stmnt.setInt(1, id);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // update student
  public static boolean updateStudent(Student student) {
    try {
      PreparedStatement stmnt = con.prepareStatement("update students set name = ?, email = ? where id = ?");
      stmnt.setString(1, student.getName());
      stmnt.setString(2, student.getEmail());
      stmnt.setInt(3, student.getId());
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // get students by promo
  public static ArrayList<Student> getStudentsByPromo(int promoId) {
    try {
      ArrayList<Student> students = new ArrayList<Student>();
      PreparedStatement stmnt = con.prepareStatement("select * from students where promo_id = ?");
      stmnt.setInt(1, promoId);
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        students.add(new Student(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id")));
      }
      return students;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ArrayList<Student> getStudentsWithNoInstructor() {
    try {
      ArrayList<Student> students = new ArrayList<Student>();
      PreparedStatement stmnt = con.prepareStatement(
          "select students.*, promos.name as promo_name from students join promos on students.promo_id = promos.id where promos.instructor_id is null");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        var promo = new Promo(rs.getInt("promo_id"), rs.getString("promo_name"));
        var student = new Student(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id"));
        student.setPromo(promo);
        students.add(student);
      }
      return students;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ArrayList<Student> getStudentsWithIntructor() {
    try {
      ArrayList<Student> students = new ArrayList<Student>();
      PreparedStatement stmnt = con.prepareStatement(
          "select students.*, promos.name as promo_name, promos.instructor_id as instructor_id from students join promos on students.promo_id = promos.id where promos.instructor_id is not null");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        var instructor = InstructorModel.getInstructorById(rs.getInt("instructor_id"));
        var promo = new Promo(rs.getInt("promo_id"), rs.getString("promo_name"), instructor);
        var student = new Student(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id"));
        student.setPromo(promo);
        students.add(student);
      }
      return students;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ArrayList<Student> getStudentsByInstructor(int instructoId) {
    try {
      ArrayList<Student> students = new ArrayList<Student>();
      PreparedStatement stmnt = con.prepareStatement(
          "select students.* from students where promo_id in (select instructor_id from promos where instructor_id = ?)");
      stmnt.setInt(1, instructoId);
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        var instructor = InstructorModel.getInstructorById(instructoId);
        var promo = PromoModel.getPromoById(rs.getInt("promo_id"));
        promo.setInstructor(instructor);
        var student = new Student(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id"));
        student.setPromo(promo);
        students.add(student);
      }
      return students;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
