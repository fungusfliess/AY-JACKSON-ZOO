package Land_Subsystem;
public class MapTester {
    public static void main(String[] args) {
        Map test = new Map (16, 16);

        System.out.println(test.buildStructureBlob(new Coord(8, 8), 'e', 4));
        System.out.println(test.buildStructureBlob(new Coord(6, 6), 'o', 4));
        test.printMap();   
        test.replace(new Coord(6, 6), 'D');
        test.printMap();  
        test.erase(new Coord (8, 8));
        test.printMap();  
        test.erase(new Coord (6, 6));
    }
}