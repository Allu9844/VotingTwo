package com.guruprasadhiremathgmail.bmsit.activity.activity.Models;

/**
 * Created by allam on 26/4/17.
 */

public class LoginPojo {

    private Result[] result;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+"]";
    }
}
