public class MapTester {
    public static void main(String[] args) {
        
        Map testMap = new Map(5, 6);
        Coord corner1 = new Coord(1, 1); 
        Coord corner2 = new Coord(5, 3);

        testMap.buildStructureBlob(corner1, 'A', 3); 

        System.out.println("Initial Map:");
        testMap.printMap();
        String saved = testMap.saveToString();

        Map testMap2 = Map.loadFromString(saved);

        System.out.println("new Map:");
        testMap2.printMap();

        
    }
}