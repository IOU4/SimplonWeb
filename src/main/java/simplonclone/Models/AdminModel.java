package simplonclone.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import simplonclone.Controllers.Admin;
import simplonclone.App;

public class AdminModel {
  private static Connection con = App.getConnection();

  public static Admin find(String email) {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from administrators where email = ?");
      stmnt.setString(1, email);
      var rs = stmnt.executeQuery();
      if (rs.next())
        return new Admin(rs.getString("name"), rs.getString("email"), rs.getInt("id"));
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  // add new promo
  public static boolean addPromo(String name) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into promos (name) values (?)");
      stmnt.setString(1, name);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  // get all promos
  public static ArrayList<String> getAllPromos() {
    ArrayList<String> promos = new ArrayList<>();
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from promos");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        promos.add(rs.getInt("id") + "- " + rs.getString("name"));
      }
      return promos;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static boolean assignInstructorToPromo(int promoId, int instructorId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("update promos set instructor_id = ? where id = ?");
      stmnt.setInt(1, instructorId);
      stmnt.setInt(2, promoId);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

}
