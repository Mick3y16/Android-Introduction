package com.example.topleaguev2;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by pedro on 16/08/2016.
 */
public class XMLParser {

    /**
     * Gets XML data from a given URL.
     *
     * @param stringURL URL.
     * @return XML data.
     */
    public String getXMLFromURL(String stringURL) {
        try {
            URL url = new URL(stringURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            return convertStreamToString(httpURLConnection.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

    /**
     * Returns the DOM of the XML data.
     *
     * @param xml XML data in string format.
     * @return DOM.
     */
    public Document getDomElement(String xml) {
        Document document = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xml));
            document = documentBuilder.parse(inputSource);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Log.e("Error: ", ex.getMessage());
        }

        return document;
    }

    /**
     * Extracts the value of an element's element using a tag name.
     *
     * @param element Element containing the other element.
     * @param tagName Tag name.
     * @return Extracted element's value.
     */
    public String getValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);

        return this.getElementValue(nodeList.item(0));
    }

    /**
     * Extracts the element value of a given node.
     *
     * @param node Node containing the value.
     * @return Element's value.
     */
    private final String getElementValue(Node node) {
        Node childNode;

        if (node != null && node.hasChildNodes()) {
            for (childNode = node.getFirstChild(); childNode != null; childNode = node.getNextSibling()) {
                if (childNode.getNodeType() == Node.TEXT_NODE) {
                    return childNode.getNodeValue();
                }
            }
        }

        return "";
    }

    /**
     * Converts an input stream to string format.
     *
     * @param inputStream Stream to be converted.
     * @return Converted input stream.
     * @throws IOException
     */
    private String convertStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];

            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }

            return writer.toString();
        } else {
            return "";
        }
    }

}
