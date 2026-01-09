public class MapTester {
    public static void main(String[] args) {
        
        Map testMap = new Map(5, 6);
        Coord corner1 = new Coord(1, 1); 
        Coord corner2 = new Coord(5, 3);

        Land testLand = new Land(10, testMap);
        testMap.buildStructureBlob(corner1, 'A', 3); 

        System.out.println("Initial Map:");
        testMap.printMap();
        String saved = testMap.saveToString();

        testLand.saveToFile("testMapSave.txt");

        
    }
}