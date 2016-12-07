package vinicius28836.CONTROL;

import java.awt.*;

import vinicius28836.MODEL.Reta;
import vinicius28836.MODEL.Point;

public class ControlarReta {

	private Graphics  desenho;
	
	//*************************************************************************************
	
	public ControlarReta ( Graphics desenho )
	{
		this.desenho = desenho;
	}
	
	//*************************************************************************************
	
	// Desenha um ponto
	private void plotaPixel(float x, float y){
		desenho.drawLine( Math.round(x), Math.round(y), Math.round(x), Math.round(y) );
	}
	
	//*************************************************************************************
				
	//Desenha a reta por DDA inteiro relação X
	public void bresenhamX(Point p1, Point p2)
	{
		   int slope;
	       float dx, dy, incE, incNE, d, x, y;
	       
	       // Onde inverte a linha x1 > x2       
	       if (p1.x() > p2.x()){
	    	   bresenhamX( p2, p1);
	            return;
	       }
	       
	       dx = p2.x() - p1.x();
	       dy = p2.y() - p1.y();
	   
	       if (dy < 0){            
	           slope = -1;
	           dy = -dy;
	       }
	       else{            
	          slope = 1;
	       }
	       
	       // Constante de Bresenham
	       incE = 2 * dy;
	       incNE = 2 * dy - 2 * dx;
	       d = 2 * dy - dx;
	       y = p1.y();       
	       for (x = p1.x(); x <= p2.x(); x++){
	           plotaPixel(x, y);
	           if (d <= 0){
	             d += incE;
	           }
	           else{
	             d += incNE;
	             y += slope;
	           }
	       }
	}
	
	//Desenha a reta por DDA inteiro relação Y
	public void bresenhamY(Point p1, Point p2)
	{
		   int slope;
	       float dx, dy, incE, incNE, d, x, y;
	       
	       // Onde inverte a linha y1 > y2       
	       if (p1.y() > p2.y()){
	    	   bresenhamY( p2, p1);
	            return;
	       }
	       
	       dx = p2.x() - p1.x();
	       dy = p2.y() - p1.y();
	   
	       if (dx < 0){            
	           slope = -1;
	           dx = -dx;
	       }else{            
	          slope = 1;
	       }
	       
	       // Constante de Bresenham
	       incE = 2 * dx;
	       incNE = 2 * dx - 2 * dy;
	       d = 2 * dx - dy;
	       x = p1.x();       
	       for (y = p1.y(); y <= p2.y(); y++){
	           plotaPixel(x, y);
	           if (d <= 0){
	             d += incE;
	           }else{
	        	   d += incNE;
	        	   x += slope;
		       }
		   }
	}
	
	//*************************************************************************************
	
	//Função para escolha de qual metodo de desenha reta
	public void desenhar ( Reta  reta)
	{
		Point p1, p2;
		float DiferencaX, DiferencaY;

		// INICIALIZANDO VARIAVEIS
		p1 = reta.getPontoInicial();
		p2 = reta.getPontoFinal();
		
		DiferencaX = Math.abs(p1.x() - p2.x());
		DiferencaY = Math.abs(p1.y() - p2.y());
		
		if(DiferencaX > DiferencaY){
			bresenhamX(p1, p2);
		}else{
			bresenhamY(p1, p2);
		}
		
	}
	
	//*************************************************************************************
	
	public Reta escala(Reta reta, float fator, Point org){
		
		Point p0, p1;
		float auxX, auxY;
		
		p0 = reta.getPontoInicial();
		p1 = reta.getPontoFinal();
		
		// Calculo para p0
		auxX = (p0.x() - org.x()) * fator + org.x();
		auxY = (p0.y() - org.y()) * fator + org.y();
		p0 = new Point( auxX, auxY );
		// Calculo para p1
		auxX = (p1.x() - org.x()) * fator + org.x();
		auxY = (p1.y() - org.y()) * fator + org.y();
		p1 = new Point( auxX, auxY );		
		
		return new Reta(p0, p1);
		
	}

	//*************************************************************************************
	
	public Reta translacao(Reta reta, Point translacao){
		Point p0, p1;
		float auxX, auxY;

		p0 = reta.getPontoInicial();
		p1 = reta.getPontoFinal();
		
		// Calculo para p0
		auxX = p0.x() + translacao.x();
		auxY = p0.y() + translacao.y();
		p0 = new Point( auxX, auxY );
		// Calculo para p1
		auxX = p1.x() + translacao.x();
		auxY = p1.y() + translacao.y();
		p1 = new Point( auxX, auxY );
		
		
		return new Reta(p0, p1);
	}
	
	//*************************************************************************************

	public Reta rotacao(Reta reta, int angulo, Point org){
		
		Point  p0, p1;
		float auxX, auxY, cos, sen;
		

		p0 = reta.getPontoInicial();
		p1 = reta.getPontoFinal();
		
		// Calculo do cos e sen
		cos = (float) Math.cos( Math.toRadians(angulo) );
		sen = (float)Math.sin( Math.toRadians(angulo) );
		
		// Calculo para p0
		auxX = org.x() + (p0.x() - org.x()) * cos - (p0.y() - org.y()) * sen;
		auxY = org.y() + (p0.y() - org.y()) * cos + (p0.x() - org.x()) * sen;
		p0 = new Point( auxX, auxY );
		// Calculo para p1
		auxX = org.x() + (p1.x() - org.x()) * cos - (p1.y() - org.y()) * sen;
		auxY = org.y() + (p1.y() - org.y()) * cos + (p1.x() - org.x()) * sen;
		p1 = new Point( auxX, auxY );
		
				
		return new Reta(p0, p1);
	}
	
	//*************************************************************************************
}
