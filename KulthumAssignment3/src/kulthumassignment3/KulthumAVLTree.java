package kulthumassignment3;


import java.util.List;
import java.util.ArrayList;


/**
 *
 * This class extends KulthumBinarySearchTree class to override the insert and 
 * delete method to re-balance the tree if necessary.
 */
public class KulthumAVLTree<E> extends KulthumBinarySearchTree<E> 
{
    // Create an empty KulthumAVLTree using a natural comparator
    // super() is implicitly called.
    public KulthumAVLTree() 
    {} 


    // Create a KulthumAVLTree with a specified comparator
    public KulthumAVLTree(java.util.Comparator<E> c) 
    {
        super(c);
    }


    // Create an KulthumAVLTree from an array of objects 
    public KulthumAVLTree(E[] objects) 
    {
        super(objects);
    }


    @Override 
    // Override createNewNode to create an KulthumAVLTreeeNode 
    protected KulthumAVLTreeNode<E> createNewNode(E e) 
    {
        return new KulthumAVLTreeNode<E>(e);
    }


    @Override 
    // Insert an element and rebalance if necessary 
    public boolean insert(E e) 
    {
        boolean successful = super.insert(e);
        if (!successful)
            // e is already in the tree
            return false; 
        else 
            // Balance from e to the root if necessary
            balancePath(e); 
        // e is inserted
        return true; 
    }


    // Update the height of a specified node 
    private void updateHeight(KulthumAVLTreeNode<E> node) 
    {
        // node is a leaf
        if (node.left == null && node.right == null) 
            node.height = 0;
        
        // node has no left subtree
        else if (node.left == null) 
            node.height = 1 + ((KulthumAVLTreeNode<E>)(node.right)).height;

        // node has no right subtree
        else if (node.right == null) 
            node.height = 1 + ((KulthumAVLTreeNode<E>)(node.left)).height;

        else
            node.height = 1 +
            Math.max(((KulthumAVLTreeNode<E>)(node.right)).height,
            ((KulthumAVLTreeNode<E>)(node.left)).height);
    }


    // Balance the nodes in the path from the specified node to the root if necessary
    private void balancePath(E e) 
    {
        java.util.ArrayList<TreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) 
        {
            KulthumAVLTreeNode<E> A = (KulthumAVLTreeNode<E>)(path.get(i));
            updateHeight(A);
            KulthumAVLTreeNode<E> parentOfA = (A == root) ? null :
                (KulthumAVLTreeNode<E>)(path.get(i - 1));

            switch (balanceFactor(A)) 
            {
                case -2:
                    if (balanceFactor((KulthumAVLTreeNode<E>)A.left) <= 0) 
                        balanceLL(A, parentOfA); // Perform LL rotation
                    else 
                        balanceLR(A, parentOfA); // Perform LR rotation
                    break;

                case +2:
                    if (balanceFactor((KulthumAVLTreeNode<E>)A.right) >= 0) 
                        balanceRR(A, parentOfA); // Perform RR rotation
                    else 
                        balanceRL(A, parentOfA); // Perform RL rotation
            }
        }
    }


    // Return the balance factor of the node 
    private int balanceFactor(KulthumAVLTreeNode<E> node) 
    {
        if (node.right == null) // node has no right subtree
            return -node.height;
        else if (node.left == null) // node has no left subtree
            return +node.height;
        else
            return ((KulthumAVLTreeNode<E>)node.right).height -
                ((KulthumAVLTreeNode<E>)node.left).height;
    }


    // Balance LL 
    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) 
    {
        TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy

        if (A == root) 
            root = B;    
        else 
        {
            if (parentOfA.left == A) 
                parentOfA.left = B;      
            else 
                parentOfA.right = B;   
        }

        A.left = B.right; // Make T2 the left subtree of A
        B.right = A; // Make A the left child of B
        updateHeight((KulthumAVLTreeNode<E>)A);
        updateHeight((KulthumAVLTreeNode<E>)B);
    }


    // Balance LR 
    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) 
    {
        TreeNode<E> B = A.left; // A is left-heavy
        TreeNode<E> C = B.right; // B is right-heavy

        if (A == root) 
            root = C;
        else 
        {
            if (parentOfA.left == A) 
                parentOfA.left = C;
            else 
                parentOfA.right = C;
        }

        A.left = C.right; // Make T3 the left subtree of A
        B.right = C.left; // Make T2 the right subtree of B
        C.left = B;
        C.right = A;

        // Adjust heights
        updateHeight((KulthumAVLTreeNode<E>)A);
        updateHeight((KulthumAVLTreeNode<E>)B);
        updateHeight((KulthumAVLTreeNode<E>)C);
    }


    // Balance RR
    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) 
    {
        TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy

        if (A == root) 
            root = B;
        else 
        {
            if (parentOfA.left == A) 
                parentOfA.left = B;
            else 
            parentOfA.right = B;      
        }

        A.right = B.left; // Make T2 the right subtree of A
        B.left = A;
        updateHeight((KulthumAVLTreeNode<E>)A);
        updateHeight((KulthumAVLTreeNode<E>)B);
    }


    // Balance RL 
    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) 
    {
        TreeNode<E> B = A.right; // A is right-heavy
        TreeNode<E> C = B.left; // B is left-heavy

        if (A == root) 
            root = C;
        else 
        {
            if (parentOfA.left == A) 
                parentOfA.left = C;      
            else 
                parentOfA.right = C;      
        }

        A.right = C.left; // Make T2 the right subtree of A
        B.left = C.right; // Make T3 the left subtree of B
        C.left = A;
        C.right = B;

        // Adjust heights
        updateHeight((KulthumAVLTreeNode<E>)A);
        updateHeight((KulthumAVLTreeNode<E>)B);
        updateHeight((KulthumAVLTreeNode<E>)C);
    }


    @Override 
    // Delete an element from the binary tree and return true if done.
    public boolean delete(E element) 
    {
        if (root == null)
            return false; // Element is not in the tree

        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) 
        {
            if (c.compare(element, current.element) < 0) 
            {
                parent = current;
                current = current.left;
            }
            else if (c.compare(element, current.element) > 0) 
            {
                parent = current;
                current = current.right;
            }
            else
            break; // Element is in the tree pointed by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left children (See Figure 23.6)
        if (current.left == null) 
        {
            // Connect the parent with the right child of the current node
            if (parent == null) 
                root = current.right;
            else 
            {
                if (c.compare(element, parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;

                // Balance the tree if necessary
                balancePath(parent.element);
            }
        }
        // Case 2: The current node has a left child
        else 
        {          
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) 
            {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost is current
                parentOfRightMost.left = rightMost.left; 

                // Balance the tree if necessary
                balancePath(parentOfRightMost.element);
        }

        size--;
        return true; // Element inserted
    }


    // KulthumAVLTreeNode is TreeNode plus height 
    protected static class KulthumAVLTreeNode<E> 
        extends KulthumBinarySearchTree.TreeNode<E> 
    {
        // New data field
        protected int height = 0; 

        public KulthumAVLTreeNode(E o) 
        {
            super(o);
        }
    }
}
