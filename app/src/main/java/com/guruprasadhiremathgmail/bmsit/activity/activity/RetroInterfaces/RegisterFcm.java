package com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces;

import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.CandidatesPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.DistictPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.MemberPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.ResultPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.StatePojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.VotePojo;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by allam on 19/4/17.
 */

public interface RegisterFcm {

    @GET("/api/State/")
    public void register(Callback<ArrayList<StatePojo>> callback);


    @GET("/api/Distict/")
    public void getDisticts(Callback<ArrayList<DistictPojo>> callback);


    @GET("/api/Candidates/")
    public void getCandidates(@Query("search") String pk, Callback<ArrayList<CandidatesPojo>> callback);

    @FormUrlEncoded
    @POST("/api/login/")
    public void signin(@Field("username") String number,@Field("password") String passwordm,Callback<MemberPojo> callback);

    @FormUrlEncoded
    @POST("/api/Register/")
    public void signup(@Field("name") String name,@Field("age") String age,@Field("city") String city,@Field("state") String state,@Field("adhar") String adhar,@Field("number") String number,@Field("password") String password,Callback<MemberPojo> callback);


    @FormUrlEncoded
    @POST("/api/Vote/")
    public void vote(@Field("member") String member,@Field("candidate") String candidate,Callback<VotePojo> callback);


    @FormUrlEncoded
    @POST("/api/result/")
    public void result(@Field("distict") String member,Callback<ArrayList<ResultPojo>> callback);


}
