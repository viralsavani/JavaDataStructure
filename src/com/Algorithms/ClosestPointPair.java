package com.Algorithms;

import java.util.List;



public class ClosestPointPair {

	public static class Point {

		public final double x;
		public final double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {  
			return "(" + x + ", " + y + ")";  }
	}


	public static class Pair {

		public Point point1 = null;
		public Point point2 = null;
		public double distance = 0.0;

		public Pair(){  

		}

		public Pair(Point point1, Point point2) {
			this.point1 = point1;
			this.point2 = point2;
			calcDistance();
		}

		public void update(Point point1, Point point2, double distance) {
			this.point1 = point1;
			this.point2 = point2;
			this.distance = distance;
		}

		public void calcDistance() {  
			this.distance = distance(point1, point2);  
		}

		public String toString() {  
			return point1 + "-" + point2 + " : " + distance;  
		}
	}



	public static double distance(Point p1, Point p2) {
		double xdist = p2.x - p1.x;
		double ydist = p2.y - p1.y;
		return Math.hypot(xdist, ydist);
	}



	public static Pair bruteForce(List<? extends Point> points) {
		int numPoints = points.size();
		if (numPoints < 2){
			return null;
		}else{
			Pair pair = new Pair(points.get(0), points.get(1));
			if (numPoints > 2)
			{
				for (int i = 0; i < numPoints - 1; i++)
				{
					Point point1 = points.get(i);
					for (int j = i + 1; j < numPoints; j++)
					{
						Point point2 = points.get(j);
						double distance = distance(point1, point2);
						if (distance < pair.distance)
							pair.update(point1, point2, distance);
					}
				}
			}
			return pair;
		}
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
