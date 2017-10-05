package com.example.batrakov.fragmenttask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Main app activity.
 * Container and provider for MainActivity and RegisterNewCatFragment.
 */
public class MainActivity extends FragmentActivity implements RegisterNewCatFragment.SendDataToMainFragment {

    @Override
    protected void onCreate(@Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendTextFieldsContent(Bundle aBundle) {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        mainFragment.addCat(aBundle);
    }
}
