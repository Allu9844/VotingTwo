package com.guruprasadhiremathgmail.bmsit.activity.activity.Models;

/**
 * Created by allam on 26/4/17.
 */

public class Result {

    private String name;

    private int role;

    private String mobile;

    private String usn;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public int getRole ()
    {
        return role;
    }

    public void setRole (int role)
    {
        this.role = role;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }

    public String getUsn ()
    {
        return usn;
    }

    public void setUsn (String usn)
    {
        this.usn = usn;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", role = "+role+", mobile = "+mobile+", usn = "+usn+"]";
    }
}
