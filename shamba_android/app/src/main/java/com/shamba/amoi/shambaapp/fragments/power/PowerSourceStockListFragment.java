package com.shamba.amoi.shambaapp.fragments.power;

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
import com.shamba.amoi.shambaapp.adapters.power.PowerSourceStockListRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.models.power.PowerSourceItem;
import com.shamba.amoi.shambaapp.models.power.PowerSourceStockItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class PowerSourceStockListFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    Button add_power_source_stock;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PowerSourceStockListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PowerSourceStockListFragment newInstance(int columnCount) {
        PowerSourceStockListFragment fragment = new PowerSourceStockListFragment();
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
        String title=PowerSourceItem.currentPowerSourceItem.getPower_source_name()+" "+
                getString(R.string.title_fragment_power_source_stocks) ;
        getActivity().setTitle(title);

        View view = inflater.inflate(R.layout.fragment_powersourcestocklist_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_power_source_stock);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new PowerSourceStockListRecyclerViewAdapter(
                (HomeActivity)this.getActivity(),
                PowerSourceStockItem.getAllPowerSourceStocks((HomeActivity) getActivity())));

        add_power_source_stock=(Button)view.findViewById(R.id.btn_add_power_source_stock);
        add_power_source_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home,new RestockPowerSource());

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
//    public interface OnListFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onListFragmentInteraction(DummyItem item);
//    }
}
