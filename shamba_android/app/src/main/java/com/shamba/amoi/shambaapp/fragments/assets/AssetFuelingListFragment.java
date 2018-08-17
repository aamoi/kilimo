package com.shamba.amoi.shambaapp.fragments.assets;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.assets.AssetFuelingListRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.fragments.product.ProductStockFragment;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.product.StockUtilizationItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class AssetFuelingListFragment extends BaseFragment {

    private Button btn_fuel_asset;
    private static final String asset_key = "asset_key";
    private Integer asset_id ;
    private OnListFragmentInteractionListener mListener;

    public AssetFuelingListFragment() {
    }
    @SuppressWarnings("unused")
    public static AssetFuelingListFragment newInstance(int asset_id) {
        AssetFuelingListFragment fragment = new AssetFuelingListFragment();
        Bundle args = new Bundle();
        args.putInt(asset_key, asset_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            asset_id = getArguments().getInt(asset_key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String title = AssetItem.currentAssetItem.getName() + " " +
                getString(R.string.title_fragment_asset_fuelings);

        getActivity().setTitle(title);
        View view = inflater.inflate(R.layout.fragment_assetfuelinglist_list, container,
                false);

        List<StockUtilizationItem> fuelingItems = new ArrayList<>();

        fuelingItems=StockUtilizationItem.getStockUtilizationItemByAssetId(StockUtilizationItem.
                getAllStockUtilizations(getActivity()),AssetItem.currentAssetItem.getId());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_asset_fuelings);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AssetFuelingListRecyclerViewAdapter(fuelingItems,
                (HomeActivity) getActivity()));

        btn_fuel_asset=(Button)view.findViewById(R.id.btn_fuel_asset);

        btn_fuel_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
//                        R.id.fragment_placeholder_home, new InventoryUtilizationFragment());
                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home, ProductStockFragment.newInstance(
                                AssetItem.currentAssetItem.getId()));
            }
        });

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

    public interface OnListFragmentInteractionListener {
    }
}
