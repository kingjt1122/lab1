package com.welcome;

import java.util.Random;

public class bridgewords {
	public static String queryBridgeWords(MatrixDG G, String word1, String word2)//鏌ヨ妗ユ帴璇�
    {
        String s = "";
        int i, i1;
        int j, k, f;
        
        for(int m=0;m<G.getV().length;m++){
        	for(int n=0;n<G.getV().length;n++){
        		if(G.getmMatrix(m,n)!=999)
        		{
        			if (G.getmMatrix(m,n)>1)
        			{
        				G.setmatrix(m,n,1);
        			}
        		}
        			
        	}
        }
        String[] strlist = new String[G.getV().length];
        for (i = 0; i < G.getV().length; i++) {
            strlist[i] = "";
        }
        int[] list = new int[G.getV().length];
        for (i = 0; i < G.getV().length; i++) {
            if (G.getV()[i].equals(word1)) {
                for (i1 = 0; i1 < G.getV().length; i1++) {
                    if (G.getmMatrix(i,i1) == 1) {
                        list[i1] = 1;
                    } else list[i1] = 0;
                }

            }

        }
        for (j = 0; j < list.length; j++) {
            if (list[j] == 1) {
                for (k = 0; k < G.getV().length; k++) {
                    if (G.getmMatrix(j,k) == 1 && G.getV()[k].equals(word2)) {
                        strlist[j] = G.getV()[j];
                    }
                }
            }
        }
        for (f = 0; f < G.getV().length; f++) {
            s += " " + strlist[f];
        }

        return s;
    }
	
	public static String generateNewText(MatrixDG G, String inputText) {//
        String s = "";
        String[] strlist = inputText.split(" ");
        String[] insert = new String[strlist.length - 1];
        String[] choicestr = new String[insert.length];
        
        for (int i = 0; i < strlist.length - 1; i++) {
            insert[i] = queryBridgeWords(G, strlist[i], strlist[i + 1]);
        }

        // choicestr 
        for (int j = 0; j < insert.length; j++) {
            insert[j] = insert[j].replaceAll("\\s+", " ");
            if (!insert[j].equals(" ")) {
                // 
                String[] splitTemp = insert[j].split("\\s+");
                // 
                if (splitTemp[0].equals("")) {
                	Random random = new Random();
                	int result=random.nextInt(splitTemp.length-1)+1;
                    insert[j] = splitTemp[result];
                } else {
                    insert[j] = splitTemp[0];
                }
            }
            choicestr[j] = insert[j];
        }

        for (int i = 0; i < choicestr.length; i++) {
            // 
            if (choicestr[i].equals(" ")) {
                s += strlist[i] + " ";
            }
            //
            else
            {
                s += strlist[i] + " " + choicestr[i] + " ";
            }
        }
        s += strlist[strlist.length - 1];
        s = s.replaceAll("\\s+", " ");
        return s;
    }
}
