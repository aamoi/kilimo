package com.shamba.amoi.shambaapp.adapters.product;

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
import com.shamba.amoi.shambaapp.models.product.ProductVarietyItem;
import com.shamba.amoi.shambaapp.models.product.VendorItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.List;


public class ProductVarietyRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductVarietyRecyclerViewAdapter.ViewHolder> {

    List<ProductVarietyItem> productVarietyItems=null;
    private final HomeActivity homeActivity;
    private ProductVarietyItem productVarietyItem;
    public Integer productID;


    public ProductVarietyRecyclerViewAdapter(List<ProductVarietyItem> items,
                                             HomeActivity homeActivity) {
        productVarietyItems = items;
        this.homeActivity = homeActivity;
    }

    public ProductVarietyRecyclerViewAdapter(List<ProductVarietyItem> items,
                                             HomeActivity homeActivity, Integer product_id) {
        productVarietyItems = items;
        this.homeActivity = homeActivity;
        this.productID=product_id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        productVarietyItem = ProductVarietyItem.selectedProductVarietyItem;

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_productstock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.product_variety_item = productVarietyItems.get(position);

        if(productVarietyItems!=null){

            String vendor = VendorItem.getVendorItemByID(VendorItem.staticVendorItemList,
                    productVarietyItems.get(position).()).getVendor_name();
            holder.supplier_name.setText(vendor);

            String purchase_date = product_stock_list.get(position).getPurchase_date().substring(0, 10);
            holder.stock_date.setText(purchase_date);
            holder.stock_quantity.setText(String.valueOf(product_stock_list.get(position).
                    getStock_balance()));

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
        }

        holder.stock_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return productVarietyItems != null ? productVarietyItems.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View product_variety_view;

        public final TextView supplier_name;
        public final TextView stock_quantity;
        public final TextView stock_date;

        public ProductVarietyItem product_variety_item;

        @SuppressLint("ResourceAsColor")
        public ViewHolder(View view) {
            super(view);
            product_variety_view = view;

            supplier_name = (TextView) view.findViewById(R.id.txt_third_column);
            stock_date = (TextView) view.findViewById(R.id.txt_first_column);
            stock_quantity = (TextView) view.findViewById(R.id.txt_second_column);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductVarietyItem.selectedProductVarietyItem = productVarietyItem;
                    ProductItem productItem=ProductItem.getProductItemByID(
                            ProductItem.getAllProducts(homeActivity),inv_stock_Item.getProduct_id());
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
                                        if((productID!=null)&&(productID>0)){
                                            BaseFragment.changeFragment(homeActivity,
                                                    R.id.fragment_placeholder_home,
                                                    InventoryUtilizationFragment.
                                                            newInstance(productID));
                                        }
                                        else{
                                            BaseFragment.changeFragment(homeActivity,
                                                    R.id.fragment_placeholder_home,
                                                    new InventoryUtilizationFragment());
                                        }
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

                    } catch (Exception ex) { ex.printStackTrace(); }
                }
            };
            product_variety_view.setOnClickListener(listener);
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
