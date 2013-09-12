package edu.columbia.cs.article.classifier;

import org.openqa.selenium.WebElement;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/6/13
 * Time: 3:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkTuple {

    private int depth;
    private URL url;



    public LinkTuple(URL url, int depth) {
        this.depth = depth;
        this.url = url;
    }

    public int getDepth() { return depth; }
    public URL getUrl() { return url; }

}
