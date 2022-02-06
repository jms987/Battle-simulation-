package army;
import map.*;

import stats.Stats;

/**
 * Pikinier atakuje dwukrotnie
 */

public class Pikeman extends AUnit {

    /**
     * konstruktor jednostki pikeman
     * @param posx pozycja x gdzie jest umieszczana jednostka
     * @param posy pozycja y gdzie jest umieszczana jednostka
     */
    public Pikeman(int posx, int posy)
    {
        super(4,60.0 , 6.0, posx, posy, 1, 2.5);
    }

    /**
     * jednostka moze zaatakowac dwa razy w przeciwenstwie do innych
     * @param map mapa z innymi jednostami ktora pozwala sprawdzic mozliwosci ruchu
     * @param enemyArmy armia przeciwnika ktora pozwala atakowac jednostki przeciwnika
     */
    @Override
    public void move(IMap map, IArmy enemyArmy) {
        super.attack(enemyArmy, 1);
        super.move(map, enemyArmy);
    }
}
