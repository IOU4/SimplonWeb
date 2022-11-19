package simplonweb.Controllers;

public class Admin extends User {
  public Admin(String name, String email, String psswd, int id) {
    super(name, email, psswd, id);
  }

  public Admin() {
    super();
  }

  public Admin(Admin admin) {
    super(admin.getName(), admin.getEmail(), admin.getPsswd(), admin.getId());
  }
}
