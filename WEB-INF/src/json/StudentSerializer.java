package json;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import domain.entities.Student;

public class StudentSerializer implements JsonSerializer<Student>{

	@Override
	public JsonElement serialize(Student student, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        
        jsonObject.addProperty("pcId",student.getPc().getHostName());
        jsonObject.addProperty("ipAddress",student.getPc().getIpAddress().getDisplayName());
        jsonObject.addProperty("helpStatus",student.getHelpStatus().getDisplayName());
        jsonObject.addProperty("waitingTimeBySecond",student.getWaitingTimeBySecond(LocalDateTime.now()));
        jsonObject.addProperty("handPriority",student.getPriority());

        return jsonObject;
	}
}
