package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flight.flightapp.R;

/**
 * Created by ramakant on 26/7/16.
 */
public class FlightListViewHolder extends RecyclerView.ViewHolder {

    public TextView getFlightTicketClass() {
        return flightTicketClass;
    }

    public void setFlightTicketClass(TextView flightTicketClass) {
        this.flightTicketClass = flightTicketClass;
    }

    public TextView getFlightTicketFare() {
        return flightTicketFare;
    }

    public void setFlightTicketFare(TextView flightTicketFare) {
        this.flightTicketFare = flightTicketFare;
    }

    public TextView getFlightLandingTime() {
        return flightLandingTime;
    }

    public void setFlightLandingTime(TextView flightLandingTime) {
        this.flightLandingTime = flightLandingTime;
    }

    public TextView getFlightTakeOffTime() {
        return flightTakeOffTime;
    }

    public void setFlightTakeOffTime(TextView flightTakeOffTime) {
        this.flightTakeOffTime = flightTakeOffTime;
    }

    public TextView getFlightName() {
        return flightName;
    }

    public void setFlightName(TextView flightName) {
        this.flightName = flightName;
    }

    public TextView getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(TextView flightDestination) {
        this.flightDestination = flightDestination;
    }

    public TextView getFlightOrigin() {
        return flightOrigin;
    }

    public void setFlightOrigin(TextView flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    private TextView flightOrigin;
    private TextView flightDestination;
    private TextView flightName;

    private TextView flightTakeOffTime;
    private TextView flightLandingTime;
    private TextView flightTicketFare;

    private TextView flightTicketClass;


    public FlightListViewHolder(View itemView) {
        super(itemView);

        flightOrigin = (TextView) itemView.findViewById(R.id.flight_origin);
        flightDestination = (TextView) itemView.findViewById(R.id.flight_destination);
        flightName = (TextView) itemView.findViewById(R.id.flight_name);

        flightTakeOffTime = (TextView) itemView.findViewById(R.id.flight_take_off_time);
        flightLandingTime = (TextView) itemView.findViewById(R.id.flight_landing_time);
        flightTicketFare = (TextView) itemView.findViewById(R.id.flight_ticket_price);

        flightTicketClass = (TextView) itemView.findViewById(R.id.flight_ticket_class);
    }
}
