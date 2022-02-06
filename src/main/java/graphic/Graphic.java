package graphic;

import java.awt.*;

/**
 * klasa pozwala tworzyc przesuwane obrazy pod kursrem myszki
 */
public class Graphic {
    private int posx = 0;
    private int posy = 0;
    private Image img;
    private int id;

    /**
     * konsturktor klasy
     */
    public Graphic() {}

    /**
     * uaktualnia pozycje funkcji
     * @param x pozycja x na ekranie
     * @param y pozycje y na ekranie
     */
    public void change_pos(int x, int y)
    {
        this.posx = x;
        this.posy = y;
    }

    /**
     * zwraca obrazek ktore jest aktulnie przetrzymywane pod kursorem
     * @return obrazek
     */
    public Image getImg()
    {
        return this.img;
    }

    /**
     * otrzymuje obrazek ktory jest trzymany pod kursorem
     * @param image obrazek
     * @param id ktora jednostka
     */
    public void giveImg(Image image,int id)
    {
    this.img = image;
    this.id = id;
    }

    /**
     * zerowanie obrazek
     */
    public void make_null()
    {
        this.img = null;
    }

    /**
     * pobieranie paramteru x obrazka
     * @return x
     */
    public int getx()
    {
        return this.posx;
    }
    /**
     * pobieranie paramteru y obrazka
     * @return y
     */
    public int gety()
    {
        return this.posy;
    }
    /**
     * pobieranie paramteru id obrazka
     * @return id
     */
    public int getid()
    {
        return this.id;
    }
}
