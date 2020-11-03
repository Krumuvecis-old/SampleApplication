package grafika;

import grafika.pages.SamplePage;
import grafika.pages.pageDemo.PageDemo;

import java.util.ArrayList;

public class GraphicsManager extends GrafikasDati {

    public static Grafika grafika;
    public static Input input;

    public static String sampleText;

    public static ArrayList<SamplePage> pageList;
    public static int activePage;

    public static void initialize(String nosaukums, String versija){
        System.out.println("GraphicsManager: Initializing.");
        initializeGraphicsVariables(nosaukums, versija);

        generatePageList();

        grafika = new Grafika();
        grafika.initialize();
        input = new Input();

    }

    private static void generatePageList(){
        activePage = 0;
        pageList = new ArrayList<>();

        pageList.add(new SamplePage());
        pageList.add(new PageDemo());
    }

    public static void run(){
        grafika.main();

    }

    public static void close(){
        System.out.println("GraphicsManager: Closing.");
        grafika.ekrans.dispose();
    }

}
