package by.boginsky.audiostore.controller.filter.xss;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * The type Xss filter.
 */
@WebFilter(filterName = "XSSFilter", urlPatterns = {"/*"})
public class XSSFilter implements Filter {

    private String apostrophe = "&#39;";

    public void init(FilterConfig filterConfig) {
        String var = filterConfig.getInitParameter("apostrophe");
        if (var != null) {
            this.apostrophe = var.trim();
        }
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new RequestWrapper((HttpServletRequest) servletRequest, this.apostrophe), servletResponse);
    }
}
