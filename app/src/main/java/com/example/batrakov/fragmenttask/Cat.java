package com.example.batrakov.fragmenttask;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Class represents single cat.
 * Created by batrakov on 28.09.17.
 */
public class Cat implements Serializable {
    private String mName;
    private String mBreed;
    private String mAge;

    /**
     * Constructor.
     * @param aName  cat's name
     * @param aBreed cat's breed
     * @param aAge   cat's age
     */
    public Cat(String aName, String aBreed, String aAge) {
        mName = aName;
        mBreed = aBreed;
        mAge = aAge;
    }

    /**
     * Allow to get cat name.
     * @return name
     */
    public String getName() {
        return mName;
    }

    /**
     * Allow to set cat name.
     * @param aName name
     */
    public void setName(String aName) {
        this.mName = aName;
    }


    /**
     * Allow to get cat breed.
     * @return breed
     */
     public String getBreed() {
        return mBreed;
    }

    /**
     * Allow to get cat age.
     * @return age
     */
    public String getAge() {
        return mAge;
    }

}
