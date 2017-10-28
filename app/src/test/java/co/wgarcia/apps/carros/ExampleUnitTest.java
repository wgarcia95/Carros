package co.wgarcia.apps.carros;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void existe_Carro_correcto(){
        ArrayList<Carro> carros = new ArrayList<>();
        Carro c1 = new Carro("ABC-123", "MAZDA", "2016", 1, 150000000);
        Carro c2 = new Carro("DCE-136", "BMW", "2019", 3, 260850000);
        Carro c3 = new Carro("FGH-937", "CHEVROLET", "2015", 2, 80000000);
        Carro c4 = new Carro("IJK-123", "TOYOTA", "2016", 4, 22000000);

        carros.add(c1);
        carros.add(c2);
        carros.add(c3);
        carros.add(c4);

        assertTrue(Metodos.exitencia_carro(carros, "FGH-937"));
    }
    @Test
    public void existe_Carro_incorrecto(){
        ArrayList<Carro> carros = new ArrayList<>();
        Carro c1 = new Carro("ABC-123", "MAZDA", "2016", 1, 150000000);
        Carro c2 = new Carro("DCE-136", "BMW", "2019", 3, 260850000);
        Carro c3 = new Carro("FGH-937", "CHEVROLET", "2015", 2, 80000000);
        Carro c4 = new Carro("IJK-123", "TOYOTA", "2016", 4, 22000000);

        carros.add(c1);
        carros.add(c2);
        carros.add(c3);
        carros.add(c4);

        assertFalse(Metodos.exitencia_carro(carros, "KHM-687"));
    }
}