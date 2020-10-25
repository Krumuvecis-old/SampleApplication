package grafika.pages;

import grafika.GraphicsManager;
import main.Main;

import java.awt.*;

public class SamplePage {

    public LayoutInfo layoutInfo;

    public SamplePage(){
        layoutInfo = new LayoutInfo();
    }

    public class LayoutInfo{
        public int headerHeight;
        public int footerHeight;

        LayoutInfo(){
            //constructor for default values

            headerHeight = 40;
            footerHeight = 40;

        }

        public void drawBackground(Graphics g){
            g.setColor(GraphicsManager.colorPaletteData.get(GraphicsManager.activeColorPalette).pair1[0]);
            g.fillRect(
                    0,0,
                    GraphicsManager.grafika.ekrans.getWidth(),
                    GraphicsManager.grafika.ekrans.getHeight());
            g.setColor(GraphicsManager.colorPaletteData.get(GraphicsManager.activeColorPalette).pair3[0]);
            g.fillRect(
                    0, headerHeight,
                    GraphicsManager.ekransSize[0],
                    Math.max(0, GraphicsManager.ekransSize[1] - (headerHeight  + footerHeight)));
        }

        public void drawLayout(Graphics g){
            drawFooter(g);
            drawHeader(g);
        }

        private void drawHeader(Graphics g){
            g.setColor(GraphicsManager.colorPaletteData.get(GraphicsManager.activeColorPalette).pair2[0]);
            g.fillRect(
                    0,0,
                    GraphicsManager.ekransSize[0], headerHeight);
            g.setColor(GraphicsManager.colorPaletteData.get(GraphicsManager.activeColorPalette).pair1[1]);
            g.drawLine(
                    0, headerHeight,
                    GraphicsManager.ekransSize[0], headerHeight);
        }

        private void drawFooter(Graphics g){
            g.setColor(GraphicsManager.colorPaletteData.get(GraphicsManager.activeColorPalette).pair2[0]);
            g.fillRect(
                    0, Math.max(0, GraphicsManager.ekransSize[1] - footerHeight),
                    GraphicsManager.ekransSize[0], footerHeight);
            g.setColor(GraphicsManager.colorPaletteData.get(GraphicsManager.activeColorPalette).pair1[1]);
            g.drawLine(
                    0, Math.max(0, GraphicsManager.ekransSize[1] - footerHeight),
                    GraphicsManager.ekransSize[0], Math.max(0, GraphicsManager.ekransSize[1] - footerHeight));
        }
    }

    public void drawBody(Graphics g){

        g.setColor(GraphicsManager.colorPaletteData.get(GraphicsManager.activeColorPalette).pair3[1]);
        g.drawString(GraphicsManager.sampleText,50,100);
        g.drawString("reize : " + Main.versionInfo.reize,50,150);
        g.drawString("X1 : " + Main.sampleVariable1,50,200);
        g.drawString("X2 : " + Main.sampleVariable2,50,215);

    }

}
