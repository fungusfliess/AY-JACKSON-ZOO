public class MapTester {
    public static void main(String[] args) {
        Map test = new Map (16, 16);

        test.buildStructureRectangular(new Coord(15, 15), new Coord(1, 0), 'A');
        test.printMap();   
    }
}