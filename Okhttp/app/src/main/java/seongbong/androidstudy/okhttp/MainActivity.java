package seongbong.androidstudy.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Application interceptors

 - Don't need to worry about intermediate responses like redirects and retries.
 - Are always invoked once, even if the HTTP response is served from the cache.
 - Observe the application's original intent. Unconcerned with OkHttp-injected headers like If-None-Match.
 - Permitted to short-circuit and not call Chain.proceed().
 - Permitted to retry and make multiple calls to Chain.proceed().

 Network Interceptors

 - Able to operate on intermediate responses like redirects and retries.
 - Not invoked for cached responses that short-circuit the network.
 - Observe the data just as it will be transmitted over the network.
 - Access to the Connection that carries the request.
 */
public class MainActivity extends AppCompatActivity {
    private static final String REQUEST_URL = "http://publicobject.com/helloworld.txt";
    private static final String REQUEST2_URL = "https://ajax.googleapis.com/ajax/services/search/images";
    private static final String REQUEST3_URL = "https://api.github.com/users/codepath";

    @Bind(R.id.response_result) TextView responseResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /**
         * Sending and Receiving Network Requests
         * should be a singleton
         */
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(REQUEST_URL)
                .build();
        /**
         * If there are any query parameters that need to be added
         * httpUrl class provided by OkHttp can be leveraged to construct the URL
         */
        HttpUrl.Builder urlBuilder = HttpUrl.parse(REQUEST2_URL).newBuilder();
        urlBuilder.addQueryParameter("v","1.0");
        urlBuilder.addQueryParameter("q","android");
        urlBuilder.addQueryParameter("rsz", "8");
        String url = urlBuilder.build().toString();

        Request request2 = new Request.Builder()
                .url(REQUEST_URL)
                .build();

        /**
         * If there are any authenticated query parameters,
         * headers can be added to the request too:
         */
        Request request3 = new Request.Builder()
                //.header("Authorization", "token abcd")
                .url(REQUEST3_URL)
                .build();

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
        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //check for failure using `isSuccessful` before proceeding
                //Calling isSuccessful() for instance if the code returned a status code of 2XX (i.e. 200, 201, etc.)
                if(!response.isSuccessful()){
                    throw new IOException("Unexpected code " + response);
                }


                //Run view-related code back on the main thread
                MainActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        //The header responses
                        Headers responseHeaders = response.headers();
                        for(int i = 0; i<responseHeaders.size();i++){
                            Log.d("responseHeaders", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }
                        try{
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);
                            Log.d("r1, responseData",json.toString());
                        }catch (IOException e){
                            e.printStackTrace();
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        //setContent
                        //responseResult.setText(responseData);
                    }
                });
            }
        });
        client.newCall(request3).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            //Processing JSON data
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);
                    final String owner = json.getString("name");
                    Log.d("r3 json data",json.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
