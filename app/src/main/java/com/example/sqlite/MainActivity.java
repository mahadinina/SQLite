package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_add,btn_viewAll;
    EditText et_name,et_age;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch customerActivity;
    ListView listCustomer;

    ArrayAdapter customerlist;
    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_add=findViewById(R.id.add);
        btn_viewAll=findViewById(R.id.view_all);
        et_name=findViewById(R.id.name);
        et_age= findViewById(R.id.et_age);
        customerActivity =findViewById(R.id.activeuser);
        listCustomer =findViewById(R.id.customerlist);

        extracted();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer_Model customer_model;
                try{
                     customer_model = new Customer_Model(-1,et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()), customerActivity.isChecked());
                    Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                    customer_model = new Customer_Model(-1,"error",0,false);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.addOne(customer_model);
                extracted();

            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              extracted();
//                Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        listCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer_Model clickCustomer = (Customer_Model) parent.getItemAtPosition(position);
                databaseHelper.deleteOne(clickCustomer);
                extracted();
                Toast.makeText(MainActivity.this, "Deleted "+ clickCustomer.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void extracted() {
        customerlist = new ArrayAdapter<Customer_Model>(MainActivity.this, android.R.layout.simple_list_item_1,databaseHelper.getEverone());
        listCustomer.setAdapter(customerlist);
    }
}