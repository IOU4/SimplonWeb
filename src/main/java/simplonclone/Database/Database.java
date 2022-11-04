package simplonclone.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  public Connection con;

  public Database(String username, String passwd, String database) {
    try {
      var url = "jdbc:postgresql://" + System.getenv("DB_HOST") + ":5432/" + database;
      this.con = DriverManager.getConnection(url, username, passwd);
    } catch (SQLException e) {
      e.printStackTrace();
      close_db();
      System.exit(1);
    }
  }

  public Database(String username, String passwd) {
    try {
      var url = "jdbc:postgresql://db:5432/simplon";
      this.con = DriverManager.getConnection(url, username, passwd);
    } catch (SQLException e) {
      System.err.println("couldn't connect to database.");
      e.printStackTrace();
      close_db();
    }
  }

  public void close_db() {
    try {
      if (this.con != null)
        this.con.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
