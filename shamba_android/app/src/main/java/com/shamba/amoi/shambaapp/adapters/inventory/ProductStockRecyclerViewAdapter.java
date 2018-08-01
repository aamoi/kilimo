package com.shamba.amoi.shambaapp.adapters.inventory;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.product.InventoryUtilizationFragment;
import com.shamba.amoi.shambaapp.fragments.product.RestockProductFragment;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.ProductStockItem;
import com.shamba.amoi.shambaapp.models.product.VendorItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.List;

public class ProductStockRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductStockRecyclerViewAdapter.ViewHolder> {

    private final List<ProductStockItem> product_stock_list;
    private final HomeActivity homeActivity;
    private ProductItem productItem;

    public ProductStockRecyclerViewAdapter(List<ProductStockItem> items,
                                           HomeActivity homeActivity) {
        product_stock_list = items;
        this.homeActivity = homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        productItem = ProductItem.selectedProductItem;

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_productstock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.inv_stock_Item = product_stock_list.get(position);

        String vendor = VendorItem.getProductItemByID(VendorItem.staticVendorItemList,
                product_stock_list.get(position).getVendor_id()).getVendor_name();
        holder.supplier_name.setText(vendor);

        String purchase_date = product_stock_list.get(position).getPurchase_date().substring(0, 10);
        holder.stock_date.setText(purchase_date);
        holder.stock_quantity.setText(String.valueOf(product_stock_list.get(position).
                getPurchase_quantity()));

        String order_status = product_stock_list.get(position).getStock_order_status();

        if (!(order_status == null)) {
            Log.d("Stock list item status", order_status);
            if (!order_status.equalsIgnoreCase("Delivered")) {
                holder.stock_date.setBackgroundColor(Color.RED);
                holder.stock_quantity.setBackgroundColor(Color.RED);
                holder.supplier_name.setBackgroundColor(Color.RED);
            }
        } else {
            Log.d("Stock list item null", order_status);
            holder.stock_date.setBackgroundColor(Color.TRANSPARENT);
            holder.stock_quantity.setBackgroundColor(Color.TRANSPARENT);
            holder.supplier_name.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.stock_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_stock_list != null ? product_stock_list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View stock_view;

        public final TextView supplier_name;
        public final TextView stock_date;
        public final TextView stock_quantity;
        public ProductStockItem inv_stock_Item;

        @SuppressLint("ResourceAsColor")
        public ViewHolder(View view) {
            super(view);
            stock_view = view;

            stock_date = (TextView) view.findViewById(R.id.txt_first_column);
            stock_quantity = (TextView) view.findViewById(R.id.txt_second_column);
            supplier_name = (TextView) view.findViewById(R.id.txt_third_column);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductStockItem.selectedProductStockItem = inv_stock_Item;
                    String product_name = productItem.getProduct_name();

                    try {
                        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);

                        builder.setTitle(Html.fromHtml(
                                "<font color='#FF7000'>Action on " + product_name +
                                        " stock dated  " +
                                        stock_date.getText().toString() + "? </font>"));

                        builder.setNegativeButton(Html.fromHtml(
                                "<font color='#FF7000'>Utilize stock</font>"),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        BaseFragment.changeFragment(homeActivity,
                                                R.id.fragment_placeholder_home,
                                                new InventoryUtilizationFragment());
                                    }
                                });

                        builder.setPositiveButton(Html.fromHtml(
                                "<font color='#FF7000'>Details</font>")
                                , new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        BaseFragment.changeFragment(homeActivity,
                                                R.id.fragment_placeholder_home,
                                                RestockProductFragment.newInstance());
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
