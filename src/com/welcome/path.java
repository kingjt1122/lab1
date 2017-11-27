package com.welcome;

public class path {

	static String calcShortestPath(MatrixDG g, String word1, String word2)
	{
		// in the algorithm, the number 999 means "false"
		if (word1==word2)
		{
			return "0";
		}
		for (int i=0;i<g.getvlen();i++)
		{
			for (int j=0;j<g.getvlen();j++)
			{
				if (g.getmMatrix(i,j)==0)
				{
					g.setmatrix(i,j,999);
				}
			}
		}
		String s="";
		int w1,w2;
		tool t=null;
		w1=t.getposition(word1,g.getV());
		if (w1==-1)
		{
			return "word1 not in the text";
		}
		w2=t.getposition(word2,g.getV());
		if (w2==-1)
		{
			return "word2 not in the text";
		}
		//System.out.print(w1+" "+w2);
		int d[]=new int[g.getvlen()];
		int p[]=new int[g.getvlen()];
		int f[]=new int[g.getvlen()];
		
		for (int i=0;i<g.getvlen();i++)
		{
			if (g.getmMatrix(w1,i)!=999)
				{
					d[i]=g.getmMatrix(w1,i);
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
		for (int i=0;i<g.getvlen()-1;i++)	//dijkstra
		{
			min=999;
			for (int j=0;j<g.getvlen();j++)
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
			for (int j=0;j<g.getvlen();j++)
			{
				if (f[j]==0 && g.getmMatrix(m,j)+d[m]<d[j])
				{
					d[j]=g.getmMatrix(m,j)+d[m];
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
			path[c]=g.getmVexs(i);
			g.setmVexs(i,g.getmVexs(i)+" *");
			c++;
			i=p[i];
		}
		g.setmVexs(w2,g.getmVexs(w2)+"*");
		path[c+1]=g.getmVexs(w1);
		g.setmVexs(w1,g.getmVexs(w1)+" *");
		int count=0;
		for (int j=99;j>=0;j--)
		{
			//count=count+1;
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
		s=s+" "+count;
		return s;
	}
}
