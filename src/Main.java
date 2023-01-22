import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Main {

	static ArrayList<Position> xtrees = new ArrayList<>();
	static ArrayList<Position> ytrees = new ArrayList<>();
	static HashMap<Rectangle, Rectangle> cache = new HashMap<>();
	
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
		
		rect = getMaxRect(rect);
		System.out.println(rect.size());
		
	}
	
	

	private static Rectangle getMaxRect(Rectangle rect) {
		// TODO Auto-generated method stub
		if (cache.containsKey(rect))
			return cache.get(rect);
		
		Position tree = getTree(xtrees, rect, 0, 1);
		int size = 0;
		Rectangle ret = null;
		
		
		if (tree==null) {
			cache.put(rect, rect);
			return rect;
		}
		
		// x direction from 0;
		Rectangle rect1 = new Rectangle(tree.x+1, rect.y, rect.width-(tree.x+1-rect.x), rect.length);
		Rectangle rect2 = new Rectangle(rect.x, tree.x-1, tree.x-rect.x-1, rect.length);
		if (rect2.size() > rect1.size()) {
			cache.put(rect, rect2);
			return rect2;
		}
		rect1 = getMaxRect(rect1);
		if (rect1.size()>size) {
			size = rect1.size();
			ret = rect1;
		}
		
		
		// x direction from right
		tree = getTree(xtrees, rect, xtrees.size()-1, -1);

		rect1 = new Rectangle(rect.x, rect.y, tree.x-rect.x, rect.length);
		rect2 = new Rectangle(tree.x+1, rect.y, rect.x+rect.width-(tree.x+1), rect.length);
		if (rect2.size() > rect1.size()) {
			cache.put(rect, rect2);
			return rect2;
		}
		rect1 = getMaxRect(rect1);
		if (rect1.size()>size) {
			size = rect1.size();
			ret = rect1;
		}

		// y direction from 0
		tree =  getTree(ytrees, rect, 0, 1);  
		rect1 = new Rectangle(rect.x, tree.y+1, rect.width, rect.length-(tree.y+1-rect.y));
		rect2 = new Rectangle(rect.x, rect.y, rect.width, tree.y-rect.y-1);
		if (rect2.size() > rect1.size()) {
			cache.put(rect, rect2);
			return rect2;
		}
		rect1 = getMaxRect(rect1);
		if (rect1.size()>size) {
			size = rect1.size();
			ret = rect1;
		}

		
		// y direction from right
		tree = getTree(ytrees, rect, ytrees.size()-1, -1); 
		rect1 = new Rectangle(rect.x, rect.y, rect.width, tree.y-rect.y);
		rect2 = new Rectangle(rect.x, tree.y+1, rect.width, rect.y+rect.length-(tree.y-1));
		if (rect2.size() > rect1.size()) {
			cache.put(rect, rect2);
			return rect2;
		}
		rect1 = getMaxRect(rect1);
		if (rect1.size()>size) {
			size = rect1.size();
			ret = rect1;
		}

		
		cache.put(rect, ret);
		return ret;
	}



	private static Position getTree(ArrayList<Position> trees, Rectangle rect, int start, int step) {
		// TODO Auto-generated method stub
		for (int i=start; i>=0 && i<trees.size();i+=step) {
			Position tree = trees.get(i);
			if (rect.x <= tree.x && rect.x+rect.width-1 >= tree.x 
					&& rect.y <= tree.y && rect.y+rect.length-1 >= tree.y) {
				return tree;
			}
		}
		return null;
	}

}

class Position {
	int x,y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length;
		result = prime * result + width;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		if (length != other.length)
			return false;
		if (width != other.width)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	
	
	
}
