package com.mivi.testapplication.VO;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.mivi.testapplication.BR;

import java.io.Serializable;

/**
 * Created by Sravanthi Musku on 19-06-2018.
 * Copyright @Payswiff Technologies Pvt Ltd
 */
public class CustomerDetails implements Observable,Serializable {
    private PropertyChangeRegistry registry =
            new PropertyChangeRegistry();

    private long balanceData;
    @Bindable
    public long getBalanceData() {
        return balanceData;
    }

    public void setBalanceData(long balancedata) {
        this.balanceData = balancedata;
        registry.notifyChange(this, BR.balanceData);
    }
    @Bindable
    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
        registry.notifyChange(this, BR.expDate);
    }
    @Bindable
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        registry.notifyChange(this, BR.price);
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        registry.notifyChange(this, BR.name);
    }

    private String expDate;
    private double price;
    private String name;
    @Bindable
    public boolean isAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
        registry.notifyChange(this, BR.autoRenewal);
    }

    private boolean autoRenewal;
    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);
    }
}
