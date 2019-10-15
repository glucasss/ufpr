package com.example.drinkmixerapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int drinkId = 3;
        Intent it = getIntent();
        String[] drinks = new String[]{"Batida Sonho de Valsa", "Drink dos Deuses", "Quentão"};
        String[] ingredients = new String[]{"3 bombons Sonho de Valsa \n" +
                                        "1 lata de leite condensado \n",
                                        "1 vidro (100ml) de leite de coco \n" +
                                        "1 copo (100ml) de suco de maracujá \n",
                                        "1 garrafa de chachaça (600ml) \n" +
                                        "600ml de água \n"};
        String[] instrucoes = new String[]{"Fazer drink 1", "Fazer drink 2", "Fazer drink 3"};
        EditText drinkName = (EditText) findViewById(R.id.drinkName);
        EditText ingBox = (EditText) findViewById(R.id.ingEditText);
        EditText instBox = (EditText) findViewById(R.id.instEditText);

        if(it!=null)
            drinkId = it.getIntExtra("drinkId", 3);

        if(drinkId == 3){
            drinkName.setText("");
            ingBox.setText("");
            Toast.makeText(this, "Drink não encontrado", Toast.LENGTH_SHORT).show();
        }else{
            drinkName.setText(drinks[drinkId]);
            ingBox.setText(ingredients[drinkId]);
            instBox.setText(instrucoes[drinkId]);
        }
    }
}
