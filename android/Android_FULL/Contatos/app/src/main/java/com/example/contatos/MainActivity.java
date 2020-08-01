package com.example.contatos;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = "http://nce.up.edu.br/juca/users.json";

    ArrayList<HashMap<String, String>> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*TextView carInfo = findViewById(R.id.carInfo);
        try{
            carInfo.setText(adicaoSimplesDeDados().toString());
            carInfo.setText(carInfo.getText() + "\n" + adicaoDeUmObjeto());
        }catch(JSONException e){
            e.printStackTrace();
        }*/

        teamList = new ArrayList<>();
        lv = findViewById(R.id.list);

        new GetTeams().execute();
    }

    private class GetTeams extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Aguarde...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0){
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;
            jsonStr = sh.makeServiceCall(url);

            if(jsonStr != null){
                try{
                    JSONArray teams = new JSONArray(jsonStr);

                    for(int i=0; i<4; i++){
                        JSONObject c = teams.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("first_name");
                        String lastName = c.getString("last_name");
                        String email = c.getString("email");
                        String gender = c.getString("gender");
                        String ipAddress = c.getString("ip_address");

                        HashMap<String, String> team = new HashMap<>();

                        team.put("id", id);
                        team.put("name", name);
                        team.put("lastName", lastName);
                        team.put("email", email);

                        teamList.add(team);
                    }
                }catch (final JSONException e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: "+e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, teamList,
                    R.layout.list_item, new String[]{"name", "lastName", "email"},
                    new int[]{R.id.name, R.id.lastName, R.id.email});

            lv.setAdapter(adapter);
        }
    }

    /*private JSONObject adicaoSimplesDeDados() throws JSONException{
        Carro carro = new Carro();
        carro.setId(1);
        carro.setModelo("Gol");
        carro.setPlaca("AAA1234");

        JSONObject carroJson = new JSONObject();
        carroJson.put("id", carro.getId());
        carroJson.put("Modelo", carro.getModelo());
        carroJson.put("Placa", carro.getPlaca());

        return carroJson;
    }

    private String adicaoDeUmObjeto() throws JSONException{
        Carro carro = new Carro();
        carro.setId(2);
        carro.setModelo("Celta");
        carro.setPlaca("BBB5678");

        JSONObject carroJson = new JSONObject();
        carroJson.put("Carro", carro);

        return carroJson.toString();
    }*/
}

