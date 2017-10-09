package com.example.batrakov.fragmenttask.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.example.batrakov.fragmenttask.Cat;
import com.example.batrakov.fragmenttask.CustomActionBar;
import com.example.batrakov.fragmenttask.MainActivity;
import com.example.batrakov.fragmenttask.R;
import com.example.batrakov.fragmenttask.RegisterNewCat.RegisterNewCatFragment;

import java.util.ArrayList;

/**
 * Fragment that represents data as a list, provide access to RegisterNewCatFragment
 * also allow to start second application that displays current list as a grid.
 * Created by batrakov on 04.10.17.
 */

public class ListFragment extends Fragment implements ListContract.View {

    /**
     * Request code for add fragment.
     */

    private View mListHeader;
    private CatAdapter mListAdapter;

    private ListContract.Presenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater aInflater, @Nullable ViewGroup aContainer,
                             @Nullable Bundle aSavedInstanceState) {
        System.out.println(this);
        System.out.println("OnCreateListFrag");
        super.onCreate(aSavedInstanceState);
        View root = aInflater.inflate(R.layout.fragment_main, aContainer, false);
        final CustomActionBar customActionBar = root.findViewById(R.id.customView);
        RecyclerView listView = root.findViewById(R.id.list);
        mListHeader = root.findViewById(R.id.listHeader);

        mListAdapter = new CatAdapter(mPresenter.readFromData());
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        listView.setLayoutAnimation(controller);
        listView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        listView.setAdapter(mListAdapter);
        mListAdapter.replaceData(mPresenter.readFromData());
        listView.scheduleLayoutAnimation();

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
    public FragmentManager getSupportFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public void setPresenter(ListContract.Presenter aPresenter) {
        System.out.println("OnSetPresenter");
        mPresenter = aPresenter;
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
