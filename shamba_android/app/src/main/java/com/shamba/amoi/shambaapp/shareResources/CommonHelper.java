package com.shamba.amoi.shambaapp.shareResources;
/**
 * Created by amoi on 15/07/2018.
 */
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
/**
 * Created by fmontet on 10/12/2015.
 */

public class CommonHelper {

    public final static String DATE_FORMAT = "MMM dd, yyyy 'at' hh:mm a";
    public static final String JSON = "application/json";
    private final static String TAG = "CommonHelper";

    public static JSONObject sendPostRequestWithJsonResponse(String requestUrl, String path, String params)
            throws IOException {

        String response=sendRequest(requestUrl + path,params,"POST","");

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;
    }

    public static JSONObject sendPutRequestWithJsonResponse(String requestUrl, String path, String params)
            throws IOException {

        String response=sendRequest(requestUrl + path,params,"PUT","");

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;
    }

//    public static String sendPostRequest(String requestUrl, String path, String params,
//                                         String authToken) throws IOException {
//        return sendRequest(requestUrl + path, params, "POST", authToken);
//    }

    public static String sendRequest(String requestUrl, String params, String method,
                                     String authToken) throws IOException {
//public static String sendRequest(String requestUrl, String params,
//                                 String authToken) throws IOException {
        Log.d(TAG, "requestUrl = " + requestUrl);
        Log.d(TAG, "params = " + params);
        Log.d(TAG, "method = " + method);
        Log.d(TAG, "authToken = " + authToken);

        URL url = new URL(requestUrl);

        HttpURLConnection urlConnection = null;

        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(method);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("charset", "utf-8");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);

        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        Log.d(TAG, "Response Code is :: " + urlConnection.getResponseCode());

