package com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces;

import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.LoginPojo;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by allam on 4/5/17.
 */

public interface StatusUploadInterface {

    @FormUrlEncoded
    @POST("/fbs/PhotoUpload/upload.php")
    public void uploadStatus(@Field("status3") String status,
                             @Field("usn") String usn,
                      Callback<String> callback);
}
