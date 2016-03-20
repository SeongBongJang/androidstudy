package seongbong.androidstudy.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SEONGBONG on 2016-03-21.
 */
public interface RequestService {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<ResponseItem>> requestItemContributors(@Path("owner") String user,@Path("repo") String repo);
}
