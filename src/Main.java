import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Position> trees = new ArrayList<>();
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int nTrees = sc.nextInt();
		for (int i=0;i<nTrees;i++) {
			trees.add(new Position(sc.nextInt()-1, sc.nextInt()-1));
		}
		
		// O(n^3*t)
		for (int squareLen = n; squareLen>=0; squareLen--) { // loop n times
			if (trySquare(squareLen)) {
				System.out.println(squareLen);
				return;
			}
		}
	}
	
	private static boolean trySquare(int squareLen) { // O(n^2*t)
		// TODO Auto-generated method stub
		for (int x=0;x<n;x++) { // loop n*n times
			for (int y=0;y<n;y++) {
				Position pos = new Position(x,y);
				if (tryPosLen(pos, squareLen)) {
					return true;
				}
			}
		}
		return false;
		
	}

	private static boolean tryPosLen(Position pos, int squareLen) { // O(t)
		// TODO Auto-generated method stub
		if (pos.x+squareLen>n || pos.y+squareLen>n) {
			return false;
		}
		
		if (containsTree(pos, squareLen)) {
			return false;
		}
		
		return true;
	}

	private static boolean containsTree(Position pos, int squareLen) {
		// TODO Auto-generated method stub
		for (Position treePos: trees) { // loop t times
			if (treePos.x>=pos.x && treePos.x<pos.x+squareLen && treePos.y>=pos.y && treePos.y<pos.y+squareLen)
				return true;
		}
		return false;
	}

}

class Position {
	int x,y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
