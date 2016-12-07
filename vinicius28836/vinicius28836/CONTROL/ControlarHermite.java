package vinicius28836.CONTROL;

import java.awt.Dimension;
import java.awt.Graphics;

import vinicius28836.MODEL.Cubo;
import vinicius28836.MODEL.Point;
import vinicius28836.MODEL.Reta;

public class ControlarHermite {
	private Graphics  		desenho;
	private ControlarReta 	controleReta;
	
	//*************************************************************************************
	
	public ControlarHermite ( Graphics desenho){
		this.desenho = desenho;
	}
	
	//*************************************************************************************
	
	//Calculo da matriz
	public Point[] calculo(Point p0 , Point p1, Point p2, Point p3) {   
        Point c0, c1, c2, c3;
        
        c3 = new Point( 
        	(2*p0.x()) + (-2*p2.x()) + (1*p1.x()) + (1*p3.x()),
        	(2*p0.y()) + (-2*p2.y()) + (1*p1.y()) + (1*p3.y()) 
        );
        c2 = new Point( 
        	(-3*p0.x()) + (3*p2.x()) + (-2*p1.x()) + (-1*p3.x()),
        	(-3*p0.y()) + (3*p2.y()) + (-2*p1.y()) + (-1*p3.y()) 
        );
        c1 = new Point( 
        	(0) + (0) + (1*p1.x()) + (0),
        	(0) + (0) + (1*p1.y()) + (0) 
        );
        c0 = new Point( 
        	(1*p0.x()) + (0) + (0) + (0),
        	(1*p0.y()) + (0) + (0) + (0) 
        ); 

        Point array[] = {c0, c1, c2, c3};
        return array;

    }

	//*************************************************************************************
	
	//Desenha curva de Hermite
	public void desenha(Cubo cubo){
		Point p0, p1, p2, p3, p1Aux, p3Aux;
		Reta reta;
		double passo = 0.001;
		
		p0 = cubo.getP0();
		p1 = cubo.getP1();
		p2 = cubo.getP2();
		p3 = cubo.getP3();
		
		
		controleReta = new ControlarReta(desenho);
		
        p1Aux = new Point( p1.x() - p0.x(), p1.y() - p0.y() );
        p3Aux = new Point( p3.x() - p2.x(), p3.y() - p2.y() );
        
        Point array[] = calculo(p0, p1Aux, p2, p3Aux);
        Point c0, c1, c2, c3;
        
        c0 = array[0];
        c1 = array[1];
        c2 = array[2];
        c3 = array[3];
        
        float x = p0.x(), y = p0.y();
        float x0, y0;
        //Calculo do pontos da curva
        for (double u = 0.00; u < 1; u += passo) {
            x0 = x;
            y0 = y;
            x = (float) (c0.x() + c1.x() * u + c2.x() * u * u + c3.x() * u * u * u);
            y = (float) (c0.y() + c1.y() * u + c2.y() * u * u + c3.y() * u * u * u);
                        
           reta = new Reta( x0, y0, x, y);
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
	
	//Verifica se houve colisão na curva Hermite
	public byte colisao(Cubo cubo, Dimension dimensao){
		Point p0, p1, p2, p3, p1Aux, p3Aux;
		//Reta reta;
		double passo = 0.001;
		
		p0 = cubo.getP0();
		p1 = cubo.getP1();
		p2 = cubo.getP2();
		p3 = cubo.getP3();
		
		
		controleReta = new ControlarReta(desenho);
		
        p1Aux = new Point( p1.x() - p0.x(), p1.y() - p0.y() );
        p3Aux = new Point( p3.x() - p2.x(), p3.y() - p2.y() );
        
        Point array[] = calculo(p0, p1Aux, p2, p3Aux);
        Point c0, c1, c2, c3;
        
        c0 = array[0];
        c1 = array[1];
        c2 = array[2];
        c3 = array[3];
        
        float x = p0.x(), y = p0.y();
        float x0, y0;
        byte colisao = 0;
        //Calculo do pontos da curva
        for (double u = 0.00; u < 1; u += passo) {
            x0 = x;
            y0 = y;
            colisao = valida(new Point(x0,y0), dimensao);
            if(colisao != 0) return colisao;
            x = (float) (c0.x() + c1.x() * u + c2.x() * u * u + c3.x() * u * u * u);
            y = (float) (c0.y() + c1.y() * u + c2.y() * u * u + c3.y() * u * u * u);
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
