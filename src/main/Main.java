package main;

import grafika.GraphicsManager;
import grafika.Input;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {

    public static boolean running, minimized, windowActive;

    public static int sampleVariable1 = 0, sampleVariable2 = 0;

    public static VersionInfo versionInfo;

    public static final String dataFilePath = "dati/";

    public static class VersionInfo {
        String nosaukums = "Title", versija = "Version";
        public int reize = 0;

        VersionInfo(){
            String filePath = dataFilePath + "settings/versijai";

            readValues(filePath);
            rewriteValues(filePath);
        }

        private void readValues(String filePath){
            ArrayList<String[]> readInfo;
            readInfo = fileHandling.FileHandler.prepareValueList(filePath);
            if (readInfo == null) readInfo = new ArrayList<>();
            for (String[] rinda : readInfo) {
                if (rinda.length > 1){
                    switch (rinda[0]) {
                        case "nosaukums" -> nosaukums = rinda[1];
                        case "versija" -> versija = rinda[1];
                        case "reize" -> {
                            reize = Integer.parseInt(rinda[1]);
                            reize ++;
                        }

                        default -> {}
                    }
                }
            }
        }

        private void rewriteValues(String filePath){
            ArrayList<String[]> writableData = new ArrayList<>();
            writableData.add(new String[]{"nosaukums", nosaukums});
            writableData.add(new String[]{"versija", versija});
            writableData.add(new String[]{"reize", reize + ""});

            fileHandling.FileHandler.writeToFile(filePath,  writableData);
        }

    }

    public static void launch(){
        System.out.println("Main: Launching.");

        versionInfo = new VersionInfo();
        GraphicsManager.initialize(versionInfo.nosaukums, versionInfo.versija);

        running = true;
        minimized = false;
        windowActive = true;
        run();
    }

    private static void run(){
        System.out.println("Main: Running.");

        while (running){

            if (!minimized){
                userInput();
                GraphicsManager.run();
                calculations();

            }


            try{
                Thread.sleep(10);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        end();
    }

    private static void userInput(){
        //Button.checkButtonActions(this); //uz ekrâna redzamo pogu notikumi
        Input.keyboardActionCheck(); //keyboard nospiesto pogu notikumi
    }

    private static void calculations(){

        sampleVariable1 ++;
        if (sampleVariable1 >= 1000) {
            changePage();
            sampleVariable1 = 0;
        }

        sampleVariable2 ++;
        if (sampleVariable2 >= 200) {
            changeColorPalette();
            sampleVariable2 = 0;
        }

    }

    private static void changePage(){
        GraphicsManager.activePage ++;
        if (GraphicsManager.activePage >= GraphicsManager.pageList.size()) GraphicsManager.activePage = 0;
    }

    private static void changeColorPalette(){
        GraphicsManager.activeColorPalette ++;
        if (GraphicsManager.activeColorPalette >= GraphicsManager.colorPaletteData.size()) GraphicsManager.activeColorPalette = 0;
    }

    public static void end(){
        System.out.println("Main: Ending.");
        GraphicsManager.close();
        System.out.println("Main: Last message.");
    }
}
