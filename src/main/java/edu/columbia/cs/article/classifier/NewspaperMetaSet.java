package edu.columbia.cs.article.classifier;

import edu.columbia.cs.article.classifier.NewspaperMeta;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewspaperMetaSet {

    private List<NewspaperMeta> newspaperMetas = new ArrayList<NewspaperMeta>();


    public NewspaperMetaSet() {

    }

    public void addNewspaperMeta(NewspaperMeta newspaperMeta) {newspaperMetas.add(newspaperMeta);}
    public List<NewspaperMeta> getNewspaperMetas() {return newspaperMetas;}
    public int size() { return newspaperMetas.size(); }
    public void add(NewspaperMeta newspaperMeta) { newspaperMetas.add(newspaperMeta); }
}
