/**
 * The Link is used to represent the Link component type
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework.impl;

import com.infogain.emulator.srcframework.Component;

public class Link extends Component {

	public Link(String componentType, String componentIdentifier, Identifier identifier) {
		setComponentType(componentType);
		setComponentIdentifier(componentIdentifier);
		setIdentifier(identifier);
		setComponentName(getComponentNameIdentifier());

	}

	@Override
	public String getComponentDefinition() {
		// TODO Auto-generated method stub
		String component = "\nWebElement element_" +  getComponentName()
				+ " = driver.findElement("+getSearchIdentifierString()+");";
		if (isAction()) {
			component += "\nelement_" + getComponentName() + ".click();";

		}
		component +="\nThread.sleep(100);";

		return component;
	}
}
