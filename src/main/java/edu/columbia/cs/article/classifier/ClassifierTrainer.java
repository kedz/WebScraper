package edu.columbia.cs.article.classifier;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassifierTrainer {

    private NewspaperMetaSet newspaperMetaSet;
    private Settings settings = Settings.getInstance();


    public ClassifierTrainer() {

        Settings settings = Settings.getInstance();
        NewspaperMetaSetFactory newspaperMetaSetFactory = NewspaperMetaSetFactory.getInstance();

        File metaXml = settings.getFile("newspaper.meta.file");
        if (metaXml.exists()) {
            try {
                MetaXmlReader reader = new MetaXmlReader();
                newspaperMetaSet = reader.readNewspaperMetasToXml(metaXml);
            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
                System.exit(-1);
            } catch (SAXException saxe) {
                saxe.printStackTrace();
                System.exit(-1);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(-1);
            }

        }

    }

    public void labelNewspaper(NewspaperMeta newspaperMeta) {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));

        WebDriver driver = new ChromeDriver(capabilities);
        driver.get(newspaperMeta.getPublicationUrl().toString());

        System.out.print("Waiting...");
        try {
            Thread.sleep(newspaperMeta.getWaitTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\tfinished!");

        Set<URL> visitedUrls = new HashSet<URL>();
        List<LinkTuple> linkTuples = processLinks(driver, settings.getInt("crawl.depth"), newspaperMeta.getPublicationUrl().getHost(),visitedUrls);

        labelLinks(driver, linkTuples, settings.getInt("crawl.depth"),newspaperMeta,visitedUrls);






    }

    private void labelLinks(WebDriver driver, List<LinkTuple> linkTuples, int crawlDepth, NewspaperMeta newspaperMeta, Set<URL> visitedUrls) {

        Random random = new Random(System.currentTimeMillis());
        JavaScriptHelper jsHelper = JavaScriptHelper.getInstance();
        JavascriptExecutor js = (JavascriptExecutor) driver;


        while(linkTuples.size()>0) {


            int index = random.nextInt(linkTuples.size());
            LinkTuple linkTuple = linkTuples.remove(index);

            driver.get(linkTuple.getUrl().toString());
            js.executeScript(jsHelper.getjQueryScript());
            js.executeScript(jsHelper.getHighlightTextScript());
            js.executeScript(jsHelper.getMenuScript());
            js.executeScript(
                    "jQuery('#article-labeler-menu').append('<span>Sites Labeled: "+newspaperMeta.getSitesLabeled() + " Links Remaining:"+linkTuples.size() +"</span>');");

            while(!driver.findElement(By.cssSelector("#article-labeler-mode")).getText().equals("next")) {

            }

            newspaperMeta.incrementSitesLabeled();

            linkTuples.addAll(processLinks(driver, crawlDepth--, newspaperMeta.getPublicationUrl().getHost(), visitedUrls));
            saveHtml(driver,newspaperMeta);


            if (driver.findElement(By.cssSelector("#close-article-labeler")).getText().endsWith("close"))
                break;

        }


        driver.quit();


    }

    private List<LinkTuple> processLinks(WebDriver driver, int depth, String host, Set<URL> visitedUrls) {

        LinkedList<LinkTuple> tuples = new LinkedList<LinkTuple>();

        if (depth>0) {

            List<WebElement> webElements = driver.findElements(By.xpath("//a"));


            for(WebElement webElement : webElements) {


                try {
                    URL linkUrl = new URL(webElement.getAttribute("href"));
                    if (host.equals(linkUrl.getHost())) {
                        tuples.add(new LinkTuple(linkUrl,depth));
                        visitedUrls.add(linkUrl);
                    } else {
                        System.out.println("URL rejected: "+webElement.getAttribute("href"));
                    }

                } catch (MalformedURLException mue) {
                    System.out.println("Malformed URL: "+webElement.getAttribute("href"));

                }
            }
        }

        return tuples;

    }

    private void saveHtml(WebDriver driver, NewspaperMeta newspaperMeta) {

        String pageHtml = driver.getPageSource();
        pageHtml = pageHtml.replaceAll("(?s)<script.*?</script>","");

        String fileName = settings.getFile("labeled.html.dir")+
                File.separator+
                newspaperMeta.getPublicationUrl().getHost()+
                "."+newspaperMeta.getSitesLabeled()+".html";
        File htmlFile = new File(fileName);

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(htmlFile), "UTF-8"));

            out.write(pageHtml);
            out.flush();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(-1);
        }

    }

    public NewspaperMetaSet getNewspaperMetaSet() {return newspaperMetaSet;}


    public void writeMetaXml()
            throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        Settings settings = Settings.getInstance();
        File metaFile = settings.getFile("newspaper.meta.file");
        if (!metaFile.getParentFile().exists())
            metaFile.getParentFile().mkdirs();


        MetaXmlWriter xmlWriter = new MetaXmlWriter();
        xmlWriter.writeNewspaperMetasToXml(metaFile,getNewspaperMetaSet());



    }

}
