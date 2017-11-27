<<<<<<< HEAD
package com.welcome;
import java.util.Scanner;
import java.util.Random;
import java.lang.*;
import java.lang.System;
import java.lang.String;
import java.util.Scanner;
import java.io .*;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

class MatrixDG {

    private String[] mVexs=null;       // 
    private int[][] mMatrix=null;    // 
    MatrixDG(int v)	//v is the number of vertexes
    {
    	mMatrix=new int[v+1][v+1];
    	mVexs=new String[v];
    	for (int i=0;i<=v;i++)
    	{
    		for(int j=0;j<=v;j++)
    		{
    			mMatrix[i][j]=0;
    			
    		}
    	}
    }
    private int vlen=0;
    public int getvlen ()
    {
    	return vlen;
    }
    public String getmVexs(int i)
    {
    	return mVexs[i];
    }
    public int getmMatrix(int i,int j)
    {
    	return mMatrix[i][j];
    }
    public String[] getV()
    {
    	return mVexs;
    }
    public void setmatrix(int i,int j,int x)
    {
    	mMatrix[i][j]=x;
    }
    public void setvlen(int i)
    {
    	vlen=i;
    }
    public void setmVexs(int i,String s)
    {
    	try{
    		mVexs[i]=s;
    	}
    	catch(Exception e){
    		return;
    	}
    	
    }
}
public class welcome extends JFrame{
	
	private static final long serialVersionUID = -2707712944901661771L;

