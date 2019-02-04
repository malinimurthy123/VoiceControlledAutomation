/**
 * The VoiceAutomationUtility is a utility class for defining the utility methods
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */

package com.infogain.emulator.srcframework;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.recognizer.Recognizer.State;
import edu.cmu.sphinx.result.Result;

public class VoiceAutomationUtility {
	static SynthesizerModeDesc desc;
	static Synthesizer synthesizer;
	static Voice voice;
	public static TestScriptSource generateScriptFile()
			throws Exception {
		String sourceFileName = "Test1";
		String sourcePackage = "com.test";
		TestScriptSource source = TestScriptSourceUtility
				.createTestScriptSourceFile(sourceFileName, sourcePackage);

		TestScriptSourceUtility.addImportsToSourceFile(source,
				"org.openqa.selenium.By");
		TestScriptSourceUtility.addImportsToSourceFile(source,
				"org.openqa.selenium.WebDriver");
		TestScriptSourceUtility.addImportsToSourceFile(source,
				"org.openqa.selenium.WebElement");
		TestScriptSourceUtility.addImportsToSourceFile(source,
				"org.openqa.selenium.chrome.ChromeDriver");
		TestScriptSourceUtility.addImportsToSourceFile(source,
				"org.openqa.selenium.support.ui.Select");
		
		return source;
	}
	public static Method  addURLConfigurationMethodToSource(TestScriptSource source, String URL)
			throws Exception {
		
		List<String> exceptions = new ArrayList<>();
		exceptions.add("Exception");
		List<Argument> arguments = new ArrayList<>();
		Argument arg = new Argument("String[]", "args");
		arguments.add(arg);
		Method method = TestScriptSourceUtility.addMethod(source, "void",
				"main", exceptions, arguments);

		StringBuffer definition = new StringBuffer();
		definition
				.append("\n\tSystem.setProperty(\"webdriver.chrome.driver\", \"D:\\\\ChromeDriver\\\\chromedriver.exe\");");
		definition.append("\n\tWebDriver driver = new ChromeDriver();");
		definition.append("\n\tdriver.get(\"" + URL + "\");");
		definition.append("\n\tThread.sleep(1000);\n");
		
		TestScriptSourceUtility.addMethodDefinition(source, method, definition);
		return method;
		
	}

	public static void addComponentToMethod(Method method,
			String componentType, Recognizer recognizer, Microphone microphone) {
		// TODO Auto-generated method stub
		System.out.println(componentType);
		
		StringBuffer defn = method.getDefinition();
		defn.append((ComponentFactory.getComponent(componentType, null, null,recognizer,microphone)).getComponentDefinition());
		method.setDefinition(defn);
		
	}
	
	public static void speakText(String text){
		
		String VOICENAME = "kevin16";   
		try {
			init(VOICENAME);
		} catch (EngineException | AudioException | EngineStateError
				| PropertyVetoException e1) {
			
			e1.printStackTrace();
		}
		VoiceManager vm = VoiceManager.getInstance();
		Voice voice = vm.getVoice(VOICENAME);
		
		voice.setStyle("casual");
		 voice.setVolume(100);
		voice.allocate();
		voice.speak(text);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}
	public static String recordAction(Microphone microphone,Recognizer recognizer ,boolean isConfirm){
		if(!recognizer.getState().equals(State.READY))
			recognizer.allocate();
		microphone.clear();
		if (isConfirm) {
			System.out.println("Confirm now");
		}
		microphone.startRecording();
		Result result = recognizer.recognize();
		microphone.stopRecording();
		recognizer.deallocate();
		System.out.println(result.getBestFinalResultNoFiller());
		return result.getBestFinalResultNoFiller();
	}
	
	private static void init(String voiceName) 
		    throws EngineException, AudioException, EngineStateError, 
		           PropertyVetoException 
		  {
		    if (desc == null) {
		      
		      System.setProperty("freetts.voices", 
		        "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		      
		      desc = new SynthesizerModeDesc(Locale.US);
		      Central.registerEngineCentral
		        ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
		      synthesizer = Central.createSynthesizer(desc);
		      synthesizer.allocate();
		      synthesizer.resume();
		      SynthesizerModeDesc smd = 
		        (SynthesizerModeDesc)synthesizer.getEngineModeDesc();
		      javax.speech.synthesis.Voice[] voices = smd.getVoices();
		      javax.speech.synthesis.Voice voice = null;
		      for(int i = 0; i < voices.length; i++) {
		        if(voices[i].getName().equals(voiceName)) {
		          voice = voices[i];
		          break;
		        }
		      }
		      synthesizer.getSynthesizerProperties().setVoice(voice);
		    }
		    
		  }
}
