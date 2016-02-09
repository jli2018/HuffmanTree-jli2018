import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Huffman Tree class.
 * I chose to import HashMap rather than TreeMap because the stored values do not need to be sorted, as they would be in TreeMap. 
 * HashMap also has faster runtime for put and peek. 
 * Huffman Code works by taking in a sentence and putting each letter of the sentence into a map with its corresponding frequency. The values of the 
 * map are converted into HuffmanNodes and transferred into a priority queue, with the elements with the lowest frequency having the highest priority. 
 * To create the tree, it takes the first two elements out of the priority queue, connects them to a new HuffmanNode with a combined frequency of the two 
 * nodes, and reinserts the new node into its proper place in the priority queue. This continues until there is only one node left in the priority queue.
 * Encode traverses through the tree, recording the path of each letter with 0 and 1 for left and right. Translates the sentence into a string of 0's and 1's. 
 * Decode reads the number, going left for 0 and right for 1. Once it hits a leaf node, it records that letter as part of the message. This continues 
 * until the entire number is read. 
 *
 * @author	Jessica Li
 * @version	02/07/16
 */
public class HuffmanTree
{
	private HashMap<String, Integer> hmap;
	//pQueue is initialized in the method intoPQueue()
	private PriorityQueue<HuffmanNode> pQueue; 
	//root is initialized in the method buildTree()
	private HuffmanNode root;
	private String sentence;

	/**
	 * Constructor for HuffmanTree that takes in a sentence that will be used to create the tree and encode/decode.
	 * Initializes the map and sentence. Creates the map and priority queue, builds the tree. 
	 * 
	 * @param s 	string for the original sentence
	 */ 
	public HuffmanTree( String s )
	{
		hmap = new HashMap<String, Integer>(); 
		sentence = s; 
		intoMap( sentence ); 
		intoPQueue(); 
		buildTree();
	}

	/**
	 * Converts String into map of all values. 
	 * For loop traverses through the string sentence, adding each character to the map. If the map already contains the letter as a key, the entry is 
	 * replaced with the same key but frequency is increased by 1. Otherwise, a new entry with that character and a frequency of one is added. 
	 * 
	 * @param s 	the original sentence
	 */ 
	private void intoMap( String s )
	{
		for ( int i = 0; i < s.length(); i++ )
		{
			String letter = s.substring(i, i+1 ); 
			if ( hmap.containsKey( letter ) )
			{
				//oh my god bane of my code -- why can't I have ++ in the parameter? why do it outside?
				//this marks where I started making progress on debugging. Before I discovered nothing, but after this point I didn't get majorly stuck again
				int frequency = hmap.get( letter ) + 1;
				hmap.put( letter, frequency );
			}
			else
				hmap.put( letter, 1 );
		}
	}

	/**
	 * Transfers elements from the map to a priority queue. Gets all the keys in map as a Set. Change the Set into an Object array. Initializes a new
	 * priority queue. Loop iterates for each element in map, adding new HuffmanNodes to the priority queue created using the key and frequency from 
	 * the array. Type casts from Object to String. 
	 */ 
	private void intoPQueue()
	{
		Set k = hmap.keySet();
		Object[] keys = k.toArray(); 
		pQueue = new PriorityQueue<HuffmanNode>(); 

		//high priority, low frequency
		//find and place low frequency nodes in first
		//all in loop
		for ( int i = 0; i < hmap.size(); i++ )
		{
			pQueue.add( new HuffmanNode( (String) keys[i], hmap.get( (String) keys[i] ) ) );
		}
		//after the loop, the pQueue should be finished, with all the nodes in the proper order. It orders it by itself!!!!		
	}

	/** 
	 * Prints the priority queue in order. For testing purposes. 
	 * Loop traverses through priority queue. Removes the top HuffmanNode, prints the value and count. Decrements i because the size of the priority
	 * queue is also decreasing by one. 
	 */ 
	public void printPQueue()
	{
		//System.out.println( pQueue.size() );
		for ( int i = 0; i < pQueue.size(); i++ )
		{
			HuffmanNode n = pQueue.poll();
			System.out.println( "V: " + n.getValue() + "     C: " + n.getCount() );
			i--;
		}
	}


