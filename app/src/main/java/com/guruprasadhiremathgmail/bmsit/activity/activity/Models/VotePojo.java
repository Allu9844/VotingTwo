package com.guruprasadhiremathgmail.bmsit.activity.activity.Models;

/**
 * Created by allam on 9/11/17.
 */

public class VotePojo {

    private String member;

    private String id;

    private String candidate;

    private String date;

    public String getMember ()
    {
        return member;
    }

    public void setMember (String member)
    {
        this.member = member;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCandidate ()
    {
        return candidate;
    }

    public void setCandidate (String candidate)
    {
        this.candidate = candidate;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [member = "+member+", id = "+id+", candidate = "+candidate+", date = "+date+"]";
    }
}
