package com.socialnetwork.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    static public String SAVE_USER_ID = "userId";
    static public String SAVE_USER_EMAIL = "userEmail";
    static public String SAVE_USER_NAME = "userName";
    private Context context;
    public static final String MyPREFERENCES = "MyPrefs";

    public PreferencesManager(Context context) {
        this.context = context;
    }

    // Guarda userid para el login
    public void setUserId(String userId){

        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SAVE_USER_ID, userId);
        editor.commit();
    }
    // Obtiene userid para el login
    public String getUserId(){

        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String strFavs = sharedpreferences.getString(SAVE_USER_ID,"");

        if(strFavs.isEmpty()){
            strFavs = "";
        }

        return strFavs;
    }

    public void setUserEmail(String userEmail){

        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SAVE_USER_EMAIL, userEmail);
        editor.commit();
    }
    // Obtiene userid para el login
    public String getUserEmail(){

        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String strFavs = sharedpreferences.getString(SAVE_USER_EMAIL,"");

        if(strFavs.isEmpty()){
            strFavs = "";
        }

        return strFavs;
    }
    public void setUserName(String userName){

        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SAVE_USER_NAME, userName);
        editor.commit();
    }
    // Obtiene userid para el login
    public String getUserName(){

        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String strFavs = sharedpreferences.getString(SAVE_USER_NAME,"");

        if(strFavs.isEmpty()){
            strFavs = "";
        }

        return strFavs;
    }
}
