package com.flight.flightapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import Adapter.FlightListAdapter;
import Adapter.FlightListModelClass;
import Interface.InterfaceFlightDetailsResponse;
import Networking.VolleyApplication;
import Parser.Parser;

public class MainActivity extends AppCompatActivity implements InterfaceFlightDetailsResponse {

    private RecyclerView rvflightList;
    private List<Object> flightList;
    private HashMap<String,String> airlineMap;
    private HashMap<String,String> airportMap;
    private InterfaceFlightDetailsResponse listener;
    FlightListAdapter flightListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fetchFlightList();

    }

    /**
     * Method is used for getting flight details from server
     */
    private void fetchFlightList() {
        String url ="http://blog.ixigo.com/sampleflightdata.json";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                airlineMap = Parser.makeAirLineMap(response);
                airportMap = Parser.makeAirPortMap(response);
                flightList = Parser.getFlightsData(response);
                listener.onResponse(flightList,airlineMap,airportMap);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        });

        VolleyApplication.getInstance().getRequestQueue().add(jsonRequest);
    }

    /**
     * initializing UI views
     */
    private void initView() {
        rvflightList = (RecyclerView) findViewById(R.id.listFlight);
        rvflightList.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvflightList.setLayoutManager(linearLayoutManager);
        listener = this;
    }


    @Override
    public void onResponse(List<Object> list, HashMap<String, String> airlineMap, HashMap<String, String> airportMap) {
        flightListAdapter = new FlightListAdapter(this,list,airlineMap,airportMap);
        rvflightList.setAdapter(flightListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.price:
                sortPrice();
                return true;
            case R.id.take_off:
                sortTakeOffTime();
                return true;
            case R.id.landing_time:
                sortLandIngTime();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * method is used for sorting the flight by landing time(in ascending order)
     */
    private void sortLandIngTime() {

        List<Object> tempList = new ArrayList<>();
        tempList.addAll(flightList);
        Collections.sort(tempList, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                FlightListModelClass lhsObj = (FlightListModelClass) lhs;
                FlightListModelClass rhsObj = (FlightListModelClass) rhs;
                return (int) (Long.parseLong(lhsObj.getLandingTime())- Long.parseLong(rhsObj.getLandingTime()));
            }
        });

        flightListAdapter.swapList(tempList);
        Toast.makeText(this,"Sorted According To Landing Time",Toast.LENGTH_LONG).show();
    }

    /**
     * method is used for sorting the flight by Take Off time(in ascending order)
     */
    private void sortTakeOffTime() {

        List<Object> tempList = new ArrayList<>();
        tempList.addAll(flightList);
        Collections.sort(tempList, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                FlightListModelClass lhsObj = (FlightListModelClass) lhs;
                FlightListModelClass rhsObj = (FlightListModelClass) rhs;
                return (int) (Long.parseLong(lhsObj.getTakeoffTime())- Long.parseLong(rhsObj.getTakeoffTime()));
            }
        });

        flightListAdapter.swapList(tempList);
        Toast.makeText(this,"Sorted According To Take-Off Time",Toast.LENGTH_LONG).show();
    }

    /**
     * method is used for sorting the flight by price(in ascending order)
     */
    private void sortPrice() {
        List<Object> tempList = new ArrayList<>();
        tempList.addAll(flightList);
        Collections.sort(tempList, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                FlightListModelClass lhsObj = (FlightListModelClass) lhs;
                FlightListModelClass rhsObj = (FlightListModelClass) rhs;
                return Integer.parseInt(lhsObj.getPrice())- Integer.parseInt(rhsObj.getPrice());
            }
        });

        flightListAdapter.swapList(tempList);
        Toast.makeText(this,"Sorted According To Increasing price",Toast.LENGTH_LONG).show();
    }
}
