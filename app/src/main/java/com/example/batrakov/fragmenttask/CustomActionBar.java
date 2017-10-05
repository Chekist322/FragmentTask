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

    private Drawable mFirstButtonDrawable;
    private Drawable mSecondButtonDrawable;
    private Drawable mThirdButtonDrawable;
    private Drawable mBackgroundShape;
    private int mColor;
    private int mButtonElevation;
    private Boolean mVertical;
    private ImageButton mFirstButton;
    private ImageButton mSecondButton;
    private ImageButton mThirdButton;

    /**
     * Constructor.
     * @param aContext context
     * @param aAttrs   attributes from XML
     */
    public CustomActionBar(Context aContext,@Nullable AttributeSet aAttrs) {
        super(aContext, aAttrs);
        TypedArray array = aContext.getTheme().obtainStyledAttributes(aAttrs, R.styleable.CustomActionBar, 0, 0);
        try {
            mFirstButtonDrawable = array.getDrawable(R.styleable.CustomActionBar_firstButtonDrawable);
            mSecondButtonDrawable = array.getDrawable(R.styleable.CustomActionBar_secondButtonDrawable);
            mThirdButtonDrawable = array.getDrawable(R.styleable.CustomActionBar_thirdButtonDrawable);
            mVertical = array.getBoolean(R.styleable.CustomActionBar_vertical, true);
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
        mFirstButton = new ImageButton(getContext());
        mSecondButton = new ImageButton(getContext());
        mThirdButton = new ImageButton(getContext());

        mFirstButton.setImageDrawable(mFirstButtonDrawable);
        mSecondButton.setImageDrawable(mSecondButtonDrawable);
        mThirdButton.setImageDrawable(mThirdButtonDrawable);

        mFirstButton.setBackground(mBackgroundShape);
        mSecondButton.setBackground(mBackgroundShape);
        mThirdButton.setBackground(mBackgroundShape);

        mFirstButton.setBackgroundColor(mColor);
        mSecondButton.setBackgroundColor(mColor);
        mThirdButton.setBackgroundColor(mColor);

        mFirstButton.setElevation(mButtonElevation);
        mSecondButton.setElevation(mButtonElevation);
        mThirdButton.setElevation(mButtonElevation);

        if (mVertical) {
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

            mFirstButton.setLayoutParams(firstParams);
            mSecondButton.setLayoutParams(secondParams);
            mThirdButton.setLayoutParams(thirdParams);
        } else {
            setOrientation(VERTICAL);
            LayoutParams params =
                    new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.setMargins(0, MARGIN_PADDING, 0, MARGIN_PADDING);

            mFirstButton.setLayoutParams(params);
            mSecondButton.setLayoutParams(params);
            mThirdButton.setLayoutParams(params);
        }
        addView(mFirstButton);
        addView(mSecondButton);
        addView(mThirdButton);

        setBackground(mBackgroundShape);
        setBackgroundColor(mColor);
        setElevation(mButtonElevation);
    }

    /**
     * Set first button OnClick listener.
     * @param aOnClickListener listener for the first button
     */
    public void setFirstButtonOnClickListener(@NonNull OnClickListener aOnClickListener) {
        mFirstButton.setOnClickListener(aOnClickListener);
    }

    /**
     * Set second button OnClick listener.
     * @param aOnClickListener listener for the second button
     */
    public void setSecondButtonOnClickListener(@NonNull OnClickListener aOnClickListener) {
        mSecondButton.setOnClickListener(aOnClickListener);
    }

    /**
     * Set third button OnClick listener.
     * @param aOnClickListener listener for the third button
     */
    public void setThirdButtonOnClickListener(@NonNull OnClickListener aOnClickListener) {
        mThirdButton.setOnClickListener(aOnClickListener);
    }

    /**
     * Set third button visibility.
     * @param aVisibility Required third button visibility state
     */
    public void setThirdButtonVisibility(int aVisibility) {
        mThirdButton.setVisibility(aVisibility);
    }
}
