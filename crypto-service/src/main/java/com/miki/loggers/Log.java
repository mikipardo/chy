package com.miki.loggers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.miki.constants.Const;
import com.miki.properties.Property;


public class Log {

    private static Logger LOG;
    
    public static Logger getLogger(Class<?> name) {
    	Property proper = new Property();
    
          try {
        	  LOG = Logger.getLogger(name);
            
            String logfile = proper.getProperty(Const.LOG_URL_FILE);
            Date fecha = new Date();

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fechaAc = formato.format(fecha);//hemos congifurado la misma traza que en properties
            
            PatternLayout defaultLayout = new PatternLayout(proper.getProperty(Const.DEFAULT_LAYOUT));
            
            RollingFileAppender rollingFileAppender = new RollingFileAppender();
            rollingFileAppender.setFile(logfile + fechaAc + ".log", true, false, 0);
            rollingFileAppender.setMaxFileSize("10MB");
            rollingFileAppender.setMaxBackupIndex(5);
            rollingFileAppender.setLayout(defaultLayout);
     
            LOG.removeAllAppenders();
            LOG.addAppender(rollingFileAppender);
            LOG.setAdditivity(true);

        } catch (IOException ex) {
     
            java.util.logging.Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LOG;
    }
}
