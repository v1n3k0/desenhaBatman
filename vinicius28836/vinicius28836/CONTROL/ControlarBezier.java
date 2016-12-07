package vinicius28836.CONTROL;

import java.awt.Dimension;
import java.awt.Graphics;

import vinicius28836.MODEL.Cubo;
import vinicius28836.MODEL.Point;
import vinicius28836.MODEL.Reta;

public class ControlarBezier {
	private Graphics  		desenho;
	private ControlarReta 	controleReta;
	
	//*************************************************************************************
	
	public ControlarBezier ( Graphics desenho)
	{
		this.desenho = desenho;
	}
	
	//*************************************************************************************	
	
	//Desenha a curva de Bezier
	public void desenha(Cubo bezier) {
		int n = 200;
		Reta reta;
		Point[] p = new Point[4];
		
		p[0] = bezier.getP0();
		p[1] = bezier.getP1();
		p[2] = bezier.getP2();
		p[3] = bezier.getP3();
		
		controleReta = new ControlarReta(desenho);
		
		float 	dt = 1.0F / n, 
				cx3 = -p[0].x() + 3 * (p[1].x() - p[2].x()) + p[3].x(),
				cy3 = -p[0].y() + 3 * (p[1].y() - p[2].y()) + p[3].y(), 
				cx2 = 3 * (p[0].x() - 2 * p[1].x() + p[2].x()),
				cy2 = 3 * (p[0].y() - 2 * p[1].y() + p[2].y()), 
				cx1 = 3 * (p[1].x() - p[0].x()), 
				cy1 = 3 * (p[1].y() - p[0].y()),
				cx0 = p[0].x(), 
				cy0 = p[0].y(), 
				x = p[0].x(), 
				y = p[0].y(), 
				x0, y0;
		
		for (int i = 1; i <= n; i++) {
			float t = i * dt;
			x0 = x;
			y0 = y;
			x = ((cx3 * t + cx2) * t + cx1) * t + cx0;
			y = ((cy3 * t + cy2) * t + cy1) * t + cy0;
			reta = new Reta(x0, y0, x, y);
			controleReta.desenhar(reta);
		}
	}
	
	//*************************************************************************************
	
	//Verifica se os pontos não passaram dos limites estabelecidos
	public byte valida(Point ponto, Dimension dimensao){
		byte colisao = 0;
		
		if(ponto.y() < 0) colisao = 1;
		if(ponto.x() > dimensao.getWidth()) colisao = 2;
		if(ponto.y() > dimensao.getHeight()) colisao = 3;
		if(ponto.x() < 0) colisao = 4;
		
		return colisao;
	}
	
	//*************************************************************************************
	
	//Verifica se houve colisão na curva Bezier
	public byte colisao(Cubo bezier, Dimension dimensao){
		int n = 200;
		Point[] p = new Point[4];
		
		p[0] = bezier.getP0();
		p[1] = bezier.getP1();
		p[2] = bezier.getP2();
		p[3] = bezier.getP3();
		
		controleReta = new ControlarReta(desenho);
		
		float 	dt = 1.0F / n, 
				cx3 = -p[0].x() + 3 * (p[1].x() - p[2].x()) + p[3].x(),
				cy3 = -p[0].y() + 3 * (p[1].y() - p[2].y()) + p[3].y(), 
				cx2 = 3 * (p[0].x() - 2 * p[1].x() + p[2].x()),
				cy2 = 3 * (p[0].y() - 2 * p[1].y() + p[2].y()), 
				cx1 = 3 * (p[1].x() - p[0].x()), 
				cy1 = 3 * (p[1].y() - p[0].y()),
				cx0 = p[0].x(), 
				cy0 = p[0].y(), 
				x = p[0].x(), 
				y = p[0].y(), 
				x0, y0;
		
		byte colisao = 0;
		
		for (int i = 1; i <= n; i++){
			float t = i * dt;
			x0 = x;
			y0 = y;
			colisao = valida(new Point(x0,y0), dimensao);
            if(colisao != 0) return colisao;
			x = ((cx3 * t + cx2) * t + cx1) * t + cx0;
			y = ((cy3 * t + cy2) * t + cy1) * t + cy0;
		}
		return 0;
	}
	