	public welcome(MatrixDG m)
	{
		super("GRAPH!");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{

			Object v[]=new Object[100];
			//inserting vertexes:
			int f=-1;
			double k=1.3;
			for (int i=0;i<m.getvlen();i++)
			{
				String ns=m.getmVexs(i).substring(m.getmVexs(i).length()-1);
				//System.out.print(ns+"\n");
				if (ns.equals("*")==false)
				{
					v[i]=graph.insertVertex(parent, null, m.getmVexs(i), 500+Math.sqrt(i)*f, 20*i,80, 30,"shape=ellipse;perimeter=ellipsePerimeter");
				
				}
				else
				{
					v[i]=graph.insertVertex(parent, null, m.getmVexs(i), 500+Math.sqrt(i)*f, 30*i,80, 30,"shape=ellipse;strokeColor=red;fillColor=green");
				}
				f=-f;
				k=k+0.05;
			}
			//inserting edges:
			for (int i=0;i<m.getvlen();i++)
			{
				for (int j=0;j<m.getvlen();j++)
				{
					if (m.getmMatrix(i,j)!=999)
					{
						if (m.getmVexs(i).substring(m.getmVexs(i).length()-1).equals("*")==true &&
								m.getmVexs(i).substring(m.getmVexs(i).length()-1).equals("*")==true	&& 
								m.getmVexs(i).substring(m.getmVexs(i).length()-2).equals("**")==false)
						{
							graph.insertEdge(parent, null, m.getmMatrix(i,j), v[i], v[j], "strokeColor=lightgreen");
						}
						else
						{
							graph.insertEdge(parent, null, m.getmMatrix(i,j), v[i], v[j]);
						}
					}
				}
			}
			
			
			
			//graph.insertEdge(parent, null, "Edge", v1, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public static void main(String[] args) 		//main
	{
		
		String filename=null;
		MatrixDG mtx;
		//mtx=createDirectedGraph( filename);		//creating graph
		//String s=" 2 \n";
		//s=calcShortestPath(mtx,"to","and");
		//showDirectedGraph(mtx);					//showing graph
		//s=randomWalk(mtx);
		//showDirectedGraph(mtx);
	//	System.out.print(s);
		
		creategraph create=null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the File:");
		filename=sc.nextLine();
		mtx=create.createDirectedGraph(filename);	
		
		//show g
		showgraph show = null;
		bridgewords b=null;
		walk w=null;
		path p=null;
		String choice="0";
		
		System.out.println("1:show the Graph.  2:search bridge words.  3:build new text.  4:shortest path.  5:random walk\n");
		choice=sc.nextLine();
		if(choice.equals("1")){
			show.showDirectedGraph(mtx);
			System.out.println("Nodes:\n");
			for (int x=0;x<mtx.getvlen();x++)
			{
				System.out.print(mtx.getmVexs(x)+" ");
			}
			
		}
		else if(choice.equals("2")){
			String[] strl = new String[mtx.getV().length];
	        String word1 = null;
	        String word2 = null;
	        System.out.print("The first word:");
	        word1 = sc.nextLine();
	        System.out.print("The second word:");
	        word2 = sc.nextLine();
	        int flag1=0;
	        int flag2=0;
	        String multi="";
	        for(int i=0;i<mtx.getV().length;i++){
	        	if(word1.equals(mtx.getmVexs(i)))
	        		flag1=1;
	        	if(word2.equals(mtx.getmVexs(i)))
	        		flag2=1;
	        }
	        if(flag1==0&&flag2==0)
	        	System.out.println("No \"" + word1 + "\" and \"" + word2 + "\" in the Graph!");
	        else if(flag1==0&&flag2!=0)
	        	System.out.println("No \"" + word1 + "\" in the Graph!");
	        else if(flag2==0&&flag1!=0)
	        	System.out.println("No \"" +  word2 + "\" in the Graph!");
	        else {
	        	
	        	String reword = b.queryBridgeWords(mtx, word1, word2);
	            strl = reword.split("\\s+");
	            if (strl.length == 0) {
	                System.out.println("No bridge words from \"" + word1 + "\" to\"" + word2 + "\" !");
	            } 
	            else if(strl.length == 2){
	                System.out.println("The bridge words from \"" + word1 + "\" to\"" + word2 + "\" is:" + reword.replaceAll("\\s+",""));
	            }
	            else{
	            	for(int i=1;i<strl.length-1;i++){
	            		multi+=strl[i]+", ";
	            	}
	            	multi+="and "+strl[strl.length-1]+".";
	            	System.out.println("The bridge words from \"" + word1 + "\" to\"" + word2 + "\" are:" + multi);
	            	
	            }
	 
	        }
	        System.out.println("over!");
			
		}
		else if(choice.equals("3")){
			//bridge Return
	        String newstr = null;
	        System.out.print("Input the new String:");
	        newstr = sc.nextLine();
	        String bridgeStr = b.generateNewText(mtx, newstr);
	        System.out.println(bridgeStr);
	        System.out.println("over!");
		}
		else if(choice.equals("4")){
			System.out.print("input the starting point and the terminal");
	        System.out.print("The starting point:");
	        String word1="",word2="",s="";
	        word1 = sc.nextLine();
	        System.out.print("The terminal:");
	        word2 = sc.nextLine();
			s=p.calcShortestPath(mtx,word1,word2);
			
			showgraph.showDirectedGraph(mtx);
			System.out.println("The shortest path is:"+s);
			
		}
		else if(choice.equals("5")){
			//System.out.println("over!");
			w.randomWalk(mtx);
		}
		
		
		
	}	//main ends


				

			

}
	
	









=======
package com.welcome;
import java.util.Scanner;
import java.util.Random;
import java.lang.*;
import java.lang.System;
import java.lang.String;
import java.util.Scanner;
import java.io .*;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

class MatrixDG {

    private String[] mVexs=null;       // 
    private int[][] mMatrix=null;    // 
    MatrixDG(int v)	//v is the number of vertexes
    {
    	mMatrix=new int[v+1][v+1];
    	mVexs=new String[v];
    	for (int i=0;i<=v;i++)
    	{
    		for(int j=0;j<=v;j++)
    		{
    			mMatrix[i][j]=0;
    			
    		}
    	}
    }
    private int vlen=0;
    public int getvlen ()
    {
    	return vlen;
    }
    public String getmVexs(int i)
    {
    	return mVexs[i];
    }
    public int getmMatrix(int i,int j)
    {
    	return mMatrix[i][j];
    }
    public String[] getV()
    {
    	return mVexs;
    }
    public void setmatrix(int i,int j,int x)
    {
    	mMatrix[i][j]=x;
    }
    public void setvlen(int i)
    {
    	vlen=i;
    }
    public void setmVexs(int i,String s)
    {
    	try{
    		mVexs[i]=s;
    	}
    	catch(Exception e){
    		return;
    	}
    	
    }
}
public class welcome extends JFrame{
	
	private static final long serialVersionUID = -2707712944901661771L;

	public welcome(MatrixDG m)
	{
		super("GRAPH!");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{

			Object v[]=new Object[100];
			//inserting vertexes:
			int f=-1;
			double k=1.3;
			for (int i=0;i<m.getvlen();i++)
			{
				String ns=m.getmVexs(i).substring(m.getmVexs(i).length()-1);
				//System.out.print(ns+"\n");
				if (ns.equals("*")==false)
				{
					v[i]=graph.insertVertex(parent, null, m.getmVexs(i), 500+Math.sqrt(i)*f, 20*i,80, 30,"shape=ellipse;perimeter=ellipsePerimeter");
				
				}
				else
				{
					v[i]=graph.insertVertex(parent, null, m.getmVexs(i), 500+Math.sqrt(i)*f, 30*i,80, 30,"shape=ellipse;strokeColor=red;fillColor=green");
				}
				f=-f;
				k=k+0.05;
			}
			//inserting edges:
			for (int i=0;i<m.getvlen();i++)
			{
				for (int j=0;j<m.getvlen();j++)
				{
					if (m.getmMatrix(i,j)!=999)
					{
						if (m.getmVexs(i).substring(m.getmVexs(i).length()-1).equals("*")==true &&
								m.getmVexs(i).substring(m.getmVexs(i).length()-1).equals("*")==true	&& 
								m.getmVexs(i).substring(m.getmVexs(i).length()-2).equals("**")==false)
						{
							graph.insertEdge(parent, null, m.getmMatrix(i,j), v[i], v[j], "strokeColor=lightgreen");
						}
						else
						{
							graph.insertEdge(parent, null, m.getmMatrix(i,j), v[i], v[j]);
						}
					}
				}
			}
			
			
			
			//graph.insertEdge(parent, null, "Edge", v1, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public static void main(String[] args) 		//main
	{
		
		String filename=null;
		MatrixDG mtx;
		//mtx=createDirectedGraph( filename);		//creating graph
		//String s=" 2 \n";
		//s=calcShortestPath(mtx,"to","and");
		//showDirectedGraph(mtx);					//showing graph
		//s=randomWalk(mtx);
		//showDirectedGraph(mtx);
	//	System.out.print(s);
		
		creategraph create=null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the File:");
		filename=sc.nextLine();
		mtx=create.createDirectedGraph(filename);	
		
		//show g
		showgraph show = null;
		bridgewords b=null;
		walk w=null;
		path p=null;
		String choice="0";
		
		System.out.println("1:show the Graph.  2:search bridge words.  3:build new text.  4:shortest path.  5:random walk\n");
		choice=sc.nextLine();
		if(choice.equals("1")){
			show.showDirectedGraph(mtx);
			System.out.println("Nodes:\n");
			for (int x=0;x<mtx.getvlen();x++)
			{
				System.out.print(mtx.getmVexs(x)+" ");
			}
			
		}
		else if(choice.equals("2")){
			String[] strl = new String[mtx.getV().length];
	        String word1 = null;
	        String word2 = null;
	        System.out.print("The first word:");
	        word1 = sc.nextLine();
	        System.out.print("The second word:");
	        word2 = sc.nextLine();
	        int flag1=0;
	        int flag2=0;
	        String multi="";
	        for(int i=0;i<mtx.getV().length;i++){
	        	if(word1.equals(mtx.getmVexs(i)))
	        		flag1=1;
	        	if(word2.equals(mtx.getmVexs(i)))
	        		flag2=1;
	        }
	        if(flag1==0&&flag2==0)
	        	System.out.println("No \"" + word1 + "\" and \"" + word2 + "\" in the Graph!");
	        else if(flag1==0&&flag2!=0)
	        	System.out.println("No \"" + word1 + "\" in the Graph!");
	        else if(flag2==0&&flag1!=0)
	        	System.out.println("No \"" +  word2 + "\" in the Graph!");
	        else {
	        	
	        	String reword = b.queryBridgeWords(mtx, word1, word2);
	            strl = reword.split("\\s+");
	            if (strl.length == 0) {
	                System.out.println("No bridge words from \"" + word1 + "\" to\"" + word2 + "\" !");
	            } 
	            else if(strl.length == 2){
	                System.out.println("The bridge words from \"" + word1 + "\" to\"" + word2 + "\" is:" + reword.replaceAll("\\s+",""));
	            }
	            else{
	            	for(int i=1;i<strl.length-1;i++){
	            		multi+=strl[i]+", ";
	            	}
	            	multi+="and "+strl[strl.length-1]+".";
	            	System.out.println("The bridge words from \"" + word1 + "\" to\"" + word2 + "\" are:" + multi);
	            	
	            }
	 
	        }
	        System.out.println("over!");
			
		}
		else if(choice.equals("3")){
			//bridge Return
	        String newstr = null;
	        System.out.print("Input the new String:");
	        newstr = sc.nextLine();
	        String bridgeStr = b.generateNewText(mtx, newstr);
	        System.out.println(bridgeStr);
	        System.out.println("over!");
		}
		else if(choice.equals("4")){
			System.out.print("input the starting point and the terminal");
	        System.out.print("The starting point:");
	        String word1="",word2="",s="";
	        word1 = sc.nextLine();
	        System.out.print("The terminal:");
	        word2 = sc.nextLine();
			s=p.calcShortestPath(mtx,word1,word2);
			
			showgraph.showDirectedGraph(mtx);
			System.out.println("The shortest path is:"+s);
			
		}
		else if(choice.equals("5")){
			//System.out.println("over!");
			w.randomWalk(mtx);
		}
		
		
		
	}	//main ends


				

			

}
	
	









>>>>>>> 996c400b770277bfb950b25ed1e63f9f07639945
