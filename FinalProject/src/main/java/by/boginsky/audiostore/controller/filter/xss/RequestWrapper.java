package by.boginsky.audiostore.controller.filter.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

/**
 * The type Request wrapper.
 */
public final class RequestWrapper extends HttpServletRequestWrapper {

    private final String apostrophe;

    /**
     * Instantiates a new Request wrapper.
     *
     * @param servletRequest the servletRequest
     * @param var the var
     */
    public RequestWrapper(HttpServletRequest servletRequest, String var) {
        super(servletRequest);
        this.apostrophe = var;
    }

    public String getParameter(String var) {
        String var2 = super.getParameter(var);
        return var2 == null ? null : this.cleanXSS(var2);
    }

    private String cleanXSS(String var1) {
        if (var1 == null) {
            return "";
        } else {
            String var2 = var1.replaceAll("\u0000", "");
            Pattern var3 = Pattern.compile("<script>(.*?)</script>", 2);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'", 42);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", 42);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("</script>", 2);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("<script(.*?)>", 42);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("eval\\((.*?)\\)", 42);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("expression\\((.*?)\\)", 42);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("javascript:", 2);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("vbscript:", 2);
            var2 = var3.matcher(var2).replaceAll("");
            var3 = Pattern.compile("onload(.*?)=", 42);
            var2 = var3.matcher(var2).replaceAll("");
            var2 = var2.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            var2 = var2.replaceAll("'", this.apostrophe);
            var2 = var2.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            return var2;
        }
    }
}
