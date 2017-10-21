package co.wgarcia.apps.carros;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Android on 21/10/2017.
 */

public class Datos {
    private static String db = "Carros";
    private static DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Carro> carros = new ArrayList<>();

    public static void guardarCarro(Carro c){
        c.setId(dbreference.push().getKey());
        dbreference.child(db).child(c.getId()).setValue(c);
    }

    public static ArrayList<Carro> obtenerCarros(){
        return carros;
    }

    public static void setCarros(ArrayList<Carro> per){
        carros = per;
    }

    public static void editarCarro(Carro c){
        dbreference.child(db).child(c.getId()).child("placa").setValue(c.getPlaca());
        dbreference.child(db).child(c.getId()).child("marca").setValue(c.getMarca());
        dbreference.child(db).child(c.getId()).child("modelo").setValue(c.getModelo());
        dbreference.child(db).child(c.getId()).child("color").setValue(c.getColor());
        dbreference.child(db).child(c.getId()).child("precio").setValue(c.getPrecio());

        //personas.set(pos, p);
    }
}
