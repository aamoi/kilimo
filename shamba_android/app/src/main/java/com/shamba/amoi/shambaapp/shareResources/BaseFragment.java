package com.shamba.amoi.shambaapp.shareResources;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignment;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignmentDao;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;
import com.shamba.amoi.shambaapp.models.inventory.ProductItem;
import com.shamba.amoi.shambaapp.models.inventory.ProductStockItem;
import com.shamba.amoi.shambaapp.models.labor.HumanResourceItem;
import com.shamba.amoi.shambaapp.models.labor.TaskAssignmentItem;
import com.shamba.amoi.shambaapp.models.projects.CropItem;
import com.shamba.amoi.shambaapp.models.projects.LocationItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingPhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
/**
 * Created by amoi on 08/02/2018.
 */
public abstract class BaseFragment extends Fragment {
    public static List<CropItem> cropItems;
    public static List<HumanResourceItem> humanResourceItemList;
    public static HumanResourceItem humanResourceItem;

    public static HashMap<String, ProductItem> productItemHashMap;
    public static ProductItem productItem;

    public static TaskItem taskItem;

    public static ProductStockItem productStockItem;
    /**
     * Switch/change from one fragment to another in an activity.
     *
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
     *
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
     *
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

//                TextView errorText = (TextView)spinner.getSelectedView();
//                errorText.setError("");
//                errorText.setTextColor(Color.RED);//just to highlight that this is an error
//                errorText.setText("my actual error text");//changes the selected item text to
                status= false;

            }
        }
        return status ;
    }

    public static HashMap<String, PlantingProgramItem> getAllPlantingPrograms(AppCompatActivity activity) {
        List<PlantingProgramItem> plantingProgramItemList = PlantingProgramItem.getAllPlantingPrograms(activity);

        HashMap<String, PlantingProgramItem> plantingProgramItemHashMap = new HashMap<>();

//        for (int i = 0; i < plantingProgramItemList.size(); ++i) {
//            plantingProgramItemHashMap.put(plantingProgramItemList.get(i).getPlan_id(),
//                    plantingProgramItemList.get(i));
//        }

        return plantingProgramItemHashMap;
    }

    public static HashMap<Integer, TaskItem> getAllProjectsTasks(AppCompatActivity activity) {
        List<TaskItem> taskItemList = TaskItem.getAllTask(activity);

        Log.d("Projectsss|", "number of task: " + String.valueOf(taskItemList.size()));


        HashMap<Integer, TaskItem> taskItemHashMap = new HashMap<>();

        for (int i = 0; i < taskItemList.size(); ++i) {
            taskItemHashMap.put(taskItemList.get(i).getId(), taskItemList.get(i));
        }

        return taskItemHashMap;
    }


    public static HashMap<String, ProductItem> getCrops() {

        String product_type = getProductType().get("Crop");

        String[] crops = {"Maize", "WaterMelon", "Beans", "Tomatoes", "Spinach", "Onions", "Beans",
                "French Beans", "Peas", "Tomatoes", "Cabbage", "Sukuma", "Managu", "Kunde", "Terere"};

        HashMap<String, ProductItem> cropItemHashMa = new HashMap<>();

        for (int i = 0; i < crops.length; i++) {
            ProductItem crop = new ProductItem();
            crop.setProduct_id(String.valueOf(i));
            crop.setProduct_name(crops[i]);
            crop.setUnit_of_measure("gm");
            crop.setProduct_type(product_type);
            cropItemHashMa.put(crops[i], crop);
        }

        return cropItemHashMa;
    }

    public static List<CropItem> saveCrops() {

        String[][] crops_str = {
                {"1", "Maize", "For Milling Flour, Roasting and Boiling"},
                {"2", "WaterMelon", "They are fruits"}, {"3", "Onions", ""},
                {"4", "Beans", " "}, {"5", "French Beans", ""}, {"6", "Peas", ""},
                {"7", "Tomatoes", " "}, {"8", "Cabbage", ""}, {"9", "Sukuma", ""},
                {"10", "Spinach", " "}, {"11", "Managu", ""}, {"12", "Kunde", ""},
                {"13", "Terere", ""},
        };

        ArrayList<CropItem> crops = new ArrayList();

        for (int i = 0; i < crops_str.length; i++) {
            CropItem crop = new CropItem();

            crop.setCrop_id(Integer.parseInt(crops_str[i][0]));
            crop.setCrop_name(crops_str[i][1]);
            crop.setCrop_description(crops_str[i][2]);
            crops.add(crop);
        }
        cropItems = crops;
        return crops;
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

