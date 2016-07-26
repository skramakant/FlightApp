package Interface;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ramakant on 26/7/16.
 */
public interface InterfaceFlightDetailsResponse {

    void onResponse(List<Object> list, HashMap<String, String> airlineMap, HashMap<String, String> airportMap);
}
