package boost.auth;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: BoostBrain
 * Date: 20.08.17
 * Time: 20:42
 */

@WebFilter(urlPatterns = "/secured/*")
public class AuthFilter implements Filter{

    @EJB
    private AuthenticateBean authenticateBean;

    @Inject
    private PersonBean personBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String resource = request.getRequestURI();

        if(!personBean.isAuthenticated()){
            personBean.setInitialRequestURI(resource);
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }

        if(!authenticateBean.isGranted(personBean.getLogin(), resource)){
            response.sendRedirect("redirect to access denied");
            return;
        }

        if(StringUtils.isNotEmpty(personBean.getInitialRequestURI())){
            response.sendRedirect(personBean.getInitialRequestURI());
            personBean.setInitialRequestURI("");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
