package army;

/**
 * podstawowa jednostka
 */
public class Peasant extends AUnit {

    /**
     * konstruktor jednostki peasanta
     * @param posx pozycja x gdzie jest umieszczana jednostka
     * @param posy pozycja y gdzie jest umieszczana jednostka
     */
    public Peasant(int posx, int posy)
    {
        super(0,25.0 , 3.0, posx, posy, 2, 1.25);
    }
}
