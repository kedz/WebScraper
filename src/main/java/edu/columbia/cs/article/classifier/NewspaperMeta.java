package edu.columbia.cs.article.classifier;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewspaperMeta {


    private final String publicationTitle;
    private final URL publicationUrl;

    private final long waitTime;
    private int sitesLabeled;

    public static class Builder {
        // Required parameters
        private final String publicationTitle;
        private final URL publicationUrl;

        //optional
        private long waitTime = 0;
        private int sitesLabeled = 0;

        public Builder(String publicationTitle, URL publicationUrl) {
            this.publicationTitle = publicationTitle;
            this.publicationUrl = publicationUrl;
        }

        public Builder waitTime(long waitTime)
        { this.waitTime = waitTime;      return this; }

        public Builder sitesLabeled(int sitesLabeled)
        { this.sitesLabeled = sitesLabeled;      return this; }

        public NewspaperMeta build() {
            return new NewspaperMeta(this);
        }
    }

    private NewspaperMeta(Builder builder) {
        publicationTitle    = builder.publicationTitle;
        publicationUrl      = builder.publicationUrl;
        waitTime            = builder.waitTime;
        sitesLabeled        = builder.sitesLabeled;
    }


    public String getPublicationTitle() { return  publicationTitle; }
    public URL getPublicationUrl() { return publicationUrl; }
    public int getSitesLabeled() { return sitesLabeled; }
    public long getWaitTime() { return waitTime; }
    public void incrementSitesLabeled() { sitesLabeled++; }

}
