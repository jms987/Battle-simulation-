
package army;
import map.*;

/**
 * AUnit jest wzorem jednostki
 */
public abstract class AUnit {

        protected int id;
        protected double hp;
        protected double damage;
        protected int x;
        protected int y;
        protected int favEnemy;
        protected double favMultipler;

    /**
     konstruktur jednostki
     * @param id id jednostki
     * @param hp aktualna ilosc zdrowia jednostki
     * @param damage obrazenia jakie zadaje jednostka innym jednostkom
     * @param x pozycja x jednostki na mapie
     * @param y pozycja y jednostki na mapie
     * @param favEnemy id jednostki na ktorej ta jednostka ma bonus ataku
     * @param favMultipler wartosc tego bonusu
     */
       public AUnit(int id, double hp,double damage,int x,int y,int favEnemy,double favMultipler)
       {
        this.id = id;
        this.damage = damage;
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.favEnemy = favEnemy;
        this.favMultipler = favMultipler;
       }

    /**
     * funkcja zwraca aktualna wartosc hp
     * @return hp
     */
    public double get_hp(){
          return this.hp;
      }

    /**
     * podstawowa funkcja ruchu dla jednostek
     * @param map mapa z innymi jednostami ktora pozwala sprawdzic mozliwosci ruchu
     * @param enemyArmy armia przeciwnika ktora pozwala atakowac jednostki przeciwnika
     */
       public void move(IMap map, IArmy enemyArmy)
       {
           int xDirection;
           int yDirection;
           AUnit target = this.searchEnemies(enemyArmy);
           if(this.attack(enemyArmy,1)) return;
           if(target.get_x() - this.x > 0){xDirection = 1;}
           else if (target.get_x() - this.x == 0){xDirection = 0;}
           else {xDirection = -1;}

           if(target.get_y() - this.y > 0) {yDirection = 1;}
           else if(target.get_y() - this.y == 0) {yDirection = 0;}
           else {yDirection = -1;}

           if(map.checkFree(this.x + xDirection, this.y))
           {
                map.removeUnit(this.x, this.y);
               System.out.print("Unit:\t"+this.getClass().getSimpleName()+ " from [" + this.get_x() + "] [" + this.get_y() +
                       "] \tmoved to\t");
                this.x += xDirection;
                System.out.println("[" + this.x + "] [" + this.y + "]");
                map.placeUnit(this.x, this.y);
           }
           else if(map.checkFree(this.x , this.y+yDirection))
           {
               map.removeUnit(this.x, this.y);
               System.out.print("Unit:\t"+this.getClass().getSimpleName()+ " from [" + this.get_x() + "] [" + this.get_y() +
                       "] \tmoved to\t");
               this.y += yDirection;
               System.out.println("[" + this.x + "] [" + this.y + "]");
               map.placeUnit(this.x, this.y);
           }
       }

    /**
     *podstawowa funkcja ataku
     * @param enemyArmy armia przeciwnika ktora pozwala atakowac jednostki przeciwnika
     * @param range zasieg ataku, rozne jednostki mmaja rozny zasieg ataku
     * @return true jezeli jednostka zaatakowala, false jezeli nie
     */
       public boolean attack(IArmy enemyArmy, int range)
       {
           AUnit target = this.searchEnemies(enemyArmy);
           if(target.get_hp() > 0.0 && 
              Math.abs(target.get_x() - this.x) <= range &&
              Math.abs(target.get_y() - this.y) <= range)
           {
               System.out.println("Unit:\t"+this.getClass().getSimpleName()+ " from [" + this.get_x() + "] [" + this.get_y() +
                       "] \tattack:\t"+target.getClass().getSimpleName() + " at \t [" + target.get_x() + "] [" + target.get_y() + "]");
               double tempdam;
               if(target.getId()==this.favEnemy) {tempdam = this.damage * this.favMultipler; }
               else{tempdam = this.damage;}
               target.takeDamage(tempdam);
               return true;
           } return false;
       }

    /**
     * funckja pozwala otrzymywac obrazenia jezeli jednostka jest atakowana
     * @param damage aktualne otrzymywane obrazania
     */
       public void takeDamage(double damage)
       {
           System.out.print("Actual unit:\t"+this.getClass().getSimpleName()+" ["+this.x+"] ["+this.y+"] old life "+this.hp);
           this.hp -= damage;
           System.out.print("\tact life:\t"+this.hp+"\n");
       }

    /**
     * funkcja zwraca id jednostki
     * @return id jednostki
     */
    public int getId(){return  this.id;}

    /**
     * funkcja zwraca pozycje x jednostki
     * @return pozycje x jednostki
     */

    public int get_x() { return this.x; }

    /**
     * funkcja zwraca pozycje y jednostki
     * @return pozycje y jednostki
     */

    public int get_y() { return this.y; }

    /**
     * funkcja zwraca jednostke ktora jest najblizsza naszej aktualnej jednostce w celu podaznia do niej
     * a pozniej ataku jej
     * @param enemyArmy armia przeciwnka do ataku jednostki
     * @return jednostke najblisza aktualnej jednostce
     */
    public AUnit searchEnemies(IArmy enemyArmy)
    {
        if(enemyArmy.getUnits().size() == 0) { return null; }

        AUnit tempUnit = enemyArmy.getUnits().get(0);
        for (AUnit enemyUnit : enemyArmy.getUnits())
        {
                if(Math.abs(this.x - enemyUnit.get_x()) < Math.abs(this.x - tempUnit.get_x()) ||
                        Math.abs(this.y - enemyUnit.get_y()) < Math.abs(this.y - tempUnit.get_y()))
                {tempUnit = enemyUnit;}
        }

        return tempUnit;
       }

}

