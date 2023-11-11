package com.piotrkafel.meta;

public class LowestCommonAncestor {

    /**
     * This solution works only under assumption that both p and q are always present in the tree.
     *
     * The intuition is that we search the tree until we find p or q. If one of them is not in the sub-tree of the other
     * than we will reach line 19 and the current node will be lowest common ancestor. If we do not run into situation
     * where left and right node is not null then it means that p \ q is the lowest common ancestor.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;

        TreeNode left =lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if(left != null && right != null) return root;
        return left == null ? right : left;
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
