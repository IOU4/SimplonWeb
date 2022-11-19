package simplonweb;

import java.sql.Connection;

import simplonweb.Database.Database;

public class App {
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

}
