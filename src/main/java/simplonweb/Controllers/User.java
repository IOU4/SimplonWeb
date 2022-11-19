package simplonweb.Controllers;

public abstract class User {
  private String name;
  private String email;
  private int id;
  private String psswd;

  public User(String name, String email, String psswd, int id) {
    this.name = name;
    this.email = email;
    this.psswd = psswd;
    this.id = id;
  }

  public User() {
    this("", "", "", 0);
  }

  public String getPsswd() {
    return psswd;
  }

  public void setPsswd(String psswd) {
    this.psswd = psswd;
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
}
