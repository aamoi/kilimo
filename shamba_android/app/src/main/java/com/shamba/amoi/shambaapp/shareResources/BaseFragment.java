package com.shamba.amoi.shambaapp.shareResources;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.models.product.ProductStockItem;
import com.shamba.amoi.shambaapp.models.projects.CropItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amoi on 08/02/2018.
 */
public abstract class BaseFragment extends Fragment {
    public static List<CropItem> cropItems;
    public static List<ResourceItem> resourceItemList;
    public static ResourceItem resourceItem;
    public static TaskItem taskItem;
    public static ProductStockItem productStockItem;
    /**
     * Switch/change from one fragment to another in an activity.
     * @param activity
     * @param activity_place_holder_id
     * @param fragment
     */
    public static void changeFragment(AppCompatActivity activity, int activity_place_holder_id,
                                      Fragment fragment) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(activity_place_holder_id, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void onBackPressed(Fragment fragment){
        fragment.getView().setFocusableInTouchMode(true);
        fragment.getView().requestFocus();
        fragment.getView().setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    return true;
                }
                return false;
            }
        } );
    }
    /**
     * Validate that mandatory input text field is not empty.
     * @return
     */
    public boolean validateMandatoryEditTextFields(EditText ... editTexts) {

        boolean status=true;

        for(EditText editText:editTexts){
            String textString = editText.getText().toString();

            if (textString.isEmpty()) {
                editText.setError("Mandatory field!");
                status= false;
            }
        }
       return status ;
    }

    /**
     * Validate that mandatory input text field is not empty.
     * @return
     */
    public boolean validateMandatorySpinnerFields(Spinner... spinners) {

        boolean status=true;



        for(Spinner spinner:spinners){

            try{
                String textString = spinner.getSelectedItem().toString();
            }
            catch(Exception e){
                ((TextView)spinner.getSelectedView()).setError("Error message");
                status= false;

            }
        }
        return status ;
    }

    public static HashMap<String, String> getProductType() {

        HashMap<String, String> product_types = new HashMap<>();

        product_types.put("Crop", "Crop");
        product_types.put("Animal", "Animal");
        product_types.put("Fuel", "Fuel");

        product_types.put("Petrol Fuel", "Petrol Fuel");
        product_types.put("Diesel Fuel", "Diesel Fuel");
        product_types.put("Petrol Pump Oil", "Petrol Pump Oil");
        product_types.put("Diesel Pump Oil", "Diesel Pump Oil");
        product_types.put("Water Pump", "Water Pump");
        product_types.put("Water Delivery Pipe", "Water Delivery Pipe");
        product_types.put("Water Horse Pipe", "Water Horse Pipe");

        return product_types;
    }
    public static List<String> getResourceTypes() {

        ArrayList<String> resource_types = new ArrayList<>();
        resource_types.add("Human Resource");
        resource_types.add("Tractor");
        resource_types.add("PickUp");
        resource_types.add("Canter");
        resource_types.add("Bull Plough");

        return resource_types;
    }
    public static List<String> getResourceContractTypes() {
        ArrayList<String> contract_type = new ArrayList<>();
        contract_type.add("Permanent");
        contract_type.add("Casual");

        return contract_type;
    }
}

