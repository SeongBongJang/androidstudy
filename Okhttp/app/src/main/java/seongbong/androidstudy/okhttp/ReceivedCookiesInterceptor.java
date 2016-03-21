package seongbong.androidstudy.okhttp;

import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by SEONGBONG on 2016-03-22.
 */
public class ReceivedCookiesInterceptor implements Interceptor{
    private static final String TAG = "Interceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originResponse = chain.proceed(chain.request());

        if(!originResponse.headers("Set-Cookie").isEmpty()){
            HashSet<String> cookies = new HashSet<>();
            for(String header : originResponse.headers("Set-Cookie")){
                cookies.add(header);
            }

        }else{
            Log.i(TAG, "ERROR");
        }
        return originResponse;
    }
}
