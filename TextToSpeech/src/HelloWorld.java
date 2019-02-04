import javax.speech.Central;
import javax.speech.EngineList;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class HelloWorld {
	public static void main(String args[]) {

		try {
			EngineList list = Central.availableSynthesizers(null);
			if (list.isEmpty()) {
				System.out.println("No Engines Available");
			} else {
				for (int i = 0; i < list.size(); i++) {
					SynthesizerModeDesc desc = (SynthesizerModeDesc) list
							.get(i);
					Voice[] voices = desc.getVoices();
					for (int j = 0; j < voices.length; j++) {
						System.out.println(voices[j].getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}