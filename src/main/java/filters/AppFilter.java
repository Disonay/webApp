package filters;

import utils.CopyPrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebFilter("/*")
public class AppFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AppFilter.class.getName());

    private void filterRequests(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String sessionId = httpRequest.getSession().getId();
        String url = httpRequest.getRequestURI();

        logger.info("Request session id: " + sessionId);
        logger.info("Request url: " + url);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        response.setCharacterEncoding("UTF8");
        final CopyPrintWriter writer = new CopyPrintWriter(response.getWriter());
        filterRequests(request);
        filterChain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
            @Override public PrintWriter getWriter() {
                return writer;
            }
        });
        logger.info(writer.getCopy());
    }
}
