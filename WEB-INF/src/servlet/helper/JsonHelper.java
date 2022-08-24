package servlet.helper;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonHelper {
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



}
