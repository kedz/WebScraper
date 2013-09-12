package edu.columbia.cs.article.classifier;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Settings {

    private static Settings SETTINGS;

    private Map<String, File> fileMap = new HashMap<String, File>();

    Properties articleClassifierProperties = new Properties();

    private Settings() {



        try {
            articleClassifierProperties.load(Settings.class.getClassLoader().getResourceAsStream("article.classifier.properties"));
        } catch( IOException ioe ) {
            ioe.printStackTrace();
            System.exit(-1);
        }


        for(String property : articleClassifierProperties.stringPropertyNames()) {
            if (property.endsWith(".file") || property.endsWith(".dir")) {
                fileMap.put(property, new File(articleClassifierProperties.getProperty(property)));

            }
        }

    }

    public static Settings getInstance() {
        if ( SETTINGS == null )
            SETTINGS = new Settings();
        return SETTINGS;
    }


    public File getFile(String property) {
        return fileMap.get(property);
    }

    public int getInt(String property) {
        return Integer.parseInt(articleClassifierProperties.getProperty(property));
    }

}
