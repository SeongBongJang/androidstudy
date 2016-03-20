package seongbong.androidstudy.retrofit;

/**
 * Created by SEONGBONG on 2016-03-21.
 */
public class ResponseItem {
    private String login;
    private String url;

    public ResponseItem() {
    }

    public ResponseItem(String url, String login) {
        this.url = url;
        this.login = login;
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

    @Override
    public String toString() {
        return "ResponseItem{" +
                "login='" + login + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
