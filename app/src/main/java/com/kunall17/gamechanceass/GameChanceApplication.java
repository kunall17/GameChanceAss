package com.kunall17.gamechanceass;

import android.app.Application;

import java.io.File;

import okhttp3.Cache;

public class GameChanceApplication extends Application {

    private static Cache mCache;
    public GameChanceApplication() {
        try {
            mCache = new Cache(new File(getCacheDir(), "http-cache"), 10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Cache provideCache() {
        return mCache;
    }
}
