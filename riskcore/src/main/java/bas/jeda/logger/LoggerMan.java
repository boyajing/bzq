/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

/**
 *
 * @author menghui
 */
public class LoggerMan {

    public String[] getLogfiles(Logger log) {
        if (log != null) {

            Appender ap = log.getAppender(log.getName() + "_APPENDER");
            if (FileAppender.class.isInstance(ap)) {
                FileAppender df = (FileAppender) ap;
                File f = new File(df.getFile());
                String path = f.getAbsolutePath();
                path = path.substring(0, path.lastIndexOf(File.separator));
                f = new File(path);
                String[] fs = f.list();
                return fs;
            } else {
                return null;
            }
        }
        return null;
    }

    public String getLogContent(Logger log, String file) {
        if (log != null) {
            Appender ap = log.getAppender(log.getName() + "_APPENDER");
            if (DailyRollingFileAppender.class.isInstance(ap)) {
                DailyRollingFileAppender df = (DailyRollingFileAppender) ap;
                File f = new File(df.getFile());
                String path = f.getAbsolutePath();
                path = path.substring(0, path.lastIndexOf(File.separator)) + File.separator + file;
                f = new File(path);
                if (f.exists()) {
                    StringBuilder sb=new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String t = null;

                        while ((t = br.readLine()) != null) {
                            sb.append(t).append("\n");
                        }
                    } catch (FileNotFoundException ex) {
                    } catch (IOException ex) {
                    }
                    return sb.toString();
                }

            }
        }
        return null;
    }
}
