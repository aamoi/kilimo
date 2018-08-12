package com.shamba.amoi.shambaapp.adapters.labor;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.labor.PaymentsFragment;
import com.shamba.amoi.shambaapp.fragments.labor.TaskAssignmentListFragment;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

public class ResourcesRecyclerViewAdapter
        extends RecyclerView.Adapter<ResourcesRecyclerViewAdapter.ViewHolder> {

    private final List<ResourceItem> resourc_list;
    private final HomeActivity homeActivity;

    public ResourcesRecyclerViewAdapter(List<ResourceItem> items,
                                        HomeActivity homeActivity) {
        resourc_list = items;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_resources, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.resource_item = resourc_list.get(position);
        holder.resource_name.setText(resourc_list.get(position).getResource_name());
        holder.hr_skills.setText(resourc_list.get(position).getPhone());

//        holder.resource_View.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
//
//                    holder.resource_name.setTextColor(Color.RED);
//                    holder.hr_skills.setTextColor(Color.RED);
//
//                    builder.setTitle(Html.fromHtml(
//                            "<font color='#FF7000'>Please select action on "+
//                                    holder.resource_name.getText().toString()+"</font>"));
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
//                            BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,new CreateResourceFragment());
//                        }
//                    });
//
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
////                    mListener.onListFragmentInteraction(holder.resource_item);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return resourc_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View resource_View;
        public final TextView resource_name;
        public final TextView hr_skills;
        public ResourceItem resource_item;

        public ViewHolder(View view) {
            super(view);
            resource_View = view;
            resource_name = (TextView) view.findViewById(R.id.txt_first_column);
            hr_skills = (TextView) view.findViewById(R.id.txt_second_column);

            DialogUtility dialogUtility= new DialogUtility(homeActivity,
                    "Select an action on the resource: ",
                    "Tasks","Payments","Details"){

                @Override
                public void onSelectNegativeDialogueOption(){
                    ResourceItem.selectedResourceItem= resource_item;
                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            new TaskAssignmentListFragment());
                }

                @Override
                public void onSelectPostiveDialogueOption(){
                    ResourceItem.selectedResourceItem= resource_item;
                    Log.d("Resources| selected!", resource_item.toString() );
                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            new PaymentsFragment());
                }

//                @Override
//                public void onSelectNeutralDialogueOption(){
//                    ResourceItem.selectedResourceItem=resource_item;
//                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
//                            new PaymentsFragment());
//                }

            };

            dialogUtility.setSimpleDialogOnRecyclerListItem(resource_View, resource_name,hr_skills);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + resource_name.getText() + "'";
        }
    }
}
