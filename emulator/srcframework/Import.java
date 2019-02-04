/**
 * The Import is used to represent the imports to be declared in the test source java file.
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework;

public class Import {

	private String importClass;

	public Import(String importClass) {
		// TODO Auto-generated constructor stub
		this.importClass = importClass;
	}

	public String getImportClass() {
		return importClass;
	}

	public void setImportClass(String importClass) {
		this.importClass = importClass;
	}
	
}
