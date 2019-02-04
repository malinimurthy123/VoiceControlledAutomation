/**
 * The Button is used to represent the Button component type
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework.impl;

import com.infogain.emulator.srcframework.Component;

public class Button extends Component {

	public  Button(String componentType, String componentIdentifier, Identifier identifier){
		setComponentType(componentType);
		setComponentIdentifier(componentIdentifier);
		setIdentifier(identifier);
		setComponentName(getComponentNameIdentifier());
	}
	@Override
	public String getComponentDefinition() {
		// TODO Auto-generated method stub
		
		String component = "\nWebElement button_" + getComponentName()
				+ " = driver.findElement("+getSearchIdentifierString()+");";
		if (isAction()) {
			component += "\nbutton_" + getComponentName() + ".click();";

		}
		component +="\nThread.sleep(1000);";
		return component;
	}

}
