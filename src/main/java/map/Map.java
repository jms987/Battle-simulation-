package map;

import army.*;

import java.util.ArrayList;

/**
 * klasa przechowujaca mape jednostek
 */
public class Map implements IMap {
    private boolean isTaken[][] = new boolean[10][10];

    public Map() {};

    /**
     * Sprawdza czy pole jest wolne
     * @param x wspolrzedna x pola
     * @param y wspolrzedna y pola
     * @return true jesli pole jest wolne
     */
    @Override
    public boolean checkFree(int x, int y) {
        if (!isTaken[x][y]) { return true;}
        else {return false;}
    }

    /**
     * Zajmuje dane pole
     * @param x wspolrzedna x pola
     * @param y wspolrzedna y pola
     */
    @Override
    public void placeUnit(int x, int y) {
        isTaken[x][y] = true;
    }

    /**
     * * Zwalnia dane pole
     * @param x wspolrzedna x pola
     * @param y wspolrzedna y pola
     */
    @Override
    public void removeUnit(int x, int y) {
        isTaken[x][y] = false;
    }

    /**
     * Aktualizuje wolne pola w zaleznosci od pozostalych jednostek w armii
     * @param army armia w ktorej jednoskti mogly umrzec i ktorej pozycje trzeba teraz zaktualizowac
     */
    @Override
    public void updateMap(IArmy army)
    {
        ArrayList<AUnit> temp1 = army.getAllUnits();
        AUnit temp2;
        for(int i =0;i<army.getAllUnits().size();i++)
        {
            temp2=temp1.get(i);
         if(temp2.get_hp()<=0)
         {
             this.removeUnit(temp2.get_x(),temp2.get_y());
         }
        }
    }

}
