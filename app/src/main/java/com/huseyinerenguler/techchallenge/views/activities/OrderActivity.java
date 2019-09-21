package com.huseyinerenguler.techchallenge.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.huseyinerenguler.techchallenge.R;
import com.huseyinerenguler.techchallenge.adapters.OrderAdapter;
import com.huseyinerenguler.techchallenge.managers.OrderManager;
import com.huseyinerenguler.techchallenge.utils.StaticParameters;

public class OrderActivity extends AppCompatActivity {

    private ListView lv_orders;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initUI();
        initAdapter();
        fetchOrders();
    }

    private void initUI() {

        lv_orders = findViewById(R.id.lv_orders);
        TextView tv_activity_title = findViewById(R.id.tv_activity_title);
        TextView tv_my_orders = findViewById(R.id.tv_my_orders);
        TextView tv_logout = findViewById(R.id.tv_logout);

        tv_activity_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 20);
        tv_my_orders.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
        tv_logout.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);

        lv_orders.setDividerHeight(StaticParameters.screenWidth / 10);
        lv_orders.setPadding(StaticParameters.screenWidth / 40, StaticParameters.screenWidth / 20, StaticParameters.screenWidth / 40, 0);
    }

    private void initAdapter() {

        orderAdapter = new OrderAdapter(this);
        lv_orders.setAdapter(orderAdapter);
    }

    public void onClick(View view) {

        if (view.getId() == R.id.tv_logout) {
            logout();
        }
    }

    private void logout() {
        // TODO show dialog
    }

    private void fetchOrders() {

        new OrderManager(new OrderManager.onActionListener() {
            @Override
            public void onNotifyDataSetChanged() {
                orderAdapter.notifyDataSetChanged();
            }
        }).fetchOrders();
    }

}