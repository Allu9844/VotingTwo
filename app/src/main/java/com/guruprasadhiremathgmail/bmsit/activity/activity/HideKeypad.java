package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by sanvi on 20/9/16.
 */
public class HideKeypad {
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
