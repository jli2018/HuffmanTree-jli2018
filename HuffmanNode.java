public class HuffmanNode implements Comparable<HuffmanNode>
{
	private String value;
	private int count;
	private HuffmanNode left;
	private HuffmanNode right;

	/**
	 * Constructor for HuffmanNode, gives value and count. Sets left and right to null. 
	 * 
	 * @param v 	string for the value
	 * @param c 	int for frequency
	 */ 
	public HuffmanNode( String v, int c )
	{
		value = v;
		count = c;
		left = null;
		right = null;
	}

	/**
	 * Constructor for HuffmanNode, sets value, count, left, and right. 
	 * 
	 * @param v 	string for the value
	 * @param c 	int for frequency
	 * @param l 	left node
	 * @param r 	right node
	 */
	public HuffmanNode( String v, int c, HuffmanNode l, HuffmanNode r )
	{
		value = v;
		count = c;
		left = l;
		right = r;
	}

	/**
	 * Accessor for value. 
	 * 
	 * @return value
	 */ 
	public String getValue()
	{
		return value;
	}

	/**
	 * Modifier for value. 
	 *
	 * @param v 	new value
	 */ 
	public void setValue( String v )
	{
		value = v;
	}

	/**
	 * Accessor for count. 
	 * 
	 * @return value
	 */ 
	public int getCount()
	{
		return count;
	}

	/**
	 * Modifier for count. 
	 *
	 * @param v 	new count
	 */ 
	public void setCount( int c )
	{
		count = c;
	}

	/**
	 * Accessor for left.  
	 * 
	 * @return value
	 */ 
	public HuffmanNode getLeft()
	{
		return left;
	}

	/**
	 * Modifier for left. 
	 *
	 * @param v 	new left
	 */ 
	public void setLeft( HuffmanNode l )
	{
		left = l;
	}

	/**
	 * Accessor for right. 
	 * 
	 * @return value
	 */ 
	public HuffmanNode getRight()
	{
		return right;
	}

	/**
	 * Modifier for right. 
	 *
	 * @param v 	new right
	 */ 
	public void setRight( HuffmanNode r )
	{
		right = r;
	}

	/**
	 * Compares the frequency of each node, returning positive if greater, 0 if equal, and negative if less. Subtracts frequency of other node from
	 * current node. 
	 *
	 * @param node 		the node being compared to
	 */ 
	public int compareTo( HuffmanNode node )
	{
		return count - node.getCount(); 
	}

	/**
	 * Returns true if node does not have any children - if left and right are both null.
	 *
	 * @return 		true if left and right are both null, false otherwise
	 */ 
	public boolean isLeaf()
	{
		return left == null && right == null;
	}

	/** 
	 * Returns the number of descendants of node, including the current node.
	 * If both left and right nodes are null, returns 1. If neither nodes are null, returns 1 + the size of left + the size of right. 
	 * If only left is null, returns 1 + the size of right. If only right is null (the else case), returns 1 + the size of left. 
	 *
	 * @return 		the size of the tree, or the total number of nodes within the tree
	 */ 
	public int size()
	{
		if ( left == null && right == null )
			return 1;
		else if ( left != null && right != null )
			return 1 + left.size() + right.size();
		else if ( left == null && right != null )
			return 1 + right.size();
		else
			return 1 + left.size();
	}


	/**
	 * Recursive method that returns String representation of tree, separated by parentheses and commas. 
	 *
	 * @return a String representation of the tree. 
	 */ 
	public String toString()
	{
		if ( left == null && right == null )
			return value.toString(); 					//does this need toString()???
		if ( left == null && right != null )
			return value.toString() + "( , " + right.toString() + ")"; 
		if ( left != null && right == null )
			return value.toString() + "(" + left.toString() + ", )"; 
		else
			return value.toString() + "(" + left.toString() + ", " + right.toString() + ")"; 
	}

	/** 
	 * Returns the maximum path length to a descendent. 
	 * Two overall if cases - one for if left is null, one for if left is not null. Within these two cases are cases for if right is null or not null. 
	 * If both left and right are null, returns 1. If only left or right is null, returns 1 + the height of the non-null node. 
	 * If neither left not right is null, returns 1 + the maximum of the heights of both nodes. 
	 *
	 * @return 		the height of the tree, or the maximum path length to a descendent
	 */ 
	public int height()
	{
		//if math.max or if statements
		//just return 1+

		if ( left == null )
		{
			//if both left and right are null
			if ( right == null )
				return 1;
			//if only left is null
			else
				return 1 + right.height();
		}
		else
		{
			//if only right is null
			if ( right == null )
				return 1 + left.height();
			//if neither left nor right is null
			else
				return 1 + Math.max( left.height(), right.height() );
		}

	}


	/** 
	 * Returns true if adding a node to tree would increase its height - or in other words, if the tree is full.
	 * If both left and right nodes are null, returns true. If only the left or right node is null, returns false. 
	 * If neither left nor right is null, checks if left and right are full. If both are full, checks the height of each. 
	 * If both have the same height, returns true. Otherwise, returns false. 
	 *
	 * @return 		true if tree is full (if adding a node to tree would increase its height), false if otherwise
	 */
	public boolean isFull()
	{
		//check to see if null
		//check to see if height is same
		if ( left == null && right == null )
			return true;
		else if ( left == null || right == null )
			return false;
		else
		{
			if ( left.isFull() && right.isFull() )
			{
				if ( left.height() == right.height() )
					return true;
			}
			return false; 
		}
	}

	/**
	 * Returns true if tree has minimal height and any holes in the tree appear in the last level to the right. 
	 * If both left and right are null, returns true. If left is full, right is complete, and the heights of both are equal, returns true. Otherwise,
	 * returns false. If left is complete, right is full, and the height difference is one, returns true. Otherwise, returns false. 
	 * If none of the if statements execute, returns false. 
	 * 
	 * @return 		true if tree has no holes, false otherwise
	 */
	public boolean isComplete()
	{
		//check nulls
		//if right is null, return left.isLeaf()
		if ( left == null && right == null )
			return true;
		if ( left == null && right != null )
			return false;
		if ( left != null && right == null )
			return left.isLeaf(); 
		else
		{
			if ( left.isFull() && right.isComplete())
			{
				if ( left.height() == right.height() )
					return true;
				return false; 
			}
			else if ( left.isComplete() && right.isFull() )
			{
				if( left.height() == right.height() + 1 )
					return true;
				return false;
			}
			return false; 
		}
	}

	/** 
	 * Returns true if the difference of heights of subtrees at every node is no greater than one. 
	 * Two overall if cases - one for when left is null and when left is not null. Within each of these cases I have more cases for 
	 * when right is null or not null. 
	 * If both left and right are null, returns true. If only left or only right is null, checks the height of the not null node. If height
	 * is less than or equal to 1, returns isBalanced() of that node. Otherwise, returns false. If neither left not right is null, checks the
	 * difference between heights of the two nodes. If difference is less than or equal to 1, returns isBalanced() of left AND right nodes. 
	 * Otherwise, returns false. 
	 *
	 * @return 		true if the difference of heights of subtrees at every node is no greater than one. Otherwise false.  
	 */ 
	public boolean isBalanced()
	{
		if ( left == null )
		{
			//if both left and right are null
			if ( right == null )
				return true;
			//if only left is null
			else
			{
				if ( right.height() <= 1 )
					return right.isBalanced();
				else 
					return false;
			}
		}
		else
		{
			//if only right is null
			if ( right == null )
			{
				if ( left.height() <= 1 )
					return left.isBalanced();
				else 
					return false;
			}
			//if neither left nor right is null
			else
			{
				if ( left.height() - right.height() <= 1 )
					return left.isBalanced() && right.isBalanced(); 
				else
					return false;
			}
		}
	}


}