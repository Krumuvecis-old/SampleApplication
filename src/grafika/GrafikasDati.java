package grafika;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class GrafikasDati {

    static int defaultCloseAction;
    public static int[] ekransSize, ekransLocation;

    static String windowTitle;

    public static int activeColorPalette = 0;
    public static ArrayList<ColorPalette> colorPaletteData;

    public static class ColorPalette{
        public String name;
        public Color[] pair1, pair2, pair3;

        ColorPalette(String _name, Color[] _pair1, Color[] _pair2, Color[] _pair3){
            name = _name;
            pair1  = new Color[]{_pair1[0], _pair1[1]}; //main background, all borders
            pair2  = new Color[]{_pair2[0], _pair2[1]}; //header&footer background, header&footer main
            pair3  = new Color[]{_pair3[0], _pair3[1]}; //body background, body main
        }
    }


    static void initializeGraphicsVariables(String nosaukums, String versija){
        defaultCloseAction = JFrame.DISPOSE_ON_CLOSE;//DO_NOTHING_ON_CLOSE;//

        windowTitle = nosaukums + ", versija: " + versija;

        SettingsInfo settingsInfo = new SettingsInfo();
        ekransSize = new int[]{
                settingsInfo.ekransSize[0],
                settingsInfo.ekransSize[1]};
        ekransLocation = new int[]{
                settingsInfo.ekransLocation[0],
                settingsInfo.ekransLocation[1]};

        generateColorPalettes();
        activeColorPalette = settingsInfo.activeColorPalette;
    }

    private static class SettingsInfo{
        int[] ekransSize = new int[]{700, 600},
                ekransLocation = new int[]{50, 50};

        int activeColorPalette = 0;

        SettingsInfo(){
            String filePath = main.Main.dataFilePath + "settings/grafikai";

            readValues(filePath);
            rewriteValues(filePath);
        }

        private void readValues(String filePath){
            ArrayList<String[]> readInfo;
            readInfo = fileHandling.FileHandler.prepareValueList(filePath);
            if (readInfo == null) readInfo = new ArrayList<>();
            for (String[] rinda : readInfo) {
                if (rinda.length > 2){
                    switch (rinda[0]) {
                        case "ekransSize" -> {
                            ekransSize[0] = Integer.parseInt(rinda[1]);
                            ekransSize[1] = Integer.parseInt(rinda[2]);
                        }
                        case "ekransLoc" -> {
                            ekransLocation[0] = Integer.parseInt(rinda[1]);
                            ekransLocation[1] = Integer.parseInt(rinda[2]);
                        }

                        default -> {}
                    }
                } else if (rinda.length == 2) {
                    switch (rinda[0]) {
                        case "colorPalette" -> activeColorPalette = Integer.parseInt(rinda[1]);
                        case "sampleText" -> GraphicsManager.sampleText = rinda[1];

                        default -> {}
                    }
                }
            }
        }

        private void rewriteValues(String filePath){
            ArrayList<String[]> writableData = new ArrayList<>();
            writableData.add(new String[]{"ekransSize", ekransSize[0] + "", ekransSize[1] + ""});
            writableData.add(new String[]{"ekransLocation", ekransLocation[0] + "", ekransLocation[1] + ""});
            writableData.add(new String[]{"sampleText", GraphicsManager.sampleText});
            writableData.add(new String[]{"colorPalette", activeColorPalette + ""});

            fileHandling.FileHandler.writeToFile(filePath, writableData);
        }

    }

    private static void generateColorPalettes(){
        colorPaletteData = new ArrayList<>();

        colorPaletteData.add(
                new ColorPalette("gray",
                        new Color[]{
                                new Color(0,0,0),
                                new Color(0,0,0)},
                        new Color[]{
                                new Color(60,60,60),
                                new Color(0,0,0)},
                        new Color[]{
                                new Color(100,100,100),
                                new Color(0,0,0)}));

        colorPaletteData.add(
                new ColorPalette("red",
                        new Color[]{
                                new Color(0,0,0),
                                new Color(40,0,0)},
                        new Color[]{
                                new Color(80,50,50),
                                new Color(40,0,0)},
                        new Color[]{
                                new Color(140,80,80),
                                new Color(70,0,0)}));

        colorPaletteData.add(
                new ColorPalette("green",
                        new Color[]{
                                new Color(0,0,0),
                                new Color(0,40,0)},
                        new Color[]{
                                new Color(50,80,50),
                                new Color(0,40,0)},
                        new Color[]{
                                new Color(80,140,80),
                                new Color(0,70,0)}));

        colorPaletteData.add(
                new ColorPalette("blue",
                        new Color[]{
                                new Color(0,0,0),
                                new Color(0,0,40)},
                        new Color[]{
                                new Color(50,50,80),
                                new Color(0,0,40)},
                        new Color[]{
                                new Color(80,80,140),
                                new Color(0,0,70)}));


    }

}
