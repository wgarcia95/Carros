package co.wgarcia.apps.carros;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class DetalleCarro extends AppCompatActivity {

    private Resources res;
    private Intent i;
    private Bundle bundle;
    private int foto, color, precio;
    private String id, placa, marca, modelo;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imgFoto2;
    private TextView lblPlaca, lblMarca, lblModelo, lblColor, lblPrecio;
    String opc[];
    private Carro carro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro);
        res = this.getResources();
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        imgFoto2 =(ImageView)findViewById(R.id.img_foto_det);
        lblPlaca = (TextView)findViewById(R.id.txt_placa_det);
        lblMarca = (TextView)findViewById(R.id.txt_marca_det);
        lblModelo = (TextView)findViewById(R.id.txt_modelo_det);
        lblColor = (TextView)findViewById(R.id.txt_color_det);
        lblPrecio = (TextView)findViewById(R.id.txt_precio_det);

        id = bundle.getString("id");
        foto = bundle.getInt("foto");
        placa = bundle.getString("placa");
        marca = bundle.getString("marca");
        modelo = bundle.getString("modelo");
        color = bundle.getInt("color");
        precio = bundle.getInt("precio");

        carro = new Carro(id, foto, placa, marca, modelo, color, precio);
        opc = res.getStringArray(R.array.color_opc);

        collapsingToolbarLayout.setTitle(placa);
        imgFoto2.setImageDrawable(ResourcesCompat.getDrawable(res,foto,null));
        lblPlaca.setText(placa);
        lblMarca.setText(marca);
        lblModelo.setText(modelo);
        lblColor.setText(opc[color]);
        lblPrecio.setText("$ "+ NumberFormat.getNumberInstance(Locale.US).format(precio));

    }

    public void editar_Carro(View v){
        Intent i = new Intent(DetalleCarro.this, EditarCarro.class);
        Bundle b = Metodos.crear_bundle(carro);
        i.putExtra("editar",b);
        startActivity(i);
    }

    public void eliminar(View v){
        String positivo,negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.titulo_eliminar_mensaje));
        builder.setMessage(res.getString(R.string.eliminar_mensaje));
        positivo = res.getString(R.string.si_eliminar_mensaje);
        negativo = res.getString(R.string.no_eliminar_mensaje);



        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                carro.eliminar();
                onBackPressed();

            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }


    public void onBackPressed(){
        finish();
        Intent i = new Intent(DetalleCarro.this,Principal.class);
        startActivity(i);
    }

}
