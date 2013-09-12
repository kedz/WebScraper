package edu.columbia.cs.article.classifier;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MetaXmlWriter {

    private static DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm-ss");

    /* XML File creation infrastructure */
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;


    public MetaXmlWriter() throws ParserConfigurationException {

        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();

    }


    public void writeNewspaperMetasToXml(File xmlFile, NewspaperMetaSet newspaperMetaSet)
            throws TransformerConfigurationException, TransformerException {

        Document document = documentBuilder.newDocument();

        Element root = document.createElement("Newspaper-Meta");
        Attr timestamp = document.createAttribute("date-modified");
        timestamp.setValue(DateTime.now().toString(fmt));

        root.setAttributeNode(timestamp);
        document.appendChild(root);

        for(NewspaperMeta newspaperMeta : newspaperMetaSet.getNewspaperMetas()) {
            Element newspaperMetaElement = makeNewspaperMetaElement(newspaperMeta,document);
            root.appendChild(newspaperMetaElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(document);
        StreamResult result =  new StreamResult(xmlFile);
        transformer.transform(source, result);


    }

    private Element makeNewspaperMetaElement(NewspaperMeta newspaperMeta, Document document) {
        Element newspaperElement = document.createElement("Newspaper");

        Element titleElement = document.createElement("Publication-title");
        titleElement.setTextContent(newspaperMeta.getPublicationTitle());
        newspaperElement.appendChild(titleElement);

        Element urlElement = document.createElement("Publication-url");
        urlElement.setTextContent(newspaperMeta.getPublicationUrl().toString());
        newspaperElement.appendChild(urlElement);

        Element sitesLabeledElement = document.createElement("Sites-labeled");
        sitesLabeledElement.setTextContent(Integer.toString(newspaperMeta.getSitesLabeled()));
        newspaperElement.appendChild(sitesLabeledElement);

        Element waitTimeElement = document.createElement("Wait-time");
        waitTimeElement.setTextContent(Long.toString(newspaperMeta.getWaitTime()));
        newspaperElement.appendChild(waitTimeElement);

        return newspaperElement;

    }
}
