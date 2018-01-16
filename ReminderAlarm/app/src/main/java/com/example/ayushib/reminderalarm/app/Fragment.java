package com.example.ayushib.reminderalarm.app;

import com.example.ayushib.reminderalarm.util.Helper;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public class Fragment <H extends Helper> extends android.support.v4.app.Fragment {

    private H helper;

    public H getHelper() {
        if (helper == null)
            helper = (H) H.getInstance();
        return helper;
    }
}
