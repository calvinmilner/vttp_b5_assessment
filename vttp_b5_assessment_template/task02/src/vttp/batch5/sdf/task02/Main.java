package vttp.batch5.sdf.task02;

import java.io.File;

public class Main {

	public static void main(String[] args) throws Exception {
		// Map<List<Integer>, Integer> utility = new HashMap<>();
		int[] emptyPos = null;
		int utility = 0;
		if(args.length <= 0) {
			System.out.println("Please input a TTT configuration file");
		}
		String[] line = args[0].split("/");
		String dirPath = line[0];
		String fileName = line[1];
		TicTacToe ttt = new TicTacToe( dirPath + File.separator + fileName);
		ttt.readFile();
		emptyPos = ttt.checkEmptyPos();
		TicTacToe tttOld = ttt;
		ttt = new TicTacToe(fileName);
		ttt.place('X', emptyPos);
		utility = ttt.evaluate();
		System.out.printf("y=%d, x=%d, utility=%d\n", ttt.getPosY(), ttt.getPosX(), utility);

		// TicTacToe tttNew = ttt.clone();
		// utility.putIfAbsent(null, null);

	}
}
