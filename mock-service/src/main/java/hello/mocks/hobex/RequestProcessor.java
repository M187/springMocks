package hello.mocks.hobex;

import org.w3c.dom.Document;

import hello.mocks.utils.XmlParser;

public class RequestProcessor {
	
	public static final String XSD_SCHEMA_PATH = "src/main/java/resources/hobex/schemas/hobex-model.xsd";
	public static final String IVALID_RESPONSE__PATH = "src/main/java/resources/hobex/hobexResponseFailed.xml";

	public Document getResponse(Document xmlRequest) {

		Document responseXmlBody = (xmlRequest);
		
		//Validate
		if (!XmlParser.validateXMLSchema(XSD_SCHEMA_PATH, xmlRequest)){
			return XmlParser.loadXMLFromString("aaa");
		}
		XmlParser.getNodeValue(xmlRequest, "Bank");
		
		return responseXmlBody;
	}
}