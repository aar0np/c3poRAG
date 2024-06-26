package com.datastaxtutorials.c3porag;

import com.fasterxml.jackson.annotation.JsonProperty;

public class C3poLangFlowRequest {
	@JsonProperty("input_value")
	private String inputValue;
	@JsonProperty("output_type")
	private String outputType;
	@JsonProperty("input_type")
	private String inputType;

	public C3poLangFlowRequest(String message) {
		inputValue = message;
		outputType = "chat";
		inputType = "chat";
	}
	
	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
}
