package json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import domain.entities.Student;

public class JsonHelper {

	public static String getJsonForTeacher(List<Student> studentList, List<Student> handupStudentList)
			throws JsonProcessingException {
		
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentJsonSerializer());
        Gson gson = builder.create();

		JsonObject jsonObject = new JsonObject();

		jsonObject.add("studentList", gson.toJsonTree(studentList).getAsJsonArray());
		jsonObject.add("handupStudentList", gson.toJsonTree(handupStudentList).getAsJsonArray());

		return gson.toJson(jsonObject);
	}


	public static String getJsonForStudent(Student student, List<Student> handupStudentList)
			throws JsonProcessingException {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentJsonSerializer());
        Gson gson = builder.create();

        GsonBuilder builderWithoutPcInfo = new GsonBuilder();
        builderWithoutPcInfo.registerTypeAdapter(Student.class, new StudentJsonWithoutPcInfoSerializer());
        Gson gsonWithoutPcInfo = builderWithoutPcInfo.create();

		JsonObject jsonObject = new JsonObject();

		jsonObject.add("clientStudent", gson.toJsonTree(student));
		jsonObject.add("handupStudentList", gsonWithoutPcInfo.toJsonTree(handupStudentList).getAsJsonArray());

		return gson.toJson(jsonObject);
	}
	
	public static Map<Student, String> getJsonListForStudent(Set<Student> connectStudentList, List<Student> studentList,
			List<Student> handupStudentList) throws JsonProcessingException {

		Map<Student, String> jsonMap = new HashMap<Student, String>();

		for (Student student : studentList) {
			if (connectStudentList.contains(student)) {
				String jsonText = getJsonForStudent(student, handupStudentList);
				jsonMap.put(student, jsonText);
			}
		}
		return jsonMap;
	}


}
