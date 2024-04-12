import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ElectionSystem {

private Election election;

  public ElectionSystem(LinkedList<String> candidates){
      election = new Election();
    election.initializeCandidates(new LinkedList<>(candidates));
  }

    public void initializeElection(LinkedList<String> candidates) {
        election.initializeCandidates(candidates);
    }

    public void simulate(int p){
      Random rand = new Random();
      for(int i = 0; i < p; i++){
          election.castRandomVote();
      }
    }

    public void rigElection(String candidate){
      election.rigElection(candidate);
    }

    public List<String> getTopKCandidates(int k){
      return election.getTopKCandidates(k);
    }
    public void auditElection(){
      election.auditElection();
    }

    public static void main (String[] args){

    Election election = new Election();

    // list of candidates
      LinkedList<String> candidates = new LinkedList<>(Arrays.asList("Marcus Fenix", "Dominic Santiago", "Damon Baird", "Cole Train", "Anya Stroud"));
      election.initializeCandidates(candidates);

      int p = 5;

      //cast votes
      election.castVote("Cole Train");
      election.castVote("Cole Train");
      election.castVote("Marcus Fenix");
      election.castVote("Anya Stroud");
      election.castVote("Anya Stroud");

      List<String> topCandidates = election.getTopKCandidates(3);

      System.out.println("Top 3 candidates after 5 votes: [");
      for (int i = 0; i < topCandidates.size(); i++) {
        System.out.print(topCandidates.get(i));
        if (i < topCandidates.size() - 1) {
          System.out.print(", ");
        }
      }
      System.out.println("]");
      // rig election
      election.rigElection("Marcus Fenix");

      // print the top 3 after rigging election
      topCandidates = election.getTopKCandidates(3);
      System.out.print("Top 3 candidates after rigging the election: [");
      for (int i = 0; i < topCandidates.size(); i++) {
        System.out.print(topCandidates.get(i));
        if (i < topCandidates.size() - 1) {
          System.out.print(", ");
        }
      }
      System.out.println("]");

      // audit election
      System.out.println("Audit:");
      election.auditElection();
    }

}
