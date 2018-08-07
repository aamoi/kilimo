package com.shamba.amoi.shambaapp.fragments.product;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.inventory.ProductsRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends BaseFragment {
    Button btn_add_product;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    public ProductsFragment() {

    }
    @SuppressWarnings("unused")
    public static ProductsFragment newInstance(int columnCount) {
        ProductsFragment fragment = new ProductsFragment();
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
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);

        List<ProductItem> productItems=new ArrayList<>();
        productItems= ProductItem.staticProductItemList;

        getActivity().setTitle(String.valueOf(productItems.size())+" "+
                getString(R.string.title_fragment_product_list));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_invenory);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(new ProductsRecyclerViewAdapter(productItems,
                (HomeActivity) this.getActivity()));

        btn_add_product = (Button) view.findViewById(R.id.btn_add_product);

        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreateProductScreen();
            }
        });
        return view;
    }

    /**
     * Switch to create product fragment when create
     * @View v
     */
    public void goToCreateProductScreen() {

        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                R.id.fragment_placeholder_home, new CreateProductFragment());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}





