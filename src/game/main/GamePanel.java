package game.main;

import game.entity.Entity;
import game.entity.Player;
import game.object.SuperObject;
import game.tile.TileManager;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // Screen settings
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale; // 48x48 pixels
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
    Sound soundEffect = new Sound();
    KeyHandler keyH = new KeyHandler (this);
    Thread gameThread;

    // Entity and Object
    public Player player = new Player( this, keyH );
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    // Game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel () {

        this.setPreferredSize( new Dimension ( screenWidth, screenHeight ));
        this.setBackground( Color.black );
        this.setDoubleBuffered( true );
        this.addKeyListener( keyH );
        this.setFocusable( true );
    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setNpc();
        playMusic(0);
        gameState = playState;
    }
    public void startGameThread() {

        gameThread = new Thread( this );
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while ( gameThread != null ) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if ( delta >= 1 ) {

                update(); //first, update the information such as character positions
                repaint(); //second, draw the screen with the updated information

                delta--;
                drawCount++;
            }

            if ( timer >= 1000000000 ) {

                System.out.println( "FPS" + drawCount );
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update () {

        if ( gameState == playState) {
            player.update();

            for ( Entity entity : npc ) {
                if ( entity != null ) {
                    entity.update();
                }
            }
        }

        if ( gameState == pauseState ) {
            gameThread.interrupt();
        }
    }

    public void paintComponent( Graphics g ) {

        super.paintComponent( g );
        Graphics2D g2 = ( Graphics2D )g;

        // DEBUG MODE
        long drawStart = 0;
        if ( keyH.checkDrawTime ) {
            drawStart = System.nanoTime();
        }

        // TILE
        tileManager.draw( g2 );

        //OBJECTS
        for ( int i = 0; i < obj.length; i++ ) {

            if ( obj[i] != null ) {
                obj[i].draw( g2, this );
            }
        }

        // NPC
        for ( int i = 0; i < obj.length; i++ ) {
            if ( npc[i] != null ) {
                npc[i].draw( g2 );
            }
        }

        // PLAYER
        player.draw( g2 );

        // UI
        ui.draw( g2 );

        // DEBUG MODE
        if ( keyH.checkDrawTime ) {
            long drawEnd = System.nanoTime();
            long timePassed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: " + timePassed, 40, 100);
        }

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

    public void playSoundEffect(int i ) {

        soundEffect.setFile(i);
        soundEffect.play();
    }
}
