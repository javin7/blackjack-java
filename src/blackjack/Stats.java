package blackjack;

import java.io.*;

class Stats {
    String file = "stats.txt";
    String temp;
    String[] stats = new String[4];
    private int wins;
    private int loses;
    private double biggestWin;
    private double biggestLoss;
    private double profit;
    private double loss;
    
    public void checkStats() {
    try {
       BufferedReader in = new BufferedReader(new FileReader(file));
       for(int i = 0; i < 6; i++) {
          
          stats[i] = in.readLine();
          if (stats[i] == null) {
             stats[i] = "0";
          }
       }
       in.close();
       wins = Integer.parseInt(stats[0]);
       loses = Integer.parseInt(stats[1]);
       biggestWin = Double.parseDouble(stats[2]);
       biggestLoss = Double.parseDouble(stats[3]);
       profit = Double.parseDouble(stats[4]);
       loss = Double.parseDouble(stats[5]);
    } catch (IOException e) {
       System.out.print(e + " : File error");
    }
 
 }

    public void updateStats() {
    	try {
    	BufferedWriter out = new BufferedWriter(new FileWriter(file, false));
        out.write(wins + "\n");
        out.write(loses + "\n");
        out.write(biggestWin + "\n");
        out.write(biggestLoss + "\n");
        out.write(profit + "\n");
        out.write(loss + "\n");
        out.close();
    	} catch (IOException e) {
    		System.out.print(e + " : File error");
    	}
    }
    
    
    public void addWin(double cash) {
    	wins++;
    	if (cash > biggestWin) {
    		biggestWin = cash;
    	}
	}
    
    public void addLoss(double cash) {
    	loses++;
    	if (cash > biggestLoss) {
    		biggestLoss = cash;
    	}
    }
    
    public int getWins() {
    	return wins;
    }
    
    public int getLoses() {
    	return wins;
    }
	
}
