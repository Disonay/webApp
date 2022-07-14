package listeners;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Date;
import java.util.logging.Logger;

public class AppSessionListener implements HttpSessionListener {
    private static final Logger logger = Logger.getLogger(AppSessionListener.class.getName());

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        logger.info("Session created: " + new Date() + " " + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        logger.info("Session closed: " + new Date() + " " + session.getId());
    }
}
