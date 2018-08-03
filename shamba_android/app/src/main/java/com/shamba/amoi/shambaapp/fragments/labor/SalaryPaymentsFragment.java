package com.shamba.amoi.shambaapp.fragments.labor;

import android.content.Context;
import android.os.Bundle;;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.labor.SalaryPaymentsRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.models.labor.TaskPaymentItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SalaryPaymentsFragment extends BaseFragment {

    Button add_payment=null;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    List<TaskPaymentItem> salaryPaymentItems;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SalaryPaymentsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SalaryPaymentsFragment newInstance(int columnCount) {
        SalaryPaymentsFragment fragment = new SalaryPaymentsFragment();
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

        String title= ResourceItem.selectedResourceItem.getResource_name()+" "+
                getString(R.string.title_fragment_salary_payment_list);

        getActivity().setTitle(title);

        View view = inflater.inflate(R.layout.fragment_salarypayments_list, container, false);

        add_payment=(Button) view.findViewById(R.id.btn_add_payment);

        salaryPaymentItems= TaskPaymentItem.getAllResourceTaskPayments(getActivity(),
                String.valueOf(ResourceItem.selectedResourceItem.getId()));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_salary_payments);
        recyclerView.setAdapter(new SalaryPaymentsRecyclerViewAdapter((HomeActivity)getActivity(),
                salaryPaymentItems));

//        add_payment

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
//        void onListFragmentInteraction(DummyItem item);
    }
}
