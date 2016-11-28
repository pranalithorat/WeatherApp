package webService;




import javax.jws.WebService;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

@WebService(endpointInterface = "webService.WebServiceInterface")
@XmlRootElement
public class WebServiceImpl implements WebServiceInterface{
	private DBCollection collection;
	public WebServiceImpl() {
		Mongo mongo = new Mongo("localhost", 27017);
		  DB db = mongo.getDB("yourdb");

		  // get a single collection
		  collection = db.getCollection("dummyColl");
	}
	
	public JSONObject getJSONObject(String cityName) throws JSONException {
		  BasicDBObject whereQuery = new BasicDBObject();
		  whereQuery.put("_id", cityName);
		  DBCursor cursor3 = collection.find(whereQuery);
		  System.out.println(" Query returns "+ cursor3.count());
		  DBObject found = null;
		  while (cursor3.hasNext()) {
			  found = cursor3.next();
		  }
		  return new JSONObject(JSON.serialize(found));
		
	}

	@Override
	public double findAvgTemperature(String cityName) {
		double sum = 0.0;
		int arrLength = 0;
		try {
			JSONObject obj = getJSONObject(cityName);
			JSONArray arr = obj.getJSONArray("list");
			arrLength = arr.length();
			 
			for (int i = 0; i < arrLength; i++) {
				   sum += (double) arr.getJSONObject(i).getJSONObject("temp").get("day");
			}
		}catch(Exception e) {
			System.out.println("getavg exception:");
		}
		
		return sum/arrLength;
	}

	@Override
	public double findMinSevenDays(String cityName) {
		double sum = 0.0;
		int arrLength = 0;
		try {
			JSONObject obj = getJSONObject(cityName);
			JSONArray arr = obj.getJSONArray("list");
			arrLength = arr.length();
			 
			for (int i = 0; i < arrLength; i++) {
				   sum += (double) arr.getJSONObject(i).getJSONObject("temp").get("min");
			}
		}catch(Exception e) {
			System.out.println("getMin exception:");
		}
		
		return sum/arrLength;

	}

	@Override
	public double findMaxSevenDays(String cityName) {
		double sum = 0.0;
		int arrLength = 0;
		try {
			JSONObject obj = getJSONObject(cityName);
			JSONArray arr = obj.getJSONArray("list");
			arrLength = arr.length();
			 
			for (int i = 0; i < arrLength; i++) {
				   sum += (double) arr.getJSONObject(i).getJSONObject("temp").get("max");
			}
		}catch(Exception e) {
			System.out.println("getMax exception:");
		}
		
		return sum/arrLength;

	}

	@Override
	public String getJSONString(String cityName) {
		/*JSONObject obj = null;
		try {
			obj = getJSONObject(cityName);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("getJavaObject Exception :");
			e.printStackTrace();
		}
		Gson gson = new Gson();
		System.out.println(" JAVA Object : "+gson.fromJson(obj.toString(), Object.class).toString());
		return gson.fromJson(obj.toString(), Object.class);*/
		String jsonString = null;
		try {
			jsonString = getJSONObject(cityName).toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

}
