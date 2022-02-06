package battle;
import army.*;
import graphic.Graphic;
import map.*;
import stats.Stats;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * glowna klasa przechowujaca mozg calej symulacje
 */
public class Battle {
    private IMap map = new Map();
    private int maxTurns;
    private Stats stats;
    private final IArmy ArmiaA = new Army();
    private final IArmy ArmiaB= new Army();
    private Graphic menuimg;

    /**
     * glowny konstruktor klasy battle
     */
    public Battle() {this.maxTurns = 5;stats = new Stats(ArmiaA, ArmiaB);}

    /**
     * zwraca linie oddzielajca psozcegolne linie w kodzie dla ulatwienia wyswietlania w wyniku symualcji
     * @param b szerokosc lini razy 5
     */
    private void line(int b)
    {
        for(int i=0;i<b*5;i++)
        {System.out.print("-");}
        System.out.print("\n");
    }

    /**
     * wykonywanie tury przez armie A
     */
    private void makeAturn()
    {
        this.map.updateMap(this.ArmiaB);
        this.stats.updateStats(this.ArmiaB.getAllUnits(), this.ArmiaA);
        this.ArmiaB.refreshArmy();
    }

    /**
     * wykonwyanie tury przez armie b
     */
    private void makeBturn()
    {
        this.map.updateMap(this.ArmiaA);
        this.stats.updateStats(this.ArmiaA.getAllUnits(), this.ArmiaB);
        this.ArmiaA.refreshArmy();
    }

    /**
     * glowna funcja symulacji
     */
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".txt";
        PrintStream o = new PrintStream(new File(fileName));
        PrintStream console = System.out;
        System.setOut(o);
        Battle b = new Battle( );
        BufferedImage img;
        b.menuimg = new Graphic();
        try {
            img = ImageIO.read(new File("src/main/java/battle/ikony.png"));
        }
        catch (IOException e)
        {   img = null;
            System.out.println("nie ma pliku");
        }
        int ind = 0;
        final Image imgs[] = new Image[12];
        for(int y=0;y< 128;y+=64) {
            for (int x = 0; x < 384; x += 64) {
                imgs[ind] = img.getSubimage(x, y, 64, 64).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind += 1;
            }
        }
        JFrame frame = new JFrame();
        frame.setBounds(1,1,1600,1000);
        JPanel pn = new JPanel(){
            @Override
            public void paint(Graphics g) {
               boolean color = true;
                for (int i = 0; i <10;i++){
                   for(int j=0;j<10;j++) {
                    if(color) {g.setColor(Color.green);}
                    else {g.setColor(Color.white);}
                   g.fillRect(650+j*90,15+i*90,90,90);
                    color = !color;
                   }
                   color = !color;
                   }
                String names[] ={"Peasant","Cavalry","Bowman","Warrior","Pikeman","Tours:\t",
                        "Peasant","Cavalry","Bowman","Warrior","Pikeman"};
                Font font = new Font("arial",0,20);
                g.drawImage(b.menuimg.getImg(), b.menuimg.getx(),b.menuimg.gety(),null);
                g.setFont(font); g.setColor(Color.black);
                for( int i = 0; i< 12;i++)
                    {
                        if(i==5|i==11) {continue;}
                     g.drawImage(imgs[i],75,50+75*i,null);
                     g.setFont(font);
                     if(i<5) {g.drawString(names[i],175,90+75*i);}
                     else{g.drawString(names[i],175,50+80*i);}
                    }
                Font font2 = new Font("arial",1,30);
                if(b.ArmiaA.getUnits().isEmpty()==false &&b.ArmiaB.getUnits().isEmpty() == false)
                {
                    g.setColor(Color.black);
                    g.fillRect(395,45,160,85);
                    g.setColor(Color.white);
                    g.fillRect(400,50,150,75);
                    g.setColor(Color.black);
                    g.setFont(font2);
                    g.drawString("START",425,100);
                }
              try{
                for (AUnit tempunit : b.ArmiaA.getUnits() )
                {
                    g.drawImage(imgs[tempunit.getId()],650+90*tempunit.get_x(),15+90*tempunit.get_y(),null);
                }
                for (AUnit tempunit : b.ArmiaB.getUnits() )
                {
                      g.drawImage(imgs[tempunit.getId()+6],650+90*tempunit.get_x(),15+90*tempunit.get_y(),null);
                }
              }
              catch(NullPointerException e) { System.out.println("pusta tablica"); }
              g.setColor(Color.black);
              g.fillRect(298,198,64,84);
              g.setFont(font);
              names[5]=names[5]+b.maxTurns;
              g.drawString(names[5],380,250);
              g.setColor(Color.white);
              g.fillRect(300,200,60,80);
              g.setColor(Color.black);
                Font font3 = new Font("arial",0,40);
                g.setFont(font3);
                g.drawString("+",320,230);
                g.drawString("-",320,270);
              g.fillRect(300,239,60,2);
            }
            };

        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                b.menuimg.change_pos((e.getX()-32),(e.getY()-32));
                frame.repaint();
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean unitx = false;
                unitx = e.getX()>75 && e.getX()<140;
                        if(unitx&&e.getY()>=50&&e.getY()<125)
                        {
                            //System.out.println("czarny peasant");
                            b.menuimg.giveImg(imgs[0],0);
                        }
                        else if(unitx&&e.getY()>=125&&e.getY()<200)
                        {
                         //System.out.println("czarny cavalery");
                         b.menuimg.giveImg(imgs[1], 1);
                        }
                        else if (unitx&&e.getY()>=200&&e.getY()<275)
                        {
                            //System.out.println("czarny bowman");
                            b.menuimg.giveImg(imgs[2], 2);

                        }
                        else if (unitx&&e.getY()>=275&& e.getY()<350)
                        {
                           //System.out.println("czarny warrior");
                            b.menuimg.giveImg(imgs[3], 3);
                        }

