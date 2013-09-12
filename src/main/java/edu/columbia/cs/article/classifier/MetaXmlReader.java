package edu.columbia.cs.article.classifier;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MetaXmlReader {

    /* XML File creation infrastructure */
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;


    public MetaXmlReader() throws ParserConfigurationException {

        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();

    }


    public NewspaperMetaSet readNewspaperMetasToXml(File xmlFile)
            throws SAXException, IOException {

        NewspaperMetaSet newspaperMetaSet = new NewspaperMetaSet();

        Document document = documentBuilder.parse(xmlFile);
        NodeList newspapers = document.getElementsByTagName("Newspaper");
        for(int i = 0; i < newspapers.getLength(); i++) {

            Element newspaperElement = (Element) newspapers.item(i);
            NewspaperMeta newspaperMeta = makeNewspaperMetaFromElement(newspaperElement);
            newspaperMetaSet.addNewspaperMeta(newspaperMeta);

        }

        return newspaperMetaSet;

    }

    private NewspaperMeta makeNewspaperMetaFromElement(Element newspaperElement) {

        String publicationTitle = newspaperElement.getElementsByTagName("Publication-title").item(0).getTextContent();
        String publicationUrlString = newspaperElement.getElementsByTagName("Publication-url").item(0).getTextContent();

        URL publicationUrl = null;
        try {
            publicationUrl = new URL(publicationUrlString);
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
            System.exit(-1);
        }

        int sitesLabeled = Integer.parseInt(newspaperElement.getElementsByTagName("Sites-labeled").item(0).getTextContent());
        long waitTime = Long.parseLong(newspaperElement.getElementsByTagName("Wait-time").item(0).getTextContent());

        NewspaperMeta newspaperMeta = new NewspaperMeta.Builder(publicationTitle,publicationUrl)
                .waitTime(waitTime)
                .sitesLabeled(sitesLabeled)
                .build();



        return newspaperMeta;


    }


}
