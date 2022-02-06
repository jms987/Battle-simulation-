package army;

import map.IMap;
import map.Map;
import java.util.Random;
import stats.*;

/**
 * Wojownik porusza sie w losowa strone
 */
public class Warrior extends AUnit {


    public Warrior(int posx, int posy) {
        super(3, 50.0, 5.0, posx, posy, 2, 1.5);
    }

    /**
     * jednostka ta porusza sie w sposob losowy
     * @param map mapa z innymi jednostami ktora pozwala sprawdzic mozliwosci ruchu
     * @param enemyArmy armia przeciwnika ktora pozwala atakowac jednostki przeciwnika
     */
    @Override
    public void move(IMap map, IArmy enemyArmy) {
        Random rand = new Random();
        int x = this.x;
        int y = this.y;
        int temp;
        boolean is_moved = !this.attack(enemyArmy, 1);
        while (is_moved) {
            temp = rand.nextInt();
            if (temp % 5 == 0) {
                is_moved = false;
                break;
            } else if (temp % 5 == 1) {
                if (this.x > 0) {
                    x = this.x - 1;
                }
            } else if (temp % 5 == 2) {
                if (this.x < 8) {
                    x = this.x + 1;
                }
            } else if (temp % 5 == 3) {
                if (this.y > 0) {
                    y = this.y - 1;
                }
            } else if (temp % 5 == 4) {
                if (this.y < 8) {
                    y = this.y + 1;
                }
            }
            if (map.checkFree(x,y)) {
                System.out.print("Unit:\t" + this.getClass().getSimpleName() + " from [" + this.get_x() + "] [" + this.get_y() +
                        "] \tmoved to\t");
                map.removeUnit(this.x, this.y);
                map.placeUnit(x, y);
                this.x = x;
                this.y = y;
                System.out.println("[" + this.x + "] [" + this.y + "]");
                is_moved = false;
            }
        }
    }
}
