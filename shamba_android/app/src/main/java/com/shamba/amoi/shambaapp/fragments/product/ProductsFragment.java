package com.shamba.amoi.shambaapp.fragments.product;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.product.ProductsRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends BaseFragment {
    Button btn_add_product;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    List<ProductItem> productItems = new ArrayList<>();
    final List<ProductItem> allItems = ProductItem.staticProductItemList;

    ProductsRecyclerViewAdapter productsRecyclerViewAdapter;
    RecyclerView recyclerView;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public ProductsFragment() { }

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

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list,
                container, false);
        productItems = ProductItem.getAllProducts(getActivity());
        getActivity().setTitle(String.valueOf(productItems.size()) + " " +
                getString(R.string.title_fragment_product_list));
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        productsRecyclerViewAdapter = new ProductsRecyclerViewAdapter(productItems,
                (HomeActivity) this.getActivity());

        productsRecyclerViewAdapter.setProductList(getContext(), productItems);

        recyclerView.setAdapter(productsRecyclerViewAdapter);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().
                getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().
                    getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String query) {
                    Log.i("onQueryTextChange", query);
                    productsRecyclerViewAdapter.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    productsRecyclerViewAdapter.getFilter().filter(query);

                    return false;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}





