package com.shamba.amoi.shambaapp.adapters.labor;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.assets.AssetServicingItem;
import com.shamba.amoi.shambaapp.models.assets.ServiceTypeItem;
import com.shamba.amoi.shambaapp.models.labor.PaymentItem;
import com.shamba.amoi.shambaapp.models.labor.TaskAssignmentItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

public class PaymentsRecyclerViewAdapter extends
        RecyclerView.Adapter<PaymentsRecyclerViewAdapter.ViewHolder> {

    private final List<PaymentItem> mValues;
    private final HomeActivity homeActivity;

    public PaymentsRecyclerViewAdapter(HomeActivity homeActivity,
                                       List<PaymentItem> items) {
        mValues = items;
        this.homeActivity = homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_payments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        if (mValues.get(position).getTask_id() > 0) {
            String task_name = TaskItem.getTaskItemById(TaskItem.staticTaskItems,
                    mValues.get(position).getTask_id()).getTask_name();
            holder.task_name.setText(task_name);
        } else {
            AssetServicingItem assetServicingItem= AssetServicingItem.getAssetServicingItemById(
                    AssetServicingItem.getAllAssetServicings(homeActivity),mValues.get(position).
                           getService_id());

            AssetItem assetItem=AssetItem.getAssetItemById(AssetItem.getAllAssets(homeActivity),
                    assetServicingItem.getAsset_id());

            String service_type_name= ServiceTypeItem.getServiceTypeById(homeActivity,
                    assetItem.getService_type_id()).getName();
            holder.task_name.setText(service_type_name);
        }

        holder.due_date.setText(mValues.get(position).getDue_date().substring(0, 10));
        holder.amount_due.setText(String.valueOf(mValues.get(position).getAmount_due()));
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

        public PaymentItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            amount_due = (TextView) view.findViewById(R.id.txt_third_column);
            due_date = (TextView) view.findViewById(R.id.txt_second_column);
            task_name = (TextView) view.findViewById(R.id.txt_first_column);

            DialogUtility dialogUtility = new DialogUtility(homeActivity,
                    "Select action on payment: ",
                    "", "Pay", "Details") {

                @Override
                public void onSelectNegativeDialogueOption() {
                    PaymentItem.selectedPaymentItem = mItem;
                    Log.d("Payment| selected!", mItem.toString());
                }

                @Override
                public void onSelectPostiveDialogueOption() {
                    PaymentItem.selectedPaymentItem = mItem;
                    Log.d("Payment| selected!", mItem.toString());
                }
            };
            dialogUtility.setSimpleDialogOnRecyclerListItem(mView,amount_due,due_date,task_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + task_name.getText() + "'";
        }
    }
}
