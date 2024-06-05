package com.datastaxtutorials.c3porag;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("")
public class C3poMainView extends VerticalLayout {

	private static final long serialVersionUID = -161464303521967013L;
	
	private C3poController controller;
	private Paragraph response;
	private TextField queryField;
	private Button queryButton;
	
	public C3poMainView() {
		controller = new C3poController();
		
		getStyle().set("background-color", "black");
		
		add(showImage());
		add(showResponse());
		add(showQueryBar());
	}
	
	private Component showImage() {
		HorizontalLayout layout = new HorizontalLayout();
		
		Image c3poImage = new Image();
		StreamResource imageResource = new StreamResource("c3po.png",
		        () -> getClass().getResourceAsStream("/images/c3po.png"));
		
		c3poImage.setSrc(imageResource);
		c3poImage.setWidth("500px");
		
		layout.add(c3poImage);
		
		return layout;
	}
	
	private Component showResponse() {
		HorizontalLayout layout = new HorizontalLayout();
		
		response = new Paragraph();
		response.setWidth("500px");
		response.setHeight("100px");
		response.getStyle().set("background-color", "aliceblue");
		
		layout.add(response);
		
		return layout;
	}
	
	private Component showQueryBar() {
		HorizontalLayout layout = new HorizontalLayout();
		
		queryField = new TextField();
		queryField.setWidth("400px");
		queryField.getStyle().set("background-color", "gainsboro");
		queryButton = new Button("Query");
		queryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		queryButton.addClickListener(click -> {
			getResponse();
		});
		
		layout.add(queryField);
		layout.add(queryButton);
		
		return layout;
	}
	
	private void getResponse() {
		
		C3poResponse c3poResponse = controller.sendMessage(queryField.getValue());
		response.setText(c3poResponse.getMessage());
		
		// clear queryField
		queryField.setValue("");
	}
}
