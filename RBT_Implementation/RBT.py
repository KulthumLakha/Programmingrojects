# Description: Program to implement Red Black Trees in Python
# Author: Kulthum Lakha ~kyl0029
# Programming Language: Python 
# Run: python RBT.py



# Import sys library
import sys


###################################################### INITIALIZATION #########################################################

# Define Node
class Node():
    def __init__(T, item):				       # New node
        T.item = item
        T.parent = None
        T.left = None
        T.right = None
        T.color = 1					       # 1 = red, 0 = black



# Define Red-Black Tree
class RBT():


    # Initialize red-black tree
    def __init__(T):
        T.NULL = Node(0)
	T.NULL.color = 0					# Black Node
	T.NULL.left = None
	T.NULL.right = None
	T.root = T.NULL


######################################################### ROTATION ############################################################

    # Left rotate node
    def LR(T,x):
        y = x.right						# Set y = right child of x
        x.right = y.left					# Turn y's left subtree into x's right subtree
        if y.left != T.NULL:
            y.left.parent = x
        y.parent = x.parent					# Link x's parent to y
        if x.parent == None:
            T.root = y
        elif x == x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.left = x						# Attach x on y's left
        x.parent = y


    # Right rotate node
    def RR(T,x):
        y = x.left						# Set y = left child of x
        x.left = y.right					# Turn y's right subtree into x's left subtree
        if y.right != T.NULL:
            y.right.parent = x
        y.parent = x.parent					# Link x's parent to y
        if x.parent == None:					
            T.root = y
        elif x == x.parent.right:
            x.parent.right = y
        else:
            x.parent.left = y
        y.right = x						# Attach x on y's right
        x.parent = y

############################################################ INSERTION #########################################################

    # Fix-up to balance tree after insertion
    def insert_fixup(T,z):
        while z.parent.color == 1:				# While parent is red
            if z.parent == z.parent.parent.right:		# If parent is a right child
                u = z.parent.parent.left			# Check parent's sibling
                if u.color == 1:				# If it is red
                    u.color = 0					# Set it to black
                    z.parent.color = 0				# Set parent as black
                    z.parent.parent.color = 1			# Set grandparent to red
                    z = z.parent.parent				# Recolor for grandparent
                else:						# Else if it is black
                    if z == z.parent.left:          		# if z is a left child
                        z = z.parent
                        T.RR(z)					# Right rotate z
                    z.parent.color = 0				# Recolor
                    z.parent.parent.color = 1
                    T.LR(z.parent.parent)		
            else:						# Else if parent is a left child
                u = z.parent.parent.right			# Check parent's sibling
                if u.color == 1:				# If it is red
                    u.color = 0					# Set it to black
                    z.parent.color = 0				# Set parent as black
                    z.parent.parent.color = 1			# Set grandparent to red
                    z = z.parent.parent				# Recolor for grandparent
                else:						# Else if it is black
                    if z == z.parent.right:			# if z is a right child
                        z = z.parent
                        T.LR(z)					# Left rotate z
                    z.parent.color = 0				# Recolor
                    z.parent.parent.color = 1
                    T.RR(z.parent.parent)
            if z == T.root:					# Stop fixing when root is reached (Tree is balanced)
                break
        T.root.color = 0					# Set root to black


    # Insert new node
    def insert(T,z):
        node = Node(z)						# Create new node
        node.parent = None 				
        node.item = z						# Set value of new node
        node.left = T.NULL					# New node doesn't have children
        node.right = T.NULL
        node.color = 1						# Red Node
        y = None
        x = T.root
        while x != T.NULL:					# Find insertion point
            y = x
            if node.item < x.item:
                x = x.left
            else:
                x = x.right
        node.parent = y						# Set aprent of node as y
        if y == None:						# If no parent, then node is the root
            T.root = node
        elif node.item < y.item:				# Check if node is y's right or left child
            y.left = node
        else:
            y.right = node
        if node.parent == None:					# If node is root, color it black
            node.color = 0
            return
        if node.parent.parent == None:				# If parent of node is root, leave node as red
            return
        T.insert_fixup(node)					# Call fix-up for node


