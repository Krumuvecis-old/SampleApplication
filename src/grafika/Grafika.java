package grafika;

import grafika.pages.SamplePage;

import javax.swing.*;
import java.awt.*;

public class Grafika extends JPanel {

    public JFrame ekrans;
    Grafika grafika;

    //te nelikt mainîgos!!!

    public void initialize(){

        ekrans = new JFrame(GraphicsManager.windowTitle);
        ekrans.setDefaultCloseOperation(GraphicsManager.defaultCloseAction);

        grafika = new Grafika();

        ekrans.getContentPane().add(grafika);
        ekrans.setResizable(true);
        ekrans.setSize(GraphicsManager.ekransSize[0], GraphicsManager.ekransSize[1]);
        ekrans.setLocation(GraphicsManager.ekransLocation[0], GraphicsManager.ekransLocation[1]);

        ekrans.setVisible(true);
    }

    public void main(){
        ekrans.repaint();
    }

    protected void paintComponent(Graphics g) {
        updateScreenSize();

        SamplePage activePage = GraphicsManager.pageList.get(GraphicsManager.activePage);
        activePage.layoutInfo.drawBackground(g);
        activePage.drawBody(g);
        activePage.layoutInfo. drawLayout(g);
    }

    private void updateScreenSize(){
        GraphicsManager.ekransSize[0] = GraphicsManager.grafika.grafika.getWidth();
        GraphicsManager.ekransSize[1] = GraphicsManager.grafika.grafika.getHeight();
    }

}
