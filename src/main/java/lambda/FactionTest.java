package lambda;


import jdk.nashorn.internal.parser.JSONParser;

import java.util.function.Function;

/**
 * @author ：huangxin
 * @modified ：
 * @since ：2020/07/11 18:44
 */
public class FactionTest {

    public static void main(String[] args) {
        String info = "小明,20,男";
        User user = User.getInstance(info,
                (str) -> {
                    String[] strings = str.split(",");
                    if (strings == null || strings.length == 0) {
                        return null;
                    }
                    return new User(strings[0], Integer.parseInt(strings[1]), strings[2]);
                });
        System.out.println(user);
    }


}

class User {

    private String name;
    private Integer age;
    private String gender;

    public User(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }


    //实现三个数据转换 String->String, String->Integer,Integer->Integer
    public static User getInstance(String info,
                                   Function<String, User> f1) {
        if (info == null) {
            return null;
        }
        return f1.apply(info);
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
