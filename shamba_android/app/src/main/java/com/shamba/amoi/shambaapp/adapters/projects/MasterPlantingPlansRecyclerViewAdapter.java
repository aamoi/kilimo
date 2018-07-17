//package com.shamba.amoi.shambaapp.adapters.projects;
//
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.shamba.amoi.shambaapp.R;
//import com.shamba.amoi.shambaapp.fragments.projects.MasterPlantingPlanDetails;
//import com.shamba.amoi.shambaapp.fragments.projects.MasterPlantingPlansFragment.OnListFragmentInteractionListener;
//import com.shamba.amoi.shambaapp.models.projects.MasterPlantingPlanItem;
//import com.shamba.amoi.shambaapp.shareResources.DialogUtility;
//
//import java.util.List;
//
///**
// * specified {@link OnListFragmentInteractionListener}.
// * TODO: Replace the implementation with code for your data type.
// */
//public class MasterPlantingPlansRecyclerViewAdapter extends
//        RecyclerView.Adapter<MasterPlantingPlansRecyclerViewAdapter.ViewHolder> {
//
//    private final List<MasterPlantingPlanItem> master_plans;
//    private final OnListFragmentInteractionListener mListener;
//
//    public MasterPlantingPlansRecyclerViewAdapter(List<MasterPlantingPlanItem> items,
//                                                  OnListFragmentInteractionListener listener) {
//        master_plans = items;
//        mListener = listener;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.submit_form_details.fragment_masterplantingplans, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.master_plans_item = master_plans.get(position);
//        holder.plan_name.setText(master_plans.get(position).getPlan_name());
//        holder.plan_date.setText(master_plans.get(position).getSeedbed_date());
//
////        holder.master_plan_View.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (null != mListener) {
////                    CreateMasterPlantingPlanFragment cmppf=new CreateMasterPlantingPlanFragment();
////                    FragmentTransaction ft=project_activity.getFragmentManager().beginTransaction();
////                    ft.replace(R.id.fragment_placeholder_projects,cmppf);
////                    ft.addToBackStack(null);
////                    ft.commit();
////
////                    mListener.onListFragmentInteraction(holder.master_plans_item);
////                }
////            }
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        try{
//            return master_plans.size();
//        }catch (Exception e){
//            return 0;
//        }
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final View master_plan_View;
//        public final TextView plan_name;
//        public final TextView plan_date;
//        public MasterPlantingPlanItem master_plans_item;
//
//        public ViewHolder(View view) {
//            super(view);
//            master_plan_View = view;
//            plan_name = (TextView) view.findViewById(R.id.master_plan_name);
//            plan_date = (TextView) view.findViewById(R.id.master_plan_date);
//
//           DialogUtility dialogUtility= new DialogUtility(project_activity,"Action on Master Plan?",
//                    "Details","Actuals"){
//               @Override
//           public void onSelectNegativeDialogueOption(){
//                   Fragment create_ps=new MasterPlantingPlanDetails();
//                   FragmentTransaction ft=project_activity.getFragmentManager().beginTransaction();
////                   ft.replace(R.id.fragment_placeholder_projects, create_ps);
//                   ft.addToBackStack(null);
//                   ft.commit();
//               }
//           };
//
//            dialogUtility.setSimpleDialogOnRecyclerListItem(master_plan_View,plan_name,plan_date);
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + " '" + plan_name.getText() + "'";
//        }
//    }
//}
