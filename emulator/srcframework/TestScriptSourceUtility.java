/**
 * The TestScriptSourceUtility is a utility class for defining the utility methods
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class TestScriptSourceUtility {

	/**
	 * The createTestScriptSourceFile is used to instantiate the TestScriptSource
	 * object
	 * 
	 * @param sourceFileName
	 * @param sourcePackage
	 * @return
	 */
	public static TestScriptSource createTestScriptSourceFile(
			String sourceFileName, String sourcePackage) {
		return new TestScriptSource(sourceFileName, sourcePackage);
	}

	
	/**
	 * The addImportsToSourceFile is used to add an import to the source hierarchy
	 * @param source
	 * @param importClass
	 * @throws Exception
	 */
	public static void addImportsToSourceFile(TestScriptSource source,
			String importClass) throws Exception {
		if (null == source) {
			throw new Exception();
		}
		Import imports = new Import(importClass);
		source.getImports().add(imports);
	}

	/**
	 * The addMethod is used to add an method to the source hierarchy
	 * @param source
	 * @param methodReturnType
	 * @param methodName
	 * @param exceptions
	 * @param arguments
	 * @return
	 * @throws Exception
	 */
	public static Method addMethod(TestScriptSource source,
			String methodReturnType, String methodName, List exceptions,
			List arguments) throws Exception {

		if (null == source) {
			throw new Exception();
		}

		Method m = new Method(methodReturnType, methodName, exceptions,
				arguments);
		if (source.getMethods().contains(m)) {
			throw new Exception();
		} else {
			source.getMethods().add(m);
		}
		return m;
	}

	/**
	 * The addMethodDefinition is used to add definition to an method in the source hierarchy
	 * @param source
	 * @param m
	 * @param definition
	 * @throws Exception
	 */
	public static void addMethodDefinition(TestScriptSource source, Method m,
			StringBuffer definition) throws Exception {

		if (null == source) {
			throw new Exception();
		}
		if (!source.getMethods().contains(m)) {
			throw new Exception();
		} else {
			source.getMethods().get(source.getMethods().indexOf(m))
					.setDefinition(definition);
		}
	}

	/**
	 * 
	 * The writeSourceToFile is used to write the source hierarchy as a hava source file to the file system
	 * 
	 * @param source
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeSourceToFile(TestScriptSource source,
			String filePath) throws IOException {

		File f = new File(filePath);
		FileUtils.cleanDirectory(f);
		
			System.out.println("deleted the old file..");
		
		
		System.out.println(f.exists());
		f=null;
		FileWriter fw = new FileWriter(filePath + "//"
				+ source.getSourceFileName() + ".src");

		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		StringBuffer sourceString = source.getSourceAsString();
		System.out.println(sourceString);
		Scanner scan = new Scanner(sourceString.toString()); 
		while (scan.hasNextLine()) {
			String oneLine = scan.nextLine();
			out.println(oneLine);
		}
		out.close();
		fw.write(writer.toString());
		fw.close();
	}
}
