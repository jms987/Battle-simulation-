package map;
import army.AUnit;
import army.Army;
import army.IArmy;

public interface IMap {

    /**
     * Sprawdza czy pole jest wolne
     * @param x wspolrzedna x pola
     * @param y wspolrzedna y pola
     * @return true jesli pole jest wolne
     */
    public boolean checkFree(int x, int y);

    /**
     * Zajmuje dane pole
     * @param x wspolrzedna x pola
     * @param y wspolrzedna y pola
     */
    public void placeUnit(int x, int y);

    /**
     * Zwalnia dane pole
     * @param x wspolrzedna x pola
     * @param y wspolrzedna y pola
     */
    public void removeUnit(int x, int y);

    /**
     * Aktualizuje wolne pola w zaleznosci od pozostalych jednostek w armii
     * @param army armia w ktorej jednoskti mogly umrzec i ktorej pozycje trzeba teraz zaktualizowac
     */
    public void updateMap(IArmy army);

}
