package Interface;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ramakant on 26/7/16.
 */
public interface InterfaceFlightDetailsResponse {
    /**
     * Call Back Method, that will be call after getting network response
     * @param list
     * @param airlineMap
     * @param airportMap
     */
    void onResponse(List<Object> list, HashMap<String, String> airlineMap, HashMap<String, String> airportMap);
}
