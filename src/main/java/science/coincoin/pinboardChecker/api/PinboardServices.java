package science.coincoin.pinboardChecker.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import science.coincoin.pinboardChecker.model.Post;

import java.util.List;

/**
 * @author Daniel Chesters (on 20/06/2018).
 */
public interface PinboardServices {

    @GET("posts/all?format=json")
    Call<List<Post>> getAllPosts(@Query("auth_token") String authToken);

}
