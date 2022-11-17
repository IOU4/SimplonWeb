package simplonweb.Entities;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "briefs", schema = "simplonweb", catalog = "")
public class BriefEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;
  @Basic
  @Column(name = "title")
  private String title;
  @Basic
  @Column(name = "content")
  private String content;
  @Basic
  @Column(name = "launchDate")
  private Date launchDate;
  @Basic
  @Column(name = "deadline")
  private Date deadline;
  @JoinColumn(name = "promoId", referencedColumnName = "id")
  private PromoEntity currentPromo;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    BriefEntity brief = (BriefEntity) o;

    if (id != brief.id)
      return false;
    if (title != null ? !title.equals(brief.title) : brief.title != null)
      return false;
    if (content != null ? !content.equals(brief.content) : brief.content != null)
      return false;
    if (launchDate != null ? !launchDate.equals(brief.launchDate) : brief.launchDate != null)
      return false;
    if (deadline != null ? !deadline.equals(brief.deadline) : brief.deadline != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (content != null ? content.hashCode() : 0);
    result = 31 * result + (launchDate != null ? launchDate.hashCode() : 0);
    result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
    return result;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getLaunchDate() {
    return launchDate;
  }

  public void setLaunchDate(Date launchDate) {
    this.launchDate = launchDate;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  public PromoEntity getCurrentPromo() {
    return currentPromo;
  }

  public void setCurrentPromo(PromoEntity currentPromo) {
    this.currentPromo = currentPromo;
  }

}
