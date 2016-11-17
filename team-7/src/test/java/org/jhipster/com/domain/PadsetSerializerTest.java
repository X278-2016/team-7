package org.jhipster.com.domain;

import java.io.File;
import java.io.File.*;

public class PadsetSerializerTest {
	public static void main(String args[]){
		PadsetDeserializer mPadsetDeserializer = new PadsetDeserializer();
		File testFile = new File("mockData.json");
		mPadsetDeserializer.getPadsetFromJSON(testFile);
		
		
	}

}
