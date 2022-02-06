package army;

import map.Map;
import stats.Stats;

/**
 * Lucznik - jednostka atakujaca z dystansu
 */
public class Bowman extends AUnit {
    /**
     * konstuktor jednostki Bowman
     * @param posx współrzędna x na planszy
     * @param posy współrzędna y na planszy
     */
    public Bowman(int posx, int posy) {
        super(2, 50.0, 8.0, posx, posy, 4, 1.3);
    }


    /**
     * W odroznieniu do AUnit, Bowman ma 2 pola zasiegu ataku
     * @param enemyArmy przeciwna armia
     * @param range zasieg ataku
     * @return zwraca true jesli jednostka zaatakowala przeciwnika
     */

        @Override
        public boolean attack(IArmy enemyArmy, int range)
        {
            return super.attack(enemyArmy,  2);
        }
    
}
