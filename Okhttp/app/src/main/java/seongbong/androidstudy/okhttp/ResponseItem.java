package seongbong.androidstudy.okhttp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SEONGBONG on 2016-03-22.
 */
public class ResponseItem {
    @SerializedName("login")
    private String item1;
    @SerializedName("url")
    private String item2;

    public ResponseItem() {
        this("item1","item2");
    }

    public ResponseItem(String item1, String item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public String getItem1() {
        return item1;
    }

    @Override
    public String toString() {
        return "ResponseItem{" +
                "item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                '}';
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }
}
