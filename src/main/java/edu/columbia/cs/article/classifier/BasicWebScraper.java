package edu.columbia.cs.article.classifier;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/4/13
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasicWebScraper {



    public static void main(String[] args) throws Exception {


        JavaScriptHelper jsHelper = JavaScriptHelper.getInstance();

        //System.getProperties().setProperty("webdriver.chrome.driver", "/home/chris/tools/www-html/chromedriver");
        //SeleniumServer server = new SeleniumServer();
       // server.start();


        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));

        WebDriver driver = new ChromeDriver(capabilities);
        //EventFiringWebDriver efwd = new EventFiringWebDriver(driver);


        driver.get("http://www.nytimes.com/2013/07/06/world/middleeast/egypt.html?pagewanted=all0");




        //driver.get("http://www.challengesales.com");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(jsHelper.getjQueryScript());
        //if (!hasJQuery) {
        /*
            js.executeScript(
                    "(function(){\n" +
                    "  var newscript = document.createElement('script');\n" +
                    "     newscript.type = 'text/javascript';\n" +
                    "     newscript.async = true;\n" +
                    "     newscript.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js';\n" +
                    "  (document.getElementsByTagName('head')[0]||document.getElementsByTagName('body')[0]).appendChild(newscript);\n" +
                    "})();");


        //}
        */

        boolean hasJQuery = (Boolean) js.executeScript(   "" +
                "if (typeof jQuery == 'undefined') {\n" +
                "   return false;    // jQuery is loaded  \n" +
                "} else {\n" +
                "   return true;    // jQuery is not loaded\n" +
                "}");


        /*
        boolean hasJQuery = (Boolean) js.executeScript(   "if (jQuery) {" +
                "return true;" +
                "else {" +
                "return false;" +
                "}" );

        */
                System.out.println("jQuery: "+hasJQuery);

        js.executeScript("jQuery('p').mouseenter(function(){ " +
                "this.oldColor = jQuery(this).css('background-color');" +
                "jQuery(this).css({'background-color': 'yellow'});" +
                "}).mouseleave(function(){ " +
                "jQuery(this).css({'background-color': this.oldColor});" +
                "}).click(function() {" +
                "jQuery(this).addClass('user-identified-content');" +
                "} );     ");



        Thread.sleep(5000);
        js.executeScript("var div = jQuery('<div />').appendTo('body');\n"
                +"jQuery(div).html('This is a test div');\n"
                +"jQuery(div).css({" +
                "'position': 'absolute', " +
                "'top': '0px', " +
                "'display': 'block', " +
                "'background-color': 'green', " +
                "'width': '100%'," +
                "'height': '100px'," +
                "'z-index': '999999'" +
                "" +
                "" +
                "" +
                " });"
              );

        Thread.sleep(10000);

        String pageHtml = driver.getPageSource();
        pageHtml = pageHtml.replaceAll("(?s)<script.*?</script>","");

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File("/home/chris/Desktop/test.html")), "UTF-8"));

        out.write(pageHtml);
        out.flush();
        out.close();
        //List<WebElement> links = driver.findElements(By.xpath("//a"));
        //for(WebElement link : links) {

          //  System.out.println(link.getText() + " " + link.getAttribute("href"));

        //}



        driver.close();
        driver.quit();






    }

}
