package blackjack;

import java.io.*;

class Stats {
	//Variables
	String file = "stats.txt";
	String[] stats = new String[6];
	private int playerIndex;
	private String username;
	private int level;
	private int wins;
	private int loses;
	private double biggestWin;
	private double biggestLoss;
	private double profit;
	private double loss;



	//Reads over stats from text file into variables
	public boolean setPlayerIndex(String username) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while (true) {
				String curr;
				curr = reader.readLine();
				int index = 0;
				if (curr == null) {
					return false;
				} else if (curr.equals(username)) {
					playerIndex = index;
					return true;
				} else {
					index++;
				}
			}
		} catch (IOException iox) {
			System.out.print(iox + "File error");
		}
		return false;
	}

	public void createPlayerProfile(String username) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write(username);
			writer.write("0");
			writer.write("0");
			writer.write("0");
			writer.write("0");
			writer.write("0");
			writer.write("0");
			writer.write("0");
		} catch (IOException iox) {
			System.out.print(iox + "File error");
		}
	}

	public void checkStats() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			BufferedReader reader = new BufferedReader(new FileReader(file));

			/*for(int i = 0; i < 6; i++) {
				if (reader.readLine() == null) {
					writer.write(0);
					stats[i] = "0";
				} else {
					stats[i] = reader.readLine();
				}
			}*/
			for (int i = 0; i < playerIndex; i++) {
				writer.write("");
			}
			level = Integer.parseInt(stats[playerIndex + 1]);
			wins = Integer.parseInt(stats[playerIndex + 2]);
			loses = Integer.parseInt(stats[playerIndex + 3]);
			biggestWin = Double.parseDouble(stats[playerIndex + 4]);
			biggestLoss = Double.parseDouble(stats[playerIndex + 5]);
			profit = Double.parseDouble(stats[playerIndex + 6]);
			loss = Double.parseDouble(stats[playerIndex + 7]);
			reader.close();
		} catch (IOException iox) {
			System.out.print(iox + " : File error");
		}

	}

	//Write stats into text file
	public void updateStats() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
			BufferedReader reader = new BufferedReader(new FileReader(file));
			for (int i = 0; i < playerIndex; i++) {
				writer.write(reader.readLine());
			}
			writer.write(level +"\n");
			writer.write(wins + "\n");
			writer.write(loses + "\n");
			writer.write(biggestWin + "\n");
			writer.write(biggestLoss + "\n");
			writer.write(profit + "\n");
			writer.write(loss + "\n");
			writer.close();
		} catch (IOException iox) {
			System.out.print(iox + " : File error");
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
		return (double) (wins / (wins + loses)) * 100;
		//double percentage = (double) (wins / (wins + loses)) * 100;
		//return percentage;
	}
}
