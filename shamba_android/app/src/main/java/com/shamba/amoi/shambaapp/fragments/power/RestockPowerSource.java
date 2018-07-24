package com.shamba.amoi.shambaapp.fragments.power;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.power.PowerSourceStock;
import com.shamba.amoi.shambaapp.db.power.PowerSourceStockDao;
import com.shamba.amoi.shambaapp.models.power.PowerSourceItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;
import com.shamba.amoi.shambaapp.shareResources.SharedUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestockPowerSource.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestockPowerSource#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestockPowerSource extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText edit_vendor;
    EditText edit_date;
    EditText edit_quantity;
    EditText edit_cost;
    EditText edit_description;
    Button submit;

    private String stock_id;
    private String product_id;
    private String vendor_name;
    private String produce_name;
    private String stock_ref;
    String date;
    private double stock_quantity;
    private double stock_price;
    private String stock_description;

    public RestockPowerSource() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestockPowerSource.
     */
    // TODO: Rename and change types and number of parameters
    public static RestockPowerSource newInstance(String param1, String param2) {
        RestockPowerSource fragment = new RestockPowerSource();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String title=getString(R.string.title_fragment_restock_power_source)+" "+
                PowerSourceItem.currentPowerSourceItem.getPower_source_name().toLowerCase();
        getActivity().setTitle(title);

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_restock_power_source, container, false);

        getViewsById(view);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stock_id="STK_"+ SharedUtilities.getTimeStamp();
                SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
                String date= df.format(Calendar.getInstance().getTime()).toString();
                String str_date=edit_date.getText().toString();
                product_id=PowerSourceItem.currentPowerSourceItem.getPs_id();
                vendor_name=edit_vendor.getText().toString();
                stock_quantity= Double.parseDouble(edit_quantity.getText().toString());
                stock_price= Double.parseDouble(edit_cost.getText().toString());
                stock_description=edit_description.getText().toString();;

               SavePowerSourceStockToDB save_stock=new SavePowerSourceStockToDB(stock_id,str_date,
                       product_id, vendor_name,stock_quantity, stock_price,stock_description );
                try {
                    save_stock.execute().get();
                }  catch (Exception e) {
                }

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home,new PowerSourceStockListFragment());
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

    class SavePowerSourceStockToDB extends AsyncTask<Void, Void , Integer> {
        PowerSourceStock powerSourceStock;
        PowerSourceStockDao powerSourceStockDao;

        public  String stock_id;
        public String stock_date;
        public  String product_id;
        public  String vendor_name;
        public  double stock_quantity;
        public  double stock_price;
        public  String stock_description;

        public SavePowerSourceStockToDB( String stock_id,String stock_date,String product_id,String vendor_name,
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
            powerSourceStockDao=db.powerSourceStockDao();
            powerSourceStock=new PowerSourceStock();

            powerSourceStock.setStock_id(stock_id);
            powerSourceStock.setStock_date(stock_date);
            powerSourceStock.setVendor_name(vendor_name);
            powerSourceStock.setPower_source_id(product_id);
            powerSourceStock.setStock_description(stock_description);
            powerSourceStock.setStock_price(stock_price);
            powerSourceStock.setStock_quantity(stock_quantity);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            powerSourceStockDao.insertPowerSourceStock(powerSourceStock);

            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

        }
    }
}
