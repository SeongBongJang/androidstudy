package seongbong.androidstudy.jackson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //request url
    private static final String REQUEST_URL = "https://api.github.com";

    //view injection
    //@Bind(R.id.result)TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /*
        jackson json data 처리 3가지 방법
        - Streaming API : 성능이 중요할 때 사용.
        - Data Binding : 편리한 사용을 원할 때 사용.
        - Tree Model : XML처럼 노드형태로 Json 데이터를 다룸. 유연성이 필요할 때 사용.
         */
        /*
        Data binding
        -Simple Data Binding means converting to and from java Maps,Lists,Strings,Nmbers,Booleans and Nulls
        -Full Data Binding means converting to and from any Java bean Type(as well as 'simple' types mentioned above)
         */
        ObjectMapper mapper = new ObjectMapper();//serialization
        //1. java String -> java Object
        String str ="{\"name\":\"장성봉\",\"age\":\"27\"}";
        try{
            Person person = mapper.readValue(str,Person.class);
            Log.d("String -> object result", person.toString());
            //2. java object -> json string
            Map<String,String> contact = new HashMap<String,String>();
            contact.put("mobile","010-7113-4083");
            contact.put("address","경기도 시흥시");
            person.setEtc(contact);

            String personStr = mapper.writeValueAsString(person);
            Log.d("Object -> String result",personStr);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(JsonParseException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }




    }
}
