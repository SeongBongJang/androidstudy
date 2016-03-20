package seongbong.androidstudy.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //request url
    private static final String REQUEST_URL = "https://api.github.com";

    //view injection
    @Bind(R.id.request_btn) Button requestBtn;
    @Bind(R.id.response_status) TextView responseStatus;
    @Bind(R.id.response_result) RecyclerView responseResult;

    //service
    RequestService requestService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REQUEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build(); //callbackExecutor -> api call을 다른 스레드에서 하고 main에 넘겨줄떄...?즉, 돌아가는 곳 설정
        requestService = retrofit.create(RequestService.class);
    }
    @OnClick(R.id.request_btn)
    public void requestBtnOnClick(){
        //Call = okhttp를 가지고 있는 하나의 인터페이스
        Call<List<ResponseItem>> call = requestService.requestItemContributors("seongbongjang", "androidStudy");
        call.enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, Response<List<ResponseItem>> response) {
                List<ResponseItem> contributors = response.body();
                if(response.message().toLowerCase().equals("ok")){
                    responseStatus.setText(response.message());
                    setContent(contributors);
                }else{
                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
            }
        });
    }
    private void setContent(List<ResponseItem> contributors){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        responseResult.setLayoutManager(layoutManager);
        responseResult.setAdapter(new RecyclerViewAdapter(MainActivity.this,contributors));
    }
}
