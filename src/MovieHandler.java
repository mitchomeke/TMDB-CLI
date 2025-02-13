import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MovieHandler {
    HashMap<String, ArrayList<String>> movies;
    String API_KEY = "Insert_API_KEY HERE";

    public MovieHandler(){
        movies = new HashMap<>();
    }
    public Iterator<String> fetchMovies(String type) throws IOException {
        URL url = URI.create("https://api.themoviedb.org/3/movie/"+type+"?language=en-US&page=1&api_key="+API_KEY).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        if (connection.getResponseCode()==200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            connection.disconnect();

            Gson gson = new Gson();
            JsonObject node = gson.fromJson(builder.toString(), JsonObject.class);
            JsonArray results = node.getAsJsonArray("results");

            ArrayList<String> titles = new ArrayList<>();
            for (int i = 0; i < results.size();i++){
                JsonObject movie = results.get(i).getAsJsonObject();
                String title = movie.get("title").toString();
                titles.add(title);
            }
            movies.put(type,titles);
        }
        return movies.get(type).listIterator();
    }
}
