package com.welcome;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class Pathtest {
	String filename="/Users/Veronique/Desktop/a.txt";
	MatrixDG mtx=createGraph(filename);
	
	
	@Test
	public void test() {
		assertEquals("you -> are -> here 2",welcome.calcShortestPath(mtx, "you", "here"));
	}
	
	
	private static int getposition(String s, String[] sw)		//get the position of s in array sw
	{
		for (int i=0;i<sw.length;i++){
			if(s.equals(sw[i])) return i;
		}
		return -1;
	}
	/*public static String calcShortestPath(MatrixDG g, String word1, String word2)
	{
		// in the algorithm, the number 999 means "false"
		for (int i=0;i<g.vlen;i++)
		{
			for (int j=0;j<g.vlen;j++)
			{
				if (g.mMatrix[i][j]==0)
				{
					g.mMatrix[i][j]=999;
				}
			}
		}
		String s="";
		int w1,w2;
		w1=getposition(word1,g.mVexs);
		if (w1==-1)
		{
			System.out.print("word not in the text");
			return "word not in the text";
		}
		w2=getposition(word2,g.mVexs);
		if (w2==-1)
		{
			System.out.print("word2 not in the text");
			return "word not in the text";
		}
		//System.out.print(w1+" "+w2);
		int d[]=new int[g.vlen];
		int p[]=new int[g.vlen];
		int f[]=new int[g.vlen];
		
		for (int i=0;i<g.vlen;i++)
		{
			if (g.mMatrix[w1][i]!=999)
				{
					d[i]=g.mMatrix[w1][i];
					p[i]=w1;
				}
			else
				{
					d[i]=999; //infinite long, not reached yet
					p[i]=999; //no parent node
				}
			
			f[i]=0;
		}
		d[w1]=0;
		f[w1]=1;
		int min=999;
		int m=999;
		for (int i=0;i<g.vlen-1;i++)	//dijkstra
		{
			min=999;
			for (int j=0;j<g.vlen;j++)
			{
				if (f[j]==0)
				{
					if (d[j]<min)
					{
						m=j;
						min=d[j];
					}
				}
			}
			//System.out.print(min+"\n");
			if (min<999)
			{
				f[m]=1;
			}
			else if (m==999)
			{
				System.out.print("not reachable\n");
				return "NULL";
			}
			for (int j=0;j<g.vlen;j++)
			{
				if (f[j]==0 && g.mMatrix[m][j]+d[m]<d[j])
				{
					d[j]=g.mMatrix[m][j]+d[m];
					p[j]=m;
				}
			}
		}//end dijkstra
		int i=w2,c=0;
		String path[]=new String[100];
		for (int j=0;j<100;j++)
			path[j]="";
		while (i!=w1)
		{
			path[c]=g.mVexs[i];
			g.mVexs[i]=g.mVexs[i]+" *";
			c++;
			i=p[i];
		}
		g.mVexs[w2]=g.mVexs[w2]+"*";
		path[c+1]=g.mVexs[w1];
		g.mVexs[w1]=g.mVexs[w1]+" *";
		int count=0;
		for (int j=99;j>=0;j--)
		{
			if (path[j]!="" && j!=0)
			{
				count=count+1;
				s=s+path[j]+" -> ";
				//System.out.print(path[j]+" -> ");
			}
			if (j==0)
			{
				s=s+path[j];
				//System.out.print(path[j]);
			}
		}
		if (word1==word2)
		{
			count=1;
			return "0";
		}
		//System.out.print("The length of the path is: "+count+"\n");
		s=s+" "+count;
		System.out.print(s);
		return s;
	}		*/	
	private static MatrixDG createGraph(String filename)	//createDirectedGraph	createDirectedGraph
	{
		String s;
		String s2="";
		try			//reading file
		{
			FileReader inFile = new FileReader(filename);
			BufferedReader buff = new BufferedReader(inFile);
			boolean endOfFile=false;
			while (!endOfFile)
			{
				s=buff.readLine();	//reading line by line to s
				if (s==null)
				{
					endOfFile=true;
				}
				else
				{
					s2=s2+s+" ";
				}
			}
			buff.close();
		}
		catch (IOException e){
			System.out.print("An Error Occurred : "+e.toString());
		}
		//reading done; converting begins (s2 to s3) 
		String s3="";
		int f=0;
		s2=s2.toLowerCase();// to lower case
		for(int i = 0; i<s2.length()-1;i++)
		{
			char c=s2.charAt(i);
			int b = (int)c;
			if(97<=b && b<=122)
			{
				s3=s3+c;
				f=0;
			}
			else if (b==32)
			{
				if (f==0)
					{
						s3=s3+' ';
						f=1;
					}
			}
			else if (b==33 || b==34 || b==39 || b==40 || b==41 || b==44 || b==45 || b==46 || b==58 || b==59 || b==63 )
			{
				if (f==0)
					{
						s3=s3+' ';
						f=1;
					}
			}
		}
		//converting to one space
		//String s4; //s4 has only one space between each words
		
		// extracting words
		String[] words = new String[100]; //all different
		String[] words1 = new String[100];	//all of the words; some are the same
		for(int i=0;i<100;i++){ //initializing 
			words1[i]="";
			words[i]="";
		}
		int i=0;
		int j=0;
		while(i<s3.length())
		{
			char c=s3.charAt(i);
			if (c!=' ')
			{
				words1[j]=words1[j]+c;
				i++;
			}
			else{
				i++;
				j++;
			}
		}

		i=0;
		j=0;
		while(i<s3.length())
		{
			char c=s3.charAt(i);
			finder:
			if (c!=' ')
			{
				words[j]=words[j]+c;
				i++;
			}
			else
			{
				if(j>0){
					
					for(int k=0;k<j;k++){
						if(words[k].equals(words[j])){
							i++;
							words[j]=""; 
							break finder;
						}
					}
					
				}
				i++;
				j++;
			
			}
		}
		
		
		
		for (i=0;i<20;i++){
			if (words[i]!="")
				{
					//System.out.print(words[i]+"\n");
				}
		}

	//DAG
		int cw=0,cw2=0;  //cw is the number of differentwords	;	cw2 is number of all words
		for (int t=0;t<words.length;t++)
		{
			if (words[t]!="")
			{
				cw++;
			}
			else
				break;
		}
		for (int t=0;t<words.length;t++)
		{
			if (words1[t]!="")
			{
				cw2++;
			}
			else
				break;
		}
		int vlen = cw;
		int elen=0;
		for(i=0;i<s3.length();i++)
		{
			char c1=s3.charAt(i);
			if(c1==' ')
			{
				elen+=1;
			}
			
		}
		String[] vexs= new String[100];
		for(i=0;i<words.length;i++){
		vexs[i]=words[i];
		}
		
		MatrixDG aMATRIX;		//creating matrix 
		aMATRIX=new MatrixDG(vlen);
		aMATRIX.vlen=cw;
		aMATRIX.mVexs= new String[vlen];
        for (int m = 0; m < aMATRIX.mVexs.length; m++)		// initializing vertexes
        	{
        		aMATRIX.mVexs[m] = words[m];
        		//System.out.print("!:  "+aMATRIX.mVexs[m]+"\n");
        	}
        aMATRIX.mMatrix = new int[vlen][vlen];
        int p1,p2;
       
        for (i = 0; i < cw2+1; i++) {
            p1=getposition(words1[i],words);
            p2=getposition(words1[i+1],words);
            
            //System.out.print(p1+"  "+p2+"\n");
            if (words1[i]!="" && words1[i+1]!="" && p2!=-1)
            {
            		aMATRIX.mMatrix[p1][p2]++;
            }
        }
        for (int x=0;x<aMATRIX.vlen;x++)
        {
        	for (int xx=0;xx<aMATRIX.vlen;xx++)
            {
            	if (aMATRIX.mMatrix[x][xx]==0)
            	{
            		aMATRIX.mMatrix[x][xx]=999;
            	}
            }
        }
        
        
        
        return aMATRIX;
	}
}