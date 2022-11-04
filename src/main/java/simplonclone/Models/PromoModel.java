package simplonclone.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import simplonclone.App;

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
}
