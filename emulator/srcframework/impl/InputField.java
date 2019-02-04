/**
 * The InputField is used to represent the InputField component type
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework.impl;

import com.infogain.emulator.srcframework.Component;

public class InputField extends Component {

	public  InputField(String componentType, String componentIdentifier, Identifier identifier){
		setComponentType(componentType);
		setComponentIdentifier(componentIdentifier);
		setIdentifier(identifier);
		setComponentName(getComponentNameIdentifier());


	}
	@Override
	public String getComponentDefinition() {
		// TODO Auto-generated method stub
		String component = "\nWebElement inputBox_" +  getComponentName()
				+ " = driver.findElement("+getSearchIdentifierString()+");";
		
		if (isAction() && null != getComponentValue()) {
			component += "\ninputBox_" +  getComponentName()
					+ ".sendKeys(\"" + getComponentValue() + "\");";

		}
		component +="\nThread.sleep(100);";

		return component;
	}

}
