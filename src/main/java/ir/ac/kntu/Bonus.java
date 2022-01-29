package ir.ac.kntu;

import javafx.scene.layout.Pane;

public class Bonus extends Thread {

    Main app;

    /**
     * This thread is used to make one bonus item appear in the beginning of easy and medium games.
     */
    @Override
    public synchronized void start() {
                BonusItem.appear(app.getRoot());
            }


}
