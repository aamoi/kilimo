package com.shamba.amoi.shambaapp.fragments.labor;

import android.content.Context;
import android.os.Bundle;;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.labor.PaymentsRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.models.labor.PaymentItem;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import java.util.List;
public class PaymentsFragment extends BaseFragment {
    Button add_payment=null;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    public PaymentsFragment() {
    }

    @SuppressWarnings("unused")
    public static PaymentsFragment newInstance(int columnCount) {
        PaymentsFragment fragment = new PaymentsFragment();
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

        List<TaskItem> taskItems=TaskItem.getAllTask(getActivity());

        String title= ResourceItem.selectedResourceItem.getResource_name()+" "+
                getString(R.string.title_fragment_salary_payment_list);
        getActivity().setTitle(title);

        View view = inflater.inflate(R.layout.fragment_payments_list, container,
                false);

        Log.d("Payment| resource id", String.valueOf( ResourceItem.selectedResourceItem.getId()));

        List<PaymentItem> resourcePayments=PaymentItem.getPaymentByResource(getActivity(),
                ResourceItem.selectedResourceItem.getId());

        Log.d("Payment| # res. pays", String.valueOf(resourcePayments.size()));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_salary_payments);
        recyclerView.setAdapter(new PaymentsRecyclerViewAdapter((HomeActivity)getActivity(),
                resourcePayments));

        add_payment=(Button) view.findViewById(R.id.btn_add_payment);

        add_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home, new CreatePaymentFragment());
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