############################################################# DELETION ###########################################################

    # Transplant subtrees
    def transplant(T, u, v):
        if u.parent == None:					# If u has no parent, v will become the root
            T.root = v
        elif u == u.parent.left:				# If u is a left child, v will become the parent's left child
            u.parent.left = v
        else:							# If u is a right child, v will become the parent's right child
            u.parent.right = v			
        v.parent = u.parent					# u's parent will become v's parent


    # Fix-up to balance tree after deletion
    def delete_fixup(T,x):
        while x != T.root and x.color == 0:			# Loop until root is reached
            if x == x.parent.left:				# If x is a left child, check its sibling (right)
                s = x.parent.right
                if s.color == 1:				# If sibling is red, recolor it to black and make parent red
                    s.color = 0
                    x.parent.color = 1
                    T.LR(x.parent)				# Left rotate parent
                    s = x.parent.right
                if s.left.color == 0 and s.right.color == 0:    # If sibling and both its children are black,
                    s.color = 1					# Set sibling to red
                    x = x.parent
                else:						# Else one child of sibling is red
                    if s.right.color == 0:			# If sibling's right child is black, recolor left as black
                        s.left.color = 0
                        s.color = 1				# Set sibling to red
                        T.RR(s)					# Right rotate sibling
                        s = x.parent.right
                    s.color = x.parent.color
                    x.parent.color = 0				# Set x's parent to black
                    s.right.color = 0
                    T.LR(x.parent)				# Left rotate x's parent
                    x = T.root
            else:						# If x is a right child, check its sibling (left)
                s = x.parent.left				
                if s.color == 1:				# If sibling is red, recolor it to black and make parent red
                    s.color = 0
                    x.parent.color = 1
                    T.RR(x.parent)				# Right rotate parent
                    s = x.parent.left

                if s.right.color == 0 and s.right.color == 0:	# If sibling and both its children are black,
                    s.color = 1 				# Set sibling to red
                    x = x.parent
                else:						# Else one child of sibling is red
                    if s.left.color == 0:			# If sibling's left child is black, recolor right as black
                        s.right.color = 0
                        s.color = 1				# Set sibling to red
                        T.LR(s)					# Left rotate sibling
                        s = x.parent.left

                    s.color = x.parent.color
                    x.parent.color = 0				# Set x's parent to black
                    s.left.color = 0
                    T.RR(x.parent)				# Right rotate x's parent
                    x = T.root
        x.color = 0						# Set x to black


    # Node deletion
    def delete_node(T, node, key):
        z = T.NULL
        while node != T.NULL:                   		# Find Node to delete
            if node.item == key:
                z = node
            if node.item <= key:
                node = node.right
            else:
                node = node.left
        if z == T.NULL:						# If node isn't found
            print("Node not found for deletion!")
            return
        y = z					
        y_original_color = y.color				# Store original color of the node
        if z.left == T.NULL:					# If z doesn't have a left child,  let right child be x
            x = z.right
            T.transplant(z, z.right)				# Call for node transplant on x
        elif (z.right == T.NULL):				# Else if z doesn't have a right child,  let left child be x
            x = z.left
            T.transplant(z, z.left)				# Call for node transplant on x
        else:							# Else if z has both children
            y = T.minimum(z.right)				# Find minimum of the subtree
            y_original_color = y.color				# Store color of y
            x = y.right
            if y.parent == z:					# If y is z's child, let y be x's parent
                x.parent = y
            else:						# Else call for node transplant
                T.transplant(y, y.right)
                y.right = z.right
                y.right.parent = y
            T.transplant(z,y)					# Call for node transplant on z
            y.left = z.left					
            y.left.parent = y
            y.color = z.color					# Recolor
        if y_original_color == 0:				# If y was originally black, call fix-up for x
            T.delete_fixup(x)
		

    # Delete a node
    def delete(T, item):
        T.delete_node(T.root, item)				# Call helper function to delete node

############################################################## PRINTING #############################################################

    # Print tree
    def Print(T, node , indent , last):
        if node != T.NULL:					# If node isn't empty
            sys.stdout.write(indent)
            if last :
                sys.stdout.write("R----")
                indent += "     "
            else :
                sys.stdout.write("L----")
                indent += "     "
            s_color = "RED" if node.color == 1 else "BLACK"	# Convert node color from binary to words
            print(str(node.item) + "(" + s_color + ")")		# print node value and color
            T.Print(node.left , indent , False)			# print node's left-subtree
            T.Print(node.right , indent , True)			# Print node's right-subtree


    # Call function to print tree
    def printTree(T):
        T.Print(T.root , "" , True)				# Call helper function to print tree

