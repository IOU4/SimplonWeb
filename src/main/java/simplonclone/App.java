package simplonclone;

import java.sql.Connection;
import java.util.Scanner;

import simplonclone.Controllers.Admin;
import simplonclone.Controllers.Instructor;
import simplonclone.Controllers.Student;
import simplonclone.Database.Database;
import simplonclone.Models.UserModel;

public class App {
  public static Scanner scanner = new Scanner(System.in);
  private static Database db = new Database(System.getenv("DB_USER"), System.getenv("DB_PSSWD"),
      System.getenv("DB_NAME"));

  public static Connection getConnection() {
    if (db == null)
      db = new Database(System.getenv("DB_USER"), System.getenv("DB_PSSWD"), System.getenv("DB_NAME"));
    return db.con;
  }

  public static Database getDatabase() {
    return db;
  }

  public void run() {
    System.out.println("welcome to simplon.ma 🎉");
    while (true) {
      var email = readEmail();
      // var email = readEmail();
      System.out.println("loggin you in... ");
      var user = UserModel.find(email);
      if (user instanceof Admin) {
        new Admin((Admin) user).handler();
        break;
      } else if (user instanceof Student) {
        new Student((Student) user).handler();
        break;
      } else if (user instanceof Instructor) {
        new Instructor((Instructor) user).handler();
        break;
      } else
        System.out.println("user not found!");
    }
    this.closeAll();
  }

  private String readEmail() {
    System.out.print("email: ");
    String email = scanner.nextLine();
    return email;
  }

  private void closeAll() {
    if (scanner != null)
      scanner.close();
  }
}
