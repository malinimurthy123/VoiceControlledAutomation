/**
 * The Method is used to represent the Method to be defined in the test source java file.
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework;

import java.util.List;

public class Method {

	final String METHOD_ACCESS_MODIFIER = "public";
	private String returnType;
	private List<String> exceptions;
	private StringBuffer definition;
	private List<Argument> argument;
	private String name;
	
	public Method(String methodReturnType, String methodName, List exceptions,List arguments) {
		// TODO Auto-generated constructor stub
		this.returnType=methodReturnType;
		this.name=methodName;
		this.exceptions=exceptions;
		this.argument=arguments;
	}
	public Method(String methodReturnType, String methodName, List arguments) {
		// TODO Auto-generated constructor stub
		this.returnType=methodReturnType;
		this.name=methodName;
		this.argument=arguments;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public List<String> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}
	public StringBuffer getDefinition() {
		return definition;
	}
	public void setDefinition(StringBuffer definition) {
		this.definition = definition;
	}
	public String getMETHOD_ACCESS_MODIFIER() {
		return METHOD_ACCESS_MODIFIER;
	}
	public List<Argument> getArgument() {
		return argument;
	}
	public void setArgument(List<Argument> argument) {
		this.argument = argument;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
