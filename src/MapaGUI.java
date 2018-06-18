import javax.swing.*;
import java.awt.*;

class MapaGUI extends JPanel {


    /**
     * This field contains number of fields(elements/meshes) in the map grid
     * in the direction of X axis (aka horizontal axe)
     */
    private   int PLANSZA_MAX_X;

    /**
     * This field contains number of fields(elements/meshes) in the map grid
     * in the direction of Y axis (aka vertical axe)
     */
    private   int PLANSZA_MAX_Y;

    /**
     * This is the offset (blank space, margin) from left border of a map panel
     * after which drawing of "Squares"(map grid meshes/elements/fields) start
     * Offset is calculated from left to right.
     */
    private static final int LEFT_OFFSET_FROM_PANEL = 20;

    /**
     * This is the offset (blank space, margin) from upper border of a map panel
     * after which drawing of "Squares"(map grid meshes/elements/fields) start
     * Offset is calculated from up to down.
     */
    private static final int UPPER_OFFSET_FROM_PANEL = 20;



    /**
     * Size in pixels
     */
    private static int SIZE_OF_SQUARE_FOR_LOW_SCREEN_RESOLUTION = 30;

    /**
     * Size in pixels
     */
    private static int SIZE_OF_SQUARE_FOR_BIG_SCREEN_RESOLUTION = SIZE_OF_SQUARE_FOR_LOW_SCREEN_RESOLUTION+10;

    /**
     * Size in pixels
     */
    private static int GAP_BEETWEEN_SQUARES_FOR_LOW_SCREEN_RESOLUTION = 5;

    /**
     * Size in pixels
     */
    private static int GAP_BEETWEEN_SQUARES_FOR_BIG_SCREEN_RESOLUTION = GAP_BEETWEEN_SQUARES_FOR_LOW_SCREEN_RESOLUTION+2;

    /**
     * Max resolution (height, in px) when screen is treated as "low screen" (low resolution screen).
     * Above this screen height we have got "big screen" (big resolution screen).
     */
    private static int LOW_SCREEN_MAXIMUM_HEIGHT =1024;

    /**
     * Square is a single element in map grid (aka map mesh)
     * Size in pixels
     */
    private  int sizeOfSquare = 30;

    /**
     * Size in pixels
     */
    private  int gapBeetweenSquares = 5;

    private MapaModel mapaModel;
    Image poleNieSprawdzone=(new ImageIcon(getClass().getResource("/img/poleUnk.jpg"))).getImage();
    Image polePudlo=(new ImageIcon(getClass().getResource("/img/polePudlo.jpg"))).getImage();
    Image poleShip=(new ImageIcon(getClass().getResource("/img/poleShip.jpg"))).getImage();


    MapaGUI(MapaModel mapaModel) {
        this.mapaModel = mapaModel;
        PLANSZA_MAX_X=mapaModel.numberOfFieldsInXdirection();
        PLANSZA_MAX_Y=mapaModel.numberOfFieldsInYdirection();
        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension d = tk.getScreenSize();
        if (Logger.isDebugEnabled()) {
            Logger.debug("Screen width = " + d.width);
            Logger.debug("Screen height = " + d.height);
        }
        /* obsluga duzych rozdzielczosci */
        if (d.height > LOW_SCREEN_MAXIMUM_HEIGHT) {
            sizeOfSquare = SIZE_OF_SQUARE_FOR_BIG_SCREEN_RESOLUTION;
            gapBeetweenSquares = GAP_BEETWEEN_SQUARES_FOR_BIG_SCREEN_RESOLUTION;
            System.out.println("High Resolution mode enabled.");
        } else
            System.out.println("Low Resolution mode.");

        this.setPreferredSize(new Dimension(0, this.getYsize()));
    }

    void refresh(){
        this.repaint();
        this.revalidate();
    }
    /**
     * Return width of panel in pixels
     *
     * @return Return width of panel in pixels
     */
    int getXsize() {
        return (PLANSZA_MAX_X * (gapBeetweenSquares + sizeOfSquare) + LEFT_OFFSET_FROM_PANEL*2);
    }

    /**
     * Return height of panel in pixels
     *
     * @return Return height of panel in pixels
     */
    int getYsize() {
        return (PLANSZA_MAX_Y * (gapBeetweenSquares + sizeOfSquare) + UPPER_OFFSET_FROM_PANEL*2);
    }

    public void paintComponent(Graphics g) {
        // wiem ze bez sensu to liczyc tyle razy ile petla sie obraca i mozna raz,
        // a pozniej podstawic do obrotow petli. optymalizacja na koncu zrobie ;)
        for (int y = 0; y < PLANSZA_MAX_Y; y++) {
            for (int x = 0; x < PLANSZA_MAX_X; x++) {
                int imageX = x * (gapBeetweenSquares + sizeOfSquare) + LEFT_OFFSET_FROM_PANEL;
                int imageY = UPPER_OFFSET_FROM_PANEL + (PLANSZA_MAX_Y - 1 - y) * (gapBeetweenSquares + sizeOfSquare);

                //switch (mapaStrzalow[y][x]) {
                switch (y) {
                    case '.':// g.drawImage(poleNieSprawdzone,imageX,imageY,this);
                        g.drawRect(imageX, imageY, sizeOfSquare, sizeOfSquare);
                        break;
                    case 'X':
                        g.drawImage(poleShip, imageX, imageY, imageX + sizeOfSquare, imageY + sizeOfSquare, 0, 0, poleShip.getWidth(null), poleShip.getHeight(null), this);
                        // case 'X': g.drawImage(poleShip,imageX,imageY,this);
                        break;
                    //default: g.drawImage(polePudlo,imageX,imageY,this);
                    default:
                        g.drawImage(polePudlo, imageX, imageY, imageX + sizeOfSquare, imageY + sizeOfSquare, 0, 0, poleShip.getWidth(null), poleShip.getHeight(null), this);
                        break;

                }
            }
        }

        //if (Logger.isDebugEnabled()) if (mapaModel !=null) mapaModel.wyswietlMapeStrzalow();
// System.out.println("poleShip.Width Height:"+poleShip.getWidth(null)+" "+poleShip.getHeight(null));
    }

}