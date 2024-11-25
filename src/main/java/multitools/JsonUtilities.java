package multitools;

import java.sql.ResultSet;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtilities {
	
	Gson gson = new Gson();
	
	public JsonUtilities() {
	}
	
	public <T> T fromJson(String jsonText, Class<T> classType) {
	    T object = null;
	    try {
	        object = new Gson().fromJson(jsonText, classType);
	    } catch (JsonSyntaxException e) {
	        e.printStackTrace();
	    }
	    return object;
	}
	
	
	
	public String parse(Object object) {

		return "";
	}

	public String paraJson(ResultSet resultSet) {
		String json = gson.toJson(resultSet);
		return json;
	}
	
	public String parse(HashMap<String, String> hashMap) {

		return "";
	}
	
	public String parse(String[]  values) {
		return "";
	}
	
	public String parse(String[][]  values) {

		return "";
	}	
}
