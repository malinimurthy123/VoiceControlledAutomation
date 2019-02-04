/**
 * The Variable is used to represent the Variable to be declared in the test source java file.
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Variable {

	private String type;
	private String name;
	private String initialization;
	private String value;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDeclaration() {
		return  type + " " + name + ";";
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getInitialization(List<Argument> args){
		List<String> arg = new ArrayList<String>();
		for(Argument argument : args){
			arg.add(argument.getName());
		}
		return name+"= new "+type+"("+StringUtils.join(arg, ',')+");";
	}
}
