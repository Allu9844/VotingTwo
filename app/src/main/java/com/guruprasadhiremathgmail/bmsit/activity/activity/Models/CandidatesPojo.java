package com.guruprasadhiremathgmail.bmsit.activity.activity.Models;

/**
 * Created by allam on 5/11/17.
 */

public class CandidatesPojo {

    private String id;

    private String distict;

    private String name;

    private String image;

    private String party;

    public boolean is_checked() {
        return is_checked;
    }

    public void setIs_checked(boolean is_checked) {
        this.is_checked = is_checked;
    }

    private boolean is_checked;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDistict ()
    {
        return distict;
    }

    public void setDistict (String distict)
    {
        this.distict = distict;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getParty ()
    {
        return party;
    }

    public void setParty (String party)
    {
        this.party = party;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", distict = "+distict+", name = "+name+", image = "+image+", party = "+party+"]";
    }
}
