package com.example.batrakov.fragmenttask;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Fragment that represents data as a list, provide access to RegisterNewCatFragment
 * also allow to start second application that displays current list as a grid.
 * Created by batrakov on 04.10.17.
 */

public class MainFragment extends Fragment {

    /**
     * Request code for add fragment.
     */
    public static final int ADD_ACT = 1;
    private static final String CAT_ARRAY = "cat array";
    private static final String CAT_INDEX = "cat index";
    private static final String CUSTOM_ACTION = "com.example.batrakov.fragmenttaskgrid.ACTION";
    private static final String DIALOG_TAG = "dialog";
    private static final int GRID_ACT = 0;
    private View mListHeader;
    private CatAdapter mListAdapter;
    private ArrayList<Cat> mListData;

    @Override
    public void onCreate(@Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListHeader = null;
        mListAdapter = null;
    }

    @Override
    public View onCreateView(LayoutInflater aInflater, @Nullable ViewGroup aContainer,
                             @Nullable Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);
        View root = aInflater.inflate(R.layout.fragment_main, aContainer, false);
        final CustomActionBar customActionBar = root.findViewById(R.id.customView);
        RecyclerView listView = root.findViewById(R.id.list);
        mListHeader = root.findViewById(R.id.listHeader);

        if (aSavedInstanceState == null) {
            mListData = new ArrayList<>();
        }

        if (mListData != null) {
            mListAdapter = new CatAdapter(mListData);
            final LayoutAnimationController controller =
                    AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);

            listView.setLayoutAnimation(controller);
            listView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            listView.setAdapter(mListAdapter);
            mListAdapter.replaceData(mListData);
            listView.scheduleLayoutAnimation();
        }

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            customActionBar.hideRegisterCallButtonVisibility();
        } else {
            customActionBar.showRegisterCallButtonVisibility();
        }

        customActionBar.setFirstIntentCallAction(new View.OnClickListener() {
            @Override
            public void onClick(View aView) {
                ArrayList<String> stringArrayList = new ArrayList<>();
                Intent intent = new Intent();
                intent.setAction(CUSTOM_ACTION);
                for (int i = 0; i < mListData.size(); i++) {
                    stringArrayList.add(mListData.get(i).getName());
                    stringArrayList.add(mListData.get(i).getBreed());
                    stringArrayList.add(mListData.get(i).getAge());
                }
                intent.putStringArrayListExtra(CAT_ARRAY, stringArrayList);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, GRID_ACT);
                }
            }
        });

        customActionBar.setSecondIntentCallAction(new View.OnClickListener() {
            @Override
            public void onClick(View aView) {
                ArrayList<String> stringArrayList = new ArrayList<>();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                for (int i = 0; i < mListData.size(); i++) {
                    stringArrayList.add(mListData.get(i).getName());
                    stringArrayList.add(mListData.get(i).getBreed());
                    stringArrayList.add(mListData.get(i).getAge());
                }
                intent.putStringArrayListExtra(CAT_ARRAY, stringArrayList);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    getActivity().startActivityForResult(intent, GRID_ACT);
                }
            }
        });

        customActionBar.setRegisterCallAction(new View.OnClickListener() {
            @Override
            public void onClick(View aView) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                RegisterNewCatFragment registerNewCatFragment = new RegisterNewCatFragment();
                registerNewCatFragment.setTargetFragment(getCurrentFragment(), ADD_ACT);
                registerNewCatFragment.show(ft, DIALOG_TAG);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int aRequestCode, int aResultCode, @NonNull Intent aData) {
        super.onActivityResult(aRequestCode, aResultCode, aData);
        if (aRequestCode == GRID_ACT) {
            if (aResultCode == Activity.RESULT_OK) {
                int index = aData.getIntExtra(CAT_INDEX, 0);
                Snackbar snackbar = Snackbar.make(mListHeader, getResources().getText(R.string.catPressed)
                        + String.valueOf(index), Snackbar.LENGTH_LONG);
                View view = snackbar.getView();
                view.setBackgroundColor(getActivity().getColor(R.color.colorPrimary));
                snackbar.show();
            }
        }
    }

    /**
     * Add new cat to mListData.
     * @param aBundle incoming bundle that contains cat parameters.
     */
    public void addCat(Bundle aBundle) {
        Cat cat = new Cat(aBundle.getString(RegisterNewCatFragment.NAME_KEY),
                aBundle.getString(RegisterNewCatFragment.BREED_KEY),
                aBundle.getString(RegisterNewCatFragment.AGE_KEY));
        mListData.add(cat);
        mListAdapter.replaceData(mListData);
    }

    /**
     * Get current fragment reference.
     * @return current fragment reference.
     */
    private Fragment getCurrentFragment() {
        return this;
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
