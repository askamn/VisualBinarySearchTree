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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.amn.Graphics.Circle;
import com.amn.Main;

public class DrawablePanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	public int i = 0;
	public int xOffset = 0;
	public int yOffset = 0;
	
	public static final int NODE_HEIGHT= 40;
	public static final int NODE_WIDTH= 40;
	public static final int FONT_SIZE = 15;
	public static final int NODE_PADDING = 5;
	
	public static final Color FONT_COLOR = new Color(255,255,255);
	public static final Color NODE_COLOR = new Color(52, 152, 219);
	
	public Main appObj;
	
	public boolean drawing = false;
	
	public DrawablePanel(Main appObj)
	{
		this.appObj = appObj;
	}
	
	public void drawEdges(Graphics g)
	{
		Graphics2D graphics2D = (Graphics2D)g;
		
		this.xOffset = 0;
		this.yOffset = this.getHeight() / 4;
		
		int level = 1;
		int nodes = 1;
		int distanceBetweenNodes = (int) (Math.pow(2, appObj.levels - 1) * ( DrawablePanel.NODE_WIDTH + DrawablePanel.NODE_PADDING ));
		
		int previousLevelX = this.getWidth() / 2;
		int previousLevelX_secondNode = 0;
		int previousLevelY = DrawablePanel.NODE_HEIGHT;
		
		for( int j = 0; j < appObj.index;  )
		{
			int xLevelOffset = 0;
			int[] firstNodeCoords = null;
			int[] secondNodeCoords = null;
			int nodesEncountered = 0;
			int distance = ( previousLevelX_secondNode - previousLevelX );
			for( int k = 0; k < nodes && j < appObj.index; k++, j++ )
			{				
				nodesEncountered++;
				if( firstNodeCoords == null && secondNodeCoords == null )
				{
					firstNodeCoords = new int[2];
					firstNodeCoords[0] = xLevelOffset + this.xOffset + this.getWidth()/2 - 7;
					firstNodeCoords[1] = this.yOffset + ( DrawablePanel.NODE_HEIGHT / 2 + 6 );
				}
				else if(firstNodeCoords != null && secondNodeCoords == null)
				{
					secondNodeCoords = new int[2];
					secondNodeCoords[0] = xLevelOffset + this.xOffset + this.getWidth()/2 - 7;
					secondNodeCoords[1] = this.yOffset + ( DrawablePanel.NODE_HEIGHT / 2 + 6 );
				}
				
				if( nodesEncountered > 2 )
				{
					nodesEncountered = 1;
					previousLevelX += distance;
				}
				
				if( appObj.nodes.get( j ) != -1 )
				{
					graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
					graphics2D.setColor(Color.BLACK);
					
					// Draw the edge
					if( level > 1 )
					{
						int startX = xLevelOffset + this.xOffset + this.getWidth()/2 - 7;
						int startY = this.yOffset + ( DrawablePanel.NODE_HEIGHT / 2 + 6 );
						
						int endX = previousLevelX;
						int endY = previousLevelY;
						
						graphics2D.drawLine( startX, startY, endX, endY);
					}
				}
				
				xLevelOffset += distanceBetweenNodes;
			}
			
			if( j > 1 )
			{
				distanceBetweenNodes /= 2;
			}
			
			nodes = nodes << 1;
			
			previousLevelX = firstNodeCoords[0];
			
			if( secondNodeCoords != null )
				previousLevelX_secondNode = secondNodeCoords[0];
			previousLevelY = this.yOffset + ( DrawablePanel.NODE_HEIGHT / 2 + 6 ); 
			
			if( j == 1 )
			{
				this.xOffset = -1 * distanceBetweenNodes / 2;
			}
			else
			{
				this.xOffset -= (distanceBetweenNodes / 2);
			}
			
			this.yOffset += DrawablePanel.NODE_HEIGHT + DrawablePanel.NODE_PADDING;
			level++;
		}
	}
	
	public void drawNodes(Graphics g)
	{		
		Graphics2D graphics2D = (Graphics2D)g;
		
		this.xOffset = 0;
		this.yOffset = this.getHeight() / 4;
		
		int nodes = 1;
		int distanceBetweenNodes = (int) (Math.pow(2, appObj.levels - 1) * ( DrawablePanel.NODE_WIDTH + DrawablePanel.NODE_PADDING ));

		for( int j = 0; j < appObj.index;  )
		{
			int xLevelOffset = 0;

			for( int k = 0; k < nodes && j < appObj.index; k++, j++ )
			{				
				if( appObj.nodes.get( j ) != -1 )
				{
					graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);		
					graphics2D.setColor( DrawablePanel.NODE_COLOR );
					
					int x = xLevelOffset + this.xOffset + this.getWidth()/2 - (DrawablePanel.NODE_WIDTH / 2);
					int y = this.yOffset;
					
					this.drawing = true;
					new Circle(this, graphics2D, x, y, DrawablePanel.NODE_WIDTH, DrawablePanel.NODE_HEIGHT);
					
					graphics2D.setColor( DrawablePanel.FONT_COLOR );
					graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, DrawablePanel.FONT_SIZE));
					graphics2D.drawString( String.valueOf( appObj.nodes.get( j ) ), xLevelOffset + this.xOffset + this.getWidth()/2 - getCharDistance(appObj.nodes.get( j )), this.yOffset + ( DrawablePanel.NODE_HEIGHT / 2 + 6 ) );
				}
				
				xLevelOffset += distanceBetweenNodes;
			}
			
			if( j > 1 )
			{
				distanceBetweenNodes /= 2;
			}
			
			nodes = nodes << 1;
			
			if( j == 1 )
			{
				this.xOffset = -1 * distanceBetweenNodes / 2;
			}
			else
			{
				this.xOffset -= (distanceBetweenNodes / 2);
			}
			
			this.yOffset += DrawablePanel.NODE_HEIGHT + DrawablePanel.NODE_PADDING;
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		this.drawEdges(g);
		this.drawNodes(g);
		
		Font font = new Font( Font.SANS_SERIF, Font.BOLD, 25 );
		this.drawString( "BINARY SEARCH TREE", g, 1, font, 0, 40, new Color(255,255,255) );
	}
	
	/* TODO: Implement Alignment */
	public void drawString(String str, Graphics g, int Alignment, Font font, int x, int y, Color color)
	{
		Graphics2D graphics = (Graphics2D)g;
		
		graphics.setColor(color);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		FontMetrics metrics = g.getFontMetrics(font);
		
		graphics.setFont( font );
		graphics.drawString( str, getWidth()/2 - metrics.stringWidth(str) / 2 + x, y );
	}
	
	public int getCharDistance(int value)
	{
		int numDigits = 0;
		
		// For the minus sign + the loop won't run if value is zero
		if( value <= 0 )
		{
			numDigits = 1;
		}
		
		while(value != 0)
		{
			value /= 10;
			numDigits++;
		}
		
		return (int) Math.ceil( 3.5 * numDigits );
	}
}