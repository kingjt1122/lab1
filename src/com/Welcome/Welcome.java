package com.Welcome;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 *  */
class input
{
    final static Scanner sc=new Scanner(System.in);
}

class MatrixDG {
	String[] mVexs;
	int[][] mMatrix;
	private int vlen;
	/**
	*  */
	MatrixDG(int v) {
		// v is the number of vertexes
		mMatrix = new int[v + 1][v + 1];
		for (int i = 0; i <= v; i++) {
			for (int j = 0; j <= v; j++) {
				mMatrix[i][j] = 0;
			}
		}
		vlen = 0;
	}
	public int getvlen() {
		return vlen;
	}

	public void setvlen(int vlen) {
		this.vlen = vlen;
	}
}

public final class Welcome extends JFrame {

	private static final long serialVersionUID = -2707712944901661771L;

	public Welcome(MatrixDG m) {
		super("GRAPH!");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {

			Object[] v = new Object[100];
			// inserting vertexes:
			int f = -1;
			double k = 1.3;
			for (int i = 0; i < m.getvlen(); i++) {
				String ns = m.mVexs[i].substring(m.mVexs[i].length() - 1);
				// System.out.print(ns+"\n");
				if (!ns.equals("*")) {
					v[i] = graph.insertVertex(parent, null, m.mVexs[i], 500 + Math.sqrt(i) * f, 20 * i, 80, 30,
							"shape=ellipse;perimeter=ellipsePerimeter");

				} else {
					v[i] = graph.insertVertex(parent, null, m.mVexs[i], 500 + Math.sqrt(i) * f, 30 * i, 80, 30,
							"shape=ellipse;strokeColor=red;fillColor=green");
				}
				f = -f;
				k = k + 0.05;
			}
			// inserting edges:
			for (int i = 0; i < m.getvlen(); i++) {
				for (int j = 0; j < m.getvlen(); j++) {
					if (m.mMatrix[i][j] != MAXLEN) {
						if (m.mVexs[i].substring(m.mVexs[i].length() - 1).equals("*")
								&& m.mVexs[j].substring(m.mVexs[j].length() - 1).equals("*")
								&& !(m.mVexs[i].substring(m.mVexs[i].length() - 2).equals("**"))) {
							graph.insertEdge(parent, null, m.mMatrix[i][j], v[i], v[j], "strokeColor=lightgreen");
						} else {
							graph.insertEdge(parent, null, m.mMatrix[i][j], v[i], v[j]);
						}
					}
				}
			}

			// graph.insertEdge(parent, null, "Edge", v1, v2);
		} finally {
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}
	public static int MAXLEN = 999;
	public static void main(String[] args) // main
	{
		
		String filename = null;
		MatrixDG mtx;
		

		Scanner sc = new Scanner(System.in);
		System.out.println("Input the File:");
		filename = sc.nextLine();
		mtx = createDirectedGraph(filename);

		// show g

		String choice = "0";

		System.out.println(
				"1:show the Graph.  2:search bridge words.  3:build new text.  4:shortest path.  5:random walk\n");
		while(!choice.equals("z")){
		choice = sc.nextLine();
		if (choice.equals("1")) {
			showDirectedGraph(mtx);
			System.out.println("Nodes:\n");
			for (int x = 0; x < mtx.getvlen(); x++) {
				System.out.print(mtx.mVexs[x] + " ");
			}

		} else if (choice.equals("2")) {
			String[] strl = new String[mtx.mVexs.length];
			String word1 = null;
			String word2 = null;
			System.out.print("The first word:");
			word1 = sc.nextLine();
			System.out.print("The second word:");
			word2 = sc.nextLine();
			int flag1 = 0;
			int flag2 = 0;
			String multi = "";
			for (int i = 0; i < mtx.mVexs.length; i++) {
				if (word1.equals(mtx.mVexs[i])) {
					flag1 = 1;
				}
				if (word2.equals(mtx.mVexs[i])) {
					flag2 = 1;
				}
			}
			if (flag1 == 0 && flag2 == 0) {
				System.out.println("No \"" + word1 + "\" and \"" + word2 + "\" in the Graph!");
			} else if (flag1 == 0 && flag2 != 0) {
				System.out.println("No \"" + word1 + "\" in the Graph!");
			} else if (flag2 == 0 && flag1 != 0) {
				System.out.println("No \"" + word2 + "\" in the Graph!");
			} else {

				String reword = queryBridgeWords(mtx, word1, word2);
				strl = reword.split("\\s+");
				if (strl.length == 0) {
					System.out.println("No bridge words from \"" + word1 + "\" to\"" + word2 + "\" !");
				} else if (strl.length == 2) {
					System.out.println("The bridge words from \"" + word1 + "\" to\"" + word2 + "\" is:"
							+ reword.replaceAll("\\s+", ""));
				} else {
					for (int i = 1; i < strl.length - 1; i++) {
						multi += strl[i] + ", ";
					}
					multi += "and " + strl[strl.length - 1] + ".";
					System.out.println("The bridge words from \"" + word1 + "\" to\"" + word2 + "\" are:" + multi);

				}

			}
			System.out.println("over!");

		} else if ("3".equals(choice)) {
			// bridge Return
			String newstr = null;
			System.out.print("Input the new String:");
			newstr = sc.nextLine();
			String bridgeStr = generateNewText(mtx, newstr);
			System.out.println(bridgeStr);
			System.out.println("over!");
		} else if (choice.equals("4")) {
			System.out.print("input the starting point and the terminal");
			System.out.print("The starting point:");
			String word1 = "", word2 = "", s = "";
			word1 = sc.nextLine();
			System.out.print("The terminal:");
			word2 = sc.nextLine();
			s = calcShortestPath(mtx, word1, word2);
			showDirectedGraph(mtx);
			System.out.println("The shortest path is:" + s);
			mtx = createDirectedGraph(filename);
		} else if (choice.equals("5")) {
			// System.out.println("over!");
			randomWalk(mtx);
		}
		}
		sc.close();
	} // main ends

	// functions:

	private static String randomWalk(MatrixDG g) {
		String s = "";
		Random random = new Random();
		int r = random.nextInt(g.getvlen() - 1); // the starting vertex
		s = s + g.mVexs[r];
		System.out.print("random walk starts. input 's' to stop:\n" + g.mVexs[r]);
		int next = r, cch = 0, ch[];
		ch = new int[g.getvlen()];
		int[] rpt = new int[g.getvlen()];
		for (int i = 0; i < g.getvlen(); i++) {
			ch[i] = 0;
			rpt[i] = -1;
		}
//		Scanner sc = new Scanner(System.in);
		int cc = 1;
		rpt[0] = r;
		while (next != -1) {

			cch = 0;
			// System.out.print(next);
			String st = input.sc.nextLine();
			// System.out.print("1"+st+"1");
			if (st.equals("s")) {
				System.out.print("STOP");
				return s;
			}
			for (int i = 0; i < g.getvlen(); i++) {
				if (g.mMatrix[next][i] != MAXLEN) {
					ch[cch] = i;
					cch++;
				}
			}
			if (cch == 0) {
				System.out.print("over. no where to go\n");
				return s;
			}
			int c = random.nextInt(cch);
			for (int x = 0; x < g.getvlen(); x++) {
				if (rpt[x] == ch[c]) {
					System.out.print("edge repeated, quit\n");
					s = s + " -> " + g.mVexs[ch[c]];
					return s;
				}
			}
			next = ch[c];
			rpt[cc] = next;
			cc++;
			s = s + " -> " + g.mVexs[next];

			System.out.print(" -> " + g.mVexs[next]);
		}
		return s;
	}

	private static String calcShortestPath(MatrixDG g, String word1, String word2) {
		// in the algorithm, the number MAXLEN means "false"
		for (int i = 0; i < g.getvlen(); i++) {
			for (int j = 0; j < g.getvlen(); j++) {
				if (g.mMatrix[i][j] == 0) {
					g.mMatrix[i][j] = MAXLEN;
				}
			}
		}

		int w1, w2;
		w1 = getposition(word1, g.mVexs);
		w2 = getposition(word2, g.mVexs);
		// System.out.print(w1+" "+w2);
		int[] d = new int[g.getvlen()];
		int[] p = new int[g.getvlen()];
		int[] f = new int[g.getvlen()];

		for (int i = 0; i < g.getvlen(); i++) {
			if (g.mMatrix[w1][i] == MAXLEN) {
			    d[i] = MAXLEN; // infinite long, not reached yet
                p[i] = MAXLEN; // no parent node
			
			} else {
			    d[i] = g.mMatrix[w1][i];
                p[i] = w1;
			}

			f[i] = 0;
		}
		d[w1] = 0;
		f[w1] = 1;
		int min = MAXLEN;
		int m = MAXLEN;
		for (int i = 0; i < g.getvlen() - 1; i++) {// dijkstra
			min = MAXLEN;
			for (int j = 0; j < g.getvlen(); j++) {
				if (f[j] == 0) {
					if (d[j] < min) {
						m = j;
						min = d[j];
					}
				}
			}
			// System.out.print(min+"\n");
			if (min < MAXLEN) {
				f[m] = 1;
			} else if (m == MAXLEN) {
				System.out.print("not reachable\n");
				return "NULL";
			}
			for (int j = 0; j < g.getvlen(); j++) {
				if (f[j] == 0 && g.mMatrix[m][j] + d[m] < d[j]) {
					d[j] = g.mMatrix[m][j] + d[m];
					p[j] = m;
				}
			}
		} // end dijkstra
		int i = w2, c = 0;
		String[] path = new String[100];
		for (int j = 0; j < 100; j++) {
			path[j] = "";
		}
		while (i != w1) {
			path[c] = g.mVexs[i];
			g.mVexs[i] = g.mVexs[i] + " *";
			c++;
			i = p[i];
		}
		g.mVexs[w2] = g.mVexs[w2] + "*";
		path[c + 1] = g.mVexs[w1];
		g.mVexs[w1] = g.mVexs[w1] + " *";
		String s = "";
		for (int j = 99; j >= 0; j--) {
			if (!path[j].equals("") && j != 0) {
				s = s + path[j] + " -> ";
				// System.out.print(path[j]+" -> ");
			}
			if (j == 0) {
				s = s + path[j];
				// System.out.print(path[j]);
			}
		}
		return s;
	}

	private static void showDirectedGraph(MatrixDG g) {
		Welcome frame = new Welcome(g);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}

	private static int getposition(String s, String[] sw) // get the position of
															// s in array sw
	{
		for (int i = 0; i < sw.length; i++) {
			if (s.equals(sw[i])) {
				return i;
			}
		}
		return -1;
	}

	private static MatrixDG createDirectedGraph(String filename) {														// createDirectedGraph
		String s;
		String s2 = "";
		try{ // reading file
			FileReader inFile = new FileReader(filename);
			BufferedReader buff = new BufferedReader(inFile);
			boolean endOfFile = false;
			while (!endOfFile) {
				s = buff.readLine(); // reading line by line to s
				if (s == null) {
					endOfFile = true;
				} else {
					s2 = s2 + s + " ";
				}
			}
			buff.close();
		} catch (IOException e) {
			System.out.print("An Error Occurred : " + e.toString());
		}// reading done; converting begins (s2 to s3)
		String s3 = "";
		int f = 0;
		s2 = s2.toLowerCase(Locale.ENGLISH); // to lower case
		for (int i = 0; i < s2.length() - 1; i++) {
			char c = s2.charAt(i);
			final int b = (int) c;
			if (97 <= b && b <= 122) {
				s3 = s3 + c;
				f = 0;
			} else if (b == 32||b == 33 || b == 34 || b == 39 || b == 40 || b == 41 || b == 44 || b == 45 || b == 46 || b == 58
					|| b == 59 || b == 63) {
				if (f == 0) {
					s3 +=' ';
					f = 1;
				}
			}
		}// converting to one space
		// String s4; //s4 has only one space between each words
		// extracting words
		String[] words = new String[100]; // all different
		String[] words1 = new String[100]; // all of the words; some are the									// same
		for (int i = 0; i < 100; i++) { // initializing
			words1[i] = "";
			words[i] = "";
		}
		int i = 0;
		int j = 0;
		while (i < s3.length()) {
			char c = s3.charAt(i);
			if (c != ' ') {
				words1[j] = words1[j] + c;
				i++;
			} else {
				i++;
				j++;
			}
		}
		i = 0;
		j = 0;
		while (i < s3.length()) {
			char c = s3.charAt(i);
			finder: if (c != ' ') {
				words[j] = words[j] + c;
				i++;
			} else {
				if (j > 0) {
					for (int k = 0; k < j; k++) {
						if (words[k].equals(words[j])) {
							i++;
							words[j] = "";
							break finder;
						}
					}
				}
				i++;
				j++;
			}
		}
		for (int k = 0; k < words.length; k++) {
			if (words[k].equals(words[words.length - 1])) {
				i++;
				words[j] = "";
				// break finder;
			}
		}
		for (i = 0; i < 20; i++) {
			if (!words[i].equals(""))// strange??
			{
				System.out.print(words[i] + "\n");
			}
		}
		// DAG
		int diffWordsNum = 0, cw2 = 0; // diffWordsNum is the number of differentwords ; cw2 is
		for (int t = 0; t < words.length; t++) {
			if (!words[t].equals("") ) {
				diffWordsNum++;
			} else {
				break;
			}
		}
		for (int t = 0; t < words.length; t++) {
			if (words1[t].equals("") ) {
				break;	
			} else {
				cw2++;
			}
		} // 用string.length()
		int vlen = diffWordsNum;
		for (i = 0; i < s3.length(); i++) {
			char c1 = s3.charAt(i);
			if (c1 == ' ') {
			}
		}
		String[] vexs = new String[100];
		for (i = 0; i < words.length; i++) {
			vexs[i] = words[i];
		}
		MatrixDG aMATRIX; // creating matrix
		aMATRIX = new MatrixDG(vlen);
		aMATRIX.setvlen(diffWordsNum);
		aMATRIX.mVexs = new String[vlen];
//		for (int m = 0; m < aMATRIX.mVexs.length; m++) // initializing vertexes
//		{
//			aMATRIX.mVexs[m] = words[m];// System.out.print("!: "+aMATRIX.mVexs[m]+"\n");
//		}		
		System.arraycopy(words, 0, aMATRIX.mVexs,0,aMATRIX.mVexs.length);
		aMATRIX.mMatrix = new int[vlen][vlen];
		int p1, p2;
		for (i = 0; i < cw2; i++) {
			p1 = getposition(words1[i], words);
			p2 = getposition(words1[i + 1], words);
			// System.out.print(p1+" "+p2+"\n");
			if (!words1[i].equals("") && !words1[i + 1].equals("")) {
				aMATRIX.mMatrix[p1][p2]++;
			}
		}
		for (int x = 0; x < aMATRIX.getvlen(); x++) {
			for (int xx = 0; xx < aMATRIX.getvlen(); xx++) {
				if (aMATRIX.mMatrix[x][xx] == 0) {
					aMATRIX.mMatrix[x][xx] = MAXLEN;
				}
			}
		}
		return aMATRIX;
	}
	
	private static String queryBridgeWords(MatrixDG g, String word1, String word2){// 鏌ヨ妗ユ帴璇�
		String s = "";
		int i, i1;
		int j, k, f;

		for (int m = 0; m < g.mVexs.length; m++) {
			for (int n = 0; n < g.mVexs.length; n++) {
				if (g.mMatrix[m][n] != MAXLEN && g.mMatrix[m][n] > 1) 
					{
						g.mMatrix[m][n] = 1;
					}
			}
		}
		String[] strlist = new String[g.mVexs.length];
		for (i = 0; i < g.mVexs.length; i++) {
			strlist[i] = "";
		}
		int[] list = new int[g.mVexs.length];
		for (i = 0; i < g.mVexs.length; i++) {
			if (g.mVexs[i].equals(word1)) {
				for (i1 = 0; i1 < g.mVexs.length; i1++) {
					if (g.mMatrix[i][i1] == 1) {
						list[i1] = 1;
					} else {
						list[i1] = 0;
					}
				}

			}

		}
		for (j = 0; j < list.length; j++) {
			if (list[j] == 1) {
				for (k = 0; k < g.mVexs.length; k++) {
					if (g.mMatrix[j][k] == 1 && g.mVexs[k].equals(word2)) {
						strlist[j] = g.mVexs[j];
					}
				}
			}
		}
		for (f = 0; f < g.mVexs.length; f++) {
			s += " " + strlist[f];
		}

		return s;
	}

	private static String generateNewText(MatrixDG g, String inputText) {//
		String s = "";
		String[] strlist = inputText.split(" ");
		String[] insert = new String[strlist.length - 1];
		String[] choicestr = new String[insert.length];

		for (int i = 0; i < strlist.length - 1; i++) {
			insert[i] = queryBridgeWords(g, strlist[i], strlist[i + 1]);
		}
		Random random = new Random();
		// choicestr
		for (int j = 0; j < insert.length; j++) {
			insert[j] = insert[j].replaceAll("\\s+", " ");
			if (!insert[j].equals(" ")) {
				//
				String[] splitTemp = insert[j].split("\\s+");
				//
				if (splitTemp[0].equals("")) {
					
					int result = random.nextInt(splitTemp.length - 1) + 1;
					insert[j] = splitTemp[result];
				} else {
					insert[j] = splitTemp[0];
				}
			}
			choicestr[j] = insert[j];
		}

		for (int i = 0; i < choicestr.length; i++) {
			if (choicestr[i].equals(" ")) {
				s += strlist[i] + " ";
				} else {
				s += strlist[i] + " " + choicestr[i] + " ";
			}
		}
		s += strlist[strlist.length - 1];
		s = s.replaceAll("\\s+", " ");
		return s;
	}
}
