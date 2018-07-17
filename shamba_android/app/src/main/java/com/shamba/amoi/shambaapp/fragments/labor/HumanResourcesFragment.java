package com.shamba.amoi.shambaapp.fragments.labor;

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
import com.shamba.amoi.shambaapp.adapters.labor.HumanResourcesRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.labor.HumanResource;
import com.shamba.amoi.shambaapp.db.labor.HumanResourceDao;
import com.shamba.amoi.shambaapp.models.labor.HumanResourceItem;
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
public class HumanResourcesFragment extends BaseFragment {

    List<HumanResourceItem> hr_list_items;
    Button add_hr;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HumanResourcesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HumanResourcesFragment newInstance(int columnCount) {
        HumanResourcesFragment fragment = new HumanResourcesFragment();
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
        getActivity().setTitle("Human Resource List");
        View view = inflater.inflate(R.layout.fragment_humanresources_list, container,
                false);
        try {
            hr_list_items=new GetHumanResources().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_list_human_resource);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new HumanResourcesRecyclerViewAdapter(hr_list_items,
                (HomeActivity)this.getActivity()));
        add_hr=(Button)view.findViewById(R.id.btn_add_hr);

        add_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),R.id.fragment_placeholder_home,
                        new CreateHumanResourceFragment());
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
        void onListFragmentInteraction(HumanResourceItem item);
    }

    class GetHumanResources extends AsyncTask<Void, Void, List<HumanResourceItem>> {
        HumanResourceDao hr_dao;
        List<HumanResourceItem> humanResourceItemList;

        @Override
        protected void onPreExecute() {
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            hr_dao=db.humanResourceDao();
            humanResourceItemList=new ArrayList();
        }

        @Override
        protected List<HumanResourceItem> doInBackground(Void... voids) {
            List<HumanResource> db_hr = hr_dao.getAllHumanResource();

            if (db_hr.size() > 0){

                for (int count = 0; count < db_hr.size(); ++count) {
                    HumanResourceItem hrItem = new HumanResourceItem();
                    hrItem.setComments_on_resource(db_hr.get(count).getComments_on_resource());
                    hrItem.setResource_contract(db_hr.get(count).getResource_contract());
                    hrItem.setResource_id(db_hr.get(count).getResource_id());
                    hrItem.setResource_joining_date(db_hr.get(count).getResource_joining_date());
                    hrItem.setResource_main_skillset(db_hr.get(count).getResource_main_skillset());
                    hrItem.setResource_name(db_hr.get(count).getResource_name());
                    hrItem.setResource_pay_rate(db_hr.get(count).getResource_pay_rate());
                    hrItem.setResource_type(db_hr.get(count).getResource_type());
                    humanResourceItemList.add(hrItem);
                }
            }

            return humanResourceItemList;
        }

        @Override
        protected void onPostExecute(List<HumanResourceItem> assetItemListssetItems) {
//            super.onPostExecute(masterPlantingPlanItems);
        }
    }
}
