package simplonclone.Controllers;

public class Student extends User {
  public Student(String email, String name, int id) {
    super(email, name, id);
  }

  public Student(Student student) {
    super(student.getEmail(), student.getName(), student.getId());
  }

  private void printMenu() {
    System.out.println("1- view briefs\n 2- assing student to promo\n  (anything else will quit) :");
  }

  public void handler() {
    System.out.println("hello : " + getName());
    printMenu();
  }
}
