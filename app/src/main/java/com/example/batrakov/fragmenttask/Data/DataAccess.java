package com.example.batrakov.fragmenttask.Data;

import com.example.batrakov.fragmenttask.Cat;

import java.util.ArrayList;

/**
 * Allow to get data from base.
 * Created by batrakov on 06.10.17.
 */

public interface DataAccess {
    /**
     * Allow to get data from base.
     * @return stored database.
     */
    ArrayList<Cat> getData();
}
