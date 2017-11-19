package com.example.android.chatapps;


import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentGetActivity extends Fragment {

    private ProgressDialog progressDialog;
    private ListView listView;
    private static String url = "http://qiscusinterview.herokuapp.com/products";

    public ArrayList<HashMap<String, String>> arrayList;

    public FragmentGetActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_get, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        arrayList = new ArrayList<>();
        listView = (ListView) getActivity().findViewById(R.id.listfrag);
        new GetJSON().execute();

    }

    private class GetJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Tolong tunggu :)");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();
            String data = httpHandler.Request(url);

            if (data != null) {
                try {

                    JSONArray oke = new JSONArray(data);
                    for (int i = 0; i < oke.length(); i++){
                        JSONObject oce = oke.getJSONObject(i);
                        String id = oce.getString("id");
                        String name = oce.getString("name");
                        String price = oce.getString("price");
                        String desc = oce.getString("description");

                        HashMap<String, String> hashMap = new HashMap<>();

                        hashMap.put("id", id);
                        hashMap.put("name", name);
                        hashMap.put("price", "Harga : "+price);
                        hashMap.put("desc", "Deskripsi : "+desc);

                        arrayList.add(hashMap);
                    }

                } catch (final JSONException e) {

                }
            } else {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (progressDialog.isShowing())
                progressDialog.dismiss();

            ListAdapter adapter = new SimpleAdapter(getActivity(), arrayList,
                    R.layout.list_item, new String[]{"name", "price",
                    "desc"}, new int[]{R.id.name,
                    R.id.price, R.id.desc});

            listView.setAdapter(adapter);
        }

    }

}
