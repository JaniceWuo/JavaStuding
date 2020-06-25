package com.janice.sportmanage.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//  Definition for a binary tree node.


public class Code {

    // Encodes a tree to a single string.
    private String res;
    public String serialize(TreeNode root) {
        if(root == null) return "[]";
        LinkedList<Integer> tree = new LinkedList<>();
        res = "[" + root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left!=null){
                queue.offer(node.left);
                tree.add(node.left.val);
                // res += "," + node.left.val;
            }
            else{
                tree.add(null);
                // res += ",null";
            }

            if(node.right!=null){
                queue.offer(node.right);
                tree.add(node.right.val);
                // res += "," + node.right.val;
            }else{
                tree.add(null);
                // res += ",null";
            }
        }
        while(tree.size()!=0 && tree.getLast()==null){
            tree.removeLast();
        }
        for(int i = 1; i < tree.size(); i++){
            res += "," + tree.get(i).toString();
        }

        res += "]";
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 2) return null;
        data = data.substring(1,data.length()-1);

        String[] Num = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(Num[0]));
        int i = 1;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(Num[i]);
            if(Num[i].equals("null")){
                node.left = null;

            }else{
                node.left = new TreeNode(Integer.valueOf(Num[i]));
                queue.offer(node.left);
            }
            i++;
            if(Num[i].equals("null")){
                node.right = null;
            }else{
                node.right = new TreeNode(Integer.valueOf(Num[i]));
                queue.offer(node.right);
            }
        }
//        System.out.println(data);
        System.out.println(root);
        return root;
    }

    public static void main(String[] args) {
        Code code = new Code();
        code.deserialize("[1,2,3,null,null,4,5,null,null,null,null]");
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
