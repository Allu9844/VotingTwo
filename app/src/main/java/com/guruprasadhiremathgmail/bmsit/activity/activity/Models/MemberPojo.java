package com.guruprasadhiremathgmail.bmsit.activity.activity.Models;

/**
 * Created by allam on 8/11/17.
 */

public class MemberPojo {

    private String id;

    private String phonenumber;

    private String account_holder_name;

    private String status;

    private String email;

    private String adhar;

    private String is_preferred;

    private String age;

    private String name;

    private String state;

    private String profile;

    private String city;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPhonenumber ()
    {
        return phonenumber;
    }

    public void setPhonenumber (String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getAccount_holder_name ()
    {
        return account_holder_name;
    }

    public void setAccount_holder_name (String account_holder_name)
    {
        this.account_holder_name = account_holder_name;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getAdhar ()
    {
        return adhar;
    }

    public void setAdhar (String adhar)
    {
        this.adhar = adhar;
    }

    public String getIs_preferred ()
    {
        return is_preferred;
    }

    public void setIs_preferred (String is_preferred)
    {
        this.is_preferred = is_preferred;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getProfile ()
    {
        return profile;
    }

    public void setProfile (String profile)
    {
        this.profile = profile;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", phonenumber = "+phonenumber+", account_holder_name = "+account_holder_name+", status = "+status+", email = "+email+", adhar = "+adhar+", is_preferred = "+is_preferred+", age = "+age+", name = "+name+", state = "+state+", profile = "+profile+", city = "+city+"]";
    }
}
