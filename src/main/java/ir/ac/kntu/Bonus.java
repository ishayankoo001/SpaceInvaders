package ir.ac.kntu;

import javafx.scene.layout.Pane;

public class Bonus extends Thread {

    Main app;

    @Override
    public synchronized void start() {
                BonusItem.appear(app.getRoot());
            }


}
