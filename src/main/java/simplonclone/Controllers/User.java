package simplonclone.Controllers;

public abstract class User {
  private String name;
  private String email;
  private int id;

  public User(String name, String email, int id) {
    this.email = email;
    this.name = name;
    this.id = id;
  }

  public User() {
    this("", "", 0);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public static User find(String email) {
    return new Admin("test", "test", 1);
  }
}
