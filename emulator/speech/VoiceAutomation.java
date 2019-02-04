/**
 * The VoiceAutomation program is the main program that is invoked on start of the program.
 * It accepts the voice inputs from the user and converts the input voice commands into text commands.
 * The Commands are then added to the source structure.
 * At the end of the program the source hierarchy is converted into a string object.
 * The String object is used to compile at runtime from within the VoiceAutomation program
 * using a JavaFileObject. The main method in the generated source code is invoked for the
 * test execution. The generated source code is then written into the file system.
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.speech;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

import com.infogain.emulator.srcframework.Method;
import com.infogain.emulator.srcframework.TestScriptSource;
import com.infogain.emulator.srcframework.TestScriptSourceUtility;
import com.infogain.emulator.srcframework.VoiceAutomationUtility;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class VoiceAutomation {

	/**
	 * A String object to hold the URL value entered by the user
	 */
	private static String URL = null;
	
	/**
	 * A reference of the TestScriptSource object
	 */
	private static TestScriptSource source;
	
	/**
	 * The path to which the source file is written to
	 */
	private static String filePath = "D:\\POC\\VoiceAutomation\\src\\com\\test";
	
	/**
	 * The path to which the compiled class of the generated test class is written to
	 */
	private static String compiledfilePath = "D:\\POC\\VoiceAutomation\\src\\output\\classes";

	/**
	 * The main method of the application.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// cleanup old directory and file
		
		File f = new File(filePath);
		FileUtils.cleanDirectory(f);
		f = new File(compiledfilePath);
		FileUtils.cleanDirectory(f);

		System.out
				.println("---------------------------------------------------------------------------------");
		System.out
				.println("-                                                                               -");
		System.out
				.println("-                                                                               -");
		System.out
				.println("-                             Voice Automation Emulator                         -");
		System.out
				.println("-                                                                               -");
		System.out
				.println("-                                                                               -");
		System.out
				.println("---------------------------------------------------------------------------------");

		//set the start time of the run
		long startTime = System.currentTimeMillis();
		
		//configure the freeetts properties
		System.setProperty(
				"com.sun.speech.freetts.audio.AudioPlayer.openFailDelayMs",
				"100");
		System.setProperty(
				"com.sun.speech.freetts.audio.AudioPlayer.totalOpenFailDelayMs",
				"30000");
		
		//create instance of the command object by loading the sphinx4 configuration file
		ConfigurationManager command = new ConfigurationManager(
				VoiceAutomation.class.getResource("command.config.xml"));
		
		//disable all the logging of messages to console.
		Logger log = LogManager.getLogManager().getLogger("");
		for (Handler h : log.getHandlers()) {

			h.setLevel(Level.SEVERE);
		}

		Logger cmRootLogger = Logger.getLogger("command.config");
		cmRootLogger.setLevel(java.util.logging.Level.OFF);
		String conFile = System.getProperty("java.util.logging.config.file");
		if (conFile == null) {
			System.setProperty("java.util.logging.config.file",
					"ignoreAllSphinx4LoggingOutput");
		}
		// create scanner input to accept the keyboard input from the user
		Scanner sc = new Scanner(System.in);

		Method method = null;

		//Play voice messages to the user
		VoiceAutomationUtility
				.speakText("Welcome to voice automation emulator program. To start please enter the U R L of the application");
		System.out.print("Enter the URL: ");
		URL = sc.nextLine();
		System.out.println("Opening URL : " + URL);
		source = VoiceAutomationUtility.generateScriptFile();
		method = VoiceAutomationUtility.addURLConfigurationMethodToSource(
				source, URL);

		// audio output convert freetts ->text to voice API
		VoiceAutomationUtility.speakText("What do you what to do now?");
		Result result = null;
		while (true) {
			boolean confirmed = true;
			
			//get instance of recognizer and the microphone instance for the voice recognition
			Recognizer commandRecognizer = (Recognizer) command
					.lookup("recognizer");

			Microphone commandMic = (Microphone) command.lookup("microphone");

			Thread.sleep(100);
			String resultText = "";
			boolean displayed = false;
			while (resultText.equals("")) {
				if (!displayed) {
					System.out.println("Voice Command Syntax :");
					System.out
							.println("1) (create component) (input field |radio|drop down|link|check box|button) (by) (id | path)");
					System.out.println("2) (end)");
					System.out.println("3) (ya|no)");
					displayed = true;
				}
				
				//get the text equivalent of the voice command
				resultText = VoiceAutomationUtility.recordAction(commandMic,
						commandRecognizer, false);

			}
			System.out.println(resultText);
			if (resultText.startsWith("ya") || resultText.startsWith("no")) {
				VoiceAutomationUtility
						.speakText("Invalid command Try again. What do you what to do now?");
				continue;
			}

			if (resultText != null) {
				System.out.println("Voice command: " + resultText + '\n');
				String speachText = resultText;
				
				//check the identifier type of the component: to create it by id or by xpath from the given 
				//input voice command
				
				if (speachText.endsWith("id")) {
					speachText = speachText.replaceAll("id", "eye d");
				}
				VoiceAutomationUtility.speakText("Voice command is  "
						+ speachText);
				VoiceAutomationUtility.speakText("Is this correct? Confirm!");

				String confirmText = VoiceAutomationUtility.recordAction(
						commandMic, commandRecognizer, true);

				System.out.println("Confirmed ?:"
						+ (confirmText.equals("ya") ? true : false));
				if (resultText != null && resultText.equals("end")
						&& !confirmText.equals("ya")) {
					confirmed = false;
				} else if (!confirmText.equals("ya")) {
					confirmed = false;
				}

				if (!confirmed) {
					VoiceAutomationUtility
							.speakText("Try again. What do you what to do now?");
					continue;
				}

				//if the parsed voice command is starting with create component then proceed to create 
				// the respective command
				if (resultText.startsWith("create component")) {
					System.out.println("creating ..");
					VoiceAutomationUtility.addComponentToMethod(method,
							resultText.substring("create".length() + 1,
									resultText.length()), commandRecognizer,
							commandMic);

				} else if (resultText.equals("end")) {
					//if the command is end then proceed to compile and execute the test case
					break;
				}
			} else {
				System.out.println("I can't hear what you said.\n");
			}
		}

		//compile and execute the test case
		compileJavaAndExecute(filePath, source.getSourceFileName(), source);

		
		//mark the end time of the program execution. calculate the total execution time and end the application
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		totalExcutionTime(totalTime);
		System.exit(1);

	}

	/**
	 * 
	 * The method is used to compile and execute the source hierarchy to a class file
	 * and then execute the main method.
	 * 
	 * @param path
	 * @param file
	 * @param source
	 * @throws Exception
	 */
	private static void compileJavaAndExecute(String path, String file,
			TestScriptSource source) throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnostics, null, null);

		JavaFileObject file1 = new JavaSourceFromString("Test1", source
				.getSourceAsString().toString());

		Iterable<? extends JavaFileObject> compilationUnits = Arrays
				.asList(file1);
		Iterable options = Arrays.asList("-d", compiledfilePath);

		CompilationTask task = compiler.getTask(null, null, diagnostics,
				options, null, compilationUnits);

		boolean success = task.call();

		URLClassLoader classLoader = URLClassLoader
				.newInstance(new URL[] { new File(compiledfilePath).toURI()
						.toURL() });
		try {
			Class.forName("com.test.Test1", true, classLoader)
					.getDeclaredMethod("main", new Class[] { String[].class })
					.invoke(null, new Object[] { null });
		} catch (Exception e) {
			e.printStackTrace();
		}

		fileManager.close();
		System.out.println("Success: " + success);
		TestScriptSourceUtility.writeSourceToFile(source, filePath);

	}

	/**
	 * Method to print the total execution time in the mm:ss format.
	 * @param time
	 */
	private static void totalExcutionTime(long time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = new Date(time);
		System.out.println("Total Execution time: " + dateFormat.format(date));
	}

}

class JavaSourceFromString extends SimpleJavaFileObject {
	final String code;

	JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/')
				+ Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
}
