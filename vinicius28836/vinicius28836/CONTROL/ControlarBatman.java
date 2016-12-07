package vinicius28836.CONTROL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import vinicius28836.MODEL.Batman;
import vinicius28836.MODEL.Cubo;
import vinicius28836.MODEL.Point;
import vinicius28836.MODEL.Reta;

public class ControlarBatman {
	
	private Graphics  			desenho;
	private ControlarBezier 	controleBezier;
	private ControlarHermite	controleHermite;
	private ControlarReta		controleReta;
	
	private Dimension			dimensao;
	private int 				largura;
	private int					altura;
	
	//*************************************************************************************
	
	public ControlarBatman ( Graphics desenho, Dimension dimensao)
	{
		this.desenho = desenho;
		this.largura = dimensao.width;
		this.altura = dimensao.height;
		this.dimensao = dimensao;
	}
	
	//*************************************************************************************
	
	//Ponto para desenho do Batman com Bezier
	public Batman batmanBezier(){
		Point p0, p1, p2, p3;
		Cubo c0, c1, c2, c3, c4, c5, c6, c7, c8;
		Reta r0, r1;		
		
		//Rabo inferior esquerdo
		p0 = new Point(683,400); // Maior Y
		p1 = new Point(640,320);
		p2 = new Point(595,320);
		p3 = new Point(580,360);
		c0 = new Cubo(p0, p1, p2, p3);
		
		
		//Rabo inferior direito
		p0 = new Point(683,400); // Maior Y
		p1 = new Point(726,320);
		p2 = new Point(771,320);
		p3 = new Point(786,360);
		c1 = new Cubo(p0, p1, p2, p3);
		
		//asa inferior esquerdo
		p0 = new Point(580,360);
		p1 = new Point(518,320);
		p2 = new Point(488,345);
		p3 = new Point(508,378);
		c2 = new Cubo(p0, p1, p2, p3);
		
		//asa inferior direito
		p0 = new Point(786,360);
		p1 = new Point(848,320);
		p2 = new Point(878,345);
		p3 = new Point(858,378);
		c3 = new Cubo(p0, p1, p2, p3);
		
		//asa esquerda
		p0 = new Point(508,378);// Menor X
		p1 = new Point(395,330);
		p2 = new Point(395,240);
		p3 = new Point(580,170);
		c4 = new Cubo(p0, p1, p2, p3);
		
		//asa direita
		p0 = new Point(858,378);// Maior X
		p1 = new Point(971,330);
		p2 = new Point(971,240);
		p3 = new Point(786,170);
		c5 = new Cubo(p0, p1, p2, p3);
		
		//orelha esquerda
		p0 = new Point(580,170);// Menor y
		p1 = new Point(550,245);
		p2 = new Point(645,245);
		p3 = new Point(640,170);
		c6 = new Cubo(p0, p1, p2, p3);
		
		//orelha direita
		p0 = new Point(786,170);
		p1 = new Point(816,245);
		p2 = new Point(721,245);
		p3 = new Point(726,170);
		c7 = new Cubo(p0, p1, p2, p3);
		
		//cabeça orelha esquerda
		p0 = new Point(640,170);
		p1 = new Point(658,187);
		r0 = new Reta(p0, p1);
		
		//cabeça orelha direita
		p0 = new Point(726,170);
		p1= new Point(708,187);
		r1 = new Reta(p0, p1);
		
		//Cabeça
		p0 = new Point(658,187);
		p1 = new Point(674,180);
		p2 = new Point(692,180);
		p3 = new Point(708,187);
		c8 = new Cubo(p0, p1, p2, p3);
		
		 return  new Batman(c0, c1, c2, c3, c4, c5, c6, c7, r0, r1, c8);
		
	}
	
	//*************************************************************************************
	
	void desenhaBezier(Batman batman, Color cor){
		Cubo cAux;
		Reta rAux;
		
		//inicializar controle de Bezier e Reta
		controleBezier = new ControlarBezier(desenho);
		controleReta = new ControlarReta(desenho);
		
		//limpa tela
		desenho.setColor(Color.black);
		desenho.fillRect(0, 0, largura, altura);
		
		desenho.setColor(Color.yellow);
		//Desenhar as curvas e retas		
		cAux = batman.getC0();
		controleBezier.desenha(cAux);
		
		cAux = batman.getC1();
		controleBezier.desenha(cAux);
		
		cAux = batman.getC2();
		controleBezier.desenha(cAux);
		
		cAux = batman.getC3();
		controleBezier.desenha(cAux);
		
		cAux = batman.getC4();
		controleBezier.desenha(cAux);
		
		cAux = batman.getC5();
		controleBezier.desenha(cAux);

		cAux = batman.getC6();
		controleBezier.desenha(cAux);
		
		cAux = batman.getC7();
		controleBezier.desenha(cAux);
		
		rAux = batman.getR0();
		controleReta.desenhar(rAux);
		
		rAux = batman.getR1();
		controleReta.desenhar(rAux);
		
		cAux = batman.getC8();
		controleBezier.desenha(cAux);
	}
	