                        else if(unitx&&e.getY()>=350 && e.getY() < 425)
                        {
                           // System.out.println("czarny Pikeman");
                            b.menuimg.giveImg(imgs[4], 4);
                        }
                        else if(unitx&&e.getY()<575 && e.getY() >= 506){
                            //System.out.println("czerwony  peasant");
                            b.menuimg.giveImg(imgs[6], 5);
                        }

                        else if(unitx&&e.getY()<650 && e.getY() >= 575)
                        {
                            //System.out.println("czerwony cavalery");
                            b.menuimg.giveImg(imgs[7], 6);
                        }

                            else if(unitx&&e.getY()>=650 && e.getY() < 725)
                        {
                            //System.out.println("czerwony bowman");
                            b.menuimg.giveImg(imgs[8], 7);
                        }

                            else if(unitx&&e.getY()>=725 && e.getY() < 800)
                        {
                            //System.out.println("czerwony warrior");
                            b.menuimg.giveImg(imgs[9], 8);
                        }

                            else if(unitx&&e.getY()>=800 && e.getY() < 875)
                        {
                            //System.out.println("czerwony Pikeman");
                            b.menuimg.giveImg(imgs[10], 9);
                        }

                else
                {
                    if(e.getX()>395&&e.getX()<560&&e.getY()>45&&e.getY()<150)
                    {
                        System.out.println("Start simulation");
                            int t2= 1;
                        AUnit tempUnit;
                        for(int t=0;t<b.maxTurns;t++)
                        {
                            b.line(2);
                            System.out.println("Actual turn:\t"+t2);
                            t2+=1;
                            b.line(1);
                            System.out.println("Army A");
                            for(int i=0;i<b.ArmiaA.getUnits().size();i++)
                            {
                                try{
                                tempUnit = b.ArmiaA.getUnits().get(i);
                                tempUnit.searchEnemies(b.ArmiaB);
                                tempUnit.move(b.map, b.ArmiaB);
                                b.ArmiaA.getUnits().set(i,tempUnit);
                                frame.paint(frame.getGraphics());
                                b.makeAturn(); }
                                catch (NullPointerException f) {
                                    b.makeAturn();break;
                                }
                            }
                            frame.repaint();
                            b.line(1);
                            System.out.println("Army B");
                            for(int i=0;i<b.ArmiaB.getUnits().size();i++)
                            {
                                try
                                {tempUnit = b.ArmiaB.getUnits().get(i);
                                tempUnit.searchEnemies(b.ArmiaA);
                                tempUnit.move(b.map, b.ArmiaA);
                                b.ArmiaB.getUnits().set(i,tempUnit);
                                frame.paint(frame.getGraphics());
                                b.makeBturn(); }
                                catch (NullPointerException f) {b.makeBturn();break;}
                            }
                            frame.repaint();
                            try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException f) {f.printStackTrace();}
                            if(b.ArmiaA.getUnits().isEmpty()|b.ArmiaB.getUnits().isEmpty()) {break;}
                        }
                        b.line(2);
                        System.out.println("END");
                        b.line(1);
                        b.stats.writeStats(b.ArmiaA, b.ArmiaB);
                        frame.setVisible(true);
                    }
                    else
                    {}
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getX() > 298 && e.getX() < 298 +64) {
                    if(e.getY() > 198+30 && e.getY() < 198+30+42) {
                        b.maxTurns ++;
                        frame.repaint();
                    }
                    if(e.getY() > 198+30+42 && e.getY() < 198+30+42+42) {
                        if(b.maxTurns>0){
                        b.maxTurns --;
                        frame.repaint();}
                    }
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if(e.getX()>650 && e.getX()<1550&&e.getY()>15&&e.getY()<915){
                    if(b.menuimg.getid() == 0)
                    {
                        Peasant tempUnit = new Peasant((e.getX()-650)/90,(e.getY()-15)/90);
                        //if(b.map.getUnit((e.getX()-650)/90,(e.getY()-15)/90) == null)
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaA.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }

                    else if(b.menuimg.getid() == 1) {
                        Cavalry tempUnit = new Cavalry((e.getX()-650)/90,(e.getY()-15)/90);
                        //if(b.map.getUnit((e.getX()-650)/90,(e.getY()-15)/90) == null)
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaA.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }

                    else if(b.menuimg.getid() == 2) {
                        Bowman tempUnit = new Bowman((e.getX()-650)/90,(e.getY()-15)/90);
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaA.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }

                    else if(b.menuimg.getid() == 3) {
                        Warrior tempUnit = new Warrior((e.getX()-650)/90,(e.getY()-15)/90);
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaA.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }

                    else if(b.menuimg.getid() == 4) {
                        Pikeman tempUnit = new Pikeman((e.getX()-650)/90,(e.getY()-15)/90);
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaA.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }

                    else if(b.menuimg.getid() == 5)
                    {
                        Peasant tempUnit = new Peasant((e.getX()-650)/90,(e.getY()-15)/90);
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaB.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }

                    else if(b.menuimg.getid() == 6) {
                        Cavalry tempUnit = new Cavalry((e.getX()-650)/90,(e.getY()-15)/90);
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaB.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }

                    else if(b.menuimg.getid() == 7) {
                        Bowman tempUnit = new Bowman((e.getX()-650)/90,(e.getY()-15)/90);
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaB.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }

                    else if(b.menuimg.getid() == 8) {
                        Warrior tempUnit = new Warrior((e.getX()-650)/90,(e.getY()-15)/90);
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaB.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }
                    else if(b.menuimg.getid() == 9) {
                        Pikeman tempUnit = new Pikeman((e.getX()-650)/90,(e.getY()-15)/90);
                        if(b.map.checkFree((e.getX()-650)/90,(e.getY()-15)/90))
                        {
                            b.ArmiaB.addUnit(tempUnit, b.map);
                            b.map.placeUnit(tempUnit.get_x(), tempUnit.get_y());
                        }
                        b.menuimg.make_null();
                        frame.repaint();
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
           public void mouseExited(MouseEvent e) { }
        });
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}

