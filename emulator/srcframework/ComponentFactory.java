/**
 * The ComponentFactory is used to get the component instance of the component the user intends to create
 * via the voice command
 * 
 * @author  Malini
 * @version 1.0
 * @since   10-12-2016 
 */
package com.infogain.emulator.srcframework;

import java.util.Scanner;

import com.infogain.emulator.srcframework.impl.Button;
import com.infogain.emulator.srcframework.impl.Identifier;
import com.infogain.emulator.srcframework.impl.InputField;
import com.infogain.emulator.srcframework.impl.Link;
import com.infogain.emulator.srcframework.impl.Radio;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;

public class ComponentFactory {

	

	public static Component getComponent(String componentType,
			String componentValue, String inputType, Recognizer recognizer,
			Microphone microphone) {
		Component component = null;
		Scanner sc = new Scanner(System.in);

		String componentTypeName = componentType.substring(
				componentType.indexOf("component")
						+ (new String("component")).length() + 1,
				componentType.indexOf("by") - 1);
		String componentIdentifier = (componentType.substring(
				componentType.indexOf("by") + 3, componentType.length()))
				.toUpperCase();

		VoiceAutomationUtility.speakText("Creating component "
				+ componentType.replaceAll("id", "eye d"));
		VoiceAutomationUtility.speakText("Enter the "
				+ (componentIdentifier.equals("ID") ? "eye d"
						: componentIdentifier) + " of the component");
		String componentIdentifierValue = "";
		while (true) {
			System.out.print("Enter the " + componentIdentifier
					+ " of the component :");
			componentIdentifierValue = sc.nextLine();
			if (componentIdentifierValue == null
					|| componentIdentifierValue.equals("")) {
				VoiceAutomationUtility
						.speakText("The "
								+ (componentIdentifier.equals("ID") ? "eye d"
										: componentIdentifier)
								+ " of the component cannot be empty. Please enter the correct value");
				continue;
			}else{
				break;
			}
		}
		String confirmText = "";

		switch (componentTypeName) {
		case "drop down":
			component = new com.infogain.emulator.srcframework.impl.List(
					"list", componentIdentifierValue,
					Identifier.valueOf(componentIdentifier));

			VoiceAutomationUtility
					.speakText("Do you want to select a value in the component ?");
			confirmText = VoiceAutomationUtility.recordAction(microphone,
					recognizer,true);
			System.out.println(confirmText);
			if (confirmText.equals("ya")) {
				VoiceAutomationUtility.speakText("Enter the value");
				component.setComponentValue(sc.nextLine());
				component.setAction(true);
				VoiceAutomationUtility.speakText("Component created");
			}
			
			break;

		case "input field":
			component = new InputField("inputField", componentIdentifierValue,
					Identifier.valueOf(componentIdentifier));
			VoiceAutomationUtility
					.speakText("Do you want to assign action to the component ?");
			confirmText = VoiceAutomationUtility.recordAction(microphone,
					recognizer,true);
			System.out.println(confirmText);
			if (confirmText.equals("ya")) {
				System.out.print("Enter the input field value :");
				VoiceAutomationUtility.speakText("Enter the input field value");
				component.setComponentValue(sc.nextLine());
				component.setAction(true);
				VoiceAutomationUtility.speakText("Component created");
			}
			break;
		case "radio":
		case "check box":
			component = new Radio(componentType, componentIdentifierValue,
					Identifier.valueOf(componentIdentifier));
			VoiceAutomationUtility
					.speakText("Do you want to check a value in the component ?");
			confirmText = VoiceAutomationUtility.recordAction(microphone,
					recognizer,true);
			System.out.println(confirmText);
			if (confirmText.equals("ya")) {
				VoiceAutomationUtility
						.speakText("Enter the index of the value");
				component.setComponentValue(sc.nextLine());
				component.setAction(true);
				VoiceAutomationUtility.speakText("Component created");
			}
			
			break;
		case "link":
			component = new Link("link", componentIdentifierValue,
					Identifier.valueOf(componentIdentifier));
			VoiceAutomationUtility
					.speakText("Do you want to assign click action to the link component ?");
			confirmText = VoiceAutomationUtility.recordAction(microphone,
					recognizer,true);
			System.out.println(confirmText);
			if (confirmText.equals("ya")) {
				component.setAction(true);
				VoiceAutomationUtility.speakText("Component created");
			}
			break;
		case "button":
			component = new Button("button", componentIdentifierValue,
					Identifier.valueOf(componentIdentifier));
			VoiceAutomationUtility
					.speakText("Do you want to assign click action to the button component ?");
			confirmText = VoiceAutomationUtility.recordAction(microphone,
					recognizer,true);
			System.out.println(confirmText);
			if (confirmText.equals("ya")) {
				component.setAction(true);
				VoiceAutomationUtility.speakText("Component created");
			}
			break;
		}

		recognizer.resetMonitors();
		return component;
	}
}
