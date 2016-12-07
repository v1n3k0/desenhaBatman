package vinicius28836.MODEL;


public class Cubo {
	private Point  p0;
	private Point  p1;
	private Point  p2;
	private Point  p3;
	
	public Cubo(Point p0, Point p1, Point p2, Point p3){
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public Point getP0(){
		return p0;
	}
	
	public Point getP1(){
		return p1;
	}
	
	public Point getP2(){
		return p2;
	}
	
	public Point getP3(){
		return p3;
	}

}
