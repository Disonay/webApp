package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import utils.CopyPrintWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class AppFilter implements Filter {
    private FilterConfig filterConfig;
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
        final CopyPrintWriter writer = new CopyPrintWriter(response.getWriter());
        filterRequests(request);
        filterChain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
            @Override public PrintWriter getWriter() {
                return writer;
            }
        });
        logger.info(writer.getCopy());
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
