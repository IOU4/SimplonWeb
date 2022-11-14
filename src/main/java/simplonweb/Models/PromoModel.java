package simplonweb.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
      if (rs.next())
        return new Promo(rs.getInt("id"), rs.getString("name"));
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
}
