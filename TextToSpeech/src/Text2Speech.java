import java.util.Locale;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import com.sun.speech.freetts.VoiceManager;

public class Text2Speech    
{    
String speaktext; 
public void dospeak(String speak,String  voicename)    
{    
    speaktext=speak;    
    String voiceName =voicename;    
    try    
    {    
        SynthesizerModeDesc desc = new SynthesizerModeDesc(null,"general",  Locale.US,null,null);    
        Synthesizer synthesizer =  Central.createSynthesizer(desc);    
        synthesizer.allocate();    
        synthesizer.resume();     
        desc = (SynthesizerModeDesc)  synthesizer.getEngineModeDesc();     
        Voice[] voices = desc.getVoices();      
        Voice voice = null;
        for (int i = 0; i < voices.length; i++)    
        {    
            if (voices[i].getName().equals(voiceName))    
            {    
                voice = voices[i];    
                break;     
            }     
        }    
        synthesizer.getSynthesizerProperties().setVoice(voice);    
        System.out.print("Speaking : "+speaktext);    
        synthesizer.speakPlainText(speaktext, null);    
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);    
        synthesizer.deallocate();    
    }    
    catch (Exception e)   
    {    
        String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";    
        System.out.println(""+e);    
        System.out.println(message);    
    }    
}    

public static void main(String[] args)    
{    
    //Text2Speech obj=new Text2Speech(); obj.dospeak("Hello i am kevin ","kevin16");
	
	com.sun.speech.freetts.Voice voice;
	String VOICENAME = "kevin16";   
	VoiceManager vm = VoiceManager.getInstance();
	voice = vm.getVoice(VOICENAME);
	voice.allocate();
	try{
		VoiceManager voiceManager = VoiceManager.getInstance();
	     // Get all available voices
	     com.sun.speech.freetts.Voice[] voices = voiceManager.getVoices();
	     for (int i = 0; i < voices.length; i++) {
	       System.out.println(voices[i].getName());
	     }
	     
	//voice.speak("Hi Mr Gaur Welcome to VITS. Thanks To choose Us");
	}catch(Exception e){}
}    
}