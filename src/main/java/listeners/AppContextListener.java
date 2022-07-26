package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.util.Date;
import java.util.logging.Logger;

@WebListener
public class AppContextListener implements ServletContextListener{

    private static final Logger logger = Logger.getLogger(AppContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("App start: " + new Date());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("App stop: " + new Date());
    }

}
