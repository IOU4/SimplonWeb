package simplonweb.Servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = { "/", "/home" })
public class Index extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    var role = req.getSession().getAttribute("role");
    if (role == null)
      res.sendRedirect("login");
    else {
      switch (role.toString()) {
        case "admin":
          res.sendRedirect("admin");
          break;
        case "instructor":
          res.sendRedirect("instructor");
          break;
        case "student":
          res.sendRedirect("student");
          break;
        default:
          res.sendRedirect("login");
          break;
      }
    }
  }
}
