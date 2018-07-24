package com.shamba.amoi.shambaapp.fragments.product;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class CreateProductFragment extends BaseFragment implements AdapterView.OnItemSelectedListener{
    EditText edit_product_name;
    Spinner spn_product_type;
    Spinner spn_product_measuringUnit;
    EditText edit_comments;

    String str_edit_product_name;
    String str_spn_product_type;
    String str_spn_product_measuringUnit;
    String str_edit_comments;
    
    private Button save_product;
    private String name;

    private OnFragmentInteractionListener mListener;

    public CreateProductFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static CreateProductFragment newInstance(String name, String vendor) {
        CreateProductFragment fragment = new CreateProductFragment();
        Bundle args = new Bundle();
        args.putString("vendor", vendor);
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Create new Product");
        // Inflate the submit_form_details for this fragment
        View view = inflater.inflate(R.layout.fragment_create_product, container, false);

//        str_edit_product_name = edit_product_vendor.getText().toString();
        edit_product_name = (EditText) view.findViewById(R.id.edit_product_name);
        edit_comments = (EditText) view.findViewById(R.id.edit_comments);
        str_edit_comments = edit_comments.getText().toString();

        save_product = (Button) view.findViewById(R.id.btn_accept_inventory_creation);
        save_product.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                str_edit_product_name = edit_product_name.getText().toString();

                str_edit_comments = edit_comments.getText().toString();

//                ProductsFragment inv_fragment = new ProductsFragment();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_placeholder_inventory, inv_fragment);
//                ft.addToBackStack(null);
//                ft.commit();
            }
        });
        return view;
    }

    public void saveProductDetails(String name, String vendor, String uom, String size) {

        SaveProductService sp = new SaveProductService(name,
                vendor, uom, size);
        sp.execute();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Save product in database
     */
    class SaveProductService extends AsyncTask<Void, Void, Integer> {

        String product_name;
        String product_vendor;
        String product_unitOfMeasure;
        String product_size;
//        ProductDao product_dao;
//        Product product;

        public SaveProductService(String name, String vendor, String unit, String size) {
            product_name = name;
            product_vendor = vendor;
            product_unitOfMeasure = unit;
            product_size = size;
        }

        @Override
        protected void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
//            product_dao = db.productDao();
//            product = new Product();
//            product.setProductName(product_name);
//            product.setProductVendor(product_vendor);
//            product.setUnitOfMeasure(product_unitOfMeasure);
//            product.setPackageSize(product_size);
            Toast.makeText(getActivity(), "CreateProductFragment, about to save product", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Integer doInBackground(Void... voids) {
//            product_dao.insertProduct(product);
//            return product_dao.getProducts().size();

            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {
//            Toast.makeText(getContext(), result.toString()+" records found", Toast.LENGTH_LONG).show();
        }
    }
}
