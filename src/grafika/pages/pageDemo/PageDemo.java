package grafika.pages.pageDemo;

import grafika.GraphicsManager;

import java.awt.*;

public class PageDemo extends grafika.pages.SamplePage{

    public PageDemo() {
        super();
        specificLayout(); // page-specific layout
    }

    private void specificLayout(){

        layoutInfo.headerHeight = 100;
        layoutInfo.footerHeight = 100;

    }

    @Override
    public void drawBody(Graphics g){
        g.setColor(GraphicsManager.colorPaletteData.get(GraphicsManager.activeColorPalette).pair3[1]);
        g.drawString("DEMO",200,200);
    }

}
