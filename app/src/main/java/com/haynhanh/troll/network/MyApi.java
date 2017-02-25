package com.haynhanh.troll.network;

import com.haynhanh.troll.model.domain.Domain;
import com.haynhanh.troll.model.item.Item;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MyApi {

    @GET("/api/v1/domain-list")
    Call<Domain> getDomainList();

    @GET("/api/v1/item-list")
    Call<Item> getItemList(@QueryMap Map<String, Object> options);

}
