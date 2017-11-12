package com.guruprasadhiremathgmail.bmsit.activity.activity.Models;

/**
 * Created by allam on 20/4/17.
 */

public class Colddrinks_item {
    private String item;

    private String availability;

    public String getItem ()
    {
        return item;
    }

    public void setItem (String item)
    {
        this.item = item;
    }

    public String getAvailability ()
    {
        return availability;
    }

    public void setAvailability (String availability)
    {
        this.availability = availability;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [item = "+item+", availability = "+availability+"]";
    }
}
