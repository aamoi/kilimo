package com.shamba.amoi.shambaapp.fragments.inventory;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.Stock;
import com.shamba.amoi.shambaapp.db.StockDao;
import com.shamba.amoi.shambaapp.db.inventory.ProductStock;
import com.shamba.amoi.shambaapp.db.inventory.ProductStockDao;
import com.shamba.amoi.shambaapp.models.inventory.ProductItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;
import com.shamba.amoi.shambaapp.shareResources.SharedUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class RestockProductFragment extends BaseFragment {
    EditText edit_vendor;
    EditText edit_date;
    EditText edit_quantity;
    EditText edit_cost;
    EditText edit_description;
    Button submit;
    ProductItem productItem;

    private String stock_id;
    private String product_id;
    private String vendor_name;
    private String produce_name;
    private String stock_ref;
    String date;
    private double stock_quantity;
    private double stock_price;
    private String stock_description;

    private OnFragmentInteractionListener mListener;
    public RestockProductFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment RestockInventoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestockProductFragment newInstance() {
        RestockProductFragment fragment = new RestockProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        productItem=BaseFragment.productItem;

        getActivity().setTitle("Add "+productItem.getProduct_name()+" stock");
        // Inflate the submit_form_details for this fragment

        View view=inflater.inflate(R.layout.fragment_restock_product, container, false);

        getViewsById(view);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stock_id="STK_"+SharedUtilities.getTimeStamp();
                SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
                String date= df.format(Calendar.getInstance().getTime()).toString();
                String str_date=edit_date.getText().toString();
                product_id=productItem.getProduct_id();
                vendor_name=edit_vendor.getText().toString();
                stock_quantity= Double.parseDouble(edit_quantity.getText().toString());
                stock_price= Double.parseDouble(edit_cost.getText().toString());
                stock_description=edit_description.getText().toString();;
                
                SaveStockToDB save_stock=new SaveStockToDB(stock_id,str_date,productItem.getProduct_id(),
                        vendor_name,stock_quantity,stock_price,stock_description );
                try {
                    save_stock.execute().get();
                }  catch (Exception e) {
                }

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home,new ProductStockFragment());
            }
        });
        return view;
    }
    
    private void getViewsById(View view){
         edit_vendor=(EditText) view.findViewById(R.id.edit_vendor);
         edit_date=(EditText) view.findViewById(R.id.edit_date);
         new DatePickerUtility(edit_date);
         edit_quantity=(EditText) view.findViewById(R.id.edit_quantity);
         edit_cost=(EditText) view.findViewById(R.id.edit_cost);
         edit_description=(EditText) view.findViewById(R.id.edit_description);
         submit=(Button)view.findViewById(R.id.btn_submit_stock);
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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

    class SaveStockToDB extends AsyncTask<Void, Void , Integer>{
        ProductStock productStock;
        ProductStockDao productStockDao;

        public  String stock_id;
        public String stock_date;
        public  String product_id;
        public  String vendor_name;
        public  double stock_quantity;
        public  double stock_price;
        public  String stock_description;

        public SaveStockToDB( String stock_id,String stock_date,String product_id,String vendor_name,
                              double stock_quantity,double stock_price,String stock_description){
            this.stock_id = stock_id;
            this.stock_date = stock_date;
            this.product_id = product_id;
            this.vendor_name = vendor_name;
            this.stock_quantity = stock_quantity;
            this.stock_price = stock_price;
            this.stock_description = stock_description;
        }

        @Override
        protected void onPreExecute(){
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            productStockDao=db.productStockDao();
            productStock=new ProductStock();
            productStock.setStock_id(stock_id);
            productStock.setStock_date(stock_date);
            productStock.setVendor_name(vendor_name);
            productStock.setProduct_id(product_id);
            productStock.setStock_description(stock_description);
            productStock.setStock_price(stock_price);
            productStock.setStock_quantity(stock_quantity);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            productStockDao.insertProductStock(productStock);

            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

        }
    }
}
