package edu.columbia.cs.article.classifier;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewspaperMetaSetFactory {

    private static NewspaperMetaSetFactory AM_FACTORY;
    private NewspaperMetaSetFactory() {}

    public static NewspaperMetaSetFactory getInstance() {
        if (AM_FACTORY == null)
            AM_FACTORY = new NewspaperMetaSetFactory();
        return AM_FACTORY;
    }

    public NewspaperMetaSet makeArticleMetaSetFromFile(File metaXml) {

        NewspaperMetaSet newspaperMetaSet = new NewspaperMetaSet();
        if (metaXml.exists()) {




        }


        return newspaperMetaSet;
    }


}
