package com.welcome;

import javax.swing.JFrame;

public class showgraph {
	public static void showDirectedGraph(MatrixDG G)
	{
		welcome frame = new welcome(G);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}
}
