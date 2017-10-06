package com.example.batrakov.fragmenttask;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
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
 * Fragment that allows to register new cat
 * and send it to MainFragment.
 * Created by batrakov on 04.10.17.
 */
public class RegisterNewCatFragment extends DialogFragment {

    /**
     * Intent key for name.
     */
    public static final String NAME_KEY = "name key";
    /**
     * Intent key for breed.
     */
    public static final String BREED_KEY = "breed key";
    /**
     * Intent key for age.
     */
    public static final String AGE_KEY = "age key";
    private SendDataToMainFragment mCallback;
    private EditText mName;
    private EditText mBreed;
    private EditText mAge;
    private View mView;


    /**
     * Allow callback communication with MainFragment.
     */
    interface SendDataToMainFragment {
        /**
         * Send text field content.
         * @param aBundle bundle with data for creating list element.
         */
        void sendTextFieldsContent(@NonNull Bundle aBundle);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle aSavedInstanceState) {
        setStyle(STYLE_NO_FRAME, R.style.MyCustomTheme);
        return super.onCreateDialog(aSavedInstanceState);
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
                String name = mName.getText().toString();
                String breed = mBreed.getText().toString();
                String age = mAge.getText().toString();
                if (checkName(name) && checkBreed(breed) && checkAge(age)) {
                    try {
                        mCallback = (SendDataToMainFragment) getActivity();
                        Bundle bundle = new Bundle();
                        bundle.putString(NAME_KEY, name);
                        bundle.putString(BREED_KEY, breed);
                        bundle.putString(AGE_KEY, age);
                        mCallback.sendTextFieldsContent(bundle);
                        if (getTargetFragment() != null) {
                            dismiss();
                        } else {
                            mName.setText("");
                            mBreed.setText("");
                            mAge.setText("");
                        }
                    } catch (ClassCastException e) {
                        throw new ClassCastException(getActivity().toString()
                                + " must implement SendDataToMainFragment");
                    }
                }
            }
        });
        return root;
    }

    @Override
    public void onDetach() {
        mCallback = null;
        mName = null;
        mBreed = null;
        mAge = null;
        mView = null;
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.dismissAllowingStateLoss();
    }

    /**
     * Check name field.
     * @param aName target string
     * @return check result
     */
    private boolean checkName(@NonNull String aName) {
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
    private boolean checkBreed(@NonNull String aBreed) {
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
    private boolean checkAge(@NonNull String aAge) {
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
