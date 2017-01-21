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

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.amn.Graphics.DrawablePanel;

public class Main implements ActionListener
{
	public static final boolean DEBUG = false;
	
	// Window Parameters
	public int 			windowWidth = 800;
	public int 			windowHeight = 600;
	public String		windowTitle = "Binary Search Tree Simulator";
	
	public JFrame 		frame;
	public JTextField 	value;
	public JButton 		insert;
	public JPanel 		Controls;
	public JPanel 		DrawPanel;
	
	public int index;
	public int levels;
	
	public BinarySearchTree BST = new BinarySearchTree();
	public ArrayList<Integer> nodes = new ArrayList<Integer>();
	
	public static void main(String[] args)
	{
		Main app = new Main();
		app.Run();
	}
	
	public void Run() 
	{
		if( Main.DEBUG )
		{
			BST.Insert(new int[]{50, 40, 80, 43, 60, 95, 105, 55, 20, 10, 45, 41});
		}
		
		this.nodes = this.BST.getNodes();
		this.index = this.nodes.size();
		this.levels = this.BST.levels;
		
		this.frame = new JFrame();
		this.frame.setTitle(this.windowTitle);
		this.frame.setBackground(new Color(52, 73, 94));
		
		// Textbox
		this.value = new JTextField( "", 15 );
		value.setBackground(new Color(41, 128, 185));
		value.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219)));
		value.setForeground(new Color(255, 255, 255));
		value.setPreferredSize(new Dimension(150, 30));
		value.setCaretColor(new Color(255,255,255));
		value.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					updateTree(e);
					value.setText("");
				}
			}
			public void keyTyped(KeyEvent e) {}
		});
		
		// Insert Button
		this.insert = new JButton("Insert");
		insert.setBackground(new Color(41, 128, 185));
		insert.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219)));
		insert.setForeground(new Color(255,255,255));
		insert.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		insert.setPreferredSize(new Dimension(100, 30));
		insert.setFocusPainted(false);
				
		insert.addActionListener( this );
	
		this.Controls = new JPanel(new FlowLayout());
		this.Controls.add(value);
		this.Controls.add(insert);
		
		DrawPanel = new DrawablePanel(this);
		DrawPanel.setBackground( new Color(52, 73, 94) );
		this.Controls.setBackground( new Color(52, 73, 94) );

		this.frame.add(DrawPanel, BorderLayout.CENTER);
		this.frame.add(this.Controls, BorderLayout.SOUTH);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize( this.windowWidth, this.windowHeight );
		
		// Center the window
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		this.frame.validate();
		this.frame.repaint();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		updateTree(e);
	}
	
	public void updateTree(AWTEvent e)
	{
		int value = Integer.parseInt( this.value.getText().trim() );
		
		this.BST.Insert(value);
		this.nodes = BST.getNodes();
		this.index = this.nodes.size();
		this.levels = this.BST.levels;
		this.DrawPanel.repaint();
		
		if( Main.DEBUG )
		{
			for(int v : this.nodes)
			{
				System.out.print( v + " -> " );
			}
			
			System.out.println();
		}
		
		this.frame.validate();
		this.frame.repaint();
	}
}
