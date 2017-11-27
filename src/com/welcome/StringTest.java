package com.welcome;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class StringTest {
	String filename="/Users/Veronique/Desktop/a.txt";
	MatrixDG mtx=createGraph.createDirectedGraph(filename)
	

	@Test
	public void test() {
		assertEquals("  are   ",bridgewords.queryBridgeWords(mtx, "you", "here"));
	}
	
}