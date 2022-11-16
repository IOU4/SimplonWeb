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

@WebFilter(urlPatterns = { "/admin", "/student" })
public class Auth implements Filter {
  public Auth() {
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    var hreq = (HttpServletRequest) req;
    var hres = (HttpServletResponse) res;
    if (hreq.getSession().getAttribute("name") == null)
      hres.sendRedirect("login");
    else
      chain.doFilter(req, res);
  }
}
