package game.main;

import game.entity.Player;
import game.object.SuperObject;
import game.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // Screen settings
    final int orginalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = orginalTitleSize * scale; // 48x48 pixels
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = titleSize * maxScreenCol; // 768 pixels
    public final int screenHeight = titleSize * maxScreenRow; // 576 pixels

    // WORLD settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS settings
    int fps = 60;

    // System settings
    public TileManager tileManager = new TileManager( this );
    public CollisionManager collisionManager = new CollisionManager( this );
    public AssetsSetter aSetter = new AssetsSetter( this );
    public Ui ui = new Ui(this);
    Sound music = new Sound();
    Sound soundEfect = new Sound();
    KeyHandler keyH = new KeyHandler ();
    Thread gameThread;

    // Entity and Object
    public Player player = new Player( this, keyH );
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel () {

        this.setPreferredSize( new Dimension ( screenWidth, screenHeight ));
        this.setBackground( Color.black );
        this.setDoubleBuffered( true );
        this.addKeyListener( keyH );
        this.setFocusable( true );
    }

    public void setupGame() {
        aSetter.setObject();

        playMusic(0);
    }
    public void startGameThread() {
        gameThread = new Thread( this );
        gameThread.start();
    }

    @Override
    public void run() {

        double drawinterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while ( gameThread != null ) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawinterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if ( delta >= 1 ) {

                update(); //first, update the information such as character positions
                repaint(); //second, draw the screen with the updated information

                delta--;
                drawCount++;
            }

            if ( timer >= 1000000000 ) {

                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update () {
        player.update();
    }

    public void paintComponent( Graphics g ) {

        super.paintComponent( g );
        Graphics2D g2 = ( Graphics2D )g;

        // TILE
        tileManager.draw( g2 );

        //OBJECTS
        for ( int i = 0; i < obj.length; i++ ) {

            if ( obj[i] != null ) {
                obj[i].draw( g2, this );
            }
        }

        // PLAYER
        player.draw( g2 );

        // UI
        ui.draw( g2 );

        g2.dispose();
    }

    public void playMusic ( int i ) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic ( ) {

        music.stop();
    }

    public void playSoundEfect ( int i ) {

        soundEfect.setFile(i);
        soundEfect.play();
    }
}
