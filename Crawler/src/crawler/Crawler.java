/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

/**
 *
 * @author azyl
 */
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author azyl
 */
public class Crawler implements Runnable{

    
     void crawl(String[] args) throws IOException {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args[0];
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        // price-wrap  post-right full
        Elements num_oferta = doc.select("div[class=post-right full] > h3 > a");
        
        System.out.println(num_oferta);
        
        for (Element href : num_oferta){
            String fileName = href.attr("abs:href").substring( href.attr("abs:href").lastIndexOf('/')+1, href.attr("abs:href").length() );
            print("%s - %s", href.attr("abs:href"),fileName);
        }
        /*
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
        * */
    }
    
  void  crawler (String URL){
        try {
            URL my_url = new URL("http://www.vimalkumarpatel.blogspot.com/");
            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));
            String strTemp = "";
            while(null != (strTemp = br.readLine())){
            System.out.println(strTemp);
        }
        } catch (Exception e) {
            e.printStackTrace();
    }
    
  }
  
  
  
  void Scout_crawler (String[] args) throws IOException{
      Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args[0];
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
        create_file(doc.html(),tempFile_name);
        Elements num_oferta = doc.select("div[class=post-right full] > h3 > a");
        Elements breadcrumbs = doc.select("div#crumbs > a");
        
        System.out.println(num_oferta);
        
        for (Element href : num_oferta){
            String LinkName = href.attr("abs:href").substring( href.attr("abs:href").lastIndexOf('/')+1, href.attr("abs:href").length() );
            print("%s - %s", href.attr("abs:href"),fileName);
        }
        
        //String links[]= 
        
        for (Element href : breadcrumbs){
            String LinkName = href.attr("abs:href").substring( href.attr("abs:href").lastIndexOf('/')+1, href.attr("abs:href").length() );
            print("%s - %s", href.text(),fileName);
        }
    }
  }
  
  
  
  void Main_crawler (String[] args) throws IOException{
      Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args[0];
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
        create_file(doc.html(),tempFile_name);
        
        Elements breadcrumbs = doc.select("div#crumbs > a");
        
       //String links[]= 
        
        for (Element href : breadcrumbs){
            String LinkName = href.attr("abs:href").substring( href.attr("abs:href").lastIndexOf('/')+1, href.attr("abs:href").length() );
            print("%s - %s", href.text(),fileName);
        }
    }
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
  
  
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    
    
    
    
    
    
    
  
  
  
  
    /**
     *
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String url[]= {"http://www.oferte.ro/categorie/animale/caini"};
        Crawler Simpleproj = new Crawler();
        Simpleproj.Scout_crawler(url);
        
        /*
        String args_s[] = {"http://www.oferte.ro/categorie/animale/caini"};
        WebCrawler crler = new WebCrawler();
        crler.crawl(args_s);
                */
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
 
    
}

