package com.guruprasadhiremathgmail.bmsit.activity.activity.Models;

/**
 * Created by allam on 5/11/17.
 */

public class DistictPojo {

    private String id;

    private String name;

    private StatePojo state;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public StatePojo getState ()
    {
        return state;
    }

    public void setState (StatePojo state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", state = "+state+"]";
    }
}
