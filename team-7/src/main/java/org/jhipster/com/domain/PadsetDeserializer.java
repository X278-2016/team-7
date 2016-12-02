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
		 
		 //System.out.println(myPadset);
		
		
		 return myPadset;
	}
	
	
	public Padset getPadsetFromString(String string) {
		
		 
		 Padset myPadset = null;
		 try {
			//JsonNode node = jp.getCodec().readTree(jp);

			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addDeserializer(Padset.class, new PadsetDeserializer());
			mapper.registerModule(module);
			 
			myPadset = mapper.readValue(string, Padset.class);
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
		 
		 //System.out.println(myPadset);
		
		
		 return myPadset;
	}

 
   

	@Override
	public Padset deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		
		
		JsonNode node = jp.getCodec().readTree(jp);
		
		//System.out.println(node);
		
		//Gets Location
		JsonNode first_name = node.get("first_name");
		JsonNode node3 = first_name.get("General");
		JsonNode node4 = node3.get("Value");
		//System.out.println(first_name);
		//System.out.println(node3);
		//System.out.println(node4);
		
		JsonNode latitude = node4.get("Latitude [deg]");
		JsonNode longitude =  node4.get("Longitude [deg]");
		//System.out.println(latitude);
		//System.out.println(longitude);
		
		Double latitudeDouble = Double.parseDouble(latitude.asText());
		Double longitudeDouble = Double.parseDouble(longitude.asText());
		
		//System.out.println("Latitude: " + latitudeDouble + " Longitude" + longitudeDouble);
		
		Padset myPadset = new Padset();
		myPadset.setmLatitude(latitudeDouble);
		myPadset.setmLongitude(longitudeDouble);
		myPadset.setmPadSetName("Test Padset");
		
		//Cooling Coils
		myPadset.setCoolingCoilsNominalSensibleHeatRatio(first_name.get("Cooling Coils").get("Nominal Sensible Heat Ratio").get("DETAILED COOLING COIL").asDouble());
		myPadset.setCoolingCoilsNominalTotalCapacity(first_name.get("Cooling Coils").get("Nominal Total Capacity [W]").get("DETAILED COOLING COIL").asDouble());
		//Heating Coils
		myPadset.setHeatingCoilsNominalTotalCapacityReheatCoilZone1(first_name.get("Heating Coils").get("Nominal Total Capacity [W]").get("REHEAT COIL ZONE 1").asDouble());
		myPadset.setHeatingCoilsNominalTotalCapacityReheatCoilZone2(first_name.get("Heating Coils").get("Nominal Total Capacity [W]").get("REHEAT COIL ZONE 2").asDouble());
		myPadset.setHeatingCoilsNominalTotalCapacityReheatCoilZone3(first_name.get("Heating Coils").get("Nominal Total Capacity [W]").get("REHEAT COIL ZONE 3").asDouble());
		//Fans
		myPadset.setFanMaxAirFlowRate(first_name.get("Fans").get("Max Air Flow Rate [m3/s]").get("SUPPLY FAN 1").asDouble());
		myPadset.setFanMotorHeatInAirFraction(first_name.get("Fans").get("Motor Heat In Air Fraction").get("SUPPLY FAN 1").asDouble());
		myPadset.setFanRatedElectricPower(first_name.get("Fans").get("Rated Electric Power [W]").get("SUPPLY FAN 1").asDouble());
		myPadset.setFanRatedPowerPerMaxAirFlowRate(first_name.get("Fans").get("Rated Power Per Max Air Flow Rate [W-s/m3]").get("SUPPLY FAN 1").asDouble());
		myPadset.setFanTotalEfficiency(first_name.get("Fans").get("Total Efficiency [W/W]").get("SUPPLY FAN 1").asDouble());
		//pumps
		myPadset.setPumpsPowerPerWaterFlowRateCircPump(first_name.get("Pumps").get("Power Per Water Flow Rate [W-s/m3]").get("CIRC PUMP").asDouble());
		myPadset.setPumpsPowerPerWaterFlowRateCondCircPump(first_name.get("Pumps").get("Power Per Water Flow Rate [W-s/m3]").get("COND CIRC PUMP").asDouble());
		myPadset.setPumpsPowerPerWaterFlowRateHwCircPump(first_name.get("Pumps").get("Power Per Water Flow Rate [W-s/m3]").get("HW CIRC PUMP").asDouble());
		
		myPadset.setPumpsHeadCircPump(first_name.get("Pumps").get("Head [pa]").get("CIRC PUMP").asDouble());
		myPadset.setPumpsHeadCondCircPump(first_name.get("Pumps").get("Head [pa]").get("COND CIRC PUMP").asDouble());
		myPadset.setPumpsHeadHwCircPump(first_name.get("Pumps").get("Head [pa]").get("HW CIRC PUMP").asDouble());
		
		myPadset.setPumpsElectricPowerCircPump(first_name.get("Pumps").get("Electric Power [W]").get("CIRC PUMP").asDouble());
		myPadset.setPumpsElectricPowerCondCircPump(first_name.get("Pumps").get("Electric Power [W]").get("COND CIRC PUMP").asDouble());
		myPadset.setPumpsPowerPerWaterFlowRateHwCircPump(first_name.get("Pumps").get("Electric Power [W]").get("HW CIRC PUMP").asDouble());
		
		myPadset.setPumpsMotorEfficiencyCircPump(first_name.get("Pumps").get("Motor Efficiency [W/W]").get("CIRC PUMP").asDouble());
		myPadset.setPumpsMotorEfficiencyCondCircPump(first_name.get("Pumps").get("Motor Efficiency [W/W]").get("COND CIRC PUMP").asDouble());
		myPadset.setPumpsMotorEfficiencyHwCircPump(first_name.get("Pumps").get("Motor Efficiency [W/W]").get("HW CIRC PUMP").asDouble());
		
		myPadset.setPumpsWaterFlowCircPump(first_name.get("Pumps").get("Water Flow [m3/s]").get("CIRC PUMP").asDouble());
		myPadset.setPumpsWaterFlowCondCircPump(first_name.get("Pumps").get("Water Flow [m3/s]").get("COND CIRC PUMP").asDouble());
		myPadset.setPumpsWaterFlowHwCircPump(first_name.get("Pumps").get("Water Flow [m3/s]").get("HW CIRC PUMP").asDouble());
		
		
		
		
		
		
		
		
		
		
		//System.out.println(myPadset);
		return myPadset;
	}
	
	
	
	
}
