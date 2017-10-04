package com.example.batrakov.fragmenttask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Main app activity.
 * Allows to browsing cat list and provide access to  AddActivity.
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent aData) {
        super.onActivityResult(aRequestCode, aResultCode, aData);
        getSupportFragmentManager().findFragmentById(R.id.mainFragment)
                .onActivityResult(aRequestCode, aResultCode, aData);
    }
}