	/**
	 * Builds the Huffman Tree from the priority queue. While the size of the priority queue is greater than 1, while loop removes top two HuffmanNodes,
	 * creates a new HuffmanNode with combined values and counts, and adds new Node to priority queue. By the time the loop terminates, the tree is 
	 * fully built. Sets root to the only remaining HuffmanNode in the priority queue, which is the top of the tree. Prints root, or the full tree, 
	 * because I want to be able to see a visual representation of the tree when I run the code. 
	 */
	private void buildTree()
	{
		while ( pQueue.size() > 1 )
		{
			HuffmanNode n1 = pQueue.poll(); 
			HuffmanNode n2 = pQueue.poll(); 
			HuffmanNode combinedNode = new HuffmanNode( n1.getValue() + n2.getValue(), n1.getCount() + n2.getCount(), n1, n2 ); 
			pQueue.offer( combinedNode ); 
		}

		//peek or poll? both work?
		//System.out.println( pQueue.peek() );
		root = pQueue.peek(); 
		System.out.println( root );
	}

	/**
	 * Encodes String s. String code stores the sequence of 0's and 1's. For loop traverses through String s, calling helper method encodeHelper()
	 * with root and a single character as parameters, adding the returned value to code. Once the loop terminates, method returns code. 
	 *
	 * @param s 	String to be encoded
	 * @return 		encoded sequence of 0's and 1's
	 *
	 */ 
	public String encode( String s )
	{
		//System.out.println( root );
		String code = "";
		for ( int i = 0; i < s.length(); i++ )
		{
			code += encodeHelper( root, s.substring( i, i+1 ) );
		}
		return code;
	}

	/** 
	 * Helper method to encode() that returns the encoded value of a single character. Recursive.
	 * Base case: if the HuffmanNode taken in is a leaf and its value equals the letter taken in, then returns empty String. 
	 * If the node's left child contains the letter in its value, returns 0 + recursive call with left child and same letter. 
	 * If the node's right child contains the letter in its value, returns 1 + recursive call with right child and same letter. 
	 * 
	 * @param n 	current HuffmanNode, used for traversing through the tree
	 * @param l 	letter/character to be encoded
	 * @return 		encoded String for a particular letter
	 */ 
	private String encodeHelper( HuffmanNode n, String l )
	{
		//System.out.println(n);
		//base case for n is leaf
		if ( n.isLeaf() && n.getValue().equals( l ) )
		{
			//System.out.println("leaf");
			return "";
		}

		//System.out.println( n.getLeft().getValue() );
		//System.out.println( n.getRight().getValue() );
		if ( n.getLeft().getValue().contains( l ) )
		{
			//System.out.println( n.getLeft().getValue() );
			return "0" + encodeHelper( n.getLeft(), l );
		}
		//I don't need all the code in the line below, but for clarity's sake
		else if ( n.getRight().getValue().contains( l ) )
		{
			//System.out.println( n.getRight().getValue() );
			return "1" + encodeHelper( n.getRight(), l );
		}
		//if neither left nor right paths contain l -- but that's not possible right, it must be contained somewhere, since we're starting from root
		//System.out.println( "out of if" );
		return "";
	}

	/** 
	 * Decodes String code. Calls helper method decodeHelper with root and code as parameters. 
	 *
	 * @param code 	the code to be decoded
	 * @return 		the String for which code translates
	 *
	 */
	public String decode( String code )
	{
		return decodeHelper( root, code ); 
	}

	/**
	 * Helper method to decode() that takes in a HuffmanNode and code as parameters. Recursive. 
	 * Base case: If n, or the current node, is a leaf and the length of code is 0, returns the value of n. 
	 * If n is a leaf and the length of code is not 0, returns the value of n + recursive call with root, code as param. This restarts the search for
	 * the next node that the code refers to. 
	 * If the first character in code equals 0, then sets n to n's left child, sets code to same String but without first character, and returns 
	 * recursive call with n, code as param. Otherwise (if first char equals 1), sets n to n's right child, sets code to same String minus first 
	 * character, and returns recursive call with n, code as param. 
	 * 
	 * @param n 	the current HuffmanNode, used to traverse the tree
	 * @param code 	the code to be decoded
	 * @return 		the String that for which the code translates
	 */
	public String decodeHelper( HuffmanNode n, String code )
	{
		if ( n.isLeaf() )
		{
			//System.out.println( "LEAF" );
			if ( code.length() == 0 )
			{
				return n.getValue();
			}
			else
			{
				return n.getValue() + decodeHelper( root, code ); 
			}
		}
		if ( code.substring(0,1).equals("0") )
		{
			n = n.getLeft(); 
			code = code.substring( 1 );
			return decodeHelper( n, code );
		}
		else
		{
			n = n.getRight(); 
			code = code.substring( 1 );
			return decodeHelper( n, code );
		}
	}

}