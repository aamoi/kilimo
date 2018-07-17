package com.shamba.amoi.shambaapp.fragments.power;

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
import com.shamba.amoi.shambaapp.adapters.power.PowerSourcesRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.power.PowerSource;
import com.shamba.amoi.shambaapp.db.power.PowerSourceDao;
import com.shamba.amoi.shambaapp.fragments.labor.TaskAssignmentListFragment;
import com.shamba.amoi.shambaapp.models.power.PowerSourceItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.SharedUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PowerSourcesFragment extends BaseFragment {


    Button add_power_source;

    List<PowerSourceItem> powerSourceItemList;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PowerSourcesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PowerSourcesFragment newInstance(int columnCount) {
        PowerSourcesFragment fragment = new PowerSourcesFragment();
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
        getActivity().setTitle(R.string.title_fragment_power_source_list);
        View view = inflater.inflate(R.layout.fragment_powersources_list, container,
                false);
        getPSList();
        try {
            powerSourceItemList=new GetPowerResources().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        powerSourceItemList.addAll(getPSList());

        powerSourceItemList.addAll(PowerSourceItem.getPowerSourceList());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_power_source);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new PowerSourcesRecyclerViewAdapter(powerSourceItemList,
                (HomeActivity)this.getActivity()));

        add_power_source=(Button)view.findViewById(R.id.btn_add_ps);
        add_power_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.changeFragment((AppCompatActivity) getActivity(),R.id.fragment_placeholder_home,
                        new CreatePowerSourceFragment());
            }
        });


        return view;
    }

    private ArrayList<PowerSourceItem> getPSList()  {

       ArrayList<PowerSourceItem> powerSourceItems = new ArrayList<>();

        String[][] ps_list=getDummyPS();

        for (int count = 0; count < ps_list.length; ++count) {

            PowerSourceItem ps = new PowerSourceItem();

            ps.setPs_id(ps_list[count][0]);
            ps.setPower_source_name(ps_list[count][1]);
//            ps.setPower_source_ref(ps_list[count][2]);
//            ps.setPower_source_measuring_unit(ps_list[count][3]);
            powerSourceItems.add(ps);
        }
        return powerSourceItems;
    }

    public String[][] getDummyPS(){

        String[][] stocks={
                {"1","Diesel","ps_fuel_diesel", "Litres" },
                {"2","Petrol","ps_fuel_petrol", "Litres" },
                {"3","Solar","ps_solar", "Litres" },
                {"4","Electricity","ps_electricity","AM" },
        };

        return stocks;
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
//        mListener = null;
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
        void onListFragmentInteraction(PowerSourceItem item);
    }

    class GetPowerResources extends AsyncTask<Void, Void, List<PowerSourceItem>> {
        PowerSourceDao powerSourceDao;
        List<PowerSourceItem> powerResourceItemList;

        @Override
        protected void onPreExecute() {
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            powerSourceDao=db.powerSourceDao();
            powerResourceItemList=new ArrayList();
        }

        @Override
        protected List<PowerSourceItem> doInBackground(Void... voids) {
            List<PowerSource> db_ps = powerSourceDao.getAllPowerSources();

            if (db_ps.size() > 0){

                for (int count = 0; count < db_ps.size(); ++count) {
                    PowerSourceItem ps_Item = new PowerSourceItem();
                    ps_Item.setComments_on_power_source(db_ps.get(count).getComments_on_power_source());
                    ps_Item.setPower_source_name(db_ps.get(count).getPower_source_name());
                    ps_Item.setPower_source_unitOfMeasure(db_ps.get(count).getPower_source_unitOfMeasure());
                    ps_Item.setPower_source_use(db_ps.get(count).getPower_source_name());

                    powerResourceItemList.add(ps_Item);
                }
            }

            return powerResourceItemList;
        }

        @Override
        protected void onPostExecute(List<PowerSourceItem> powerSourceItemList) {
//            super.onPostExecute(masterPlantingPlanItems);
        }
    }
}
