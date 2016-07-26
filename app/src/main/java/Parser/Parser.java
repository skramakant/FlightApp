package Parser;

import android.content.Context;
import android.widget.AutoCompleteTextView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.FlightListModelClass;

/**
 * Created by ramakant on 26/7/16.
 */
public class Parser {

    private Context mContext;
    private static String AIRLINE_MAP = "airlineMap";
    private static String AIRPORT_MAP = "airportMap";
    private static String FLIGHT_DATA = "flightsData";


    public static HashMap<String,String> makeAirLineMap(JSONObject object){
        JSONObject jsonObject = null;
        try {
            jsonObject = object.getJSONObject(AIRLINE_MAP);
            HashMap<String,String> addAirline = new HashMap<>();
            addAirline.put("SJ", jsonObject.getString("SJ"));
            addAirline.put("AI",jsonObject.getString("AI"));

            addAirline.put("G8", jsonObject.getString("G8"));
            addAirline.put("JA",jsonObject.getString("JA"));
            addAirline.put("IN",jsonObject.getString("IN"));
            return addAirline;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String,String> makeAirPortMap(JSONObject object){
        JSONObject jsonObject = null;
        try {
            jsonObject = object.getJSONObject(AIRPORT_MAP);
            HashMap<String,String> addAirport = new HashMap<>();
            addAirport.put("DEL", jsonObject.getString("DEL"));
            addAirport.put("MUM",jsonObject.getString("MUM"));
            return addAirport;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Object> getFlightsData(JSONObject response){
        try {
            JSONArray jsonArray = response.getJSONArray(FLIGHT_DATA);
            if(jsonArray == null) return null;

            List<Object> makeList = new ArrayList<>();
            Gson gson = new Gson();

            for (int i=0; i<jsonArray.length();i++){

                JSONObject object = jsonArray.getJSONObject(i);

                FlightListModelClass flightListModelClass = new FlightListModelClass();
                flightListModelClass.setClas(object.getString("class"));
                flightListModelClass.setAirlineCode(object.getString("airlineCode"));
                flightListModelClass.setDestinationCode(object.getString("destinationCode"));
                flightListModelClass.setLandingTime(object.getString("landingTime"));
                flightListModelClass.setOriginCode(object.getString("originCode"));
                flightListModelClass.setPrice(object.getString("price"));
                flightListModelClass.setTakeoffTime(object.getString("takeoffTime"));

                //Object result = gson.fromJson(object, FlightListModelClass.class);
                makeList.add(flightListModelClass);
            }
            return makeList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
