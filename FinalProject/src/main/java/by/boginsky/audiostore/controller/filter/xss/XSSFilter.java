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

    public void init(FilterConfig var1) {
        String var2 = var1.getInitParameter("apostrophe");
        if (var2 != null) {
            this.apostrophe = var2.trim();
        }
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException {
        var3.doFilter(new RequestWrapper((HttpServletRequest) var1, this.apostrophe), var2);
    }
}
