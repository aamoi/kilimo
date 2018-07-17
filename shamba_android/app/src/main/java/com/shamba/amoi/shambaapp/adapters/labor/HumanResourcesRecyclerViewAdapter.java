package com.shamba.amoi.shambaapp.adapters.labor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.labor.HumanResourcesFragment.OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.fragments.labor.SalaryPaymentsFragment;
import com.shamba.amoi.shambaapp.fragments.labor.TaskAssignmentListFragment;
import com.shamba.amoi.shambaapp.fragments.projects.TaskSchedulingFragment;
import com.shamba.amoi.shambaapp.models.labor.HumanResourceItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class HumanResourcesRecyclerViewAdapter
        extends RecyclerView.Adapter<HumanResourcesRecyclerViewAdapter.ViewHolder> {

    private final List<HumanResourceItem> hr_list;
    private final HomeActivity homeActivity;

    public HumanResourcesRecyclerViewAdapter(List<HumanResourceItem> items,
                                             HomeActivity homeActivity) {
        hr_list = items;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_humanresources, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.hr_item = hr_list.get(position);
        holder.hr_name.setText(hr_list.get(position).getResource_name());
        holder.hr_skills.setText(hr_list.get(position).getResource_joining_date());

//        holder.hr_View.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
//
//                    holder.hr_name.setTextColor(Color.RED);
//                    holder.hr_skills.setTextColor(Color.RED);
//
//                    builder.setTitle(Html.fromHtml(
//                            "<font color='#FF7000'>Please select action on "+
//                                    holder.hr_name.getText().toString()+"</font>"));
//
//
//                    builder.setNeutralButton(Html.fromHtml("<font color='#FF7000'>Assign task</font>"),
//                            new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                        }
//                    });
//
//                    builder.setNegativeButton( Html.fromHtml("<font color='#FF7000'>Pay</font>")
//                            , new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                                                  }
//                    });
//                    builder.setPositiveButton("Details", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,new CreateHumanResourceFragment());
//                        }
//                    });
//
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
////                    mListener.onListFragmentInteraction(holder.hr_item);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return hr_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View hr_View;
        public final TextView hr_name;
        public final TextView hr_skills;
        public HumanResourceItem hr_item;

        public ViewHolder(View view) {
            super(view);
            hr_View = view;
            hr_name = (TextView) view.findViewById(R.id.txt_first_column);
            hr_skills = (TextView) view.findViewById(R.id.txt_second_column);

            DialogUtility dialogUtility= new DialogUtility(homeActivity,
                    "Select an action on the resource: ",
                    "Tasks","Payments","Details"){

                @Override
                public void onSelectNegativeDialogueOption(){
                    HumanResourceItem.current_hr_resource=hr_item;
                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            new TaskAssignmentListFragment());
                }

                @Override
                public void onSelectPostiveDialogueOption(){
                    HumanResourceItem.current_hr_resource=hr_item;
                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            new SalaryPaymentsFragment());
                }
            };

            dialogUtility.setSimpleDialogOnRecyclerListItem(hr_View,hr_name,hr_skills);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + hr_name.getText() + "'";
        }
    }
}
