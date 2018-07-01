package com.example.ekrzkit.myapp_21042018;

/**
 * Created by ekrzkit on 2018-06-30.
 */

public class LoginResponse {
    private boolean valid;
    private String info;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
