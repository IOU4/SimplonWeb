package simplonclone.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import simplonclone.App;
import simplonclone.Controllers.Brief;

public class BriefModel {
  private static Connection con = App.getConnection();

  // get al briefs
  public static ArrayList<Brief> getAllBriefs() {
    try {
      ArrayList<Brief> briefs = new ArrayList<Brief>();
      PreparedStatement stmnt = con.prepareStatement("select * from briefs");
      var rs = stmnt.executeQuery();
      while (rs.next()) {
        briefs.add(new Brief(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getDate("launch_date"),
            rs.getDate("deadline")));
      }
      return briefs;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  // add brief
  public static boolean addBrief(Brief brief) {
    try {
      PreparedStatement stmnt = con
          .prepareStatement("insert into briefs(title,content,launch_date,deadline) values(?,?,?,?)");
      stmnt.setString(1, brief.getTitle());
      stmnt.setString(2, brief.getContent());
      stmnt.setDate(3, brief.getLaunchDate());
      stmnt.setDate(4, brief.getDeadline());
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  // assign brief to promo
  public static boolean assignBriefToPromo(int briefId, int promoId) {
    try {
      PreparedStatement stmnt = con.prepareStatement("update briefs set promo_id = ? where id = ?");
      stmnt.setInt(1, promoId);
      stmnt.setInt(2, briefId);
      stmnt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

}
