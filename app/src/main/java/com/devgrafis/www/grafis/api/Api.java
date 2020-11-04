package com.devgrafis.www.grafis.api;

import com.devgrafis.www.grafis.model.Material;
import com.devgrafis.www.grafis.model.value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("signup.php")
    Call<value> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<value> login(
            @Field("email") String email,
            @Field("password") String password);

    @GET("getMaterial.php")
    Call<value> getMaterial();

    @GET("getGroup.php")
    Call<value> getGroup();
}
