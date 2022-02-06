
package army;
import map.*;
import java.util.ArrayList;

/**
 * Armia przechowuje jednostki oraz usuwa martwe
 */

public class Army implements IArmy {


    private ArrayList<AUnit> units = new ArrayList<>();

    /**
     * domyslny konsturktor
     */
    public Army() { }

    /**
     * Dodaje jednostke do armi oraz zajmuje jej miejsce na mapie
     * @param unit jednostka ktora ma zostac umieszczona w armi
     * @param map mapa na ktorej ma zostac odnotowana obecnosc jednostki
     */
    @Override
    public void addUnit(AUnit unit, IMap map)
    {
        try {
            units.add(unit);
            map.placeUnit(unit.x, unit.y);
        }
        catch (NullPointerException e)
        {
            units.add(0,unit);
        }
    }

    /**
     * Metoda zwraca liste jednostek ktore SA ZYWE
     * @return zwraca jednostki danej armii
     */
    @Override
    public ArrayList<AUnit> getUnits(){
        ArrayList<AUnit> units_temp = new ArrayList<>();
        for (int i=0; i<this.units.size(); i++)
        {
            AUnit unit = this.units.get(i);
            if (unit.get_hp() > 0.0){
                units_temp.add(unit);
            }
        }
        return units_temp;
    }

    /**
     * Zwraca liste WSZYSTKICH jednostek (tez z hp < 0)
     * @return lista wszystkich jednostek
     */
    @Override
    public ArrayList<AUnit> getAllUnits()
    {
        return this.units;
    }

    /**
     * Metoda usuwa z armii martwe jednostki
     */
    @Override
    public void refreshArmy()
    {
        ArrayList<AUnit> temp;
        temp = this.getUnits();
        this.units = temp;
    }

    /**
     * usuwa wybrana jednostke z armi
     * @param target jednostka ktora ma zostac usunieta
     */
    @Override
    public void removeUnit(AUnit target) {this.units.remove(target);}
    
}
