package simplonclone.Models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import simplonclone.Controllers.Admin;
import simplonclone.App;
import simplonclone.Controllers.User;

public class UserModel {

  private Connection con = App.getDatabase().con;

  public ArrayList<Admin> getAdmins() {
    var admins = new ArrayList<Admin>();
    try {
      var rs = con.createStatement().executeQuery("select name, email, id from administrators;");
      while (rs.next()) {
        admins.add(new Admin(rs.getString("name"), rs.getString("email"), rs.getInt("id")));
      }
    } catch (SQLException ex) {
      System.out.println("couldn't get admins");
      ex.printStackTrace();
    }
    return admins;
  }

  public static User find(String email) {
    var admin = AdminModel.find(email);
    if (admin != null)
      return admin;
    var student = StudentModel.find(email);
    if (student != null)
      return student;
    var instrucor = InstructorModel.find(email);
    if (instrucor != null)
      return instrucor;
    return null;
  }

}
