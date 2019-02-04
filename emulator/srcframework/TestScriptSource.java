/**
 * The TestScriptSource is used to represent the TestScriptSource which is the java representation of the 
 * test java source file.
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TestScriptSource {

	private List<Import> imports;
	private List<Method> methods;
	private String sourcePackage;
	private String sourceFileName;
	final String SPACE=" ";
	private List<Variable> varriables;
	public TestScriptSource(String sourceFileName, String sourcePackage) {
		// TODO Auto-generated constructor stub
		this.sourceFileName = sourceFileName;
		this.sourcePackage = sourcePackage;
		this.imports = new ArrayList<Import>();
		this.methods = new ArrayList<Method>();
	}
	public List<Import> getImports() {
		return imports;
	}
	public void setImports(List<Import> imports) {
		this.imports = imports;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public String getSourcePackage() {
		return sourcePackage;
	}
	public void setSourcePackage(String sourcePackage) {
		this.sourcePackage = sourcePackage;
	}
	public String getSourceFileName() {
		return sourceFileName;
	}
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}
	public StringBuffer getSourceAsString() {
		// TODO Auto-generated method stub
		StringBuffer sourceString = new StringBuffer();
		
		sourceString.append("package"+SPACE+sourcePackage+";\n");

		for(Import imp : imports){
			sourceString.append("import"+SPACE+imp.getImportClass()+";\n");
		}
		sourceString.append("public class "+sourceFileName+"{\n");
		for(Method method : methods){
			
			sourceString.append(method.METHOD_ACCESS_MODIFIER+SPACE+"static"+SPACE+method.getReturnType()
					+SPACE+method.getName());
			
			if(method.getArgument()!=null){
				sourceString.append("("+StringUtils.join(method.getArgument(), ',')+")");
			}else{
				sourceString.append("()");
			}
			
			if(method.getExceptions()!=null){
				sourceString.append(SPACE+"throws"+SPACE+StringUtils.join(method.getExceptions(), ','));
			}
			sourceString.append("{\n");
			sourceString.append(method.getDefinition());
			sourceString.append("\n\tThread.sleep(10000);");
			sourceString.append("\n\tdriver.close();");
			sourceString.append("\n}");
			
		}
		sourceString.append("\n}");
		return sourceString;
	}
	@Override
	public String toString() {
		return "TestScriptSource [imports=" + imports + ", methods=" + methods
				+ ", sourcePackage=" + sourcePackage + ", sourceFileName="
				+ sourceFileName + "]";
	}
	
	
}
