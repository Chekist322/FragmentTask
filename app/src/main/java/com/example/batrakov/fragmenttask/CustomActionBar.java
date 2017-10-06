package com.example.batrakov.fragmenttask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Custom actionbar that provide
 * Created by batrakov on 03.10.17.
 */

public class CustomActionBar extends LinearLayout {

    private static final int MARGIN_PADDING = 40;

    private Drawable mFirstIntentCallDrawable;
    private Drawable mSecondIntentCallDrawable;
    private Drawable mRegisterCallDrawable;
    private Drawable mBackgroundShape;
    private int mColor;
    private int mButtonElevation;
    private ImageButton mFirstIntentCall;
    private ImageButton mSecondIntentCall;
    private ImageButton mRegisterCall;

    /**
     * Constructor.
     * @param aContext context
     * @param aAttrs   attributes from XML
     */
    public CustomActionBar(Context aContext, @Nullable AttributeSet aAttrs) {
        super(aContext, aAttrs);
        TypedArray array = aContext.getTheme().obtainStyledAttributes(aAttrs, R.styleable.CustomActionBar, 0, 0);
        try {
            mFirstIntentCallDrawable = array.getDrawable(R.styleable.CustomActionBar_firstButtonDrawable);
            mSecondIntentCallDrawable = array.getDrawable(R.styleable.CustomActionBar_secondButtonDrawable);
            mRegisterCallDrawable = array.getDrawable(R.styleable.CustomActionBar_thirdButtonDrawable);
            mButtonElevation = array.getInteger(R.styleable.CustomActionBar_buttonsElevation, 0);
            mColor = array.getColor(R.styleable.CustomActionBar_mainColor,
                    ContextCompat.getColor(aContext, R.color.white));
            mBackgroundShape = array.getDrawable(R.styleable.CustomActionBar_backgroundShape);
        } finally {
            array.recycle();
        }
        init();
    }

    /**
     * Visual initialization.
     */
    private void init() {
        mFirstIntentCall = new ImageButton(getContext());
        mSecondIntentCall = new ImageButton(getContext());
        mRegisterCall = new ImageButton(getContext());

        mFirstIntentCall.setImageDrawable(mFirstIntentCallDrawable);
        mSecondIntentCall.setImageDrawable(mSecondIntentCallDrawable);
        mRegisterCall.setImageDrawable(mRegisterCallDrawable);

        mFirstIntentCall.setBackground(mBackgroundShape);
        mSecondIntentCall.setBackground(mBackgroundShape);
        mRegisterCall.setBackground(mBackgroundShape);

        mFirstIntentCall.setBackgroundColor(mColor);
        mSecondIntentCall.setBackgroundColor(mColor);
        mRegisterCall.setBackgroundColor(mColor);

        mFirstIntentCall.setElevation(mButtonElevation);
        mSecondIntentCall.setElevation(mButtonElevation);
        mRegisterCall.setElevation(mButtonElevation);

        setOrientation(HORIZONTAL);
        LayoutParams firstParams =
                new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        firstParams.weight = 1;
        firstParams.setMarginStart(MARGIN_PADDING);
        firstParams.setMarginEnd(0);

        LayoutParams secondParams =
                new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        secondParams.weight = 1;
        secondParams.setMarginStart(MARGIN_PADDING);
        secondParams.setMarginEnd(MARGIN_PADDING);

        LayoutParams thirdParams =
                new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        thirdParams.weight = 1;
        thirdParams.setMarginStart(0);
        thirdParams.setMarginEnd(MARGIN_PADDING);

        mFirstIntentCall.setLayoutParams(firstParams);
        mSecondIntentCall.setLayoutParams(secondParams);
        mRegisterCall.setLayoutParams(thirdParams);
        mRegisterCall.setVisibility(VISIBLE);
        addView(mFirstIntentCall);
        addView(mSecondIntentCall);
        addView(mRegisterCall);

        setBackground(mBackgroundShape);
        setBackgroundColor(mColor);
        setElevation(mButtonElevation);
    }

    /**
     * Set first button OnClick listener.
     * @param aOnClickListener listener for the first button
     */
    public void setFirstIntentCallAction(@NonNull OnClickListener aOnClickListener) {
        mFirstIntentCall.setOnClickListener(aOnClickListener);
    }

    /**
     * Set second button OnClick listener.
     * @param aOnClickListener listener for the second button
     */
    public void setSecondIntentCallAction(@NonNull OnClickListener aOnClickListener) {
        mSecondIntentCall.setOnClickListener(aOnClickListener);
    }

    /**
     * Set third button OnClick listener.
     * @param aOnClickListener listener for the third button
     */
    public void setRegisterCallAction(@NonNull OnClickListener aOnClickListener) {
        mRegisterCall.setOnClickListener(aOnClickListener);
    }

    /**
     * Hide third button visibility.
     */
    public void hideRegisterCallButtonVisibility() {
        mRegisterCall.setVisibility(GONE);
    }

    /**
     * Show third button visibility.
     */
    public void showRegisterCallButtonVisibility() {
        mRegisterCall.setVisibility(VISIBLE);
    }
}
