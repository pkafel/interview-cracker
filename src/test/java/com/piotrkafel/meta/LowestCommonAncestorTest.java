package com.piotrkafel.meta;

import com.piotrkafel.meta.LowestCommonAncestor.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LowestCommonAncestorTest {

    @Test
    public void test() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);

        Assertions.assertEquals(
                new TreeNode(1).val,
                new LowestCommonAncestor().lowestCommonAncestor(
                        tree, tree, tree.left).val
        );
    }

    @Test
    public void test2() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);

        Assertions.assertEquals(
                new TreeNode(1).val,
                new LowestCommonAncestor().lowestCommonAncestor(
                        tree, tree.right, tree.left).val
        );
    }
}
