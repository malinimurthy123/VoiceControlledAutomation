/**
 * The Component class represents the component that is to be created by the voice command given by user
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */
package com.infogain.emulator.srcframework;

import java.util.Random;

import com.infogain.emulator.srcframework.impl.Identifier;

public abstract class  Component {

	/**
	 * The componentType represents the type of the component
	 */
	private String componentType;
	
	/**
	 * The componentIdentifier represents the name of the component
	 */
	private String componentIdentifier; 
	
	/**
	 * The componentValue represents the value the component can get assigned if an action is set
	 * to the component
	 */
	private String componentValue;
	
	/**
	 * The inputType represents the input type of the component
	 */
	private String inputType;
	
	/**
	 * The boolean flag to represent if the component is set an action or not
	 */
	private boolean action;
	
	/**
	 * The identifier represents the identifier type of the component : ID or XPATH
	 */
	private Identifier identifier;
	
	/**
	 * The component name of the component
	 */
	private String componentName;
	
	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public boolean isAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
	}

	public String getComponentIdentifier() {
		return componentIdentifier;
	}

	public void setComponentIdentifier(String componentIdentifier) {
		this.componentIdentifier = componentIdentifier;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public abstract String getComponentDefinition();
	
	
	public String getComponentNameIdentifier(){
		String componentName="";
		switch (getIdentifier()) {
		case ID:
			componentName= getComponentIdentifier().replaceAll("-", "");		
			break;
		case PATH:
			componentName= Integer.toString((new Random()).nextInt(100));
			break;
	
		}
		return componentName;
	}
	
	public String getSearchIdentifierString(){
		String identify="";
		switch (getIdentifier()) {
		case ID:
			identify="By.id(\""+getComponentIdentifier()+"\")";
			break;
		case PATH:
			identify="By.xpath(\""+getComponentIdentifier()+"\")";
			break;
	
		}
		return identify;
	}
	
}
