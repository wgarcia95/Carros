package co.wgarcia.apps.carros;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Android on 21/10/2017.
 */

public class Metodos {

    public static int fotoAleatoria(ArrayList<Integer> fotos){
        int select;
        Random r = new Random();
        select = r.nextInt(fotos.size());
        return fotos.get(select);
    }

    public static boolean existePlaca(ArrayList<Carro> carros, String placa){
        for (int i = 0; i <carros.size() ; i++) {
            if(carros.get(i).getPlaca().equalsIgnoreCase(placa)){
                return true;
            }
        }
        return false;

    }

    public static Bundle crear_bundle(Carro c){
        Bundle b = new Bundle();
        b.putString("id", c.getId());
        b.putInt("foto", c.getFoto());
        b.putString("placa", c.getPlaca());
        b.putString("marca", c.getMarca());
        b.putString("modelo", c.getModelo());
        b.putInt("color", c.getColor());
        b.putInt("precio", c.getPrecio());

        return b;
    }

    public static boolean carrosIguales(Carro c1, Carro c2){
        if(c1.getPlaca().equalsIgnoreCase(c2.getPlaca())){
            return  true;
        }
        return false;
    }
}
