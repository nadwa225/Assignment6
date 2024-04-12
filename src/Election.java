import java.util.*;

public class Election {
    private Map<String, Integer> votes; // stores candidate votes
    private PriorityQueue<Map.Entry<String, Integer>> maxHeap; // to get the top candidate

    public Election(){
        votes = new HashMap<>();
        maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());

    }

    public void initializeCandidates(LinkedList<String> candidates){
        for(String candidate: candidates){
            votes.put(candidate, 0);
        }
    }

    public void  castVote(String candidate){
        if(!votes.containsKey(candidate)){
            throw new IllegalArgumentException("Can not find candidate");
        }
        int count = votes.get(candidate);
        votes.put(candidate, count + 1);
        updateMaxHeap();
    }

    public void castRandomVote(){
        Random rand = new Random();
        List<String> candidates = new ArrayList<>(votes.keySet());
        String randCandidates = candidates.get(rand.nextInt(candidates.size()));
        castVote(randCandidates);
    }

    public void rigElection(String candidate) {
        Integer candidateVotes = votes.get(candidate);
        if(candidateVotes == null){
            throw new IllegalArgumentException("Candidate not found");
        }

        // Total number of votes excluding the rigged candidate
        int totalVotesExcludingRigged = votes.values().stream()
                .mapToInt(Integer::intValue)
                .sum() - candidateVotes;

        // Update candidate vote count
        votes.put(candidate, candidateVotes + totalVotesExcludingRigged);

        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            if (!entry.getKey().equals(candidate)) {
                double equal = (double) entry.getValue() / totalVotesExcludingRigged;
                int additionalVotes = (int) Math.ceil(equal * totalVotesExcludingRigged);
                entry.setValue(entry.getValue() + additionalVotes);
            }
        }

        updateMaxHeap();
    }

    public List<String> getTopKCandidates(int k) {
        List<String> topCandidates = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            topCandidates.add(maxHeap.poll().getKey());
        }
        return topCandidates;
    }

    public void auditElection() {
        for (String candidate : votes.keySet()) {
            System.out.println(candidate + " - " + votes.get(candidate));
        }
    }
    private void updateMaxHeap() {
        maxHeap.clear();
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            maxHeap.offer(entry);
        }
    }
}


