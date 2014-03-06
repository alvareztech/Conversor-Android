package com.danyalvarez.conversor.numericalsystems;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.danyalvarez.conversor.numericalsystems.adapters.ResultsListAdapter;

public class MainActivity extends ActionBarActivity {

    private EditText mValueEditText;
    private Spinner mBaseSpinner;

    private ListView mList;
    private ResultsListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

        mValueEditText = (EditText) findViewById(R.id.valueEditText);
        mBaseSpinner = (Spinner) findViewById(R.id.baseSpinner);

        mList = (ListView) findViewById(android.R.id.list);
        mListAdapter = new ResultsListAdapter(this);
        mList.setAdapter(mListAdapter);

        mValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                colocarBaseCorrectaEnSpinner();
                hacerConvercion();
            }
        });

        mBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                hacerConvercion();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    protected void colocarBaseCorrectaEnSpinner() {
        String numero = mValueEditText.getText().toString();
        int base = buscarMayor(numero);
        mBaseSpinner.setSelection(base);
    }

    private int buscarMayor(String numero) {
        int mayor = 0;
        for (int i = 0; i < numero.length(); i++) {
            int caracter = numero.charAt(i);
            if (caracter > mayor) {
                mayor = caracter;
            }
        }
        String mayorStr = (char) mayor + "";

        String[] bases = {"2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
                "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (int i = 0; i < bases.length; i++) {
            if (mayorStr.equals(bases[i])) {
                return i + 1;
            }
        }
        return 0;
    }

    protected void hacerConvercion() {
        try {
            mListAdapter.clear();
            String numero = mValueEditText.getText().toString();
            int base = mBaseSpinner.getSelectedItemPosition() + 2;
            for (int i = 2; i <= 36; i++) {
                mListAdapter.addItem("Base " + i, convertir(numero, base, i));
            }
        } catch (Exception ex) {
            Toast.makeText(this, R.string.please_valid_number, Toast.LENGTH_SHORT).show();
        }
    }

    private String convertir(String numero, int base, int baseDestino) {
        int numeroBase10 = Integer.parseInt(numero, base);
        String numeroDestino = Integer.toString(numeroBase10, baseDestino);
        return numeroDestino.toUpperCase();
    }

}
