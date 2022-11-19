package simplonweb.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class StudentEntity {
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

  @OneToOne
  @JoinColumn(name = "promo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "promo_id_fk"))
  private PromoEntity promo;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    StudentEntity apprenant = (StudentEntity) o;

    if (id != apprenant.id)
      return false;
    if (name != null ? !name.equals(apprenant.name) : apprenant.name != null)
      return false;
    if (email != null ? !email.equals(apprenant.email) : apprenant.email != null)
      return false;
    if (psswd != null ? !psswd.equals(apprenant.psswd) : apprenant.psswd != null)
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

  @Override
  public String toString() {
    return "StudentEntity [id=" + id + ", name=" + name + ", email=" + email + ", psswd=" + psswd + "]";
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

  public PromoEntity getPromo() {
    return promo;
  }

  public void setPromo(PromoEntity promo) {
    this.promo = promo;
  }

}
