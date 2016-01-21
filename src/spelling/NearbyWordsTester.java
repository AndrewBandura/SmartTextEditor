package spelling;


import org.junit.Before;

import java.util.List;

/**
 * Created by Andrew on 10.01.2016.
 */
public class NearbyWordsTester {
    private String dictFile = "data/words.small.txt";


    String word = "i";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
//        emptyDict = new AutoCompleteDictionaryTrie();
//        smallDict = new AutoCompleteDictionaryTrie();
//        largeDict = new AutoCompleteDictionaryTrie();
//
//        smallDict.addWord("Hello");
//        smallDict.addWord("HElLo");
//        smallDict.addWord("help");
//        smallDict.addWord("he");
//        smallDict.addWord("hem");
//        smallDict.addWord("hot");
//        smallDict.addWord("hey");
//        smallDict.addWord("a");
//        smallDict.addWord("subsequent");
//
//        DictionaryLoader.loadDictionary(largeDict, dictFile);
        Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "data/dict.txt");
        NearbyWords w = new NearbyWords(d);
        List<String> l = w.distanceOne(word, true);
    }

}
