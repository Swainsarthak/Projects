import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Weather_fetching {
    //fetching weather data for a given location.
    public static JSONObject get_weather_data(String location_name){

        // getting location coordinates using the geolocation API.
        JSONArray location_data=get_location_Data(location_name);
        assert location_data != null;
        JSONObject location= (JSONObject) location_data.get(0);
        double latitude= (double) location.get("latitude");
        double longitutde= (double) location.get("longitude");


        String urlString_for_getting_weather="https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + latitude + "&longitude=" +longitutde+ "&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m&timezone=auto";
        try {
            HttpURLConnection weather_fetch_api=fetch_API_responses(urlString_for_getting_weather);
            //checking response status
            if(weather_fetch_api.getResponseCode()!=200){
                System.out.println("Error: the connection is not connected perfectly!");
                return null;
            }
            else {
                //storing the response result in the json data

                StringBuilder result_json=new StringBuilder();
                Scanner sc=new Scanner(weather_fetch_api.getInputStream());
                while (sc.hasNext()){
                    result_json.append(sc.nextLine());
                }
                sc.close();
                weather_fetch_api.connect();


                JSONParser paser=new JSONParser();
                JSONObject resultJsonobj= (JSONObject) paser.parse(String.valueOf(result_json));
                JSONObject hours= (JSONObject) resultJsonobj.get("hourly");
                JSONArray time= (JSONArray) hours.get("time");


                // got the exact index in the time list of current time
                int time_index=fetch_the_time_index(time);

                //now we will get the other weather details.


                //getting temperature details
                JSONArray temperature_data= (JSONArray) hours.get("temperature_2m");
                double temperature= (double) temperature_data.get(time_index);

                //getting weather codition
                JSONArray weather_code= (JSONArray) hours.get("weather_code");
                String weather_Condition= convert_Weather_code((long) weather_code.get(time_index));


                //getting humidity
                JSONArray humididty_data= (JSONArray) hours.get("relative_humidity_2m");
                long humidity= (long) humididty_data.get(time_index);


                //getting windspeed
                JSONArray windspeed_data= (JSONArray) hours.get("wind_speed_10m");
                double windspeed= (double) windspeed_data.get(time_index);

                JSONObject weather_final_data=new JSONObject();
                weather_final_data.put("Temperature",temperature);
                weather_final_data.put("Weather_condition",weather_Condition);
                weather_final_data.put("Humidity",humidity);
                weather_final_data.put("Windspeed",windspeed);

                return weather_final_data;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int fetch_the_time_index(JSONArray time){
        String curr_time=get_current_time();
        for (int i=0;i<time.size();i++){
            String times= (String) time.get(i);
            if (times.equalsIgnoreCase(curr_time)){
                return i;
            }
        }
        return 0;
    }
    //retrieves geographical coordinates of the location name.
    public static JSONArray get_location_Data(String location_name){
        location_name=location_name.replace(" ","+");
        String urlName="https://geocoding-api.open-meteo.com/v1/search?name="+location_name+"&count=10&language=en&format=json";

        try{
            // this class is use as HTTP request to call the API
            HttpURLConnection connection_Server=fetch_API_responses(urlName);


            //if response is not 200 then the connection is established perfectly.
            if (connection_Server.getResponseCode()!=200){
                System.out.println("Error: the connection is not connected perfectly!");
                return null;
            }
            else {
                StringBuilder json_location_file=new StringBuilder();
                Scanner input_from=new Scanner(connection_Server.getInputStream());
                while (input_from.hasNext()){
                    json_location_file.append(input_from.nextLine());

                }
                input_from.close();
                connection_Server.disconnect();

                JSONParser parser=new JSONParser();
                JSONObject result_JSON_data= (JSONObject) parser.parse(String.valueOf(json_location_file));
                return (JSONArray) result_JSON_data.get("results");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public static HttpURLConnection fetch_API_responses(String urlName){
        try {
            //attempting to get a connection
            URL uniform_resourse_locator=new URL(urlName);
            HttpURLConnection con= (HttpURLConnection) uniform_resourse_locator.openConnection();

            //get request is given to request for the location coordinates
            con.setRequestMethod("GET");
            con.connect();
            return con;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  static String get_current_time(){
        LocalDateTime localDateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH' :00 '" );
        String formated=localDateTime.format(formatter);
        return formated;
    }
    public static String convert_Weather_code(long  weather_code){
        String weather_cond="";
        if (weather_code==0L){
            weather_cond="Clear";
        } else if (weather_code>=0L && weather_code<=3L) {
            weather_cond="Cloudy";
        } else if ((weather_code>=51L && weather_code<=67L) || (weather_code>=80L && weather_code<=99L)) {
            weather_cond="Rain";
        } else if (weather_code>=71L && weather_code<=77L) {
            weather_cond="Snow";
        }
        return weather_cond;
    }
}
