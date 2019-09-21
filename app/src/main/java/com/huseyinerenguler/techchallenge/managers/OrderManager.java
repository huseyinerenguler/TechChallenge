package com.huseyinerenguler.techchallenge.managers;

import android.os.AsyncTask;

import com.huseyinerenguler.techchallenge.models.Order;
import com.huseyinerenguler.techchallenge.utils.StaticParameters;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class OrderManager extends AsyncTask<Void, Void, Void> {

    public interface onActionListener {
        void onNotifyDataSetChanged();
    }

    private onActionListener listener;

    public OrderManager(onActionListener listener) {
        this.listener = listener;
    }

    public void fetchOrders() {
        this.execute();
    }

    @Override
    protected void onPreExecute() {
        StaticParameters.orders.clear();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        final String URL = "https://kariyertechchallenge.mockable.io/";

        try {
            Document document = Jsoup.connect(URL).ignoreContentType(true).get();
            JSONArray jsonArray = new JSONArray(document.body().text());

            for (int i = 0; i < jsonArray.length(); i++) {

                Order order = new Order();
                order.setDate(jsonArray.getJSONObject(i).getString("date"));
                order.setMonth(jsonArray.getJSONObject(i).getString("month"));
                order.setMarketName(jsonArray.getJSONObject(i).getString("marketName"));
                order.setOrderName(jsonArray.getJSONObject(i).getString("orderName"));
                order.setProductPrice(jsonArray.getJSONObject(i).getDouble("productPrice"));
                order.setProductState(jsonArray.getJSONObject(i).getString("productState"));
                order.setProductDetail(new Order().new ProductDetail(
                        jsonArray.getJSONObject(i).getJSONObject("productDetail").getString("orderDetail"),
                        jsonArray.getJSONObject(i).getJSONObject("productDetail").getDouble("summaryPrice")));

                StaticParameters.orders.add(order);
                listener.onNotifyDataSetChanged();
            }
        } catch (Exception e) {
        }
        return null;
    }

}