package blackjack;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Stats {
	//Variables
	String file = "stats.txt";
	String temp;
	String[] stats = new String[6];
	private int wins;
	private int loses;
	private double biggestWin;
	private double biggestLoss;
	private double profit;
	private double loss;
	
	//Reads over stats from text file into variables
	public void checkStats() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < 6; i++) {
				if (reader.readLine() != "") {
					writer.write(0);
					stats[i] = "0";
				} else {
					stats[i] = reader.readLine();
				}
			}
			wins = Integer.parseInt(stats[0]);
			loses = Integer.parseInt(stats[1]);
			biggestWin = Double.parseDouble(stats[2]);
			biggestLoss = Double.parseDouble(stats[3]);
			profit = Double.parseDouble(stats[4]);
			loss = Double.parseDouble(stats[5]);
			reader.close();
		} catch (IOException e) {
			System.out.print(e + " : File error");
		}

	}

	//Write stats into text file
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
		profit += cash;
	}

	public void addLoss(double cash) {
		loses++;
		if (cash > biggestLoss) {
			biggestLoss = cash;
		}
		loss += cash;
	}

	public void increaseProfit(double cash) {
		profit += cash;
	}

	public void increaseLoss(double cash) {
		loss += cash;
	}

	public int getWins() {
		return wins;
	}

	public int getLoses() {
		return loses;
	}

	public double getProfit() {
		return profit;
	}

	public double getLoss() {
		return loss;
	}

	public double getBiggestWin() {
		return biggestWin;
	}

	public double getBiggestLoss() {
		return biggestLoss;
	}

	public double winPercentage() {
		double percentage = (wins / (wins + loses)) * 100;
		return percentage;
	}
}
