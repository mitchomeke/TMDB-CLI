import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MovieHandler handler = new MovieHandler();
        String line;
        do {
            line = reader.readLine();
            fetchMovie(line,handler);
        }while (!line.equals("quit"));
    }

    private static void fetchMovie(String line, MovieHandler handler) throws IOException {
        Iterator<String> movies = handler.fetchMovies(line);
        while (movies.hasNext()){
            System.out.println(movies.next());
        }
    }
}