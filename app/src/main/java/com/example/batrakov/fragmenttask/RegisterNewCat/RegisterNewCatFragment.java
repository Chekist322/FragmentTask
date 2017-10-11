package com.example.batrakov.fragmenttask.RegisterNewCat;

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

import com.example.batrakov.fragmenttask.R;

/**
 * Fragment that allows to register new cat
 * and send it to ListFragment.
 * Created by batrakov on 04.10.17.
 */
public class RegisterNewCatFragment extends DialogFragment implements RegisterNewCatContract.View {


    private EditText mName;
    private EditText mBreed;
    private EditText mAge;
    private View mView;

    private RegisterNewCatPresenter mPresenter;

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
                mPresenter.sendDataToList(name, breed, age);
            }
        });
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getTargetFragment() != null) {
            this.dismiss();
        }
    }

    @Override
    public void setPresenter(RegisterNewCatContract.Presenter aPresenter) {
        mPresenter = (RegisterNewCatPresenter) aPresenter;
    }

    @Override
    public void showNameError() {
        Snackbar snackbar = Snackbar.make(mView, getResources().getText(R.string.nameError), Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(getActivity().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void showBreedError() {
        Snackbar snackbar = Snackbar.make(mView, getResources().getText(R.string.breedError), Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(getActivity().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void showAgeError() {
        Snackbar snackbar = Snackbar.make(mView, getResources().getText(R.string.ageError), Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void clearFields() {
        mName.setText("");
        mBreed.setText("");
        mAge.setText("");
    }
}