	//*************************************************************************************
	
	public Cubo escala(Cubo cubo, float fator, Point org){
		
		Point p0, p1, p2, p3;
		float auxX, auxY;
		
		p0 = cubo.getP0();
		p1 = cubo.getP1();
		p2 = cubo.getP2();
		p3 = cubo.getP3();
		
		// Calculo para p0
		auxX = (p0.x() - org.x()) * fator + org.x();
		auxY = (p0.y() - org.y()) * fator + org.y();
		p0 = new Point( auxX, auxY );
		// Calculo para p1
		auxX = (p1.x() - org.x()) * fator + org.x();
		auxY = (p1.y() - org.y()) * fator + org.y();
		p1 = new Point( auxX, auxY );
		// Calculo para p2
		auxX = (p2.x() - org.x()) * fator + org.x();
		auxY = (p2.y() - org.y()) * fator + org.y();
		p2 = new Point( auxX, auxY );
		// Calculo para p3
		auxX = (p3.x() - org.x()) * fator + org.x();
		auxY = (p3.y() - org.y()) * fator + org.y();
		p3 = new Point( auxX, auxY );
		
		
		return new Cubo(p0, p1, p2, p3);
		
	}

	//*************************************************************************************
	
	public Cubo translacao(Cubo cubo, Point translacao){
		Point p0, p1, p2, p3;
		float auxX, auxY;

		p0 = cubo.getP0();
		p1 = cubo.getP1();
		p2 = cubo.getP2();
		p3 = cubo.getP3();
		
		// Calculo para p0
		auxX = p0.x() + translacao.x();
		auxY = p0.y() + translacao.y();
		p0 = new Point( auxX, auxY );
		// Calculo para p1
		auxX = p1.x() + translacao.x();
		auxY = p1.y() + translacao.y();
		p1 = new Point( auxX, auxY );
		// Calculo para p2
		auxX = p2.x() + translacao.x();
		auxY = p2.y() + translacao.y();
		p2 = new Point( auxX, auxY );
		// Calculo para p3
		auxX = p3.x() + translacao.x();
		auxY = p3.y() + translacao.y();
		p3 = new Point( auxX, auxY );
		
		
		return 	 new Cubo(p0, p1, p2, p3);
	}
	
	//*************************************************************************************
	
	public Cubo rotacao(Cubo cubo, int angulo, Point org){
		
		Point  p0, p1, p2, p3;
		float auxX, auxY, cos, sen;
		

		p0 = cubo.getP0();
		p1 = cubo.getP1();
		p2 = cubo.getP2();
		p3 = cubo.getP3();
		
		// Calculo do cos e sen
		cos = (float) Math.cos( Math.toRadians(angulo) );
		sen = (float) Math.sin( Math.toRadians(angulo) );
		
		// Calculo para p0
		auxX = org.x() + (p0.x() - org.x()) * cos - (p0.y() - org.y()) * sen;
		auxY = org.y() + (p0.y() - org.y()) * cos + (p0.x() - org.x()) * sen;
		p0 = new Point( auxX, auxY );
		// Calculo para p1
		auxX = org.x() + (p1.x() - org.x()) * cos - (p1.y() - org.y()) * sen;
		auxY = org.y() + (p1.y() - org.y()) * cos + (p1.x() - org.x()) * sen;
		p1 = new Point( auxX, auxY );
		// Calculo para p2
		auxX = org.x() + (p2.x() - org.x()) * cos - (p2.y() - org.y()) * sen;
		auxY = org.y() + (p2.y() - org.y()) * cos + (p2.x() - org.x()) * sen;
		p2 = new Point( auxX, auxY );
		// Calculo para p3
		auxX = org.x() + (p3.x() - org.x()) * cos - (p3.y() - org.y()) * sen;
		auxY = org.y() + (p3.y() - org.y()) * cos + (p3.x() - org.x()) * sen;
		p3 = new Point( auxX, auxY );
		
				
		return new Cubo(p0, p1, p2, p3);
	}
	
	//*************************************************************************************
}
