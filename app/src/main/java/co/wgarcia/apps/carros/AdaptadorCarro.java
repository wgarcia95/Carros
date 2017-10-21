package co.wgarcia.apps.carros;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Android on 21/10/2017.
 */

public class AdaptadorCarro extends RecyclerView.Adapter<AdaptadorCarro.CarroViewHolder>{

    private ArrayList<Carro> carros;
    private Resources res;
    private OnCarroClickListener clickListener;

    public AdaptadorCarro(ArrayList<Carro> carros, Context contexto, OnCarroClickListener clickListener) {
        this.carros = carros;
        this.res = contexto.getResources();
        this.clickListener = clickListener;
    }

    @Override
    public CarroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewcarro,parent,false);
        return new CarroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarroViewHolder holder, int position) {
        final Carro c = carros.get(position);
        holder.foto.setImageDrawable(ResourcesCompat.getDrawable(res,c.getFoto(),null));
        holder.placa.setText(c.getPlaca());
        holder.marca.setText(c.getMarca());
        holder.modelo.setText(c.getModelo());
        holder.precio.setText("$ "+ NumberFormat.getNumberInstance(Locale.US).format(c.getPrecio()));
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onCarroClick(c);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    public static class CarroViewHolder extends RecyclerView.ViewHolder{
        private View v;
        private ImageView foto;
        private TextView placa, marca, modelo, precio;

        public CarroViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            foto = (ImageView)v.findViewById(R.id.img_foto);
            placa= (TextView)v.findViewById(R.id.lbl_det_placa);
            marca= (TextView)v.findViewById(R.id.lbl_det_marca);
            modelo=(TextView)v.findViewById(R.id.lbl_det_modelo);
            precio = (TextView)v.findViewById(R.id.lbl_det_precio);
        }
    }

    public interface OnCarroClickListener{
        void onCarroClick(Carro c);
    }
}
