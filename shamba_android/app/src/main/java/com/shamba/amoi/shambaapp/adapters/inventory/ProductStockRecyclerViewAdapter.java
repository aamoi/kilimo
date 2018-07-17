package com.shamba.amoi.shambaapp.adapters.inventory;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.inventory.InventoryUtilizationFragment;
import com.shamba.amoi.shambaapp.fragments.inventory.ProductStockFragment.OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.models.inventory.ProductStockItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProductStockRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductStockRecyclerViewAdapter.ViewHolder> {

    private final List<ProductStockItem> product_stock_list;
    private final HomeActivity homeActivity;

    public ProductStockRecyclerViewAdapter(List<ProductStockItem> items,
                                           HomeActivity homeActivity) {
        product_stock_list = items;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_productstock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.inv_stock_Item = product_stock_list.get(position);

        holder.supplier_name.setText(product_stock_list.get(position).getVendor_name());
        holder.stock_date.setText(product_stock_list.get(position).getStock_date());
        holder.stock_quantity.setText(String.valueOf(product_stock_list.get(position).getStock_quantity()));

        holder.stock_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (null != mListener) {
//                    mListener.onListFragmentInteraction(holder.inv_stock_Item);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_stock_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View stock_view;

        public final TextView supplier_name;
        public final TextView stock_date;
        public final TextView stock_quantity;
        public ProductStockItem inv_stock_Item;

        public ViewHolder(View view) {
            super(view);
            stock_view = view;

            stock_date=(TextView) view.findViewById(R.id.txt_first_column);
            stock_quantity= (TextView) view.findViewById(R.id.txt_second_column);
             supplier_name= (TextView) view.findViewById(R.id.txt_third_column);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseFragment.productStockItem=inv_stock_Item;
                    String product_name=BaseFragment.productItem.getProduct_name();

                    try {
                        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);

                        builder.setTitle(Html.fromHtml(
                                "<font color='#FF7000'>Action on "+product_name +" stock dated  "+
                                        stock_date.getText().toString()+"? </font>"));

                        builder.setNegativeButton(Html.fromHtml("<font color='#FF7000'>Utilize stock</font>"),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        BaseFragment.changeFragment(homeActivity,
                                                R.id.fragment_placeholder_home,
                                                new InventoryUtilizationFragment());

                                        BaseFragment.changeFragment(homeActivity,
                                                R.id.fragment_placeholder_home,new InventoryUtilizationFragment());
                                    }
                                });

                        builder.setPositiveButton( Html.fromHtml("<font color='#FF7000'>Details</font>")
                                , new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();

                    } catch (Exception ex)

                    {
                        ex.printStackTrace();
                    }
                }
            };

            stock_view.setOnClickListener(listener);
            supplier_name.setOnClickListener(listener);
            stock_date.setOnClickListener(listener);
            stock_quantity.setOnClickListener(listener);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + stock_date.getText() + "'";
        }
    }
}
