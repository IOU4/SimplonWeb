package simplonweb.Controllers;

import simplonweb.Models.AdminModel;
import simplonweb.Models.InstructorModel;
import simplonweb.Models.StudentModel;

public class LoginService {
  public static User login(String email, String psswd) {
    var admin = AdminModel.find(email);
    if (admin != null && admin.getPsswd().equals(psswd))
      return admin;
    var student = StudentModel.find(email);
    if (student != null && student.getPsswd().equals(psswd))
      return student;
    var instrucor = InstructorModel.find(email);
    if (instrucor != null && student.getPsswd().equals(psswd))
      return instrucor;
    return null;
  }
}
