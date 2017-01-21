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

public class Queue<Type> {
	public int frontPointer;
	public int rearPointer;
	public ArrayList<Type> queue = new ArrayList<Type>();
	
	public Queue()
	{
		frontPointer = rearPointer = 0;
	}
	
	public boolean Enqueue( Type value )
	{
		queue.add(value);
		rearPointer++;
		
		return true;
	}
	
	public Type Dequeue()
	{
		Type value = queue.get(frontPointer);
		queue.remove(frontPointer);
		rearPointer--;
		
		return value;
	}
	
	public int Size()
	{
		return rearPointer;
	}
	
	public boolean isEmpty()
	{
		if(this.Size() == 0)
			return true;
		
		return false;
	}
}
