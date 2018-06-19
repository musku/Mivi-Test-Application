package com.mivi.testapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mivi.testapplication.VO.CustomerDetails;
import com.mivi.testapplication.databinding.CustomerDetailsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class CustomerDetailsActivity extends AppCompatActivity {
    private CustomerDetailsBinding binding;
    private CustomerDetails customerDetails = new CustomerDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Using data binding to set Data to UI,We can also use ViewModels and live data for best orientation change results.
        binding = DataBindingUtil.setContentView(this, R.layout.customer_details);

        //Using basic json parsing as we do not require all fields data this will take less time for development,
        // instead of creating pojo class and converting using converter
        JSONObject json;
        JSONArray included = null;
        JSONObject subscription = null, products = null;
        JSONObject attributes = null;
        try {
            json = new JSONObject(loadCollectionJsonFromAsset());
            if(json!=null)
            included = json.getJSONArray("included");
            if(included!=null) {
                subscription = (JSONObject) included.get(1);
                products = (JSONObject) included.get(2);
            }
            if(subscription!=null)
            attributes = (JSONObject) subscription.get("attributes");
            if(attributes!=null)
            customerDetails.setBalanceData(attributes.getLong("included-data-balance"));
            customerDetails.setExpDate(attributes.getString("expiry-date"));
            customerDetails.setAutoRenewal(attributes.getBoolean("auto-renewal"));
            if(products!=null)
            attributes = (JSONObject) products.get("attributes");
            customerDetails.setName(attributes.getString("name"));
            customerDetails.setPrice(attributes.getDouble("price"));
            binding.setCustDetails(customerDetails);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String loadCollectionJsonFromAsset() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("collection.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
