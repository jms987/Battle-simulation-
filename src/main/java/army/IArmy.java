
package army;
import map.*;

import java.util.ArrayList;

public interface IArmy {
    /**
     * Dodaje jednostke do armi oraz zajmuje jej miejsce na mapie
     * @param unit jednostka ktora ma zostac umieszczona w armi
     * @param map mapa na ktorej ma zostac odnotowana obecnosc jednostki
     */
    public void addUnit(AUnit unit, IMap map);

    /**
     * Metoda zwraca liste jednostek ktore SA ZYWE
     * @return zwraca jednostki danej armii
     */
    public ArrayList<AUnit> getUnits();

    /**
     * usuwa wybrana jednostke z armi
     * @param target jednostka ktora ma zostac usunieta
     */
    public void removeUnit(AUnit target);

    /**
     * Zwraca liste WSZYSTKICH jednostek (tez z hp < 0)
     * @return lista wszystkich jednostek
     */
    public ArrayList<AUnit> getAllUnits();

    /**
     * Metoda usuwa z armii martwe jednostki
     */
    public void refreshArmy();

}
