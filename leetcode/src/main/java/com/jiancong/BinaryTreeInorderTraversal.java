package com.jiancong;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jiancongchen on 2021/5/6
 */
public class BinaryTreeInorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null){
            return result;
        }
        if(root.left != null){
            result.addAll(inorderTraversal(root.left));
        }
        result.add(root.val);
        if (root.right != null){
            result.addAll(inorderTraversal(root.right));
        }
        return result;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node2.left = node3;
        TreeNode node1 = new TreeNode(1);
        node1.right = node2;
        List<Integer> list = inorderTraversal(node1);
        System.out.println(list);
    }
}
