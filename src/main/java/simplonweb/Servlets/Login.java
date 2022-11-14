package simplonweb.Servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simplonweb.Controllers.Admin;
import simplonweb.Controllers.Instructor;
import simplonweb.Controllers.LoginService;
import simplonweb.Controllers.Student;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    if (req.getSession().getAttribute("role") != null)
      res.sendRedirect("home");
    else
      req.getRequestDispatcher("pages/login.jsp").forward(req, res);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    var session = req.getSession();
    var email = req.getParameter("email");
    var psswd = req.getParameter("password");
    var user = LoginService.login(email, psswd);
    if (user != null) {
      String role = "uknown";
      if (user instanceof Student)
        role = "student";
      else if (user instanceof Admin)
        role = "admin";
      else if (user instanceof Instructor)
        role = "instructor";
      session.setAttribute("email", email);
      session.setAttribute("name", user.getName());
      session.setAttribute("role", role);
      res.sendRedirect("home");
    } else
      req.getRequestDispatcher("pages/login.jsp").forward(req, res);
  }

}
