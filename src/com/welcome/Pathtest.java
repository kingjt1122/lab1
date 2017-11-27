package com.welcome;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.lang.*;
import java.lang.System;
import java.lang.String;
import java.util.Scanner;
import java.io .*;

import javax.swing.JFrame;

import org.junit.Test;

public class Pathtest {
	String filename="c:\\b.txt";
	MatrixDG mtx=creategraph.createDirectedGraph(filename);
	@Test
	public void test() {
		//System.out.println(path.calcShortestPath(mtx, "qwe", "asd"));
		assertEquals("0",path.calcShortestPath(mtx, "a", "a"));
	}
	
	
	private static int getposition(String s, String[] sw)		//get the position of s in array sw
	{
		for (int i=0;i<sw.length;i++){
			if(s.equals(sw[i])) return i;
		}
		return -1;
	}
}

