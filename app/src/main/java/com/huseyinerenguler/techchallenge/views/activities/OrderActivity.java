package com.huseyinerenguler.techchallenge.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.huseyinerenguler.techchallenge.R;
import com.huseyinerenguler.techchallenge.managers.OrderManager;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        fetchOrders();
    }

    private void fetchOrders() {

        new OrderManager(new OrderManager.onActionListener() {
            @Override
            public void onNotifyDataSetChanged() {
                // TODO
            }
        }).fetchOrders();
    }

}