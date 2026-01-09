import java.io.*;

public class Land {
    
    // FIELDS
    private int currentNumStructures;
    private int maxNumStructures;
    private Map landMap;
    private Structure[] structureList;


    // ACCESSOR MUTATORS
    public int getCurrentNumStructures() {
        return currentNumStructures;
    }
    public void setCurrentNumStructures(int currentNumStructures) {
        this.currentNumStructures = currentNumStructures;
    }
    public int getMaxNumStructures() {
        return maxNumStructures;
    }
    public void setMaxNumStructures(int maxNumStructures) {
        this.maxNumStructures = maxNumStructures;
    }

    // CONSTRUCTOR
    public Land(int maxNumStructures, Map landMap) {
        this.maxNumStructures = maxNumStructures;
        this.landMap = landMap;
        this.currentNumStructures = 0;
        this.structureList = new Structure[maxNumStructures];
    }

    /*
    @description: Saves land's map and list of structures to a file.
    @params: String filename represents the name of the file to save to.
    @returns: boolean indicating success.
    */
    public boolean saveToFile (String filename) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            // write landMap into file.
            out.write(this.landMap.saveToString());

            // use out.write("\n") to create new lines because out.newLine() may cause formatting issues on different OS. (I think, I read this from a Google search.)
            out.write("\n");
            out.write(this.maxNumStructures + "\n");
            out.write(this.currentNumStructures + "\n");
            out.write("\n");
            // writing Structure info into the file.
            for (int i = 0; i < this.currentNumStructures; i++) {
                out.write(this.structureList[i].saveToString());
                // any minor adjustments to formatting can be done here. (wait until Structure class is done)
            }
             
            out.close();
            return true;
        } catch (IOException iox) {
            System.out.println("Error writing to file: " + filename);
            return false;
        }
    }

    /*
    @description: Loads land's map and list of structures from a file.
    @params: String filename represents the name of the file to load from.
    @returns: boolean indicating success.

    NOTE: This will OVERWRITE ANY EXISTING DATA in the Land object.
    */
    public boolean loadFromFile (String filename) {
        String input, sumString;
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            // reading landMap from file
            sumString = "";
            // if input is an empty line, stop reading. Check null to avoid errors. 
            // This will still read the empty line, so the next line is the beginning of the next section.
            while ((input = in.readLine()) != null && !input.isEmpty()) {
                sumString += input + "\n";
            }
            // pass this formatted String to Map.loadFromString to create landMap.
            this.landMap = Map.loadFromString(sumString);
            
            // reading currentNumStructures
            input = in.readLine();
            this.maxNumStructures = Integer.parseInt(input);
            input = in.readLine();
            this.currentNumStructures = Integer.parseInt(input);
            // creating new array of Structure
            this.structureList = new Structure[this.maxNumStructures];

            in.skip(1);
            // reading Structure info from the file.
            for (int i = 0; i < this.currentNumStructures; i++) {
                sumString = "";
                // if input is an empty line, stop reading. Check null to avoid errors. This will still read the empty line.
                while ((input = in.readLine()) != null && !input.isEmpty()) {
                    sumString += input + "\n";
                }

                this.structureList[i] = Structure.loadFromString(sumString);
            }

            
             
            in.close();
            return true;
        } catch (IOException iox) {
            System.out.println("Error reading from file: " + filename);
        }
        return false;
    }


}