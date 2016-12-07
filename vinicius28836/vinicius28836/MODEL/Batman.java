package vinicius28836.MODEL;


public class Batman {
	private Cubo c0;//Rabo inferior esquerdo
	private Cubo c1;//Rabo inferior direito
	private Cubo c2;//asa inferior esquerdo
	private Cubo c3;//asa inferior direito
	private Cubo c4;//asa esquerda
	private Cubo c5;//asa direita
	private Cubo c6;//orelha esquerda
	private Cubo c7;//orelha direita
	private Reta r0;//cabeça orelha esquerda
	private Reta r1;//cabeça orelha direita
	private Cubo c8;//Cabeça
	
	public Batman(Cubo c0, Cubo c1, Cubo c2, Cubo c3, 
			Cubo c4, Cubo c5, Cubo c6, Cubo c7,
			Reta r0, Reta r1, Cubo c8){
		this.c0 = c0;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.c6 = c6;
		this.c7 = c7;
		this.r0 = r0;
		this.r1 = r1;
		this.c8 = c8;
		
	}

	public Cubo getC0() {
		return c0;
	}

	public Cubo getC1() {
		return c1;
	}

	public Cubo getC2() {
		return c2;
	}

	public Cubo getC3() {
		return c3;
	}

	public Cubo getC4() {
		return c4;
	}

	public Cubo getC5() {
		return c5;
	}

	public Cubo getC6() {
		return c6;
	}

	public Cubo getC7() {
		return c7;
	}
	
	public Cubo getC8() {
		return c8;
	}

	public Reta getR0() {
		return r0;
	}

	public Reta getR1() {
		return r1;
	}
	
	public Point getCentro(){
		return new Point ( (c5.getP0().x() - c4.getP0().x())/2 + c4.getP0().x(), (c0.getP0().y() - c6.getP0().y() )/2 + c6.getP0().y() );
	}
	
}
