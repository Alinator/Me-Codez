
package server;
import java.util.Comparator;

/**
 * @author Kirill Blazhko
 * @param <type>
 * @param <weightType>
 * This class is used to sort Nodes by weight to be able to get the least Node:
 */

public class NodeByWeightComparator<type, weightType> implements Comparator<Node<type>>
{
    public int compare(Node<type> node1, Node<type> node2) {
    	if (node1.getWeight() > node2.getWeight()) {
    		return 1;
    	} 
    	else if (node1.getWeight() < node2.getWeight()) {
    		return -1;
    	} 
    	else {
    		return 0;
    	} 
    }
}

