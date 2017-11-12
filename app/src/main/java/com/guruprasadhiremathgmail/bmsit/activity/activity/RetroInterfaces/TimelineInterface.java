package com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces;

import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.TimelinePojo;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by allam on 26/4/17.
 */

public interface TimelineInterface {

    @GET("/fbs/bmsittimeline.php")
    public void getPaymentTypes(Callback<TimelinePojo> response);
}
