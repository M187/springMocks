package hello.mocks.hobex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hello.mocks.utils.XmlParser;

public class RequestProcessor {

	public BasicHttpResponse getResponse(Document xmlRequest) {

		BasicHttpResponse response = generateSuccResponseSkelet();

		Document responseXmlBody = buildXmlPaymentResponse(xmlRequest);

		return response;
	}

	private BasicHttpResponse generateSuccResponseSkelet() {

		BasicHttpResponse response = new BasicHttpResponse(
				new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 201, "OK"));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.000");

		Header[] headers = null;

		headers[0] = (new BasicHeader("Date", df.format(new Date())));
		headers[1] = (new BasicHeader("Server", "Apache"));
		headers[2] = (new BasicHeader("Content-Type", "text/xml;charset=UTF-8"));
		headers[3] = (new BasicHeader("Connection", "close"));
		headers[4] = (new BasicHeader("Strict-Transport-Security:", "max-age=31536000; includeSubDomains"));
		// TODO: headers[5] = (new BasicHeader("Content-Length",
		// String.valueOf(mockRequest.httpResponse.getContentCount())));
		response.setHeaders(headers);

		return response;
	}

	private Document buildXmlPaymentResponse(Document xmlRequest){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);			
			
			Element customer = doc.createElement("customer");
			rootElement.appendChild(customer);

			Element contacts = doc.createElement("contacts");
			customer.appendChild(contacts);
			
			Element addresses = doc.createElement("addresses");
			contacts.appendChild(addresses);
			
			Element address = doc.createElement("address");
			addresses.appendChild(address);
			
		
			Element nationality = doc.createElement("nationality");
			nationality.appendChild(doc.createTextNode(XmlParser.getNodeValue(xmlRequest, "nationality")));
			contacts.appendChild(nationality);			
			
			Element payment = doc.createElement("payment");
			
			Attr currency = doc.createAttribute("currency");
			currency.setValue(XmlParser.getNodeAttributeValue(xmlRequest, "payment", "currency"));
			payment.setAttributeNode(currency);
			
			Attr method = doc.createAttribute("mathod");
			currency.setValue(XmlParser.getNodeAttributeValue(xmlRequest, "payment", "method"));
			payment.setAttributeNode(method);			
			
			rootElement.appendChild(payment);				
			
			Element amount = doc.createElement("amount");
			amount.appendChild(doc.createTextNode(XmlParser.getNodeValue(xmlRequest, "amount")));
			payment.appendChild(amount);
			
			return doc;
		} catch (Exception e) {
			return null;
		}
	}
}