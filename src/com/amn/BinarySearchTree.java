/**
 * Binary Search Tree Visual Simulator
 * Copyright 2016-2017
 * All rights reserved.
 * 
 * Use, reproduction, distribution, and modification of this code is subject to the terms and
 * conditions of the MIT license, available in the LICENSE file
 * 
 * @author 	AmN (askamn)
 * @version 1.3
 * @website http://askamn.com | http://askamn.github.io
 */

package com.amn;

import java.util.ArrayList;

public class BinarySearchTree 
{
	public int numberOfNodes = 0;
	public int levels = 1;
    public Node root;
    public Node star;
    
    public BinarySearchTree()
    {
        root = new Node();
    }
    
    void Insert(int[] items)
    {
    	for( int item : items )
    	{
    		this.Insert(item);
    	}
    }
    
    void Insert(int item)
    {
    	this.numberOfNodes++;
    	
        if( root.data == Node.EMPTY )
        {
            root.setData(item);
        }
        
        else
        {
            Node temp   = new Node();
            Node parent = new Node();
            
            star = new Node(item);
            temp = parent = root ;
            
            int depth = 1;
            while(temp != null)
            {
            	if( depth >= levels )
            		levels++;
            	
                if(temp.data > item )
                {
                    parent = temp ;
                    temp = temp.left;
                }
                else
                {
                    parent = temp ;
                    temp = temp.right ;
                }
                
                depth++;
            }

            if(parent.data > item)
            {
                parent.left=star;
            }
            else
            {
                parent.right=star;
            }
        }
    }
    
    public ArrayList<Integer> nodes;
    
    public ArrayList<Integer> getNodes()
    {
    	Queue<Node> queue = new Queue<Node>();
    	
    	this.nodes = new ArrayList<Integer>();
    	
    	if( root == null )
    	{
    		return this.nodes;
    	}
    	
    	queue.Enqueue( root );
    	int traversedNodes = 0;
    	while(traversedNodes < this.numberOfNodes)
    	{
    		Node temp_node = queue.Dequeue();
    		
    		if( temp_node == null )
    		{
    			this.nodes.add(-1);
    			
    			// We need to do this... Otherwise, below, our queue will throw NullPointerException
    			temp_node = new Node();
    		}
    		else
    		{
    			this.nodes.add(temp_node.data);
    			traversedNodes++;
    		}
    		
    		queue.Enqueue(temp_node.left);
    		queue.Enqueue(temp_node.right);
    	}
    	
    	return this.nodes; 
    }
    
    public void preOrderTraversal(Node root)
    {
    	nodes.add( ( root != null ) ? root.data : -1 );    	
        if( root != null ) 
        {
        	preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }
}

class Node {
    public Node left;
    public Node right;
    
    int data;
    
    public final static int EMPTY = -999999;
    
    public Node()
    {
        left = right = null;
        data = EMPTY;
    }
    
    public Node( int value )
    {
        left = right = null;
        this.data = value;
    }
    
    public void setLeft(Node left)
    {
       this.left = left;
    }
    
    public void setRight(Node right)
    {
    	this.right = right;
    }
    
    public void setData(int value)
    {
        data = value;
    }
    
    public Node getLeft()
    {
        return left;
    }
    public Node getRight()
    {
        return right;
    }
    
    public int getData()
    {
        return data;
    }
}