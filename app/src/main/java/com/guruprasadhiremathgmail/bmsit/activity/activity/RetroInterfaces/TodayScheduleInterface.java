package com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces;

import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.CanteenPojo;


import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by allam on 19/1/17.
 */

public interface TodayScheduleInterface {
    @GET("/fbs/displaytodaysspecial.php")
    public void getSchedule(Callback<CanteenPojo> response);


}
