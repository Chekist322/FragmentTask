package com.example.batrakov.fragmenttask.List;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.example.batrakov.fragmenttask.Cat;
import com.example.batrakov.fragmenttask.CustomActionBar;
import com.example.batrakov.fragmenttask.R;

import java.util.ArrayList;

/**
 * Fragment that represents data as a list, provide access to RegisterNewCatFragment
 * also allow to start second application that displays current list as a grid.
 * Created by batrakov on 04.10.17.
 */

public class ListFragment extends Fragment implements ListContract.View {

    private static final String TAG = "ListFragment";
    /**
     * Request code for add fragment.
     */

    private View mListHeader;

    private RecyclerView mListView;

    private ListContract.Presenter mPresenter;

    private CatAdapter mListAdapter;

    @Override
    public void onSaveInstanceState(Bundle aOutState) {
    }

    @Override
    public void onCreate(@Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater aInflater, @Nullable ViewGroup aContainer,
                             @Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        View root = aInflater.inflate(R.layout.fragment_main, aContainer, false);
        final CustomActionBar customActionBar = root.findViewById(R.id.customView);
        mListView = root.findViewById(R.id.list);
        mListHeader = root.findViewById(R.id.listHeader);
        mListAdapter = new CatAdapter(mPresenter.readFromData());
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        mListView.setLayoutAnimation(controller);
        mListView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mListView.setAdapter(mListAdapter);
        updateListView();
        mListView.scheduleLayoutAnimation();

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            customActionBar.hideRegisterCallButtonVisibility();
        } else {
            customActionBar.showRegisterCallButtonVisibility();
        }

        customActionBar.setFirstIntentCallAction(new View.OnClickListener() {
            @Override
            public void onClick(View aView) {
                mPresenter.startFirstIntentCall();
            }
        });

        customActionBar.setSecondIntentCallAction(new View.OnClickListener() {
            @Override
            public void onClick(View aView) {
                mPresenter.startSecondIntentCall();
            }
        });

        customActionBar.setRegisterCallAction(new View.OnClickListener() {
            @Override
            public void onClick(View aView) {
                mPresenter.startRegisterNewCatDialog(getFragmentManager().beginTransaction());
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int aRequestCode, int aResultCode, @NonNull Intent aData) {
        mPresenter.checkActivityResult(aRequestCode, aResultCode, aData);
        super.onActivityResult(aRequestCode, aResultCode, aData);
    }

    /**
     * Get current fragment reference.
     * @return current fragment reference.
     */
    public Fragment getCurrentFragment() {
        return this;
    }

    @Override
    public void showPressedGridElement(int aIndex) {
        Snackbar snackbar = Snackbar.make(mListHeader, getResources().getText(R.string.catPressed)
                + String.valueOf(aIndex), Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(getActivity().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public FragmentManager getSupportFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public void updateListView() {
        mListAdapter.replaceData(mPresenter.readFromData());
    }

    @Override
    public void setPresenter(ListContract.Presenter aPresenter) {
        mPresenter = aPresenter;
        Log.i(TAG, "setPresenter: this " + String.valueOf(this));
    }

    /**
     * Holder for RecyclerView Adapter.
     */
    private final class CatHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mBreed;
        private TextView mAge;

        /**
         * Constructor.
         * @param aItemView item view
         */
        private CatHolder(View aItemView) {
            super(aItemView);
            mName = itemView.findViewById(R.id.name);
            mBreed = itemView.findViewById(R.id.breed);
            mAge = itemView.findViewById(R.id.age);
        }

        /**
         * View filling.
         * @param aCat cat from list
         */
        void bindView(Cat aCat) {
            mName.setText(aCat.getName());
            mBreed.setText(aCat.getBreed());
            mAge.setText(aCat.getAge());
        }
    }

    /**
     * Adapter for RecyclerView.
     */
    private class CatAdapter extends RecyclerView.Adapter<CatHolder> {

        private ArrayList<Cat> mList;

        /**
         * Constructor.
         * @param aList target list for fill.
         */
        CatAdapter(ArrayList<Cat> aList) {
            setHasStableIds(true);
            mList = aList;
        }

        /**
         * List updating.
         * @param aList new target list.
         */
        void replaceData(ArrayList<Cat> aList) {
            mList = aList;
            if (mList != null) {
                if (!mList.isEmpty()) {
                    mListHeader.setVisibility(View.VISIBLE);
                } else {
                    mListHeader.setVisibility(View.INVISIBLE);
                }
                notifyDataSetChanged();
            }
        }

        @Override
        public CatHolder onCreateViewHolder(ViewGroup aParent, int aViewType) {
            View rowView = LayoutInflater.from(aParent.getContext()).inflate(R.layout.list_item, aParent, false);
            return new CatHolder(rowView);
        }

        @Override
        public void onBindViewHolder(CatHolder aHolder, int aPosition) {
            Cat cat = mList.get(aPosition);
            aHolder.bindView(cat);
        }

        @Override
        public long getItemId(int aPosition) {
            return aPosition;
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
