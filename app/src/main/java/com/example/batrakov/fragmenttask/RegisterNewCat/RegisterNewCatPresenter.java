package com.example.batrakov.fragmenttask.RegisterNewCat;

import android.support.annotation.NonNull;

import com.example.batrakov.fragmenttask.Cat;

/**
 * Presenter for {@link RegisterNewCatFragment}
 * Created by batrakov on 06.10.17.
 */
public class RegisterNewCatPresenter implements RegisterNewCatContract.Presenter {

    private RegisterNewCatContract.View mRegisterView;

    /**
     * Allow callback communication with ListFragment.
     */
    public interface SendDataFromRegistrationFragment {
        /**
         * Send text field content.
         * @param aCat target Cat.
         */
        void sendTextFieldsContent(@NonNull Cat aCat);
    }

    /**
     * Constructor.
     * @param aRegisterView current {@link RegisterNewCatFragment}
     */
    public RegisterNewCatPresenter(RegisterNewCatContract.View aRegisterView) {
        mRegisterView = aRegisterView;
        mRegisterView.setPresenter(this);
    }

    @Override
    public void sendDataToList(String aName, String aBreed, String aAge) {
        if (checkName(aName) && checkBreed(aBreed) && checkAge(aAge)) {
            try {
                SendDataFromRegistrationFragment mCallback =
                        (SendDataFromRegistrationFragment) mRegisterView.getActivity();
                mCallback.sendTextFieldsContent(new Cat(aName, aBreed, aAge));
                if (mRegisterView.getTargetFragment() != null) {
                    mRegisterView.dismiss();
                } else {
                    mRegisterView.clearFields();
                }
            } catch (ClassCastException e) {
                throw new ClassCastException(mRegisterView.getActivity().toString()
                        + " must implement SendDataFromRegistrationFragment");
            }
        }
    }

    /**
     * Check name field.
     * @param aName target string.
     * @return check result.
     */
    private boolean checkName(@NonNull String aName) {
        if (!aName.matches("([a-zA-Zа-яА-Я]+\\s?)+")) {
            mRegisterView.showNameError();
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
            mRegisterView.showBreedError();
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
            mRegisterView.showAgeError();
            return false;
        } else {
            return true;
        }
    }
}
