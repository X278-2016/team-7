package org.jhipster.com.domain;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;




public class PadsetDeserializer extends StdDeserializer<Padset> {
	public PadsetDeserializer() { 
        this(null); 
    } 
 
    public PadsetDeserializer(Class<?> vc) { 
        super(vc); 
    }

	public Padset getPadsetFromJSON(File file) {
		
		 
		 Padset myPadset = null;
		 try {
			//JsonNode node = jp.getCodec().readTree(jp);

			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addDeserializer(Padset.class, new PadsetDeserializer());
			mapper.registerModule(module);
			 
			myPadset = mapper.readValue(file, Padset.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println(myPadset);
		
		
		 return null;
	}

 
   

	@Override
	public Padset deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		
		JsonNode node = jp.getCodec().readTree(jp);
		
		System.out.println(node);
		JsonNode node2 = node.get(12);
		JsonNode node3 = node2.get("General");
		JsonNode node4 = node3.get("Value");
		System.out.println(node2);
		System.out.println(node3);
		System.out.println(node4);
		
		JsonNode latitude = node4.get("Latitude [deg]");
		JsonNode longitude =  node4.get("Longitude [deg]");
		System.out.println(latitude);
		System.out.println(longitude);
		
		Double latitudeDouble = Double.parseDouble(latitude.asText());
		Double longitudeDouble = Double.parseDouble(longitude.asText());
		
		System.out.println("Latitude: " + latitudeDouble + " Longitude" + longitudeDouble);
		
		Padset myPadset = new Padset();
		myPadset.setmLatitude(latitudeDouble);
		myPadset.setmLongitude(longitudeDouble);
		myPadset.setmPadSetName("Test Padset");
		
		//Test ID, should never be used
		myPadset.setId(new Long(-1));
		System.out.println(myPadset);
		return myPadset;
	}
	
	
	
	
}
