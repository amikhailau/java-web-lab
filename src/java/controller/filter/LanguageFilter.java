package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "languageFilter", urlPatterns = { "/newWeb_6/*" })
public class LanguageFilter implements Filter {

    public LanguageFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("utf8");
        if (req.getSession().getAttribute("userLocale") == null) {
            req.getSession().setAttribute("userLocale", req.getLocale());
        }
        String lang = request.getParameter("lang");
        if (lang != null) {
            Locale locale = new Locale(lang);
            req.getSession().setAttribute("userLocale", locale);
        }
        chain.doFilter(request, response);
    }
}
