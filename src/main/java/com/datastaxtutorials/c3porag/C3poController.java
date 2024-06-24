package com.datastaxtutorials.c3porag;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/c3po")
@RestController
public class C3poController {

	private RestTemplate restTemplate;
	
	//private final String LANGFLOW_URL = "http://127.0.0.1:7860/api/v1/run/9ed6ea74-b2ce-483f-8387-1653c427d314?stream=false";
	private static  final String LANGFLOW_URL = System.getenv("LANGFLOW_URL");
	
	public C3poController() {
		restTemplate = new RestTemplateBuilder().build();
	}
	
	public C3poResponse sendMessage(String message) {
		
		C3poResponse returnVal = new C3poResponse();
		
		C3poLangFlowRequest request = new C3poLangFlowRequest(message);

		Object response = restTemplate.postForObject(
				LANGFLOW_URL,
				request,
				Object.class);

		String strResponse = response.toString();
		 
		// parse message
		int start = strResponse.indexOf("{results={result=");
		int end = strResponse.indexOf("}", start + 17);
		String strC3poResponse = strResponse.substring(start + 17, end);
		returnVal.setMessage(strC3poResponse);
		
		return returnVal;
	}
	
	@PostMapping("/send/")
	public ResponseEntity<C3poResponse> sendMessage(
            HttpServletRequest req,
            @RequestBody MessageRequest messageReq) {
		
		return ResponseEntity.ok(sendMessage(messageReq.getMessage()));
	}
}
