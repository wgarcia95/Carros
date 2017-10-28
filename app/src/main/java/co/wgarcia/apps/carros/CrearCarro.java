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
import android.widget.Toast;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;

public class CrearCarro extends AppCompatActivity {

    private TextInputLayout iPlaca, iMarca, iModelo, iPrecio;
    private EditText txtplaca, txtmarca, txtmodelo, txtprecio;
    private Spinner color;

    private Resources res;

    private ArrayList<Integer> fotos;
    private ArrayAdapter<String> adapter;
    private String[] opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carro);

        res = this.getResources();

        iPlaca = (TextInputLayout)findViewById(R.id.caja_Placa);
        iMarca = (TextInputLayout)findViewById(R.id.caja_Marca);
        iModelo = (TextInputLayout)findViewById(R.id.caja_Modelo);
        iPrecio = (TextInputLayout)findViewById(R.id.caja_Precio);

        txtplaca = (EditText)findViewById(R.id.txt_Placa);
        txtmarca = (EditText)findViewById(R.id.txt_Marca);
        txtmodelo = (EditText)findViewById(R.id.txt_Modelo);
        txtprecio = (EditText)findViewById(R.id.txt_Precio);
        color = (Spinner)findViewById(R.id.spn_Color);
        opc = res.getStringArray(R.array.color_opc);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opc);
        color.setAdapter(adapter);
        color.setSelection(0);
        iniciar_fotos();
    }

    public void guardar(View v){
        if(validar(v)) {
            Carro c = new Carro(Metodos.fotoAleatoria(fotos), txtplaca.getText().toString().trim(),
                    txtmarca.getText().toString().trim(), txtmodelo.getText().toString().trim(),
                    color.getSelectedItemPosition(),
                    Integer.parseInt(txtprecio.getText().toString()));
            if(Metodos.existePlaca(Datos.obtenerCarros(), c.getPlaca())==false){
                c.guardar();
                Snackbar.make(v, res.getString(R.string.guardado), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                onBackPressed();
            }else{
                Snackbar.make(v, res.getString(R.string.error_editar), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            limpiar();
        }
    }

    public boolean validar(View v){
        if(validar_vacio(txtplaca, iPlaca)){
            return false;
        }else if (validar_vacio(txtmarca, iMarca)){
            return false;
        }else if(validar_vacio(txtmodelo, iModelo)){
            return false;
        }else if(color.getSelectedItemPosition()== 0){
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

    public void limpiar(View v){
        limpiar();
    }

    private void limpiar(){
        txtplaca.setText("");
        txtmarca.setText("");
        txtmodelo.setText("");
        color.setSelection(0);
        txtprecio.setText("");
        txtplaca.requestFocus();
    }

    public void iniciar_fotos(){
        fotos = new ArrayList<>();
        fotos.add(R.drawable.imagen1);
        fotos.add(R.drawable.imagen2);
        fotos.add(R.drawable.imagen3);
        fotos.add(R.drawable.imagen4);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(CrearCarro.this,Principal.class);
        startActivity(i);
    }
}
