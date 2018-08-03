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
import com.shamba.amoi.shambaapp.db.labor.Resource;
import com.shamba.amoi.shambaapp.db.labor.ResourceDao;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;
public class HumanResourcesFragment extends BaseFragment {

    List<ResourceItem> resourceItemList;
    Button add_hr;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    public HumanResourcesFragment() {
    }

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
        getActivity().setTitle("Resource List");
        View view = inflater.inflate(R.layout.fragment_humanresources_list, container,
                false);

        resourceItemList=ResourceItem.staticResourceItemList;

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_list_human_resource);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new HumanResourcesRecyclerViewAdapter(resourceItemList,
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ResourceItem item);
    }

    class GetHumanResources extends AsyncTask<Void, Void, List<ResourceItem>> {
        ResourceDao resourceDao;
        List<ResourceItem> resourceItemList;

        @Override
        protected void onPreExecute() {
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            resourceDao=db.resourceDao();
            resourceItemList =new ArrayList();
        }

        @Override
        protected List<ResourceItem> doInBackground(Void... voids) {
            List<Resource> db_hr = resourceDao.getAllResources();

            if (db_hr.size() > 0){

                for (int count = 0; count < db_hr.size(); ++count) {
                    ResourceItem resourceItem = new ResourceItem();
//                    hrItem.setComments_on_resource(db_hr.get(count).getComments_on_resource());
//                    hrItem.setResource_contract(db_hr.get(count).getResource_contract());
//                    hrItem.setResource_id(db_hr.get(count).getResource_id());
//                    hrItem.setResource_joining_date(db_hr.get(count).getResource_joining_date());
//                    hrItem.setResource_main_skillset(db_hr.get(count).getResource_main_skillset());
//                    hrItem.setResource_name(db_hr.get(count).getResource_name());
//                    hrItem.setResource_pay_rate(db_hr.get(count).getResource_pay_rate());
//                    hrItem.setResource_type(db_hr.get(count).getResource_type());
                    resourceItemList.add(resourceItem);
                }
            }

            return resourceItemList;
        }

        @Override
        protected void onPostExecute(List<ResourceItem> assetItemListssetItems) {
//            super.onPostExecute(masterPlantingPlanItems);
        }
    }
}
