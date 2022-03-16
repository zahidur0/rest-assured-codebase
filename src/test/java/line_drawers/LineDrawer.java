package line_drawers;

public class LineDrawer {
    public static void HorizontalLineDrawer(){
        int lineLength = 120;
        for (int i = 0; i <= lineLength; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
