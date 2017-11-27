package com.welcome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class creategraph {
	public static MatrixDG createDirectedGraph(String filename)	//createDirectedGraph	createDirectedGraph
	{
		String s;
		String s2 = null;
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
		for(int k=0;k<words.length;k++){
			if(words[k].equals(words[words.length-1])){
				i++;
				words[j]=""; 
				//break finder;
			}
		}
		
		
		for (i=0;i<20;i++){
			if (words[i]!="")
				{
					System.out.print(words[i]+"\n");
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
		aMATRIX.setvlen(cw);
		//aMATRIX.mVexs= new String[vlen];
        for (int m = 0; m < aMATRIX.getvlen(); m++)		// initializing vertexes
        	{
        		aMATRIX.setmVexs(m, words[m]);
        		//System.out.p0rint("!:  "+aMATRIX.mVexs[m]+"\n");
        	}
        //aMATRIX.mMatrix = new int[vlen][vlen];
        int p1,p2;
        tool t=null;
        for (i = 0; i < cw2; i++) {
            p1=t.getposition(words1[i],words);
            p2=t.getposition(words1[i+1],words);
           // System.out.print(p1+"  "+p2+"\n");
            if (words1[i]!="" && words1[i+1]!="")
            {
            		aMATRIX.setmatrix(p1,p2,aMATRIX.getmMatrix(p1, p2)+1);
            }
        }
        for (int x=0;x<aMATRIX.getvlen();x++)
        {
        	for (int xx=0;xx<aMATRIX.getvlen();xx++)
            {
            	if (aMATRIX.getmMatrix(x,xx)==0)
            	{
            		aMATRIX.setmatrix(x,xx,999);
            	}
            }
        }
        
        
        return aMATRIX;
	}
}
