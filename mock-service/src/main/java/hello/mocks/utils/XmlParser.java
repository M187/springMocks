package hello.mocks.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class XmlParser {

	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
	
	public static String documentToString(Document xmlDoc){
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(xmlDoc), new StreamResult(writer));
			return writer.getBuffer().toString().replaceAll("\n|\r", "");
		} catch (TransformerException e) {
			return ("Error while transforming xmlDoc to String");
		}
		
	}
	
	public static String getNodeValue(Document myXmlDoc, String nodeName){
		Node node = myXmlDoc.getElementsByTagName(nodeName).item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			return (eElement.getTextContent());
		}
		return "";
	}
	
	public static String getNodeValue(Document myXmlDoc, String nodeName, int index){
		Node node = myXmlDoc.getElementsByTagName(nodeName).item(index);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			return (eElement.getTextContent());
		}
		return "";
	}
	
	public static String getNodeAttributeValue(Document myXmlDoc, String nodeName, String attributeName){
		Node node = myXmlDoc.getElementsByTagName(nodeName).item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			return (eElement.getAttribute(attributeName));
		}
		return "";
	}
	
	public static String getNodeAttributeValue(Document myXmlDoc, String nodeName, int index, String attributeName){
		Node node = myXmlDoc.getElementsByTagName(nodeName).item(index);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			return (eElement.getAttribute(attributeName));
		}
		return "";
	}	
}
