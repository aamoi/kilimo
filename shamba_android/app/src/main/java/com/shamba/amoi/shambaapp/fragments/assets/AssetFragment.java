package com.shamba.amoi.shambaapp.fragments.assets;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
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
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.assets.Asset;
import com.shamba.amoi.shambaapp.db.assets.AssetDao;;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.adapters.assets.AssetRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AssetFragment extends BaseFragment {

    Button add_asset;

    List<AssetItem> asset_list;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AssetFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AssetFragment newInstance(int columnCount) {
        AssetFragment fragment = new AssetFragment();
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
        getActivity().setTitle(R.string.title_fragment_assets);

        View view = inflater.inflate(R.layout.fragment_asset_list, container, false);


        GetAssets assets=new GetAssets();
        try {
            asset_list=assets.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        asset_list.addAll(AssetItem.getAssets());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_assets);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AssetRecyclerViewAdapter((HomeActivity)getActivity(),asset_list));

        add_asset=(Button)view.findViewById(R.id.btn_add_asset);
        add_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),R.id.fragment_placeholder_home,
                        new CreateAssetFragment());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(AssetItem uri);
    }

    class GetAssets extends AsyncTask<Void, Void, List<AssetItem>> {

        AssetDao assetDao;
        List<AssetItem> assetItems;

        @Override
        protected void onPreExecute() {
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            assetDao=db.assetDao();
            assetItems=new ArrayList();
        }

        @Override
        protected List<AssetItem> doInBackground(Void... voids) {

            List<Asset> db_assets = assetDao.getAllAssets();

            if (db_assets.size() > 0){

                for (int count = 0; count < db_assets.size(); ++count) {
                    AssetItem assetItem = new AssetItem();
                    assetItem.setAsset_name(db_assets.get(count).getAsset_name());
                    assetItem.setManufacturer(db_assets.get(count).getManufacturer());
                    assetItem.setCapacity(db_assets.get(count).getCapacity());
                    assetItem.setUnit_of_measure(db_assets.get(count).getUnit_of_measure());

                    assetItems.add(assetItem);
                }
            }

            return assetItems;
        }

        @Override
        protected void onPostExecute(List<AssetItem> assetItemListssetItems) {
//            super.onPostExecute(masterPlantingPlanItems);
        }
    }
}
