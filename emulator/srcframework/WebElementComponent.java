/**
 * The WebElementComponent is a representation of the web element
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */


package com.infogain.emulator.srcframework;

import org.openqa.selenium.WebElement;


public class WebElementComponent {

	private WebElement webElement;
	private Variable variable;
	public WebElement getWebElement() {
		return webElement;
	}
	public void setWebElement(WebElement webElement) {
		this.webElement = webElement;
	}
	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	
	
}
