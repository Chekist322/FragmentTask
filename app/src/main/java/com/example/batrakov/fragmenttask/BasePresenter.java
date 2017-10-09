package com.example.batrakov.fragmenttask;

import android.app.FragmentManager;

/**
 * Created by batrakov on 06.10.17.
 */

public interface BasePresenter {
    void start();

    android.support.v4.app.FragmentManager getFragmentManager();
}