############################################################## TRAVERSALS ###########################################################

    # Preorder traversal
    def pre(T,node):
        if node != T.NULL:
            sys.stdout.write(str(node.item) + " ")
            T.pre(node.left)					# In-depth traversal of right subtree
            T.pre(node.right)					# followed by left subtree

    # Call function for preorder traversal
    def preorder(T):
        T.pre(T.root)


    # Inorder traversal
    def inT(T, node):
        if node != T.NULL:
            T.inT(node.left)					# Left child -> parent -> right child
            sys.stdout.write(str(node.item) + " ")
            T.inT(node.right)

    # Call function for inorder traversal
    def inorder(T):
        T.inT(T.root)


    # Postorder traversal
    def post(T, node):
        if node != T.NULL:
            T.post(node.left)					# Parent node visited after children nodes
            T.post(node.right)
            sys.stdout.write(str(node.item) + " ")

    # Call function for postorder traversal
    def postorder(T):
        T.post(T.root)

############################################################## SEARCH ###########################################################

    # Search the tree
    def searchTree(T, node, key):			
        if node == T.NULL or key == node.item:
            return node
        if key < node.item:					# Search on left if key is smaller than current node
            return T.searchTree(node.left, key)			# and vice versa until found.
        return T.searchTree(node.right, key)

    # Call function for search
    def search(T, k):
        return T.searchTree(T.root, k)


######################################################## OTHER OPERATIONS ########################################################

    # Find minimum
    def minimum(T, node=None):
        if node is None:					
            node = T.root
        if node == T.NULL:
            return T.NULL
        while node.left != T.NULL:
            node = node.left
        return node						# Return left-most node

    def maximum(T, node=None):
        if node is None:
            node = T.root
        if node == T.NULL:
            return T.NULL
        while node.right != T.NULL:
            node = node.right
        return node						# Return right-most node

    def successor(T,x):
        if x.right != T.NULL:					# Smallest on right subtree
            return T.minimum(x.right)
        y = x.parent
        while y != T.NULL and x == y.right:
            x = y
            y = y.parent
        return y

    def predecessor(T,x):
        if (x.left != T.NULL):					# Greatest on left subtree
            return T.maximum(x.left)
        y = x.parent
        while y != T.NULL and x == y.left:
            x = y
            y = y.parent
        return y


############################################################## MAIN ###########################################################

if __name__ == "__main__":
    tree = RBT()						# Create a red-black tree
	

    print("\nAfter inserting 35:")				# Insert nodes and print
    tree.insert(35)
    tree.printTree()

    print("\nAfter inserting 18:")
    tree.insert(18)
    tree.printTree()

    print("\nAfter inserting 70:")
    tree.insert(70)
    tree.printTree()

    print("\nAfter inserting 26:")
    tree.insert(26)
    tree.printTree()

    print("\nAfter inserting 15:")
    tree.insert(15)
    tree.printTree()

    print("\nAfter inserting 55:")
    tree.insert(55)
    tree.printTree()


    print("\nMax Node: " + str(tree.maximum().item))     	# Maximum
    print("Min Node: " + str(tree.minimum().item))     		# Minimum


    print("\nSearch Result for 26: " +                        	# Search
          str(tree.search(26).item))
    print("Search Result for 10: " + 
          str(tree.search(10).item))

 
    print("\nThe predecessor of 26: " + 			# Predecessor
          str(tree.predecessor(tree.search(26)).item))

    print("The successor of 26: " + 				# Successor
          str(tree.successor(tree.search(26)).item))


    print("\nPreorder traversal:")			        # Tree traversals
    tree.preorder()
    
    print("\n\nInorder traversal:")
    tree.inorder()
    
    print("\n\nPostorder traversal:")
    tree.postorder()


    print("\n\nAfter deleting 26:")				# Delete nodes and print
    tree.delete(26)
    tree.printTree()

    print("\nAfter deleting 70:")
    tree.delete(70)
    tree.printTree()

    print("\nAfter deleting 35:")
    tree.delete(35)
    tree.printTree()

    print("\nAfter deleting 55:")
    tree.delete(55)
    tree.printTree()
	
    print("\nAfter deleting 26:")
    tree.delete(26)
    tree.printTree()

    print("\nAfter deleting 18:")
    tree.delete(18)
    tree.printTree()

    print("\nAfter deleting 15:")
    tree.delete(15)
    tree.printTree()



 





  