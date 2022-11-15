package simplonweb.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import simplonweb.App;
import simplonweb.Controllers.Promo;

public class PromoModel {
  private static Connection con = App.getConnection();

  public static void getAllPromos() {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from promos");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getInt("id") + "- " + rs.getString("name"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static Promo getPromoById(int promoId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from promos where id = ?");
      stmnt.setInt(1, promoId);
      var rs = stmnt.executeQuery();
      if (rs.next()) {
        if (rs.getObject("instructor_id") != null)
          return new Promo(rs.getInt("id"), rs.getString("name"),
              InstructorModel.getInstructorById(rs.getInt("instructor_id")));
        else
          return new Promo(rs.getInt("id"), rs.getString("name"));
      }
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static ArrayList<Promo> getPromosWithNoIntructor() {
    try {
      PreparedStatement stmnt = con.prepareStatement("select * from promos where instructor_id is null");
      var rs = stmnt.executeQuery();
      var promos = new ArrayList<Promo>();
      while (rs.next()) {
        promos.add(new Promo(rs.getInt("id"), rs.getString("name")));
      }
      return promos;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static int getStudentsCount(int promoId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("select count(*) from students where promo_id = ?");
      stmnt.setInt(1, promoId);
      var rs = stmnt.executeQuery();
      if (rs.next())
        return rs.getInt(1);
      return 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public static boolean add(String name) {
    try {
      PreparedStatement stmnt = con.prepareStatement("insert into promos (name) values (?)");
      stmnt.setString(1, name);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

}