	//*************************************************************************************
	
	public byte colisaoBezier(Batman batman){
		Cubo c0, c4, c5;
		byte colAux, colisao = 0;
		
		c0 = batman.getC0();
		colAux = controleBezier.valida(c0.getP0(), dimensao);
		if(colAux > 0) colisao = colAux;
		c4 = batman.getC4();
		colAux = controleBezier.colisao(c4, dimensao);
		if(colAux > 0) colisao = colAux;
		c5 = batman.getC5();
		colAux = controleBezier.colisao(c5, dimensao);
		if(colAux > 0) colisao = colAux;
		
		return colisao;
	}
	
	//*************************************************************************************
	
	public Batman escalaBezier(Batman batman, float escala){
		Cubo c0, c1, c2, c3, c4, c5, c6, c7, c8;
		Reta r0, r1;
		Cubo cAux;
		Reta rAux;
		
		cAux = batman.getC0();
		c0 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC1();
		c1 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC2();
		c2 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC3();
		c3 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC4();
		c4 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC5();
		c5 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC6();
		c6 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC7();
		c7 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		rAux = batman.getR0();
		r0 = controleReta.escala(rAux, escala, batman.getCentro());
		
		rAux = batman.getR1();
		r1 = controleReta.escala(rAux, escala, batman.getCentro());
		
		cAux = batman.getC8();
		c8 = controleBezier.escala(cAux, escala, batman.getCentro());
		
		return  new Batman(c0, c1, c2, c3, c4, c5, c6, c7, r0, r1, c8);
	}
	
	//*************************************************************************************
	
	public Batman translacaoBezier(Batman batman, Point novoPonto){
		Cubo c0, c1, c2, c3, c4, c5, c6, c7, c8;
		Reta r0, r1;
		Cubo cAux;
		Reta rAux;
		Point novoCentro;
		
		novoCentro = new Point( novoPonto.x() - batman.getCentro().x(), novoPonto.y() - batman.getCentro().y());
		
		cAux = batman.getC0();
		c0 = controleBezier.translacao(cAux, novoCentro);
		
		cAux = batman.getC1();
		c1 = controleBezier.translacao(cAux, novoCentro);
		
		cAux = batman.getC2();
		c2 = controleBezier.translacao(cAux, novoCentro);
		
		cAux = batman.getC3();
		c3 = controleBezier.translacao(cAux, novoCentro);
		
		cAux = batman.getC4();
		c4 = controleBezier.translacao(cAux, novoCentro);
		
		cAux = batman.getC5();
		c5 = controleBezier.translacao(cAux, novoCentro);
		
		cAux = batman.getC6();
		c6 = controleBezier.translacao(cAux, novoCentro);
		
		cAux = batman.getC7();
		c7 = controleBezier.translacao(cAux, novoCentro);
		
		rAux = batman.getR0();
		r0 = controleReta.translacao(rAux, novoCentro);
		
		rAux = batman.getR1();
		r1 = controleReta.translacao(rAux, novoCentro);
		
		cAux = batman.getC8();
		c8 = controleBezier.translacao(cAux, novoCentro);
		
		return new Batman(c0, c1, c2, c3, c4, c5, c6, c7, r0, r1, c8);
	}
		
	//*************************************************************************************
	
	public Batman rotacaoBezier(Batman batman, int angulo){
		Cubo c0, c1, c2, c3, c4, c5, c6, c7, c8;
		Reta r0, r1;
		Cubo cAux;
		Reta rAux;
		Point origem;
		
		origem = batman.getCentro();
		
		cAux = batman.getC0();
		c0 = controleBezier.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC1();
		c1 = controleBezier.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC2();
		c2 = controleBezier.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC3();
		c3 = controleBezier.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC4();
		c4 = controleBezier.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC5();
		c5 = controleBezier.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC6();
		c6 = controleBezier.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC7();
		c7 = controleBezier.rotacao(cAux, angulo, origem);
		
		rAux = batman.getR0();
		r0 = controleReta.rotacao(rAux, angulo, origem);
		
		rAux = batman.getR1();
		r1 = controleReta.rotacao(rAux, angulo, origem);
		
		cAux = batman.getC8();
		c8 = controleBezier.rotacao(cAux, angulo, origem);
		
		return new Batman(c0, c1, c2, c3, c4, c5, c6, c7, r0, r1, c8);
	}
	
