package blackjack;

import java.io.*;

class Stats {
	//Variables
	String file = "stats.txt";
	String[] stats = new String[7];
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
	public boolean setPlayerIndex(String name) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while (true) {
				String curr;
				curr = reader.readLine();
				int index = 0;
				if (curr == null) {
					createPlayerProfile(name);
					playerIndex = index;
					System.out.print(playerIndex);
					System.out.println("Profile not found, creating new profile.");
					return false;
				} else if (curr.equals(name)) {
					playerIndex = index;
					username = name;
					System.out.print(playerIndex);
					System.out.println("Profile found, logging in.");
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

	public void createPlayerProfile(String name) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.write(name);
			username = name;
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
			//BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			BufferedReader reader = new BufferedReader(new FileReader(file));
			for (int i = 0; i <= playerIndex; i++) {
				reader.readLine();
			}
			/*for(int i = 0; i < 7; i++) {
				if (reader.readLine() == null) {
					writer.write(0);
					stats[i] = "0";
				} else {
					stats[i] = reader.readLine();
				}
			}*/
			level = Integer.parseInt(reader.readLine());
			wins = Integer.parseInt(reader.readLine());
			loses = Integer.parseInt(reader.readLine());
			biggestWin = Double.parseDouble(reader.readLine());
			biggestLoss = Double.parseDouble(reader.readLine());
			profit = Double.parseDouble(reader.readLine());
			loss = Double.parseDouble(reader.readLine());
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
				//writer.write("wajt")
			}
			writer.write(username + "\n");
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
