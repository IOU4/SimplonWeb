package simplonweb.Models;

import simplonweb.Controllers.Instructor;
import simplonweb.Entities.InstructorEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import simplonweb.App;
import simplonweb.Config.Config;

public class InstructorModel {
  private static EntityManager em = Config.getInstance().getEm();
  private static Connection con = App.getConnection();

  public static Instructor find(String email) {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from instructors where email = ?");
      stmnt.setString(1, email);
      var rs = stmnt.executeQuery();
      if (rs.next())
        return new Instructor(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id"));
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  // get all instructors
  public static List<InstructorEntity> getAllInstructors() {
    try {
      em.getTransaction().begin();
      TypedQuery<InstructorEntity> query = em.createQuery("SELECT i FROM InstructorEntity i", InstructorEntity.class);
      List<InstructorEntity> instructors = query.getResultList();
      em.getTransaction().commit();
      return instructors;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Instructor getInstructorById(int instructor_id) {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from instructors where id = ?");
      stmnt.setInt(1, instructor_id);
      var rs = stmnt.executeQuery();
      if (rs.next())
        return new Instructor(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id"));
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  // get instructors with no promo
  public static ArrayList<Instructor> getInstructorsWithNoPromo() {
    try {
      ArrayList<Instructor> instructors = new ArrayList<Instructor>();
      PreparedStatement stmnt = con
          .prepareStatement(
              "select * from instructors where id not in (select instructor_id from promos where instructor_id is not null)");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        instructors
            .add(new Instructor(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), rs.getInt("id")));
      }
      return instructors;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ArrayList<Instructor> getInstructorsWithPromo() {
    try {
      ArrayList<Instructor> instructors = new ArrayList<Instructor>();
      PreparedStatement stmnt = con
          .prepareStatement(
              "select instructors.*, promos.id as promo_id from instructors join promos on instructors.id = promos.instructor_id where instructors.id in (select instructor_id from promos)");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        var promo = PromoModel.getPromoById(rs.getInt("promo_id"));
        instructors
            .add(new Instructor(rs.getString("name"), rs.getString("email"), rs.getString("psswd"), promo,
                rs.getInt("id")));
      }
      return instructors;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  // add new instructor
  public static boolean add(Instructor instructor) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into instructors (name, email, psswd) values (?, ?, ?)");
      stmnt.setString(1, instructor.getName());
      stmnt.setString(2, instructor.getEmail());
      stmnt.setString(3, instructor.getPsswd());
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean delete(int id) {
    try {
      PreparedStatement stmnt = con.prepareStatement("delete from instructors where id = ?");
      stmnt.setInt(1, id);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean assign(int instructorId, int promoId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("update promos set instructor_id = ? where id = ?");
      stmnt.setInt(1, instructorId);
      stmnt.setInt(2, promoId);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
