package spelling;

import java.io.PrintWriter;

public class DictionaryGrader {
    public static void main(String args[]) {
        PrintWriter out;
        try {
            out = new PrintWriter("grader_output/module4.part1.out");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int incorrect = 0;
        int tests = 0;
        String feedback = "";

        try {

            Dictionary dictBST = new DictionaryBST();

            feedback += "** Test #1: Adding a new word to the BST dictionary...";
            feedback += "addWord returned " + dictBST.addWord("tEst") + ".\n";
            
            feedback += "** Test #2: Adding second word to BST dictionary...";
            dictBST.addWord("second");
            feedback += "Dictionary size is " + dictBST.size() + ".\n";
            
            feedback += "** Test #3: Retrieving the word from the first test...";
            feedback += "isWord returned " + dictBST.isWord("teSt") + ".\n";


            feedback += "** Test #4: Adding lots of words and retrieving some...";
            dictBST.addWord("seconds");
            dictBST.addWord("seconded");
            dictBST.addWord("secondhand");
            dictBST.addWord("selma");
            feedback += "isWord(seconded) returned " + dictBST.isWord("seconded") + "; isWord(selma) returned " + dictBST.isWord("selma") + ".\n";


            feedback += "** Test #5: Testing non-word in DictBST...";
            feedback += "isWord(soup) returned " + dictBST.isWord("soup") + ".\n";

        } catch (Exception e) {
            out.println("Runtime error: " + e);
            out.close();
            return;
        }

        feedback += "Tests complete. Make sure everything looks right.";

        out.println(feedback);
        out.close();
    }
}
