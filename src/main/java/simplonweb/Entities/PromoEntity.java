package simplonweb.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "promos", schema = "simplonweb", catalog = "")
public class PromoEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Basic
  @Column(name = "name")
  private String name;

  @JoinColumn(name = "instructor_id", referencedColumnName = "id")
  private InstructorEntity currentInstructor;

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

  public InstructorEntity getCurrentInstructor() {
    return currentInstructor;
  }

  public void setCurrentInstructor(InstructorEntity currentInstructor) {
    this.currentInstructor = currentInstructor;
  }
}
