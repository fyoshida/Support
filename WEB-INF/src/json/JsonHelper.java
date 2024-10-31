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
		
		// 通常の学生用Gsonを生成
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentSerializer());
        Gson gson = builder.create();

        // Jsonオブジェクトを生成
		JsonObject jsonObject = new JsonObject();

		jsonObject.add("studentList", gson.toJsonTree(studentList).getAsJsonArray());
		jsonObject.add("handupStudentList", gson.toJsonTree(handupStudentList).getAsJsonArray());

		return gson.toJson(jsonObject);
	}


	public static String getJsonForStudent(Student student, List<Student> handupStudentList)
			throws JsonProcessingException {

		// 通常の学生用Gsonを生成
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentSerializer());
        Gson gson = builder.create();

        // PC情報を除く学生用GSONを生成
        GsonBuilder builderWithoutPcInfo = new GsonBuilder();
        builderWithoutPcInfo.registerTypeAdapter(Student.class, new StudentWithoutPcInfoSerializer());
        Gson gsonWithoutPcInfo = builderWithoutPcInfo.create();

        // Jsonオブジェクトを生成
		JsonObject jsonObject = new JsonObject();

		jsonObject.add("clientStudent", gson.toJsonTree(student));
		jsonObject.add("handupStudentList", gsonWithoutPcInfo.toJsonTree(handupStudentList).getAsJsonArray());

		return gson.toJson(jsonObject);
	}
	
	public static Map<Student, String> getJsonListForStudent(Set<Student> connectStudentList, List<Student> studentList,
			List<Student> handupStudentList) throws JsonProcessingException {

		Map<Student, String> jsonMap = new HashMap<Student, String>();

        // 接続している学生ごとに送信用のJSONを生成
		for (Student student : studentList) {
			if (connectStudentList.contains(student)) {
				String jsonText = getJsonForStudent(student, handupStudentList);
				jsonMap.put(student, jsonText);
			}
		}
		
		return jsonMap;
	}


}
