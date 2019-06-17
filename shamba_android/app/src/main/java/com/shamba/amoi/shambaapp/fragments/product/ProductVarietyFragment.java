package com.shamba.amoi.shambaapp.fragments.product;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.product.ProductVarietyRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.adapters.product.ProductsRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.fragments.product.dummy.DummyContent;
import com.shamba.amoi.shambaapp.fragments.product.dummy.DummyContent.DummyItem;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.ProductVarietyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ProductVarietyFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<ProductVarietyItem> productVarietyItemList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductVarietyFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProductVarietyFragment newInstance(int columnCount) {
        ProductVarietyFragment fragment = new ProductVarietyFragment();
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
        View view = inflater.inflate(R.layout.fragment_productvariety_list, container, false);

        productVarietyItemList = ProductVarietyItem.getProductVarieties(getActivity(),
                ProductItem.selectedProductItem.getId());

        getActivity().setTitle(String.valueOf(productVarietyItemList.size()) + " " +
                getString(R.string.title_fragment_product_list));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        prod = new ProductsRecyclerViewAdapter(productItems,
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
