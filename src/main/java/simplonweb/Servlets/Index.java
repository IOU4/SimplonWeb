package simplonweb.Servlets;

import java.io.IOException;
import java.util.List;

import jakarta.persistence.spi.PersistenceProvider;
import jakarta.persistence.spi.PersistenceProviderResolver;
import jakarta.persistence.spi.PersistenceProviderResolverHolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = { "/", "/home" })
public class Index extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    PersistenceProviderResolver resolver = PersistenceProviderResolverHolder.getPersistenceProviderResolver();
    List<PersistenceProvider> providers = resolver.getPersistenceProviders();

    System.out.println("providers: " + providers.size());
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
