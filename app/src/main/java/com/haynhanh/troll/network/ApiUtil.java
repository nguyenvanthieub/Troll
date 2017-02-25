package com.haynhanh.troll.network;

/**
 * Created by peter on 2/19/17.
 */

public class ApiUtil {

    public static MyApiEndpointInterface getApiEndpointInterface() {
        return RetrofitClient.getClient().create(MyApiEndpointInterface.class);
    }

}
