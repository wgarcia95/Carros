package co.wgarcia.apps.carros;

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

    public static boolean exitencia_carro(ArrayList<Carro> carros, String placa){
        for (int i = 0; i <carros.size() ; i++) {
            if(carros.get(i).getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }
}
