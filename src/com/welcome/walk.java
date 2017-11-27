package com.welcome;

import java.util.Random;
import java.util.Scanner;

public class walk {
	public static String randomWalk(MatrixDG g)
	{
		String s="";
		Random random = new Random();
		int r = random.nextInt(g.getvlen()-1); //the starting vertex
		s=s+g.getmVexs(r);
		System.out.print("random walk starts. input 's' to stop:\n"+g.getmVexs(r));
		int next=r,cch=0,ch[];
		ch=new int[g.getvlen()];
		int rpt[]=new int[g.getvlen()];
		for (int i=0;i<g.getvlen();i++)
		{
			ch[i]=0;
			rpt[i]=-1;
		}
		Scanner sc= new Scanner(System.in);
		int cc=1;
		rpt[0]=r;
		while (next!=-1)
		{
			
			cch=0;
			//System.out.print(next);
			String st = sc.nextLine();
			//System.out.print("1"+st+"1");
			if (st.equals("s"))
			{
				System.out.print("STOP");
				return s;
			}
			for (int i=0;i<g.getvlen();i++ )
			{
				if (g.getmMatrix(next,i)!=999)
				{
					ch[cch]=i;
					cch++;
				}
			}
			if (cch==0)
			{
				System.out.print("over. no where to go\n");
				return s;
			}
			int c = random.nextInt(cch);
			for (int x=0;x<g.getvlen();x++)
			{
				if (rpt[x]==ch[c])
				{
					System.out.print("edge repeated, quit\n");
					s=s+" -> "+g.getmVexs(ch[c]);
					return s;
				}
			}
			next=ch[c];
			rpt[cc]=next;
			cc++;
			s=s+" -> "+g.getmVexs(next);
			
			System.out.print(" -> "+g.getmVexs(next));
		}
		sc.close();
		return s;
	}
}
