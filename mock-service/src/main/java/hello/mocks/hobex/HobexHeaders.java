package hello.mocks.hobex;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpHeaders;

class HobexHeaders extends HttpHeaders{

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.000");
	
	HobexHeaders(String string){
		this.set("Date", df.format(new Date()));
		this.set("Server", "Microsoft-IIS/6.0");
		this.set("X-Powered-By", "ASP.NET");
		this.set("Content-Length", String.valueOf(string.length()));
		this.set("Content-Type", "text/xml");
		this.set("Cache-control", "private");
		//TODO: finish these headers.
	}
}
