package co.wgarcia.apps.carros;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;

public class EditarCarro extends AppCompatActivity {

    private Resources res;
    private Intent i;
    private Bundle bundle;
    private int foto, color, precio;
    private String id, placa, marca, modelo;
    private Carro carro;

    private TextInputLayout iPlaca, iMarca, iModelo, iPrecio;
    private EditText txtplaca, txtmarca, txtmodelo, txtprecio;
    private Spinner scolor;

    private ArrayList<Integer> fotos;
    private ArrayAdapter<String> adapter;
    private String[] opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_carro);

        res = this.getResources();

        iPlaca = (TextInputLayout)findViewById(R.id.caja_Placa_edit);
        iMarca = (TextInputLayout)findViewById(R.id.caja_Marca_edit);
        iModelo = (TextInputLayout)findViewById(R.id.caja_Modelo_edit);
        iPrecio = (TextInputLayout)findViewById(R.id.caja_Precio_edit);

        txtplaca = (EditText)findViewById(R.id.txt_Placa_edit);
        txtmarca = (EditText)findViewById(R.id.txt_Marca_edit);
        txtmodelo = (EditText)findViewById(R.id.txt_Modelo_edit);
        txtprecio = (EditText)findViewById(R.id.txt_Precio_edit);
        scolor = (Spinner)findViewById(R.id.spn_Color_edit);
        opc = res.getStringArray(R.array.color_opc);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opc);
        scolor.setAdapter(adapter);

        i = getIntent();
        bundle = i.getBundleExtra("editar");
        id = bundle.getString("id");
        foto = bundle.getInt("foto");
        placa = bundle.getString("placa");
        marca = bundle.getString("marca");
        modelo = bundle.getString("modelo");
        color = bundle.getInt("color");
        precio = bundle.getInt("precio");

        carro = new Carro(id, foto, placa, marca, modelo, color, precio);
        reiniciar();

    }

    public void editar(View v){
        if(validar(v)) {
            Carro c = new Carro(id, foto, txtplaca.getText().toString().trim(),
                    txtmarca.getText().toString().trim(), txtmodelo.getText().toString().trim(),
                    scolor.getSelectedItemPosition(), Integer.parseInt(txtprecio.getText().toString()));
            if (Metodos.carrosIguales(carro, c)){
                Datos.editarCarro(c);
                Snackbar.make(v, res.getString(R.string.guardado), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                onBackPressed(c);
            } else if(Metodos.existePlaca(Datos.obtenerCarros(), c.getPlaca()) == false){
                Datos.editarCarro(c);
                Snackbar.make(v, res.getString(R.string.guardado), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                onBackPressed();
            }else{
                Snackbar.make(v, res.getString(R.string.error_editar), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

    }

    public boolean validar(View v){
        if(validar_vacio(txtplaca, iPlaca)){
            return false;
        }else if (validar_vacio(txtmarca, iMarca)){
            return false;
        }else if(validar_vacio(txtmodelo, iModelo)){
            return false;
        }else if(scolor.getSelectedItemPosition()== 0){
            Snackbar.make(v, res.getString(R.string.errorColor), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }else if(validar_vacio(txtprecio, iPrecio)){
            return false;
        }
        return true;
    }

    public boolean validar_vacio(TextView t, TextInputLayout ct){
        if (t.getText().toString().isEmpty()){
            t.requestFocus();
            t.setError(res.getString(R.string.errorVacio));
            return true;
        }
        return false;
    }

    private void reiniciar(){
        txtplaca.setText(placa);
        txtmarca.setText(marca);
        txtmodelo.setText(modelo);
        scolor.setSelection(color);
        txtprecio.setText(precio+"");
    }
    public void reiniciar(View v){
        reiniciar();
    }

    public void onBackPressed(Carro c){
        finish();
        Intent i = new Intent(EditarCarro.this, DetalleCarro.class);
        Bundle b = Metodos.crear_bundle(c);
        i.putExtra("datos", b);
        startActivity(i);
    }

}
