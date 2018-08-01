package com.shamba.amoi.shambaapp.shareResources;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shamba.amoi.shambaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amoi on 02/01/2018.
 */

public class SpinnerUtility  implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    View view;

    public SpinnerUtility(Spinner spinner, View view ){
        this.spinner = spinner;
        this.view = view;
    }


    public void setUpSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.product_unitOfMeasure_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static Spinner setDynamicSpinner(Context context, Spinner spinner,
                                            List<String> spinner_items){

        ArrayAdapter<String> adapter;

        adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, spinner_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return spinner;

    }

    /**
     * @param spinner
     */
    public void onSpinnersItemSelected(Spinner spinner) {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                onSpinnerItemSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                onSpinnerNothingSelected();
            }
        });

    }

    public void onSpinnerItemSelected() {
    }

    public void onSpinnerNothingSelected() {
    }
}
