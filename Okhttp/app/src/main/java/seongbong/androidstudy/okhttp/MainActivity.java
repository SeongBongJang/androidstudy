package seongbong.androidstudy.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //request url
    private static final String REQUEST_URL = "https://api.github.com/repos/square/retrofit/contributors";
    private static final String REQUEST2_URL = "https://api.github.com/users/codepath";
    private static final String REQUEST3_URL = "https://api.github.com/repos/seongbongjang/androidStudy";

    //view injection
    @Bind(R.id.request_btn) Button requestBtn;
    @Bind(R.id.response_status) TextView responseStatus;
    @Bind(R.id.response_result) RecyclerView responseResult;

    //OkHttpClient
    OkHttpClient client;

    //handler
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHandler = new Handler(Looper.getMainLooper());
        /**
         * Sending and Receiving Network Requests
         * Application interceptors
         *      Don't need to worry about intermediate responses like redirects and retries.
         *      Are always invoked once, even if the HTTP response is served from the cache.
         *      Observe the application's original intent. Unconcerned with OkHttp-injected headers like If-None-Match.
         *      Permitted to short-circuit and not call Chain.proceed().
         *      Permitted to retry and make multiple calls to Chain.proceed().
         * Network Interceptors
         *      Able to operate on intermediate responses like redirects and retries.
         *      Not invoked for cached responses that short-circuit the network.
         *      Observe the data just as it will be transmitted over the network.
         *      Access to the Connection that carries the request.
         */
        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Log.d("intercepter",request.toString());
                        okhttp3.Response response = chain.proceed(request);
                        return response;
                    }
                })
                .build();


    }

    @OnClick(R.id.request_btn)
    public void requestBtnOnclick(){
        Request request = new Request.Builder()
                .url(REQUEST_URL)
                .build();
        /**
         * If there are any query parameters that need to be added
         * httpUrl class provided by OkHttp can be leveraged to construct the URL
         * */
        /*
        HttpUrl.Builder urlBuilder = HttpUrl.parse(REQUEST_URL).newBuilder();
        urlBuilder.addQueryParameter("owner","seongbongjang");
        urlBuilder.addQueryParameter("repo","androidStudy");
        String url = urlBuilder.build().toString();

        Request request2 = new Request.Builder()
                .url(url)
                .build();
        */
        /**
         * If there are any authenticated query parameters,
         * headers can be added to the request too:
        */
         /*Request request3 = new Request.Builder()
                //.header("Authorization", "token abcd")
                .url(REQUEST3_URL)
                .build();
        */

        //Synchronous Network Calls
        /*try{
            Response response = client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }*/

        //Asynchronous Network Calls
        /**
         * We can also make asynchronous network calls too by creating a Call object,
         * using the enqueue() method,
         * and passing an anonymous Callback object that implements both onFailure() and onResponse()
         */
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //Processing JSON data
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if(!response.isSuccessful()){
                    throw new IOException("Unexpected code " + response);
                }else{
                    //The header responses
                    Headers responseHeaders = response.headers();
                    for(int i = 0; i<responseHeaders.size();i++){
                        Log.d("responseHeaders", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    /**
                     * Processing JSON data with Gson
                     * Note that the string() method on the response body will load the entire data into memory.
                     * To make more efficient use of memory,
                     * it is recommended that the response be processed as a stream by using charStream() instead.
                     */
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<ResponseItem>>(){}.getType();
                    final List<ResponseItem> contributors = gson.fromJson(response.body().charStream(),type);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseStatus.setText(response.message());
                            setContent(contributors);
                        }
                    });
                }
            }
        });
    }

    private void setContent(List<ResponseItem> contributors){
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        responseResult.setLayoutManager(layoutManager);
        responseResult.setAdapter(new RecyclerViewAdapter(MainActivity.this,contributors));
    }
}
