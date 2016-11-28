package rmiServer;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class RMIServant extends UnicastRemoteObject implements Hello {

	DBCollection collection;
	protected RMIServant() throws RemoteException {
		try {

			@SuppressWarnings("deprecation")
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("yourdb");
			
			collection = db.getCollection("dummyColl");
		} catch(Exception e) {
			
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getContent(String cityName, int days) throws RemoteException {
		try{
			if (cityName.contains(" ")) {
				cityName = cityName.replace(" ", "%20");
			}
			Reader reader = new InputStreamReader(new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+cityName+"&mode=json&units=metric&cnt="+days+"&appid=07dd10df3892862edc84fd6fce30e819").openStream()); //Read the json output
			Gson gson = new GsonBuilder().create();
			 
			Object obj = gson.fromJson(reader, Object.class);
			JSONObject json = new JSONObject(obj.toString());
			System.out.println("Json "+ json.toString());
			DBObject dbObject = (DBObject) JSON.parse(json.toString());
			System.out.println("dbObject "+dbObject.toString());
			dbObject.put("_id",json.getJSONObject("city").get("name").toString() );
			System.out.println("id : "+ dbObject.get("_id") + "and "+ json.toString());
			//System.out.println("city name is " + "\"city\":"+json.getJSONObject("city").toString());
			DBObject removeObject = (DBObject) JSON.parse("{\"_id\":\""+json.getJSONObject("city").get("name").toString()+"\"}");			
			collection.remove(removeObject);
			collection.insert(dbObject);
			System.out.println(json.toString());
			return (obj);
		}catch(Exception e){
			System.out.println(e);

			return null;
		}
	}

	

}
