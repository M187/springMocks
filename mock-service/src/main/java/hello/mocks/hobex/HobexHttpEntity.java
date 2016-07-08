package hello.mocks.hobex;

import org.springframework.http.HttpEntity;

public class HobexHttpEntity extends HttpEntity<String>{
	
	public HobexHttpEntity(String string){
		super(string, new HobexHeaders(string));
	}
}
