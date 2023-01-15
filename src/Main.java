import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Position> xtrees = new ArrayList<>();
		ArrayList<Position> ytrees = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int nTrees = sc.nextInt();
		for (int i=0;i<nTrees;i++) {
			Position tree = new Position(sc.nextInt()-1, sc.nextInt()-1);
			xtrees.add(tree);
			ytrees.add(tree);
		}
		
		Collections.sort(xtrees, (x,y)->x.x-y.x);
		Collections.sort(ytrees, (x,y)->x.y-y.y);

		Rectangle rect = new Rectangle(0,0,n,n);
		
		rect = getMaxRect(rect, xtrees, ytrees);
		System.out.println(rect.size());
		
	}
	
	

	private static Rectangle getMaxRect(Rectangle rect, ArrayList xtrees,
			ArrayList ytrees) {
		// TODO Auto-generated method stub
		Position tree = (Position)xtrees.get(0);
		Rectangle rect1 = new Rectangle(tree.x, rect.y, rect.width-(tree.x-rect.x), rect.length);
		ArrayList newXTrees = (ArrayList)xtrees.clone();
		newXTrees.remove(tree);
		ArrayList newYTrees = (ArrayList)ytrees.clone();
		newYTrees.remove(tree);
		rect1 = getMaxRect(rect1, newXTrees, newYTrees);
		
		tree = (Position)xtrees.get(xtrees.size()-1);
		Rectangle rect2 = new Rectangle(rect.x, rect.y, rect.width-(rect.x-tree.x), rect.length);
		newXTrees = (ArrayList)xtrees.clone();
		newXTrees.remove(tree);
		newYTrees = (ArrayList)ytrees.clone();
		newYTrees.remove(tree);
		rect2 = getMaxRect(rect2, newXTrees, newYTrees);
		
		tree = (Position)ytrees.get(0);
		Rectangle rect3 = new Rectangle(rect.x, tree.y, rect.width, rect.length-(tree.y-rect.y));
		newXTrees = (ArrayList)xtrees.clone();
		newXTrees.remove(tree);
		newYTrees = (ArrayList)ytrees.clone();
		newYTrees.remove(tree);
		rect3 = getMaxRect(rect3, newXTrees, newYTrees);
		
		tree = (Position)ytrees.get(ytrees.size()-1);
		Rectangle rect4 = new Rectangle(rect.x, rect.y, rect.width, rect.length-(rect.y-tree.y));
		newXTrees = (ArrayList)xtrees.clone();
		newXTrees.remove(tree);
		newYTrees = (ArrayList)ytrees.clone();
		newYTrees.remove(tree);
		rect4 = getMaxRect(rect4, newXTrees, newYTrees);
		
		int size = 0;
		if (rect1.size()>size) {
			size = rect1.size();
		}
		if (rect2.size()>size) {
			size = rect2.size();
		}
		if (rect3.size()>size) {
			size = rect3.size();
		}
		if (rect4.size()>size) {
			size = rect4.size();
		}
		
		return size;
	}

}

class Position {
	int x,y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Rectangle implements Comparable<Rectangle> {
	int x, y;
	int width, length;
	
	public Rectangle(int x, int y, int width, int length) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
	}

	@Override
	public int compareTo(Rectangle o) {
		// TODO Auto-generated method stub
		return this.size() - o.size();
	}

	public int size() {
		// TODO Auto-generated method stub
		if (this.width<this.length)
			return this.width;
		else
			return this.length;
	}

	
	
	
}
