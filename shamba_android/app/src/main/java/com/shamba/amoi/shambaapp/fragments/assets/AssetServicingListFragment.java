package com.shamba.amoi.shambaapp.fragments.assets;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.assets.AssetServicingListRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.assets.AssetServicingItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class AssetServicingListFragment extends BaseFragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public AssetServicingListFragment() {
    }

    @SuppressWarnings("unused")
    public static AssetServicingListFragment newInstance(int columnCount) {
        AssetServicingListFragment fragment = new AssetServicingListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String title= AssetItem.currentAssetItem.getName() +" "+
                getString(R.string.title_fragment_asset_servicngs);
        getActivity().setTitle(title);

        View view = inflater.inflate(R.layout.fragment_assetservicinglist_list, container,
                false);

        List<AssetServicingItem> assetServicingItems=new  ArrayList();
        assetServicingItems=AssetServicingItem.getAllAssetServicings(getActivity());

        // Set the adapter
        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.recycler_list_asset_servicings);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AssetServicingListRecyclerViewAdapter(assetServicingItems,
                (HomeActivity)getActivity()));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnListFragmentInteractionListener {// TODO: Update argument type and name
    }
}
