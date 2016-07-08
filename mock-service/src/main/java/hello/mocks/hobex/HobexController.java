package hello.mocks.hobex;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import hello.mocks.utils.XmlParser;

@RestController
public class HobexController {

	protected RequestProcessor requestProcessor; // TODO: should belong to
													// superclass.

	{
		this.requestProcessor = new RequestProcessor();
	}

	@RequestMapping(value = "/hobex", method = RequestMethod.POST, headers = { "content-type=application/xml" })
	public HttpEntity<String> ratepay(@RequestBody String data) {

		Document myXmlRequest = null;
		Document myXmlResponse = null;

		myXmlRequest = XmlParser.loadXMLFromString(data);

		myXmlResponse = requestProcessor.getResponse(myXmlRequest);

		return new HobexHttpEntity(XmlParser.documentToString(myXmlResponse));
	}
}