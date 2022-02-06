package army;

import map.IMap;
import map.Map;
import stats.Stats;

/**
 * Kawaleria - jednostka wykonujaca ruch dwukrotnie
 */
public class Cavalry extends AUnit {
    /**
     * Konstruktor jednostki Cavalry
     * @param posx pozycja x na mapie
     * @param posy pozycja y na mapie
     */
    public Cavalry(int posx, int posy)
    {
        super(1,100.0 , 15.0, posx, posy, 3, 1.25);
    }

    /**
     * Jednostka wykonuje swój ruch dwukrotnie
     * @param map mapa z innymi jednostami ktora pozwala sprawdzic mozliwosci ruchu
     * @param enemyArmy armia przeciwnika ktora pozwala atakowac jednostki
     */
    @Override
    public void move(IMap map, IArmy enemyArmy) {
      for (int i=0; i<2; i++){
        super.move(map, enemyArmy);
      }

    }

}
