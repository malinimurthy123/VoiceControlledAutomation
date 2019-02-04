/**
 * The List is used to represent the List component type
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework.impl;

import com.infogain.emulator.srcframework.Component;

public class List extends Component {

	public  List(String componentType, String componentIdentifier, Identifier identifier){
		setComponentType(componentType);
		setComponentIdentifier(componentIdentifier);
		setIdentifier(identifier);
		setComponentName(getComponentNameIdentifier());

	}
	@Override
	public String getComponentDefinition() {
		
		String component = "\nSelect select_" +  getComponentName()
				+ " = new Select(driver.findElement("+getSearchIdentifierString()+"));";
		if (isAction() && null != getComponentValue() ) {
			component += "\nselect_" +  getComponentName()
					+ ".selectByIndex(" + getComponentValue() + ");";
		}
		component +="\nThread.sleep(100);";

		return component;
	}

}
