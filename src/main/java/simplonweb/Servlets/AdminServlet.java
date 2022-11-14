package simplonweb.Servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simplonweb.Controllers.Instructor;
import simplonweb.Controllers.Promo;
import simplonweb.Controllers.Student;

@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("students", Student.getAll());
    req.setAttribute("instructors", Instructor.getAll());
    req.setAttribute("promos", Promo.getAll());
    req.getRequestDispatcher("pages/admin.jsp").forward(req, res);
  }
}
