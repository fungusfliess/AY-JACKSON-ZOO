public class MapTester {
    public static void main(String[] args) {
        
        Map testMap = new Map(5, 6);
        testMap.printMap();
        Coord corner1 = new Coord(1, 1); 
        Coord corner2 = new Coord(5, 3);

        testMap.buildStructureBlob(corner1, 'A', 3); 
        testMap.printMap();
        System.out.println();

        testMap.erase(corner1);
        testMap.printMap();
        System.out.println();

        testMap.buildStructureBlob(corner2, 'B', 4);
        testMap.printMap();
        System.out.println();
        System.out.println(testMap.areaOf(corner2));
        testMap.printMap();
        System.out.println();

        
    }
}