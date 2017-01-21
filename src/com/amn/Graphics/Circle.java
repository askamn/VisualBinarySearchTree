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

package com.amn.Graphics;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.amn.Graphics.DrawablePanel;

public class Circle implements ActionListener
{	
	public int maxWidth = 0;
	public int maxHeight = 0;
	public int width = 0;
	public int height = 0;
	public Graphics2D g;
	
	public int x;
	public int y;
	
	public DrawablePanel panel;
	
	public Circle(DrawablePanel panel, Graphics2D g, int x, int y, int maxWidth, int maxHeight)
	{
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
		this.x = x;
		this.y = y;
		this.g = g;
		
		this.panel = panel;
		
		this.paint();
	}
	
	public void paint()
	{
		g.fillOval(this.x, this.y, this.maxWidth, this.maxHeight);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if( this.width <= this.maxWidth )
		{
			this.width++;
			this.height++;
			
			paint();
			
			this.panel.appObj.frame.repaint();
		}
		else
		{
			panel.drawing = false;
		}
	}
}