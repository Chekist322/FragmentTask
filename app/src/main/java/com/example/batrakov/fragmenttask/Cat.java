package com.example.batrakov.fragmenttask;

import java.io.Serializable;

/**
 *
 * Created by batrakov on 28.09.17.
 */

class Cat implements Serializable {
    private String mName;
    private String mBreed;
    private String mAge;

    /**
     * Constructor.
     * @param aName cat's name
     * @param aBreed cat's breed
     * @param aAge cat's age
     */
    Cat(String aName, String aBreed, String aAge) {
        mName = aName;
        mBreed = aBreed;
        mAge = aAge;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return mName;
    }

    /**
     *
     * @param aName name
     */
    public void setName(String aName) {
        this.mName = aName;
    }

    /**
     *
     * @return breed
     */
    public String getBreed() {
        return mBreed;
    }

    /**
     *
     * @param aBreed breed
     */
    public void setBreed(String aBreed) {
        this.mBreed = aBreed;
    }

    /**
     *
     * @return age
     */
    public String getAge() {
        return mAge;
    }

    /**
     *
     * @param aAge age
     */
    public void setAge(String aAge) {
        this.mAge = aAge;
    }
}
