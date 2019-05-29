// CSE 143 Final Preparation Answers (Binary Trees) - Week 9 

public class IntTree {
    public IntTreeNode overallRoot;

    // post: constructs an empty tree
    public IntTree() {
        overallRoot = null;
    }

    // pre : max > 0
    // post: constructs a sequential tree with given number of
    //       nodes
    public IntTree(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }
    
    // #1 evenLevels
    public void evenLevels() {
        overallRoot = evenLevels(overallRoot, 1);
    }

    private IntTreeNode evenLevels(IntTreeNode root, int level) {
        if (root != null) {
            if (root.left == null && root.right == null && (level % 2) == 1) {
                root  = null;
            } else {
                root.left = evenLevels(root.left, level + 1);
                root.right = evenLevels(root.right, level + 1);
            }
        }
        return root;
    }

    // #2 tighten
    public void tighten() {
        overallRoot = tighten(overallRoot);
    }

    private IntTreeNode tighten(IntTreeNode root) {
        if (root != null) {
            root.left = tighten(root.left);
            root.right= tighten(root.right);
            if (root.left == null && root.right != null) {
                root = root.right; 
            } else if (root.left != null && root.right == null) {
                root = root.left;
            }
        }
        return root;
    }

    // #3 doubleIt
    public void doubleIt() {
        overallRoot = doubleIt(overallRoot);
    }

    private IntTreeNode doubleIt(IntTreeNode root) {
        if (root != null) {
            root.left = doubleIt(root.left);
            root.right = doubleIt(root.right);
            root.left = new IntTreeNode(root.data * 2, root.left, null); 
            root.right = new IntTreeNode(root.data * 2, null, root.right);
        }   
        return root;
    }


    /*************************************************************/
    /**********************IntTree methods from class*************/
    // post: returns a sequential tree with n as its root unless
    //       n is greater than max, in which case it returns an
    //       empty tree
    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntTreeNode(n, buildTree(2 * n, max),
                                   buildTree(2 * n + 1, max));
        }
    }

    // pre : tree is a binary search tree
    // post: value is added to overall tree so as to preserve the binary search
    //       tree property
    public void add(int value) {
        overallRoot = add(overallRoot, value);
    }

    // post: value is added to given binary search tree so as to preserve the
    //       binary search tree property
    private IntTreeNode add(IntTreeNode root, int value) {
        if (root == null) {
            root = new IntTreeNode(value);
        } else if (value <= root.data) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
	}
        return root;
    }

    // post: prints the tree contents using a preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    // post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    // post: prints the tree contents using an inorder traversal
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    // post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    // post: prints the tree contents, one per line, following an
    //       inorder traversal and using indentation to indicate node
    //       depth; prints right to left so that it looks correct when
    //       the output is rotated; prints "empty" for an empty tree
    public void printSideways() {
        if (overallRoot == null) {
            System.out.println("empty tree");
        } else {
            printSideways(overallRoot, 0);
        }
    }

    // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }  
        }
    }
}
