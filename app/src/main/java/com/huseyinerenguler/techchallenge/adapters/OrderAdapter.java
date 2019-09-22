package com.huseyinerenguler.techchallenge.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import com.huseyinerenguler.techchallenge.R;
import com.huseyinerenguler.techchallenge.utils.StaticParameters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public OrderAdapter(Context context) {
        this.context = context;
        this.layoutInflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return StaticParameters.orders.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.listview_row_order, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.cl_date = convertView.findViewById(R.id.cl_date);
            viewHolder.cl_order = convertView.findViewById(R.id.cl_order);
            viewHolder.cl_productDetail = convertView.findViewById(R.id.cl_productDetail);
            viewHolder.view_productState = convertView.findViewById(R.id.view_productState);
            viewHolder.tv_date_day = convertView.findViewById(R.id.tv_date_day);
            viewHolder.tv_date_month = convertView.findViewById(R.id.tv_date_month);
            viewHolder.tv_marketName = convertView.findViewById(R.id.tv_marketName);
            viewHolder.tv_orderName = convertView.findViewById(R.id.tv_orderName);
            viewHolder.tv_productPrice = convertView.findViewById(R.id.tv_productPrice);
            viewHolder.tv_productState = convertView.findViewById(R.id.tv_productState);
            viewHolder.tv_orderDetail = convertView.findViewById(R.id.tv_orderDetail);
            viewHolder.tv_summaryPrice = convertView.findViewById(R.id.tv_summaryPrice);

            // In order to create the same UI in all screen sizes, size adjustments are made according to the screen width.
            ((ConstraintLayout) viewHolder.cl_date.getParent()).setPadding(
                    StaticParameters.screenWidth / 40,
                    StaticParameters.screenWidth / 20,
                    StaticParameters.screenWidth / 40,
                    StaticParameters.screenWidth / 20);
            viewHolder.cl_productDetail.setPadding(
                    StaticParameters.screenWidth / 40,
                    StaticParameters.screenWidth / 40,
                    StaticParameters.screenWidth / 40,
                    StaticParameters.screenWidth / 40);
            viewHolder.cl_order.setPadding(StaticParameters.screenWidth / 27, 0, 0, 0);
            viewHolder.tv_productState.setPadding(StaticParameters.screenWidth / 36, 0, 0, 0);

            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewHolder.cl_productDetail.getLayoutParams();
            layoutParams.topMargin = StaticParameters.screenWidth / 40;
            viewHolder.cl_productDetail.setLayoutParams(layoutParams);

            viewHolder.tv_date_day.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 9);
            viewHolder.tv_date_month.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
            viewHolder.tv_marketName.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
            viewHolder.tv_orderName.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
            viewHolder.tv_productPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
            viewHolder.tv_productState.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
            viewHolder.tv_orderDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);
            viewHolder.tv_summaryPrice.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticParameters.screenWidth / 22);

            convertView.setTag(viewHolder);

        } else
            viewHolder = (ViewHolder) convertView.getTag();

        // When clicked an order, the order details are shown or hidden.
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.cl_productDetail.getVisibility() == View.VISIBLE)
                    viewHolder.cl_productDetail.setVisibility(View.GONE);
                else
                    viewHolder.cl_productDetail.setVisibility(View.VISIBLE);
            }
        });

        // Birbirini takip eden siparişler eğer aynı tarihe sahipse, sadece 1 tanesinin tarihi gösterilir, diğerleri gizlenir.
        viewHolder.cl_date.setVisibility(View.VISIBLE);
        if (position > 0)
            if (StaticParameters.orders.get(position).getDate().equals(StaticParameters.orders.get(position - 1).getDate()))
                if (StaticParameters.orders.get(position).getMonth().equals(StaticParameters.orders.get(position - 1).getMonth()))
                    viewHolder.cl_date.setVisibility(View.INVISIBLE);

        // Month which is a number, is converted to text.
        if (viewHolder.cl_date.getVisibility() == View.VISIBLE) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, Integer.valueOf(StaticParameters.orders.get(position).getMonth()) - 1);
            viewHolder.tv_date_month.setText(new SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.getTime()));
            viewHolder.tv_date_day.setText(StaticParameters.orders.get(position).getDate());
        }

        // Other order informations are added to the relevant fields.
        viewHolder.tv_marketName.setText(StaticParameters.orders.get(position).getMarketName());
        viewHolder.tv_orderName.setText(StaticParameters.orders.get(position).getOrderName());
        viewHolder.tv_productPrice.setText(String.format(context.getString(R.string.listview_row_order_product_price), StaticParameters.orders.get(position).getProductPrice()));
        viewHolder.tv_orderDetail.setText(StaticParameters.orders.get(position).getProductDetail().getOrderDetail());
        viewHolder.tv_summaryPrice.setText(String.format(context.getString(R.string.listview_row_order_product_price), StaticParameters.orders.get(position).getProductDetail().getSummaryPrice()));

        String productState = StaticParameters.orders.get(position).getProductState();
        viewHolder.tv_productState.setText(productState);

        // Depending on the state of the product, state section is colored.
        if (productState.equalsIgnoreCase("Yolda")) {
            ViewCompat.setBackgroundTintList(viewHolder.view_productState, ColorStateList.valueOf(Color.argb(255, 0, 170, 0)));
            viewHolder.tv_productState.setTextColor(Color.argb(255, 0, 170, 0));

        } else if (productState.equalsIgnoreCase("Hazırlanıyor")) {
            ViewCompat.setBackgroundTintList(viewHolder.view_productState, ColorStateList.valueOf(Color.argb(255, 255, 150, 0)));
            viewHolder.tv_productState.setTextColor(Color.argb(255, 255, 150, 0));

        } else if (productState.equalsIgnoreCase("Onay Bekliyor")) {
            ViewCompat.setBackgroundTintList(viewHolder.view_productState, ColorStateList.valueOf(Color.argb(255, 255, 0, 0)));
            viewHolder.tv_productState.setTextColor(Color.argb(255, 255, 0, 0));

        } else {
            ViewCompat.setBackgroundTintList(viewHolder.view_productState, ColorStateList.valueOf(Color.argb(255, 0, 0, 0)));
            viewHolder.tv_productState.setTextColor(Color.argb(255, 0, 0, 0));
            viewHolder.tv_productState.setText("");
        }

        return convertView;
    }

    private static class ViewHolder {
        private ConstraintLayout cl_date;
        private ConstraintLayout cl_order;
        private ConstraintLayout cl_productDetail;
        private View view_productState;
        private TextView tv_date_day;
        private TextView tv_date_month;
        private TextView tv_marketName;
        private TextView tv_orderName;
        private TextView tv_productPrice;
        private TextView tv_productState;
        private TextView tv_orderDetail;
        private TextView tv_summaryPrice;
    }

}