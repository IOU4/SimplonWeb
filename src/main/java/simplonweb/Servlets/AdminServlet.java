package simplonweb.Servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simplonweb.Controllers.Brief;
import simplonweb.Controllers.Instructor;
import simplonweb.Controllers.Promo;
import simplonweb.Controllers.Student;

@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("students", Student.getAll());
    req.setAttribute("instructors", Instructor.getAll());
    req.setAttribute("promos", Promo.getAll());
    req.setAttribute("briefs", Brief.getAll());
    req.setAttribute("username", req.getSession().getAttribute("name"));
    req.getRequestDispatcher("pages/admin.jsp").forward(req, res);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String action = req.getParameter("action");
    switch (action) {
      case "addStudent":
        Student.add(req.getParameter("name"), req.getParameter("email"), req.getParameter("psswd"));
        break;
      case "addInstructor":
        Instructor.add(req.getParameter("name"), req.getParameter("email"),
            req.getParameter("psswd"));
        break;
      case "addPromo":
        Promo.add(req.getParameter("name"));
        break;
      case "deleteStudent":
        Student.delete(req.getParameter("id"));
        break;
      case "deleteInstructor":
        Instructor.delete(req.getParameter("id"));
        break;
      // case "deletePromo":
      // Promo.delete(req.getParameter("id"));
      // break;
      // case "updateStudent":
      // Student.update(req.getParameter("id"), req.getParameter("name"),
      // req.getParameter("email"), req.getParameter("promo"));
      // break;
      // case "updateInstructor":
      // Instructor.update(req.getParameter("id"), req.getParameter("name"),
      // req.getParameter("email"), req.getParameter("promo"));
      // break;
      // case "updatePromo":
      // Promo.update(req.getParameter("id"), req.getParameter("name"),
      // req.getParameter("instructor"));
      // break;
    }
    res.sendRedirect("admin");
  }
}
