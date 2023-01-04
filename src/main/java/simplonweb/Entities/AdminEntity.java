package simplonweb.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "administrators")
public class AdminEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Basic
  @Column(name = "name")
  private String name;

  @Basic
  @Column(name = "email")
  private String email;

  @Basic
  @Column(name = "psswd")
  private String psswd;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getPsswd() {
    return psswd;
  }

  public void setPsswd(String psswd) {
    this.psswd = psswd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    AdminEntity admin = (AdminEntity) o;

    if (id != admin.id)
      return false;
    if (name != null ? !name.equals(admin.name) : admin.name != null)
      return false;
    if (email != null ? !email.equals(admin.email) : admin.email != null)
      return false;
    if (psswd != null ? !psswd.equals(admin.psswd) : admin.psswd != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (psswd != null ? psswd.hashCode() : 0);
    return result;
  }
}
