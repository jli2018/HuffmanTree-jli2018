/** 
 * HuffmanRunner class. 
 *
 * @author	Jessica Li
 * @version	02/07/16
 */ 
public class HuffmanRunner
{
	/**
	 * Main method, tests HuffmanTree. 
	 * Creates new HuffmanTree, encodes String and prints result, decodes encoded String and prints result. 
	 * 
	 * @param args 	main method parameter
	 */ 
	public static void main( String[] args )
	{
		//HuffmanNode law = new HuffmanNode( "key", 2 ); 
		System.out.println();
		HuffmanTree hTree = new HuffmanTree( "Maya Huffman huffs at Huffman trees." ); 
		//hTree.printPQueue(); 
		System.out.println();
		String code = hTree.encode( "Maya Huffman huffs at Huffman trees." );
		System.out.println( code + "\n");
		System.out.println( hTree.decode( code ) );
	}	
}



