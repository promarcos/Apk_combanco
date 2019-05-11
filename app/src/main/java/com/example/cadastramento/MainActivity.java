package com.example.cadastramento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private BDSQLiteHelper bd;

    ListView lvlistalivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new BDSQLiteHelper(this);
        lvlistalivros = (ListView) findViewById(R.id.list_view);
        Button btn_inserir = (Button) findViewById(R.id.btn_inserir);
        btn_inserir.setOnClickListener(new View.OnClickListener()
            @Override

        );
    }
}
