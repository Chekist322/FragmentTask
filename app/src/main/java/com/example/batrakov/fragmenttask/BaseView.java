package com.example.batrakov.fragmenttask;

import android.support.v4.app.Fragment;

/**
 * Created by batrakov on 06.10.17.
 */

public interface BaseView<T> {
    void setPresenter(T aPresenter);
}
