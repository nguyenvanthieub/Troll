package com.haynhanh.troll.network;

public class ApiUtil {

    public static MyApi getApi() {
        return RetrofitClient.getClient().create(MyApi.class);
    }

}
