package hello.mocks.hobex;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import hello.mocks.utils.XmlParser;

@RestController
public class HobexController {

    protected RequestProcessor requestProcessor; //TODO: should belong to superclass.
    
    {
    	this.requestProcessor = new RequestProcessor();
    }

    @RequestMapping(
    		value = "/hobex",
    		method = RequestMethod.POST,
    		headers = {"content-type=application/xml"})
    public HttpEntity<String> ratepay(@RequestBody String data) {
    	
    	Document myXmlRequest = null;
    	
    	try {
			myXmlRequest = XmlParser.loadXMLFromString(data);
		} catch (Exception e) {
			System.out.println("a");
		}
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set(HttpHeaders.CONTENT_TYPE, "text/xml;charset=UTF-8");
    	
    	return new HttpEntity<String>(XmlParser.documentToString(myXmlRequest), headers);
    }
}