	//*************************************************************************************
	
	//Ponto para desenho Batman com Hermite
	public Batman batmanHermite(){
		Point p0, p1, p2, p3;
		Cubo c0, c1, c2, c3, c4, c5, c6, c7, c8;
		Reta r0, r1;		
		
		//Rabo inferior esquerdo
		p0 = new Point(683,400);// Maior Y
		p1 = new Point(615,165);
		p2 = new Point(580,360);
		p3 = new Point(570,445);
		c0 = new Cubo(p0, p1, p2, p3);
		
		
		//Rabo inferior direito
		p0 = new Point(683,400);// Maior Y
		p1 = new Point(751,165);
		p2 = new Point(786,360);
		p3 = new Point(796,445);
		c1 = new Cubo(p0, p1, p2, p3);
		
		//asa inferior esquerdo
		p0 = new Point(580,360);
		p1 = new Point(415,270);
		p2 = new Point(508,378);
		p3 = new Point(565,470);
		c2 = new Cubo(p0, p1, p2, p3);
		
		//asa inferior direito
		p0 = new Point(786,360);
		p1 = new Point(951,270);
		p2 = new Point(858,378);
		p3 = new Point(801,470);
		c3 = new Cubo(p0, p1, p2, p3);
		
		//asa esquerda
		p0 = new Point(508,378);// Menor X
		p1 = new Point(105,270);
		p2 = new Point(580,170);
		p3 = new Point(1030,35);
		c4 = new Cubo(p0, p1, p2, p3);
		
		//asa direita
		p0 = new Point(858,378);// Maior X
		p1 = new Point(1261,270);
		p2 = new Point(786,170);
		p3 = new Point(336,35);
		c5 = new Cubo(p0, p1, p2, p3);
		
		//orelha esquerda
		p0 = new Point(580,170);// Menor y
		p1 = new Point(500,440);
		p2 = new Point(640,170);
		p3 = new Point(620,0);
		c6 = new Cubo(p0, p1, p2, p3);
		
		//orelha direita
		p0 = new Point(786,170);
		p1 = new Point(866,440);
		p2 = new Point(726,170);
		p3 = new Point(746,0);
		c7 = new Cubo(p0, p1, p2, p3);
		
		//cabeça orelha esquerda
		p0 = new Point(640,170);
		p1 = new Point(658,187);
		r0 = new Reta(p0, p1);
		
		//cabeça orelha direita
		p0 = new Point(726,170);
		p1= new Point(708,187);
		r1 = new Reta(p0, p1);
		
		//Cabeça
		p0 = new Point(658,187);
		p1 = new Point(665,168);
		p2 = new Point(708,187);
		p3 = new Point(720,210);
		c8 = new Cubo(p0, p1, p2, p3);
		
		 return  new Batman(c0, c1, c2, c3, c4, c5, c6, c7, r0, r1, c8);
		
	}
	
	//*************************************************************************************
	
	void desenhaHermite(Batman batman, Color cor){
		Cubo cAux;
		Reta rAux;
		
		//inicializar controle de Bezier e Reta
		controleHermite = new ControlarHermite(desenho);
		controleReta = new ControlarReta(desenho);
		
		//limpa tela
		desenho.setColor(Color.black);
		desenho.fillRect(0, 0, largura, altura);
		
		desenho.setColor(Color.yellow);
		//Desenhar as curvas e retas		
		cAux = batman.getC0();
		controleHermite.desenha(cAux);
		
		cAux = batman.getC1();
		controleHermite.desenha(cAux);
		
		cAux = batman.getC2();
		controleHermite.desenha(cAux);
		
		cAux = batman.getC3();
		controleHermite.desenha(cAux);
		
		cAux = batman.getC4();
		controleHermite.desenha(cAux);
		
		cAux = batman.getC5();
		controleHermite.desenha(cAux);
		
		cAux = batman.getC6();
		controleHermite.desenha(cAux);
		
		cAux = batman.getC7();
		controleHermite.desenha(cAux);
		
		rAux = batman.getR0();
		controleReta.desenhar(rAux);
		
		rAux = batman.getR1();
		controleReta.desenhar(rAux);
		
		cAux = batman.getC8();
		controleHermite.desenha(cAux);
	}
	
