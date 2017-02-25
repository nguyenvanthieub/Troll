package com.haynhanh.troll.network;

import com.haynhanh.troll.model.Domain;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by peter on 2/19/17.
 */

public interface MyApiEndpointInterface {

    @GET("/api/v1/domain-list")
    Call<Domain> getDomain();


}
