package com.example.batrakov.fragmenttask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 *
 * Created by batrakov on 04.10.17.
 */

public class AddFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setRetainInstance(true);
    }

    /**
     * Intent key for name.
     */
    public static final String NAME_KEY = "name key";

    @Override
    public void onPause() {
        super.onPause();
        this.dismissAllowingStateLoss();
    }

    /**
     * Intent key for breed.
     */
    public static final String BREED_KEY = "breed key";

    /**
     * Intent key for age.
     */
    public static final String AGE_KEY = "age key";

    private EditText mName;
    private EditText mBreed;
    private EditText mAge;
    private View mView;

    @Override
    public void show(FragmentManager aManager, String aTag) {
        super.show(aManager, aTag);

    }

    @Override
    public View onCreateView(LayoutInflater aInflater, @Nullable ViewGroup aContainer,
                             @Nullable Bundle aSavedInstanceState) {
        View root = aInflater.inflate(R.layout.fragment_add, aContainer, false);
        mView = root.findViewById(R.id.layoutForSnackbar);
        mName = root.findViewById(R.id.nameField);
        mBreed = root.findViewById(R.id.breedField);
        mAge = root.findViewById(R.id.ageField);
        Button register = root.findViewById(R.id.registerButton);

        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            if (window != null) {
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.TOP | Gravity.END;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);
            }
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View aView) {
                Fragment mainFragment = getFragmentManager().findFragmentById(R.id.mainFragment);
                String name = mName.getText().toString();
                String breed = mBreed.getText().toString();
                String age = mAge.getText().toString();
                if (mainFragment != null) {
                    if (checkName(name) && checkBreed(breed) && checkAge(age)) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.putExtra(NAME_KEY, name);
                        intent.putExtra(BREED_KEY, breed);
                        intent.putExtra(AGE_KEY, age);
                        if (getDialog() != null) {
                            mainFragment.onActivityResult(MainFragment.ADD_ACT, Activity.RESULT_OK, intent);
                            dismiss();
                        } else {
                            mainFragment.onActivityResult(MainFragment.ADD_ACT, Activity.RESULT_OK, intent);
                            mName.setText("");
                            mBreed.setText("");
                            mAge.setText("");
                        }

                    }
                }
            }
        });
        return root;
    }

    /**
     * Check name field.
     * @param aName target string
     * @return check result
     */
    private boolean checkName(String aName) {
        if (!aName.matches("([a-zA-Zа-яА-Я]+\\s?)+")) {
            Snackbar snackbar = Snackbar.make(mView, getResources().getText(R.string.nameError), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(getActivity().getColor(R.color.colorPrimary));
            snackbar.show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check breed field.
     * @param aBreed target string
     * @return check result
     */
    private boolean checkBreed(String aBreed) {
        if (!aBreed.matches("([a-zA-Zа-яА-Я]+\\s?)+")) {
            Snackbar snackbar = Snackbar.make(mView, getResources().getText(R.string.breedError), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(getActivity().getColor(R.color.colorPrimary));
            snackbar.show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check age field.
     * @param aAge target string
     * @return check result
     */
    private boolean checkAge(String aAge) {
        if (!aAge.matches("\\d+")) {
            Snackbar snackbar = Snackbar.make(mView, getResources().getText(R.string.ageError), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            snackbar.show();
            return false;
        } else {
            return true;
        }
    }
}
