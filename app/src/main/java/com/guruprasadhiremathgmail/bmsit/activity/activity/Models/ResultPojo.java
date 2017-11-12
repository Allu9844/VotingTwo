package com.guruprasadhiremathgmail.bmsit.activity.activity.Models;

/**
 * Created by allam on 12/11/17.
 */

public class ResultPojo {

    private String party;

    private String votes;

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    private String candidate;
}
