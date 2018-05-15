package com.nantian.utils;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;

public class UploadProgressListener implements ProgressListener {
     private HttpServletRequest request;
     private DecimalFormat df = new DecimalFormat("#00.0");
     public UploadProgressListener(HttpServletRequest request){
         this.request = request;
     }

     @Override
     public void update(long bytesRead, long bytesTotal, int items) {
         double percent= (double)bytesRead*100/(double)bytesTotal;
         System.out.println(df.format(percent));
         request.getSession().setAttribute("UPLOAD_PERCENTAGE", df.format(percent));
     }
 }
