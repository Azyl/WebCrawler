/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author user
 */

public class WorkerThread implements Runnable {
    
     
    private String command;
     
    public WorkerThread(String s){
        this.command=s;
    }
 
    
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    
void create_file (Object doc,String fpath)
    {
        Path logfile = FileSystems.getDefault().getPath(fpath);
        //fileW=fpath;
        String s = (String) doc;
        byte data[] = s.getBytes();
        try (OutputStream out = new BufferedOutputStream(
                 Files.newOutputStream(logfile,CREATE))) {
           
            out.write(data, 0, data.length);
            System.out.println("Log file created");
            
        } catch (IOException x) {
            System.err.println(x);
        }
    }
    
    
    
    
    
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        try {
            processCommand();
        } catch (IOException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+" End.");
    }
 
    private void processCommand() throws IOException {
        try {
            //Thread.sleep(5000);
            
        String url = this.command;
        //String url = "http://www.vimalkumarpatel.blogspot.com/";
        
        print("Checking for saved versions of the url:");
        
        
        URL location = Crawler.class.getProtectionDomain().getCodeSource().getLocation();
        String fileName = url.substring( url.lastIndexOf('/')+1, url.length() );
        File tempFile = new File(location.getFile()+fileName);
        String tempFile_name=location.getFile()+fileName;
        System.out.println(tempFile);
        
        
        if (tempFile.exists() == true) {
           System.out.println("File exists, url already fetched nothing to do");
        }
        else {
            System.out.println("File does not exists");
            print("Fetching %s...", tempFile_name);
        
                
        //File input = new File("/tmp/input.html");
        //Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        //create_file(doc.html(),tempFile_name);
        
        Elements breadcrumbs = doc.select("div#crumbs > a");
        
       //String links[]= 
        
        for (Element href : breadcrumbs){
            String LinkName = href.attr("abs:href").substring( href.attr("abs:href").lastIndexOf('/')+1, href.attr("abs:href").length() );
            print("%s - %s", href.text(),fileName);
                }
            }
            
            Thread.sleep(1000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public String toString(){
        return this.command;
    }
}