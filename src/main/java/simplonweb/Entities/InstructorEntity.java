package simplonweb.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "instructors")
public class InstructorEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;

  private String name;

  private String email;

  private String psswd;

  @OneToOne(mappedBy = "instructor")
  private PromoEntity currentPromo;

  public PromoEntity getCurrentPromo() {
    return currentPromo;
  }

  public void setCurrentPromo(PromoEntity currentPromo) {
    this.currentPromo = currentPromo;
  }

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

    InstructorEntity formateur = (InstructorEntity) o;

    if (id != formateur.id)
      return false;
    if (name != null ? !name.equals(formateur.name) : formateur.name != null)
      return false;
    if (email != null ? !email.equals(formateur.email) : formateur.email != null)
      return false;
    if (psswd != null ? !psswd.equals(formateur.psswd) : formateur.psswd != null)
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