	//*************************************************************************************
	
	public byte colisaoHermite(Batman batman){
		Cubo c0, c4, c5;
		byte colAux, colisao = 0;
		
		c0 = batman.getC0();
		colAux = controleHermite.valida(c0.getP0(), dimensao);
		if(colAux > 0) colisao = colAux;
		c4 = batman.getC4();
		colAux = controleHermite.colisao(c4, dimensao);
		if(colAux > 0) colisao = colAux;
		c5 = batman.getC5();
		colAux = controleHermite.colisao(c5, dimensao);
		if(colAux > 0) colisao = colAux;
		
		return colisao;
	}
	
	//*************************************************************************************
	
	public Batman escalaHermite(Batman batman, float escala){
		Cubo c0, c1, c2, c3, c4, c5, c6, c7, c8;
		Reta r0, r1;
		Cubo cAux;
		Reta rAux;
		
		cAux = batman.getC0();
		c0 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC1();
		c1 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC2();
		c2 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC3();
		c3 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC4();
		c4 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC5();
		c5 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC6();
		c6 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		cAux = batman.getC7();
		c7 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		rAux = batman.getR0();
		r0 = controleReta.escala(rAux, escala, batman.getCentro());
		
		rAux = batman.getR1();
		r1 = controleReta.escala(rAux, escala, batman.getCentro());
		
		cAux = batman.getC8();
		c8 = controleHermite.escala(cAux, escala, batman.getCentro());
		
		return  new Batman(c0, c1, c2, c3, c4, c5, c6, c7, r0, r1, c8);
	}
	
	//*************************************************************************************
	
	public Batman translacaoHermite(Batman batman, Point novoPonto){
		Cubo c0, c1, c2, c3, c4, c5, c6, c7, c8;
		Reta r0, r1;
		Cubo cAux;
		Reta rAux;
		Point novoCentro;
		
		novoCentro = new Point( novoPonto.x() - batman.getCentro().x(), novoPonto.y() - batman.getCentro().y());
		
		cAux = batman.getC0();
		c0 = controleHermite.translacao(cAux, novoCentro);
		
		cAux = batman.getC1();
		c1 = controleHermite.translacao(cAux, novoCentro);
		
		cAux = batman.getC2();
		c2 = controleHermite.translacao(cAux, novoCentro);
		
		cAux = batman.getC3();
		c3 = controleHermite.translacao(cAux, novoCentro);
		
		cAux = batman.getC4();
		c4 = controleHermite.translacao(cAux, novoCentro);
		
		cAux = batman.getC5();
		c5 = controleHermite.translacao(cAux, novoCentro);
		
		cAux = batman.getC6();
		c6 = controleHermite.translacao(cAux, novoCentro);
		
		cAux = batman.getC7();
		c7 = controleHermite.translacao(cAux, novoCentro);
		
		rAux = batman.getR0();
		r0 = controleReta.translacao(rAux, novoCentro);
		
		rAux = batman.getR1();
		r1 = controleReta.translacao(rAux, novoCentro);
		
		cAux = batman.getC8();
		c8 = controleHermite.translacao(cAux, novoCentro);
		
		return new Batman(c0, c1, c2, c3, c4, c5, c6, c7, r0, r1, c8);
	}
	
	//*************************************************************************************
	
	public Batman rotacaoHermite(Batman batman, int angulo){
		Cubo c0, c1, c2, c3, c4, c5, c6, c7, c8;
		Reta r0, r1;
		Cubo cAux;
		Reta rAux;
		Point origem;
		
		origem = batman.getCentro();
		
		cAux = batman.getC0();
		c0 = controleHermite.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC1();
		c1 = controleHermite.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC2();
		c2 = controleHermite.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC3();
		c3 = controleHermite.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC4();
		c4 = controleHermite.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC5();
		c5 = controleHermite.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC6();
		c6 = controleHermite.rotacao(cAux, angulo, origem);
		
		cAux = batman.getC7();
		c7 = controleHermite.rotacao(cAux, angulo, origem);
		
		rAux = batman.getR0();
		r0 = controleReta.rotacao(rAux, angulo, origem);
		
		rAux = batman.getR1();
		r1 = controleReta.rotacao(rAux, angulo, origem);
		
		cAux = batman.getC8();
		c8 = controleHermite.rotacao(cAux, angulo, origem);
		
		return new Batman(c0, c1, c2, c3, c4, c5, c6, c7, r0, r1, c8);
	}
	
	//*************************************************************************************
}
