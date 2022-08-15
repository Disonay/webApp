package listeners;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

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