        String resultString = "";
        if (urlConnection.getResponseCode() == 401) {
            if (requestUrl.contains("/login")) {
                return getAuthenticationFailure().toString();
            } else {
                return getAccessDeniedFailure().toString();
            }
        } else {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            resultString = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
        }
        return resultString;
    }

    /**
     * Converts get response to Json objects!
     * @param requestUrl
     * @param path
     * @param params
     * @return
     * @throws IOException
     */
    public static List<JSONObject> sendGetRequestWithJsonResponse(String requestUrl, String path,
                                                                  String params)
            throws IOException, JSONException {

        String response=sendGet(requestUrl,path, params);

        JSONArray jArray = new JSONArray(response);

        List<JSONObject> json_objects=new ArrayList<>();

        for(int i=0;i<jArray.length();++i) {
            JSONObject jsonObject = jArray.getJSONObject(i);
            json_objects.add(jsonObject);
        }

       return  json_objects;
    }

    /**
     * Implements get rest apis
     * @param requestUrl
     * @param path
     * @param params
     * @return
     * @throws IOException
     */
    public static String sendGet(String requestUrl, String path, String params) throws IOException {

        Log.d(TAG, "requestUrl = " + requestUrl);
        Log.d(TAG, "params = " + params);

        URL url = new URL(requestUrl + path + params);

        HttpURLConnection urlConnection = null;
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        int responseCode = urlConnection.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
        return response.toString();
    }

    public static String sendPUTRequest(String requestUrl, String path, String params,
                                        String authToken) throws IOException {
        return sendRequest(requestUrl + path, params, "PUT", authToken);
    }

    public static JSONObject getAuthenticationFailure() {
        JSONObject jsonObject = new JSONObject();

        try {
//            jsonObject.put("message", Kionect.getAppContext().getString(R.string.invalid_login));
            jsonObject.put("success", false);
            jsonObject.put("data", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject getAccessDeniedFailure() {
        JSONObject jsonObject = new JSONObject();

        try {
//            jsonObject.put("message", Kionect.getAppContext().getString(R.string.error_access_denied));
            jsonObject.put("success", false);
            jsonObject.put("data", null);
/**
 *SessionManager sessionManager = new SessionManager(Kionect.getAppContext());
 sessionManager.clearSettings();
 *
 */

            //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //startActivity(intent);
            //finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

//    public static String getCurrencyFormat(double value) {
//       DecimalFormat decimalFormat = new DecimalFormat(Kionect.getAppContext().getString(R.string.string_currency) + "###,###.##");
//        decimalFormat.setMinimumFractionDigits(2);
//        return decimalFormat.format(value);
//    }

    public static String getDecimalFormat(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        decimalFormat.setMinimumFractionDigits(2);
        return decimalFormat.format(value);
    }

    public static String getDateAsString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setAmPmStrings(new String[]{"am", "pm"});
        sdf.setDateFormatSymbols(symbols);
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }

    public static String getDateAsString(Date date, String dateFormat) {
        if (date != null && dateFormat != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                return sdf.format(date);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static Date getDateFromString(String date, String dateFormat) {
        if (date != null && dateFormat != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                return sdf.parse(date);
            } catch (Exception e) {
            }
        }
        return null;
    }

//    public static boolean isDeviceOnline() {
//        Context context = Kionect.getAppContext();
//        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        return (networkInfo != null && networkInfo.isConnected());
//    }

//    public static String getInternationalFormat(String mobileNumber, String countryCode) {
//        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
//        try {
//            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(mobileNumber, countryCode);
//            return phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
//        } catch (NumberParseException e) {
//            Log.e(TAG, "NumberParseException was thrown: " + e.toString());
//            return mobileNumber;
//        }
//    }

    public static String internationalizeNumber(String phone) {
        String abc = phone.replaceAll("\\s+", "").replace("+", "");
        if (abc.startsWith("0")) {
            abc = "+254" + abc.substring(1, abc.length());
        }
//        else if(abc.startsWith("+" + "254"))
//        {
//            abc = abc + abc.substring("254".length() + 1, abc.length());
//        }

        else if (abc.startsWith("254")) {
            abc = "+" + abc;
        }


        return abc.replaceAll("\\s+", "").replaceAll("-", "");
    }

    public static void closeKeyboard(Activity activity) {

        if (activity != null) {
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

    }


    /*
* This method is used as we cannot store a number starting with 0 in a json file
* */
    public static String getZeroFormattedCount(int count) {
        if (count < 10 && count != 0) {
            return "0" + count;
        }

        return "" + count;
    }

//    public static boolean isPhoneNumberValid(String mobileNumber, String countryCode) {
//        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
//        try {
//            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(mobileNumber, countryCode);
//            if (!phoneUtil.isValidNumber(numberProto)) {
//                return false;
//            }
//        } catch (NumberParseException e) {
//            Log.e(TAG, "NumberParseException was thrown: " + e.toString());
//            return false;
//        }
//        return true;
//    }


    public static int generateRandomNumber(int max) {
        return new Random().nextInt(max + 1);
    }

//    public static int dpToPixel(Context context, int dpValue) {
//        float d = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * d); // margin in pixels
//    }
//
//    public static SSLSocketFactory getSSLSocketFactory() {
//        return getSSLSocketFactory(R.raw.stage);
//    }
//
//    public static SSLSocketFactory getSSLSocketFactory(int certFile) {
//        try {
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
//            InputStream caInput = null;
//            if (BuildConfig.LOAD_CERT) {
//                caInput = Kionect.getAppContext().getResources().openRawResource(certFile);
//            }
//
//            Certificate ca;
//            try {
//                ca = cf.generateCertificate(caInput);
//                Log.d("CommonHelper", "ca=" + ((X509Certificate) ca).getSubjectDN());
//            } finally {
//                caInput.close();
//            }
//
//            // Create a KeyStore containing our trusted CAs
//            String keyStoreType = KeyStore.getDefaultType();
//            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//            keyStore.load(null, null);
//            keyStore.setCertificateEntry("ca", ca);
//
//            // Create a TrustManager that trusts the CAs in our KeyStore
//            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//            tmf.init(keyStore);
//
//            // Create an SSLContext that uses our TrustManager
//            SSLContext context = null;
//            SSLSocketFactory noSSLv3Factory = null;
//            try {
//                context = SSLContext.getInstance("TLSv1.2");
//                context.init(null, tmf.getTrustManagers(), null);
//                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
//                    noSSLv3Factory = new TLSSocketFactory(context.getSocketFactory());
//                } else {
//                    noSSLv3Factory = context.getSocketFactory();
//                }
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (KeyManagementException e) {
//                e.printStackTrace();
//            }
//
//            Log.d(TAG, "Default SSL Cipher(s) :: " + Arrays.toString(((SSLSocket) noSSLv3Factory.createSocket()).getEnabledProtocols()));
//            return noSSLv3Factory;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static String getDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormat.format(date);
    }
}
