package simplonweb.Filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/student", "/instructor" })
public class Auth implements Filter {
  public Auth() {
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    var hreq = (HttpServletRequest) req;
    var hres = (HttpServletResponse) res;
    // get url path
    var path = hreq.getRequestURI();
    var role = hreq.getSession().getAttribute("role");
    // compare to corresponding servlet
    if (role == null)
      hres.sendRedirect("login");
    switch (path) {
      case "/admin":
        if (role == "admin")
          chain.doFilter(req, res);
        break;
      case "/student":
        if (role == "student")
          chain.doFilter(req, res);
        break;
      case "/instructor":
        if (role == "instructor")
          chain.doFilter(req, res);
        break;
      default:
        hres.sendRedirect("login");
        break;
    }
  }
}
