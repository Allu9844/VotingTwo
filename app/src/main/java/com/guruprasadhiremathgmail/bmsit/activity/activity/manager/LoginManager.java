package com.guruprasadhiremathgmail.bmsit.activity.activity.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.DistictPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.StatePojo;

import java.util.ArrayList;

/**
 * Created by sanvi on 16/9/16.
 */
public class LoginManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name

    public static ArrayList<StatePojo> states;
    public static ArrayList<DistictPojo> disticts;

    private static final String PREF_NAME = "FirstTimeLaunch";

    private static final String IS_TOKEN_ADDED = "IsFirstTimeLaunch";

    private static final String IS_TOKEN_GEN = "IsTokenGen";


    private static final String USN = "USN";

    private static final String  ROLE = "ROLE";

    public LoginManager(Context context) {

        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_TOKEN_ADDED, isFirstTime);
        editor.commit();
    }

    public boolean getFistTime() {
        return pref.getBoolean(IS_TOKEN_ADDED, true);
    }



    public void setIsTokenGen(boolean isFirstTime) {
        editor.putBoolean(IS_TOKEN_GEN, isFirstTime);
        editor.commit();
    }

    public boolean getIstokenGen() {
        return pref.getBoolean(IS_TOKEN_GEN, false);
    }


    public void setUsn(String isFirstTime) {
        editor.putString(USN, isFirstTime);
        editor.commit();
    }

    public String getUsn() {
        return pref.getString(USN,null);
    }



    public void setRole(int isFirstTime) {
        editor.putInt(ROLE, isFirstTime);
        editor.commit();
    }

    public int getRole() {
        return pref.getInt(ROLE,2);
    }


}
