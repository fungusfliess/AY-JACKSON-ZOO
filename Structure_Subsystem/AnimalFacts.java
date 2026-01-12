public class AnimalFacts{
   private String[] facts;
   private int numFacts;
   
   public AnimalFacts(String[] facts){
      this.facts = facts;
      numFacts = facts.length;
   }
   
   public String getAnimalFact(int index){
      return facts[index];
   }

   public int getLength(){
      return facts.length;
   }

   public String pickRandomFact(){
      return facts[(int)(Math.random() * numFacts + 1)];
   }
}