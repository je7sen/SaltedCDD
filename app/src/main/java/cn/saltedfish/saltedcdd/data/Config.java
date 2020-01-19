package cn.saltedfish.saltedcdd.data;

import android.content.Context;
import android.content.SharedPreferences;

import cn.saltedfish.saltedcdd.stage.GameApplication;

public class Config {
    protected static String sNickname;

    protected static float sBGMVolume;

    protected static int sPlayerMode;

    protected static final String PrefName = "Config";

    public static void load()
    {
        SharedPreferences prefs = GameApplication.getAppContext().getSharedPreferences(PrefName, Context.MODE_PRIVATE);
        sNickname = prefs.getString("nickname", "SaltedFish");
        sBGMVolume = prefs.getFloat("bgm_volume", 0.8f);
        sPlayerMode = prefs.getInt("playerMode", 3);
    }

    public static String getNickname()
    {
        return sNickname;
    }

    public static void setNickname(String pNickname)
    {
        sNickname = pNickname;
    }

    public static float getBGMVolume()
    {
        return sBGMVolume;
    }

    public static void setBGMVolume(float pBGMVolume)
    {
        sBGMVolume = pBGMVolume;
    }

    public static int getPlayerMode()
    {
        return sPlayerMode;
    }

    public static void setPlayerMode(int pPlayerMode)
    {
        sPlayerMode = pPlayerMode;
    }

    public static void save()
    {
        SharedPreferences prefs = GameApplication.getAppContext().getSharedPreferences(PrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nickname", sNickname);
        editor.putFloat("bgm_volume", sBGMVolume);
        editor.putInt("playerMode", sPlayerMode);
        editor.apply();
    }
}