package cc.xzwb.bookstore.zfjw.model;

import lombok.NonNull;

public class User {
    public String getStuCode() {
        return stuCode;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String stuCode;
    private String password;

    private String name;

    public User(String stuCode, String password) {
        this.stuCode = stuCode;
        this.password = password;
    }

    public static User builder(@NonNull String stuCode, @NonNull String password) {
        return new User(stuCode, password);
    }

}
