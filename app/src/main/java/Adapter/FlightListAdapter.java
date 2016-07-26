package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flight.flightapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Parser.Parser;

/**
 * Created by ramakant on 26/7/16.
 */
public class FlightListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> flightList;
    private Context mContext;
    private Parser parser;
    private HashMap<String,String> airlineMap;
    private HashMap<String,String> airportMap;

    public FlightListAdapter(Context context, List<Object> list,HashMap<String, String> airlineMap, HashMap<String, String> airportMap){
        this.mContext = context;
        this.flightList = list;
        this.airlineMap = airlineMap;
        this.airportMap = airportMap;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_flight_list_item,parent,false);
        return new FlightListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FlightListViewHolder flightListViewHolder = (FlightListViewHolder) holder;
        FlightListModelClass object = (FlightListModelClass) flightList.get(position);

        flightListViewHolder.getFlightTakeOffTime().setText("T" +getTime(object.getTakeoffTime()));
        flightListViewHolder.getFlightLandingTime().setText("L" +getTime(object.getLandingTime()));
        flightListViewHolder.getFlightTicketFare().setText(object.getPrice()+"rs");

        flightListViewHolder.getFlightOrigin().setText(airportMap.get(object.getOriginCode()));
        flightListViewHolder.getFlightDestination().setText(airportMap.get(object.getDestinationCode()));
        flightListViewHolder.getFlightName().setText(airlineMap.get(object.getAirlineCode()));

        flightListViewHolder.getFlightTicketClass().setText(object.getClas());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public void swapList(List<Object> list){
        this.flightList = list;
        notifyDataSetChanged();
    }

    /**
     * function is used for converting milliseconds to time
     * @param seconds
     * @return time
     */
    private String getTime(String seconds){
        long time = Long.parseLong(seconds);
        Date date = new Date(time);
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        String formatted = formatter.format(date );
        return formatted;
    }
}
