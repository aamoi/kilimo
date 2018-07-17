package com.shamba.amoi.shambaapp.adapters.labor;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.labor.SalaryPaymentsFragment.OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.models.labor.SalaryPaymentItem;
import com.shamba.amoi.shambaapp.models.labor.TaskPaymentItem;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SalaryPaymentsRecyclerViewAdapter extends
        RecyclerView.Adapter<SalaryPaymentsRecyclerViewAdapter.ViewHolder> {

    private final List<TaskPaymentItem> mValues;
    private final HomeActivity homeActivity;

    public SalaryPaymentsRecyclerViewAdapter( HomeActivity homeActivity,
                                              List<TaskPaymentItem> items) {
        mValues = items;
        this.homeActivity = homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_salarypayments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.task_name.setText(mValues.get(position).getTask_id());
        holder.due_date.setText(mValues.get(position).getPayment_due_date());
        holder.amount_due.setText(String.valueOf(mValues.get(position).getBalance_before()));

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView task_name;
        public final TextView due_date;
        public final TextView amount_due;

        public TaskPaymentItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            amount_due= (TextView) view.findViewById(R.id.txt_third_column);
            due_date= (TextView) view.findViewById(R.id.txt_second_column);
            task_name= (TextView) view.findViewById(R.id.txt_first_column);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + task_name.getText() + "'";
        }
    }
}
