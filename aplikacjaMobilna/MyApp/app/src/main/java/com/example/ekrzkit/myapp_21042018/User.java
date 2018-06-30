package com.example.ekrzkit.myapp_21042018;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ekrzkit on 2018-04-21.
 */

public class User {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
