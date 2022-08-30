package helper;

import static org.junit.Assert.*;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.HelpStatus;
import model.IpAddress;
import network.NetworkFactory;


public class JsonConverter {
	public static String getJsonText(PcJson pcJson) throws JsonProcessingException{
		String jsonText = "";

		if(pcJson == null ) {
			return jsonText="null";
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonText = mapper.writeValueAsString(pcJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonText;
	}

	public static String getJsonText(List<PcJson> pcJsonList) throws JsonProcessingException{
		String jsonText = "";

		if(pcJsonList == null ) {
			return "null";
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonText = mapper.writeValueAsString(pcJsonList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonText;
	}

	public static PcJson getPcJson(String jsonText) throws JsonProcessingException{
		PcJson pcJson=null;

		if(jsonText == null ) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			pcJson = mapper.readValue(jsonText, PcJson.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return pcJson;
	}

	public static List<PcJson> getPcJsonList(String jsonText) throws JsonProcessingException{
		List<PcJson> pcJsonList=null;

		if(jsonText == null ) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			pcJsonList = mapper.readValue(jsonText, new TypeReference<>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return pcJsonList;
	}
}
