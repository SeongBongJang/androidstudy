package seongbong.androidstudy.jackson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SEONGBONG on 2016-04-06.
 */
public class Person {
    private String name;
    private int age;
    private List<String> favoriteFood;
    private Map<String,String> etc;

    public Person() {

    }

    public Person(String name, int age) {
        this(name,age,new ArrayList<String>(),new HashMap<String, String>());
    }

    public Person(String name, int age, List<String> favoriteFood, Map<String, String> etc) {
        this.name = name;
        this.age = age;
        this.favoriteFood = favoriteFood;
        this.etc = etc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(List<String> favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public Map<String, String> getEtc() {
        return etc;
    }

    public void setEtc(Map<String, String> etc) {
        this.etc = etc;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", favoriteFood=" + favoriteFood +
                ", etc=" + etc +
                '}';
    }
}

