package simplonweb.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "promos")
public class PromoEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;

  private String name;

  @OneToOne(optional = true)
  @JoinColumn(name = "instructor_id", nullable = true, updatable = true)
  private InstructorEntity instructor;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PromoEntity other = (PromoEntity) obj;
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
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

  public InstructorEntity getInstructor() {
    return instructor;
  }

  public void setInstructor(InstructorEntity instructor) {
    this.instructor = instructor;
  }
}
