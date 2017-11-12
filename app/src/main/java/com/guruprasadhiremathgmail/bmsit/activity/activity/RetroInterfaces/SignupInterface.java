package com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces;

import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.RegisterPojo;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by allam on 26/4/17.
 */

public interface SignupInterface {

    @FormUrlEncoded
    @POST("/fbs/bmsitregister.php")
    public void register(@Field("usn") String usn,
                         @Field("mobile") String mobile,
                         @Field("name") String name,
                         @Field("pass") String pass,
                         Callback<RegisterPojo> callback);
}
