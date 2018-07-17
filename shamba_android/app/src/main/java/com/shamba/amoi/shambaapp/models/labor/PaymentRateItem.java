package com.shamba.amoi.shambaapp.models.labor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amoi on 15/02/2018.
 */

public class PaymentRateItem{
    String pay_rate_id;
    String pay_rate_name;
    double pay_rate_amount;
    String pay_rate_resource_type;
    String pay_rate_type;
    String comments;

    public void setPay_rate_amount(double pay_rate_amount) {
        this.pay_rate_amount = pay_rate_amount;
    }

    public double getPay_rate_amount() {
        return pay_rate_amount;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPay_rate_frequency(String pay_rate_frequency) {
        this.pay_rate_type = pay_rate_frequency;
    }

    public void setPay_rate_id(String pay_rate_id) {
        this.pay_rate_type = pay_rate_id;
    }

    public void setPay_rate_resource_type(String pay_rate_resource_type) {
        this.pay_rate_resource_type = pay_rate_resource_type;
    }

    public String getPay_rate_resource_type() {
        return pay_rate_resource_type;
    }

    public void setPay_rate_name(String pay_rate_name) {
        this.pay_rate_name = pay_rate_name;
    }

    public String getComments() {
        return comments;
    }

    public String getPay_rate_id() {
        return pay_rate_id;
    }

    public String getPay_rate_frequency() {
        return pay_rate_type;
    }

    public String getPay_rate_name() {
        return pay_rate_name;
    }

    /**
     * Provide public access a payment rate item by name.
     * @param key
     * @return
     */
    public static PaymentRateItem getPaymentRateItemByName(String key){
        return getPaymentRates().get(key);
    }

    /**
     * Provides public access to all payment packaged in list.
     * @return
     */
    public static List<PaymentRateItem> getPayRateList(){
        List<PaymentRateItem> paymentRateItems=new ArrayList<>();
        paymentRateItems=(List)getPaymentRates().values();
        return paymentRateItems;
    }

    /**
     * Provides public access to all payment packaged in hashmap by name.
     * @return
     */
    public static HashMap<String, PaymentRateItem> getPaymentRates(){

        HashMap<String, PaymentRateItem> paymentRateItemHashMap=new HashMap<>();

        String[][] rates={{"100","daily"},{"150","daily"},{"200","daily"},{"250","daily"},{"300","daily"},
                {"350","daily"},{"400","daily"},{"1200","weekly"},{"1500","weekly"},{"1800","weekly"},
                {"4000","monthly"},{"4500","monthly"},{"5000","monthly"},{"5500","monthly"},
                {"6000","monthly"},{"6500","monthly"},{"7000","monthly"},{"7500","monthly"},
                {"8000","monthly"}};

        String resource_type="human";

        for(int i=0;i<rates.length; i++){

            PaymentRateItem paymentRateItem=new PaymentRateItem();

            paymentRateItem.setPay_rate_amount(Double.parseDouble(rates[i][0]));
            paymentRateItem.setPay_rate_frequency(rates[i][1]);
            paymentRateItem.setPay_rate_name(rates[i][0]+"-"+rates[i][1]);
            paymentRateItem.setPay_rate_resource_type(resource_type);
            paymentRateItem.setPay_rate_id(resource_type+"-"+rates[i][0]+"-"+rates[i][1]);

            paymentRateItemHashMap.put(resource_type+"-"+rates[i][0]+"-"+rates[i][1],paymentRateItem);
        }
        return paymentRateItemHashMap;
    }
}
