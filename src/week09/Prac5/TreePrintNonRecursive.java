package week09.Prac5;

public class TreePrintNonRecursive {

    /**
     * An object in this class is a node in a binary tree
     * in which the nodes contain items of type String.
     */
    static class StrTreeNode {
        String item;  // The item
        StrTreeNode left;  // Pointer to left subtree.
        StrTreeNode right; // Pointer to right subtree.
        StrTreeNode( String str ) {
            // Constructor.  Make a node to contain str.
            item = str;
        }
    } // end class StrTreeNode

    static class TreeQueue {

        /**
         * An object of type Node holds one of the items
         * in the linked list that represents the queue.
         */
        private static class Node {
            StrTreeNode item;
            Node next;
        }

        private Node head = null;  // Points to first Node in the queue.
        // The queue is empty when head is null.

        private Node tail = null;  // Points to last Node in the queue.


         // Add N to the back of the queue.

        void enqueue( StrTreeNode tree ) {
            Node newTail = new Node();  // A Node to hold the new item.
            newTail.item = tree;
            if (head == null) {

                head = newTail;
                tail = newTail;
            }
            else {
                // The new node becomes the new tail of the list.
                // (The head of the list is unaffected.)
                tail.next = newTail;
                tail = newTail;
            }
        }
         // Remove and return the front item in the queue.
         //Throws an IllegalStateException if the queue is empty.

        StrTreeNode dequeue() {
            if ( head == null)
                throw new IllegalStateException("Can not dequeue from an empty queue.");
            StrTreeNode firstItem = head.item;
            head = head.next;  // The previous second item is now first.
            if (head == null) {

                tail = null;
            }
            return firstItem;
        }

        //Return true if the queue is empty, false if contains one or more items

        boolean isEmpty() {
            return (head == null);
        }

    } // end class TreeQueue

    static StrTreeNode root;  // A pointer to the root of the binary tree.

    static void levelOrderPrint(StrTreeNode root) {
        if (root == null)
            return;  // There is nothing to print in an empty tree.
        TreeQueue queue;   // The queue, which will only hold non-null nodes.
        queue = new TreeQueue();
        queue.enqueue(root);
        while ( queue.isEmpty() == false ) {
            StrTreeNode node = queue.dequeue();
            System.out.println( node.item );
            if ( node.left != null )
                queue.enqueue( node.left );
            if ( node.right != null )
                queue.enqueue( node.right );
        }
    } // end levelOrderPrint()



    static void treeInsert(String newItem) {
        if ( root == null ) {
            // The tree is empty.  Set root to point to a new node
            // containing the new item.
            root = new StrTreeNode( newItem );
            return;
        }
        StrTreeNode runner; // Runs down the tree to find a place for newItem.
        runner = root;   // Start at the root.
        while (true) {
            if ( newItem.compareTo(runner.item) < 0 ) {

                if ( runner.left == null ) {
                    runner.left = new StrTreeNode( newItem );
                    return;  // New item has been added to the tree.
                }
                else
                    runner = runner.left;
            }
            else {
                // Since the new item is greater than or equal to the
                // item in runner, it belongs in the right subtree of
                // runner.  If there is an open space at runner.right,
                // add a new node there.  Otherwise, advance runner
                // down one level to the right.
                if ( runner.right == null ) {
                    runner.right = new StrTreeNode( newItem );
                    return;  // New item has been added to the tree.
                }
                else
                    runner = runner.right;
            }
        } // end while
    }  // end treeInsert()

    public static void main(String[] args) {
        treeInsert("joe");
        treeInsert("sandra");
        treeInsert("sancho");
        treeInsert("rashford");
        treeInsert("bruno");
        treeInsert("antony");
        treeInsert("harry");
        treeInsert("luke");
        treeInsert("tyrell");
        levelOrderPrint(root);
    } // end main()

}
