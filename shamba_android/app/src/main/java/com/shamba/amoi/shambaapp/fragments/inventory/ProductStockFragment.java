package com.shamba.amoi.shambaapp.fragments.inventory;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.inventory.ProductStockRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.inventory.ProductStock;
import com.shamba.amoi.shambaapp.db.inventory.ProductStockDao;
import com.shamba.amoi.shambaapp.models.inventory.ProductItem;
import com.shamba.amoi.shambaapp.models.inventory.ProductStockItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ProductStockFragment extends BaseFragment {

    ProductItem productItem;
    Button add_stock;
    int productId;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProductStockFragment newInstance(int product_id,String produce_name,
                                                   String manufacturer, String package_size) {

        ProductStockFragment fragment = new ProductStockFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        productItem= BaseFragment.productItem;

        View view = inflater.inflate(R.layout.fragment_productstock_list, container,
                false);

        productItem= BaseFragment.productItem;

        List<ProductStockItem> product_stock_list=new ArrayList<>();

        try {
            GetStockService get_stocks = new GetStockService();
            product_stock_list = get_stocks.execute().get();
        } catch (Exception e) {

        }
        double total_quantity=0.0;

        for(int i=0;i<product_stock_list.size();++i){
            total_quantity=total_quantity+product_stock_list.get(i).getStock_quantity();
        }

        String stock_size=String.valueOf(product_stock_list.size());
        Log.d("stock quantity", stock_size);

        getActivity().setTitle(stock_size+" "+productItem.getProduct_name()+" "+
                getString(R.string.title_fragment_product_stocks)+
                "("+ String.valueOf(total_quantity)+
                productItem.getUnit_of_measure()+")");

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_list_product_stock);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(new ProductStockRecyclerViewAdapter(product_stock_list,
                (HomeActivity)this.getActivity()) );

        add_stock=(Button)view.findViewById(R.id.btn_add_product_stock);
        add_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home,new RestockProductFragment());

            }
        });
        return view;
    }

    public String[][] getDummyProductStocks(){

        String[][] stocks={
                {"1","REC010101","Cabbage","12/12/2017", "3","50", "To plant on BlockA" },
                {"2","REC010102","Cabbage","14/12/2017", "2","100", "To plant on BlockB" },
                {"3","REC010103","Cabbage","30/12/2017", "1","250", "To plant on BlockC" },
        };

        return stocks;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ProductStockItem item);
    }

    class GetStockService extends AsyncTask<Void, Void, List<ProductStockItem>>{
        ProductStock stock;
        ProductStockDao stockDao;
        List<ProductStockItem> productStockItems;

        @Override
        protected void onPreExecute(){
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            stockDao=db.productStockDao();
            stock=new ProductStock();
            productStockItems=new ArrayList<>();
        }

        @Override
        protected List<ProductStockItem> doInBackground(Void... voids) {

            List<ProductStock> productStocks=stockDao.getProductStocks(productItem.getProduct_id());
            for(int i=0;i<productStocks.size();++i){
                ProductStockItem productStockItem=new ProductStockItem();
                productStockItem.setProduct_id(productStocks.get(i).getProduct_id());
                productStockItem.setStock_date(productStocks.get(i).getStock_date());
                productStockItem.setStock_description(productStocks.get(i).getStock_description());
                productStockItem.setStock_price(productStocks.get(i).getStock_price());
                productStockItem.setStock_quantity(productStocks.get(i).getStock_quantity());
                productStockItem.setVendor_name(productStocks.get(i).getVendor_name());
                productStockItems.add(productStockItem);
            }

            return productStockItems;
        }

        @Override
        protected void onPostExecute(List<ProductStockItem> result){
        }
    }
}
