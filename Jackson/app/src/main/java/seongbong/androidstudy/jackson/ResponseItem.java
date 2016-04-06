package seongbong.androidstudy.jackson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by SEONGBONG on 2016-04-06.
 */
public class ResponseItem {
    private String login;
    private String url;


    public ResponseItem() {
        this("login","url");
    }
    public ResponseItem(String login, String url) {
        this.login = login;
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResponseItem{" +
                "login='" + login + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
