package stats;

import army.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * klasa przechowuje informacje o statystykach  gry
 */

public class Stats {

    private HashMap<IArmy, HashMap<String, Integer>> unitsLost = new HashMap<IArmy, HashMap<String, Integer>>();

    /**
     * konstruktor tworzy statysyki dla armi obecnych w grze
     * @param firstArmy pierwsza armia dla ktorej sa robione statystyki
     * @param secondArmy druga armia dla ktorej sa robioen statystyki
     */
    public Stats(IArmy firstArmy, IArmy secondArmy){
        this.unitsLost.put(firstArmy, new HashMap<String, Integer>());
        this.unitsLost.put(secondArmy, new HashMap<String, Integer>());
        String unit_type_names[] = {"Peasant", "Cavalry", "Bowman", "Warrior", "Pikeman"};
        for (String unit_type : unit_type_names){
            this.unitsLost.get(firstArmy).put(unit_type, 0);
            this.unitsLost.get(secondArmy).put(unit_type, 0);
        }
    }

    /**
     * uaktualnia statystyki dotyczace
     * @param ar1 lista wszystkich jednostek
     * @param army armia w ktorej sprawdzamy jednostki
     */
    public void updateStats(ArrayList<AUnit> ar1,IArmy army)
    {
        int range= ar1.size();
        AUnit temp;
        for(int i=0;i<range;i++)
        {
                temp = ar1.get(i);
                if(temp.get_hp()<=0)
                {
                    this.unitsLost.get(army).put(temp.getClass().getSimpleName(),
                            unitsLost.get(army).get(temp.getClass().getSimpleName()) +1 );
                }
        }
    }

    /**
     * funckja wypisuje koncowe statystki dla dwoch armi
     * @param firstArmy pierwsza armia
     * @param secondArmy druga armia
     */
    public void  writeStats(IArmy firstArmy, IArmy secondArmy) {
        System.out.println("Stats");
        System.out.println("Units lost in first army: " + unitsLost.get(firstArmy));
        System.out.println("Units lost in second army: " + unitsLost.get(secondArmy));
    }
}
