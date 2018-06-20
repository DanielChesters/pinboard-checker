package science.coincoin.pinboardChecker;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import science.coincoin.pinboardChecker.api.PinboardServices;
import science.coincoin.pinboardChecker.model.Post;
import science.coincoin.pinboardChecker.model.URL;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Daniel Chesters (on 20/06/2018).
 */
public class PinboardChecker {

    public static void main(String[] args) throws IOException {

        String authToken = System.getenv("PINBOARD_AUTH_TOKEN");

        if (authToken == null || authToken.isEmpty()) {
            System.err.println("The PINBOARD_AUTH_TOKEN env variable must be set");
            System.exit(1);
        }

        PinboardChecker pinboardChecker = new PinboardChecker();

        final List<Post> allPosts = pinboardChecker.getAllPosts(authToken);
        List<URL> allURLs =
            allPosts.stream().map(post -> new URL(post.getHref())).collect(Collectors.toList());

        pinboardChecker.checkURLs(allURLs);

        pinboardChecker.showDeadURLs(allURLs);
    }

    public void showDeadURLs(List<URL> allURLs) {
        allURLs.stream()
            .filter(url -> !url.isSuccessful() && url.getStatus() != 405)
            .forEach(url -> System.out.println(
                String.format("%s : %d(%s)", url.getHref(), url.getStatus(), url.getMessage())));
    }

    public void checkURLs(List<URL> URLs) {
        OkHttpClient client = new OkHttpClient();
        URLs.forEach(url -> {
            Request request =
                new Request.Builder()
                    .url(url.getHref())
                    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0")
                    .head().build();
            final Response response;
            try {
                response = client.newCall(request).execute();
                url.setStatus(response.code());
                url.setMessage(response.message());
                url.setSuccessful(response.isSuccessful());
            } catch (IOException e) {
                url.setStatus(999);
                url.setMessage(e.getMessage());
                url.setSuccessful(false);
            }
        });
    }

    public List<Post> getAllPosts(String authToken) throws IOException {
        PinboardServices pinboardServices = createPinboardServices();
        return pinboardServices.getAllPosts(authToken).execute().body();
    }

    private PinboardServices createPinboardServices() {
        return new Retrofit.Builder()
            .baseUrl("https://api.pinboard.in/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PinboardServices.class);
    }

}
