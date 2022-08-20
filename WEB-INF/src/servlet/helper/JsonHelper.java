package servlet.helper;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.PcJson;

public class JsonHelper {

	public static String getJsonText(Pc pc) throws JsonProcessingException{
		String jsonText = "";

		if(pcJson == null ) {
			return jsonText="null";
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonText = mapper.writeValueAsString(pc);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonText;
	}

	public static String getJsonText(List<Pc> pcList) throws JsonProcessingException{
		String jsonText = "";

		if(pcJsonList == null ) {
			return "null";
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonText = mapper.writeValueAsString(pcList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonText;
	}



}
