package blackjack;

import java.io.*;
import java.util.*;

class Database {
	//Variables
	private static String file = "stats.txt";
	private static int playerIndex;
	private static String username;
	private static int level;
	private static int wins;
	private static int loses;
	private static double biggestWin;
	private static double biggestLoss;
	private static double profit;
	private static double loss;



	//Get the text line number of the player profile
	public static boolean setPlayerIndex(String name) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String currentLine;
			int index = 0;
			while( null != (currentLine=reader.readLine())) {
				if (currentLine.equals(name)) {
					playerIndex = index;
					username = name;
					System.out.print(index);
					System.out.println("Profile found, logging in.");
					return true;
				} else {
					index++;
				}
			}
			createPlayerProfile(name);
			playerIndex = index;
			System.out.print(index);
			System.out.println("Profile not found, creating new profile.");
			return false;
		} catch (IOException iox) {
			System.out.print(iox + "File error");
		}
		return false;
	}

	//Create a new profile
	public static void createPlayerProfile(String name) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			username = name;
			writer.write(name + "\n"); //Username
			writer.write("0\n"); //Level
			writer.write("0\n"); //Wins
			writer.write("0\n"); //Losses
			writer.write("0\n"); //Biggest Win
			writer.write("0\n"); //Biggest Loss
			writer.write("0\n"); //Profit
			writer.write("0\n"); //Loss
			writer.close();
		} catch (IOException iox) {
			System.out.print(iox + "File error");
		}
	}

	//Update blackjack stats from the file
	public static void checkStats() {
		try {
			//BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			BufferedReader reader = new BufferedReader(new FileReader(file));
			for (int i = 0; i < playerIndex; i++) {
				reader.readLine();
			}
			username = reader.readLine();
			//System.out.print(username);

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
	public static void updateStats() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			ArrayList<String> preLines = new ArrayList<String>();
			ArrayList<String> postLines = new ArrayList<String>();
			//Get lines before profile lines
			for (int i = 0; i < playerIndex; i++) {
				preLines.add(reader.readLine());
			}

			//Skip lines inbetween
			for (int i = 0; i < 8;i++) {
				reader.readLine();
			}

			//Get lines after profile lines
			String currentLine;
			while(null != (currentLine=reader.readLine())) {
				postLines.add(currentLine);
			}

			//Write data back into file with new data
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
			for (int i = 0; i < preLines.size(); i++) {
				writer.write(preLines.get(i) + "\n");
			}
			writer.write(username + "\n");
			writer.write(level +"\n");
			writer.write(wins + "\n");
			writer.write(loses + "\n");
			writer.write(biggestWin + "\n");
			writer.write(biggestLoss + "\n");
			writer.write(profit + "\n");
			writer.write(loss + "\n");
			for (int i = 0; i < postLines.size(); i++) {
				writer.write(postLines.get(i)+ "\n");
			}
			writer.close();
		} catch (IOException iox) {
			System.out.print(iox + " : File error");
		}
	}

	public static void deleteProfile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			ArrayList<String> preLines = new ArrayList<String>();
			ArrayList<String> postLines = new ArrayList<String>();
			//Get lines before profile lines
			for (int i = 0; i < playerIndex; i++) {
				preLines.add(reader.readLine());
			}

			//Skip lines in between
			for (int i = 0; i < 8;i++) {
				reader.readLine();
			}

			//Get lines after profile lines
			String currentLine;
			while(null != (currentLine=reader.readLine())) {
				postLines.add(currentLine);
			}

			//Write data back into file without that player
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
			for (int i = 0; i < preLines.size(); i++) {
				writer.write(preLines.get(i) + "\n");
			}
			for (int i = 0; i < postLines.size(); i++) {
				writer.write(postLines.get(i)+ "\n");
			}
			writer.close();
		} catch (IOException iox) {
			System.out.print(iox + " : File error");
		}
	}

	//Display top players sorted by level
	public static void displayByLevel() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> level = new ArrayList<String>();

			//Read the username and level into seperate arraylists
			String nameLine;
			while (null != (nameLine = reader.readLine())) {
				names.add(nameLine);
				level.add(reader.readLine());
				reader.readLine();
				reader.readLine();
				reader.readLine();
				reader.readLine();
				reader.readLine();
				reader.readLine();
			}

			//Turn arraylist into an array
			String[] nameArr = new String[names.size()];
			String[] levelArr = new String[level.size()];
			nameArr = names.toArray(nameArr);
			levelArr = level.toArray(levelArr);

			//Sort array based on level
			for (int i = 0; i < level.size(); i++) {
				for (int j = 0; j < level.size(); j++) {
					int temp1;
					String temp2;
					if (Integer.parseInt(levelArr[j]) < Integer.parseInt(levelArr[i])) {
						temp1 = Integer.parseInt(levelArr[i]);
						levelArr[i] = levelArr[j];
						levelArr[j] = String.valueOf(temp1);

						temp2 = nameArr[i];
						nameArr[i] = nameArr[j];
						nameArr[j] = temp2;
					}
				}
			}

			//Print out top 5 players
			System.out.println("\n----------------------------\nThese are the top 5 players!\n---------------------------- ");
			for (int i = 0; i < 5 && i < level.size(); i++) {
				System.out.println(i+1 + ": " + nameArr[i] + " | Level: " + levelArr[i]);
			}
		} catch (IOException iox) {
			System.out.println("File error!");
		}
	}

	//Insertion Sort
	public static void sortFileByLevel() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> level = new ArrayList<String>();
			ArrayList<Integer> wins = new ArrayList<>();
			ArrayList<Integer> loses = new ArrayList<>();
			ArrayList<Double> biggestWin = new ArrayList<>();
			ArrayList<Double> biggestLoss = new ArrayList<>();
			ArrayList<Double> profit = new ArrayList<>();
			ArrayList<Double> loss = new ArrayList<>();


			//Read the username and level into seperate arraylists
			String nameLine;
			int size = level.size();
			while (null != (nameLine = reader.readLine())) {
				names.add(nameLine);
				level.add(reader.readLine());
				wins.add(Integer.parseInt(reader.readLine()));
				loses.add(Integer.parseInt(reader.readLine()));
				biggestWin.add(Double.parseDouble(reader.readLine()));
				biggestLoss.add(Double.parseDouble(reader.readLine()));
				profit.add(Double.parseDouble(reader.readLine()));
				loss.add(Double.parseDouble(reader.readLine()));
			}

			//Turn arraylist into an array
			String[] nameArr = new String[size];
			String[] levelArr = new String[size];
			Integer[] winsArr = new Integer[size];
			Integer[] losesArr = new Integer[size];
			Double[] biggestWinArr = new Double[size];
			Double[] biggestLossArr = new Double[size];
			Double[] profitArr = new Double[size];
			Double[] lossArr = new Double[size];

			nameArr = names.toArray(nameArr);
			levelArr = level.toArray(levelArr);
			winsArr = wins.toArray(winsArr);
			losesArr = loses.toArray(losesArr);
			biggestWinArr = biggestWin.toArray(biggestWinArr);
			biggestLossArr = biggestLoss.toArray(biggestLossArr);
			profitArr = profit.toArray(profitArr);
			lossArr = loss.toArray(lossArr);

			boolean sorted = false;
			int temp;
			double temp2;
			String temp1;
			while(!sorted) {
				sorted = true;
				for (int i = 0; i < levelArr.length - 1; i++) {
					if (Integer.parseInt(levelArr[i]) > Integer.parseInt(levelArr[i+1])) {
						temp1 = nameArr[i];
						nameArr[i] = nameArr[i+1];
						nameArr[i+1] = temp1;

						temp = 	Integer.parseInt(levelArr[i]);
						levelArr[i] = levelArr[i+1];
						levelArr[i+1] = Integer.toString(temp);

						temp = 	winsArr[i];
						winsArr[i] = winsArr[i+1];
						winsArr[i+1] = temp;

						temp = losesArr[i];
						losesArr[i] = losesArr[i+1];
						losesArr[i+1] = temp;

						temp2 = biggestWinArr[i];
						biggestWinArr[i] = biggestWinArr[i+1];
						biggestWinArr[i+1] = temp2;

						temp2 = biggestLossArr[i];
						biggestLossArr[i] = biggestLossArr[i+1];
						biggestLossArr[i+1] = temp2;

						temp2 = profitArr[i];
						lossArr[i] = lossArr[i+1];
						lossArr[i+1] = temp2;

						temp2 = lossArr[i];
						lossArr[i] = lossArr[i+1];
						lossArr[i+1] = temp2;

						sorted = false;
					}
				}
			}
			/*System.out.println(Arrays.toString(nameArr));
			System.out.println(Arrays.toString(levelArr));
			System.out.println(Arrays.toString(winsArr));
			System.out.println(Arrays.toString(losesArr));
			System.out.println(Arrays.toString(biggestWinArr));
			System.out.println(Arrays.toString(biggestLossArr));
			System.out.println(Arrays.toString(profitArr));
			System.out.println(Arrays.toString(lossArr));*/

			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));

			for (int i = nameArr.length-1; i >= 0; i--) {
				writer.write(nameArr[i] + "\n");
				writer.write(levelArr[i] + "\n");
				writer.write(winsArr[i] + "\n");
				writer.write(losesArr[i] + "\n");
				writer.write(biggestWinArr[i] + "\n");
				writer.write(biggestLossArr[i] + "\n");
				writer.write(profitArr[i] + "\n");
				writer.write(lossArr[i] + "\n");
			}
			writer.close();
		} catch (IOException iox) {
			System.out.println("File error!");
		}
	}

	public static void displayStats() {
		if (wins != 0 || loses != 0) {
			System.out.println("-------------STATISTICS-------------");
			System.out.printf("Your W/L %% is %.2f%%%n", winPercentage());

			System.out.printf("Your level is %d%n", level);

			if (profit != 0) {
				System.out.printf("%nYour total profit is $%.2f%n", profit);
			}

			if (loss != 0) {
				System.out.printf("Your total loss is $%.2f%n", loss);
			}

			if (biggestWin != 0) {
				System.out.printf("Your biggest win is $%.2f%n", biggestWin);
			}

			if (biggestLoss != 0) {
				System.out.printf("Your biggest loss is $%.2f%n", biggestLoss);
			}
			System.out.println("------------------------------------\n");
		}
	}

	public static void addLevel(double num) {
		level += (int) num;
	}
	public static void addWin(double cash) {
		wins++;
		if (cash > biggestWin) {
			biggestWin = cash;
		}
		profit += cash;
	}

	public static void addLoss(double cash) {
		loses++;
		if (cash > biggestLoss) {
			biggestLoss = cash;
		}
		loss += cash;
	}

	public static void increaseProfit(double cash) {
		profit += cash;
	}

	public static void increaseLoss(double cash) {
		loss += cash;
	}

	public static int getLevel() {
		return level;
	}

	public static int getWins() {
		return wins;
	}

	public static int getLoses() {
		return loses;
	}

	public static double getProfit() {
		return profit;
	}

	public static double getLoss() {
		return loss;
	}

	public static double getBiggestWin() {
		return biggestWin;
	}

	public static double getBiggestLoss() {
		return biggestLoss;
	}

	public static double winPercentage() {
		return (double) (wins / (wins + loses)) * 100;
		//double percentage = (double) (wins / (wins + loses)) * 100;
		//return percentage;
	}
}
