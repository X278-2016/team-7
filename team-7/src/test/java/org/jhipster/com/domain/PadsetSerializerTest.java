package org.jhipster.com.domain;

import java.io.File;
import java.io.File.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

public class PadsetSerializerTest {
	public static void main(String args[]) throws IOException {
		PadsetDeserializer mPadsetDeserializer = new PadsetDeserializer();
		File testFile = new File("mockData.json");
		mPadsetDeserializer.getPadsetFromJSON(testFile);
		
		
		
		String stringFromFile = readFile("mockData.json", Charset.defaultCharset());
		
		Padset m = mPadsetDeserializer.getPadsetFromString(stringFromFile);
		
		
		System.out.println(m);
	}
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
	{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
	}

}
