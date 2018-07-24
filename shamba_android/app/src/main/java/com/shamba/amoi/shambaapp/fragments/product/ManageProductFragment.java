package com.shamba.amoi.shambaapp.fragments.product;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ManageProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ManageProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageProductFragment extends Fragment {
    TextView product_name;
    TextView product_vendor;
    TextView package_size;
    Button restock_product;
    Button unstock_product;

    String productName;
    String productVendor;
    String packageSize;
    int product_id;

    private OnFragmentInteractionListener mListener;

    public ManageProductFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param name product_name.
     * @param vendor product_vendor.
     * @return A new instance of fragment ManageProductFragment.
     */
    public static ManageProductFragment newInstance(String name, String vendor,
                                                    String package_size, int product_id) {
        ManageProductFragment fragment = new ManageProductFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("vendor", vendor);
        args.putString("package_size", package_size);
        args.putInt("product_id", product_id);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productName=getArguments().getString("name");
            productVendor=getArguments().getString("vendor");
            packageSize=getArguments().getString("package_size");
            product_id=getArguments().getInt("product_id");
        }
        else{}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the submit_form_details for this fragment
        View view=inflater.inflate(R.layout.fragment_manage_product, container, false);
        product_vendor=(TextView) view.findViewById(R.id.product_vendor);
        product_name=(TextView) view.findViewById(R.id.product_name);
        package_size=(TextView) view.findViewById(R.id.package_size);
        product_name.setText(productName);
        product_vendor.setText(productVendor);
        package_size.setText(packageSize);

        restock_product=(Button)view.findViewById(R.id.btn_restock_product);
        restock_product.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //goToRestockProductScreen();
                goToManageProductStockScreen();
            }
        });
        unstock_product=(Button)view.findViewById(R.id.btn_unstock_product);
        unstock_product.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goToUnstockProductScreen();
            }
        });

        return view ;
    }

    /**
     * Load restock screen
     */
    public void goToRestockProductScreen() {
//        RestockProductFragment restock_prod = new RestockProductFragment();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_placeholder_inventory, restock_prod);
//        ft.addToBackStack(null);
//        ft.commit();
    }


    /**
     * Load restock screen
     */
    public void goToManageProductStockScreen() {
        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                R.id.fragment_placeholder_home,new ProductStockFragment());
    }

    /**
     * Load restock screen
     */
    public void goToUnstockProductScreen() {

        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                R.id.fragment_placeholder_home,new UnstockProductFragment());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
