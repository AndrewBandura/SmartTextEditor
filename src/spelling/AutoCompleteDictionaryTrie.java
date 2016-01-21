package spelling;

import java.util.*;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		if(isWord(word))
			return false;

		TrieNode v = root;
		for (char ch : word.toLowerCase().toCharArray()) {
			if (!v.getValidNextCharacters().contains(ch)) {
				v.insert(ch);
			}
			v = v.getChild(ch);
		}
		v.setEndsWord(true);
		size++;

	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		TrieNode v = root;
		for (char ch : s.toLowerCase().toCharArray()) {
			if (v.getValidNextCharacters().contains(ch)) {
				v = v.getChild(ch);
			}
			if(v.getText().equals(s.toLowerCase()) && v.endsWord())
				return true;
			//v = v.getChild(ch);
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param //text The text to use at the word stem
     * @param //n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions


		 Queue<TrieNode> queue = new LinkedList<>();
		 List<String> completions = new ArrayList<>();
		 TrieNode stem = getNodeByPrefix(prefix);
		 if(stem==null)
			 return completions;

		 TrieNode next = stem;
		 queue.add(stem);
			 while(!queue.isEmpty() && completions.size()<numCompletions ) {
				 TrieNode item = queue.remove();
				 if (item != null ) {
					 if(item.endsWord()){
					 	completions.add(item.getText());}
					 for(char ch:item.getValidNextCharacters()){
					 	next = item.getChild(ch);
						 queue.add(next);
				 }
			 }
		 }
		 return completions;
     }

	public TrieNode getNodeByPrefix(String prefix){
		TrieNode v = root;
		if(prefix=="")
			return root;
		for (char ch : prefix.toLowerCase().toCharArray()) {
			if (v.getValidNextCharacters().contains(ch)) {
				v = v.getChild(ch);
			}
			if(v.getText().equals(prefix.toLowerCase()))
				return v;
		}
		return null;
	}

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}