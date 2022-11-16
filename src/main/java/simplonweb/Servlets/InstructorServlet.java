package simplonweb.Servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simplonweb.Controllers.Brief;
import simplonweb.Controllers.Promo;
import simplonweb.Controllers.Student;

@WebServlet(name = "instructor", value = "/instructor")
public class InstructorServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    // var session = req.getSession();
    req.setAttribute("students", Student.getAll());
    req.setAttribute("promos", Promo.getAll());
    req.setAttribute("briefs", Brief.getAll());
    req.setAttribute("username", req.getSession().getAttribute("name"));
    req.setAttribute("role", "instructor");
    req.getRequestDispatcher("pages/instructor.jsp").forward(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String action = req.getParameter("action");
    switch (action) {
      case "addStudentToPromo":
        Student.addStudentToPromo(req.getParameter("studentId"), req.getParameter("promoId"));
        break;
      case "assignBrief":
        Brief.assignBrief(req.getParameter("briefId"), req.getParameter("promoId"));
        break;
      default:
        break;
    }
    res.sendRedirect("instructor");
  }
}
