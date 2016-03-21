package seongbong.androidstudy.okhttp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SEONGBONG on 2016-03-22.
 */
public class ResponseItems {
    private List<ResponseItem> responseItemList;

    public ResponseItems() {
        this(new ArrayList<ResponseItem>());
    }
    public ResponseItems(ResponseItems responseItemList){
        for(ResponseItem responseItem : responseItemList.getResponseItemList()){
            this.responseItemList.add(responseItem);
        }
    }
    public ResponseItems(List<ResponseItem> responseItemList) {
        this.responseItemList = responseItemList;
    }

    public List<ResponseItem> getResponseItemList() {
        return responseItemList;
    }

    public void setResponseItemList(List<ResponseItem> responseItemList) {
        this.responseItemList = responseItemList;
    }
    public int size(){
        return this.responseItemList.size();
    }
    @Override
    public String toString() {
        return "ResponseItems{" +
                "responseItemList=" + responseItemList +
                '}';
    }
}
