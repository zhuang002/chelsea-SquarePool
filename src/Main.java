import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	static ArrayList<Position> xtrees = new ArrayList<>();
	static ArrayList<Position> ytrees = new ArrayList<>();
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		while (!xtrees.isEmpty()) {
			rect = removeATree(rect);
		}
		
		if (rect.width<rect.length) {
			System.out.println(rect.width);
		} else {
			System.out.println(rect.length);
		}
	}
	
	private static Rectangle removeATree(Rectangle rect) {
		// TODO Auto-generated method stub
		TryRemoveValue v1 = tryRemoveAnXTree(rect);
		TryRemoveValue v2 = tryRemoveAnYTree(rect);
		
		if (v1.rect.compareTo(v2.rect)<0) {
			xtrees.remove(v2.tree);
			ytrees.remove(v2.tree);
			return v2.rect;
		} else {
			xtrees.remove(v1.tree);
			ytrees.remove(v1.tree);
			return v1.rect;
		}
		
	}

	private static TryRemoveValue tryRemoveAnXTree(Rectangle rect) {
		// TODO Auto-generated method stub
		int lDistance = xtrees.get(0).x-rect.x;
		int rDistance = rect.x-xtrees.get(xtrees.size()-1).x;
		TryRemoveValue ret = new TryRemoveValue();
		if (lDistance < rDistance) {
			ret.tree = xtrees.get(0);
			ret.rect = new Rectangle(ret.tree.x+1, rect.y, rect.width-lDistance, rect.length);
			
		} else {
			ret.tree = xtrees.get(xtrees.size()-1);
			ret.rect = new Rectangle(rect.x, rect.y, rect.width-rDistance, rect.length);
			
		}
		return ret;
	}
	
	private static TryRemoveValue tryRemoveAnYTree(Rectangle rect) {
		// TODO Auto-generated method stub
		int lDistance = ytrees.get(0).y-rect.y;
		int rDistance = rect.y-ytrees.get(ytrees.size()-1).y;
		TryRemoveValue ret = new TryRemoveValue();
		if (lDistance < rDistance) {
			ret.tree = ytrees.get(0);
			ret.rect = new Rectangle(rect.x, ret.tree.y+1, rect.width, rect.length-lDistance);
			
		} else {
			ret.tree = ytrees.get(ytrees.size()-1);
			ret.rect = new Rectangle(rect.x, rect.y, rect.width, rect.length-rDistance);
			
		}
		return ret;
	}
	
}

class TryRemoveValue {
	Rectangle rect;
	Position tree;
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

	private int size() {
		// TODO Auto-generated method stub
		if (this.width<this.length)
			return this.width;
		else
			return this.length;
	}

	
	
	
}
