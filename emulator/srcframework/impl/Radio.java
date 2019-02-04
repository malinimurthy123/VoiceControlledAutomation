/**
 * The Radio is used to represent the Radio component type
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework.impl;

import com.infogain.emulator.srcframework.Component;

public class Radio extends Component {

	public  Radio(String componentType, String componentIdentifier, Identifier identifier){
		setComponentType(componentType);
		setComponentIdentifier(componentIdentifier);
		setIdentifier(identifier);
		setComponentName(getComponentNameIdentifier());

	}
	@Override
	public String getComponentDefinition() {
		// TODO Auto-generated method stub
		
		
		String component = "\nList<WebElement>  listOfValues_" +  getComponentName()
				+ " = new Select(driver.findElements("+getSearchIdentifierString()+"));";

		if (null != component) {
			component += "\nlistOfValues_" +  getComponentName() + ".get("
					+ Integer.parseInt(getComponentValue()) + ");";
		}
		component +="\nThread.sleep(100);";

		return component;
	}

}
