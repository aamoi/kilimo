package com.shamba.amoi.shambaapp.adapters.power;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.labor.SalaryPaymentsFragment;
import com.shamba.amoi.shambaapp.fragments.labor.TaskAssignmentListFragment;
import com.shamba.amoi.shambaapp.fragments.power.PowerSourceStockListFragment;
import com.shamba.amoi.shambaapp.fragments.power.PowerSourcesFragment.OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.fragments.power.RestockPowerSource;
import com.shamba.amoi.shambaapp.models.labor.HumanResourceItem;
import com.shamba.amoi.shambaapp.models.power.PowerSourceItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PowerSourcesRecyclerViewAdapter
        extends RecyclerView.Adapter<PowerSourcesRecyclerViewAdapter.ViewHolder> {

    private final List<PowerSourceItem> powerSourceItemList;
    private final HomeActivity homeActivity;

    public PowerSourcesRecyclerViewAdapter(List<PowerSourceItem> items, HomeActivity homeActivity) {
        powerSourceItemList = items;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_powersources, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.ps_Item = powerSourceItemList.get(position);
        holder.ps_name.setText(powerSourceItemList.get(position).getPower_source_name());
        holder.ps_uom.setText(powerSourceItemList.get(position).getPower_source_unitOfMeasure());

//        holder.ps_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(powerActivity);
//
//
//                    builder.setTitle(Html.fromHtml(
//                            "<font color='#FF7000'>Action on "+
//                                    holder.ps_name.getText().toString()+" ?</font>"));
//
//
////                    builder.setNeutralButton(Html.fromHtml("<font color='#FF7000'>Stock</font>"),
////                            new DialogInterface.OnClickListener() {
////                                public void onClick(DialogInterface dialog, int id) {
////                                }
////                            });
//
//                    builder.setNegativeButton( Html.fromHtml("<font color='#FF7000'>Stock</font>")
//                            , new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                }
//                            });
//                    builder.setPositiveButton("Details", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                                           }
//                    });
//
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                    mListener.onListFragmentInteraction(holder.ps_Item);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        try{
            return powerSourceItemList.size();
        }
        catch(Exception e){
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View ps_view;
        public final TextView ps_name;
        public final TextView ps_uom;
        public PowerSourceItem ps_Item;

        public ViewHolder(View view) {
            super(view);
            ps_view = view;
            ps_name = (TextView) view.findViewById(R.id.txt_first_column);
            ps_uom = (TextView) view.findViewById(R.id.txt_second_column);

            DialogUtility dialogUtility= new DialogUtility(homeActivity,
                    "Select an action on power source: ",
                    "Details","Restock","Consume"){

                @Override
                public void onSelectNegativeDialogueOption(){
                    PowerSourceItem.currentPowerSourceItem=ps_Item;
                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            new TaskAssignmentListFragment());
                }

                @Override
                public void onSelectPostiveDialogueOption(){
                    PowerSourceItem.currentPowerSourceItem=ps_Item;
                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            new PowerSourceStockListFragment());
                }
            };

            dialogUtility.setSimpleDialogOnRecyclerListItem(ps_view,ps_name,ps_uom);
        }

        @Override
        public String toString() {
            return super.toString() + " 'pppppp" + ps_name.getText() + "'";
        }
    }
}
