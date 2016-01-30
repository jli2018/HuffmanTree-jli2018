import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

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
 * @version 01/29/16
 */
public class HuffmanTree
{
	HashMap<String, Integer> hmap = new HashMap<String, Integer>(); 
	HuffmanNode root;
	String sentence;

	/**
	 * Constructor
	 * 
	 * @param s 	string for the original sentence
	 */ 
	public HuffmanTree( String s )
	{
		sentence = s; 
	}

	//method to convert String into map of all values. 
	public void intoMap( String s )
	{
		for ( int i = 0; i < s.length(); s++ )
		{
			String letter = s.substring(i, i+1 ); 
			if ( hmap.containsKey( letter ) )
			{
				int frequency = hmap.get( letter );
				hmap.put( letter, frequency++ );
			}
			else
				hmap.put( letter, 1 );
		}
	}

	public void intoPQueue()
	{
		Set k = hmap.keySet();
		//Is it okay to have this array as a String[]?
		String[] keys = k.toArray(); 
		PriorityQueue nodes = new PriorityQueue(); 

		//high priority, low frequency
		//find and place low frequency nodes in first
		//all in loop
		int minF = hmap.get( keys[0] );
		for ( int i = 1; i < keys.length; i++ )
		{
			if ( hmap.get( keys[i] ) < minF )
			{
				minF = hmap.get( keys[i] );
			}
		}
		nodes.add( new HuffmanNode( keys[i], hmap.get( keys[i] ) ) );
	}

	public void buildTree()
	{
		//do i need this? 
	}

	public void encode()
	{

	}

	public voic decode()
	{

	}

}