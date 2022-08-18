package servlet;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.PcJson;

public class JsonHelper {
	public static String getJsonList(List<PcJson> pcJsonList) throws JsonProcessingException{
		String jsonList = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonList = mapper.writeValueAsString(pcJsonList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonList;
	}

}
