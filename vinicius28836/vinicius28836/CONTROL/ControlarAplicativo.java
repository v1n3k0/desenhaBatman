package vinicius28836.CONTROL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

import vinicius28836.MODEL.Batman;
import vinicius28836.MODEL.Cubo;
import vinicius28836.MODEL.Point;
import vinicius28836.VIEW.MontarPainelInicial;

public class ControlarAplicativo implements MouseListener, MouseMotionListener, ActionListener{
	
	private MontarPainelInicial			pnCenario;
	private Graphics           			desenho;
	private JLabel						barraStatus;
	
	private Cubo						cubo;
	private Batman						batman;
	private Point						p0;
	private Point						p1;
	private Point						p2;
	private Point						p3;
	
	private ControlarBezier				controleBezier;
	private ControlarHermite			controleHermite;
	private ControlarBatman				controleBatman;
	
	
	private byte						contPonto = 0;
	private float 						escala = 1;
	private byte						tipoCurva = 0;
	private boolean						anima = false;
	private boolean						para;
	private byte						tempo = 100;
	private byte 						passo = 2;
	
	//*******************************************************************************************
	
	public ControlarAplicativo(){
		pnCenario = new MontarPainelInicial(this);
		pnCenario.showPanel();
	}
	
	//*******************************************************************************************
	
	//Ação dos botões
	public void actionPerformed(ActionEvent e){
		String comando;
		
		comando = e.getActionCommand();
		
		if(comando.equals("fechar")){
			System.exit(0);
		}
		// Limpa tela de desenho
		if (comando.equals("limpar")) {
			desenho = pnCenario.iniciarGraphics();
			desenho.setColor(Color.black);
			desenho.fillRect(0, 0, pnCenario.Outputtamanho().width, pnCenario.Outputtamanho().height);
			tipoCurva = 0;
		}
		// Desenha curva Bezier
		if(comando.equals("cbezier")){
			tipoCurva = 3;
		}
		// Desenha curva Hermite
		if(comando.equals("chermite")){
			tipoCurva = 4;
		}
		// Desenha Batman Bezier
		if(comando.equals("bezier")){
			desenharBatmanBezier();
			tipoCurva = 1;
		}
		// Desenha Batman Hermite
		if(comando.equals("hermite")){
			desenharBatmanHermite();
			tipoCurva = 2;
		}
		// Zoom out
		if(comando.equals("out")){
			escalarBatman(escala + 0.1f);
		}
		//Zoom in
		if(comando.equals("in")){
			escalarBatman(escala - 0.1f);
		}
		//Rotaciona Batman
		if(comando.equals("horario")){
			rotacaoBatman(10);
		}
		//Rotaciona Batman
		if(comando.equals("antihorario")){
			rotacaoBatman(-10);
		}
		//Animar Batman
		if(comando.equals("animar")){
			animarBatman();
			anima = true;
		}
		//Para animação
		if(comando.equals("para")){
			para = false;
			anima = false;
		}
	}
	
	//*******************************************************************************************
	
	
	//Desenha uma curva Bezier
	public void desenhaBezier(int x, int y){
		
		desenho = pnCenario.iniciarGraphics();
		desenho.setColor(Color.white);
		desenho.drawRect(x - 2, y - 2, 4, 4);		
	
		//Cubo de bezier
		if(contPonto == 0){
			p0 = new Point(x,y);
		}
		if(contPonto == 1){
			p1 = new Point(x,y);
		}
		if(contPonto == 2){
			p2 = new Point(x,y);
		}
		if(contPonto == 3){
			p3 = new Point(x,y);			
			cubo = new Cubo(p0, p1, p2, p3);			
			
			controleBezier = new ControlarBezier(desenho);
			desenho.setColor(Color.yellow);
			controleBezier.desenha(cubo);
		}
		contPonto++;
		if(contPonto == 4 )contPonto = 0;
	
	}
	
	//*******************************************************************************************
	
	//Desenha uma curva Hermite
	public void desenhaHermite(int x, int y){
		
		desenho = pnCenario.iniciarGraphics();
		desenho.setColor(Color.white);
		desenho.drawRect(x - 2, y - 2, 4, 4);		
	
		//Cubo de bezier
		if(contPonto == 0){
			p0 = new Point(x,y);
		}
		if(contPonto == 1){
			p1 = new Point(x,y);
		}
		if(contPonto == 2){
			p2 = new Point(x,y);
		}
		if(contPonto == 3){
			p3 = new Point(x,y);			
			cubo = new Cubo(p0, p1, p2, p3);			
			
			controleHermite = new ControlarHermite(desenho);
			desenho.setColor(Color.yellow);
			controleHermite.desenha(cubo);
		}
		contPonto++;
		if(contPonto == 4 )contPonto = 0;
	
	}
	
	//*******************************************************************************************
	
	//Metodo para desenhar Batman com Bezier
	public void desenharBatmanBezier(){
		
		desenho = pnCenario.iniciarGraphics();
		controleBatman = new ControlarBatman(desenho, pnCenario.Outputtamanho());
		batman = controleBatman.batmanBezier();
		controleBatman.desenhaBezier(batman, Color.yellow);
	}
	
	//*******************************************************************************************
	
	//Metodo para desenhar Batman com Hermite
	public void desenharBatmanHermite(){
		
		desenho = pnCenario.iniciarGraphics();
		controleBatman = new ControlarBatman(desenho, pnCenario.Outputtamanho());
		batman = controleBatman.batmanHermite();
		controleBatman.desenhaHermite(batman, Color.yellow);
	}
		
	//*******************************************************************************************
	
	// Metodo para Zoom in e Zoom out de Bezier e Hermite
	public void escalarBatman(float escala){
		//Para Bezier
		if(tipoCurva == 1){
			batman = controleBatman.escalaBezier(batman, escala);
			if(!anima) controleBatman.desenhaBezier(batman, Color.yellow);
		}
		//Para Hermite
		if(tipoCurva == 2){
			batman = controleBatman.escalaHermite(batman, escala);
			if(!anima) controleBatman.desenhaHermite(batman, Color.yellow);
		}
	}
	
	//*******************************************************************************************
	
	//Metodo para mover batman em Bezier e Hermite
	public void translacaoBatman(Point novoPonto){
		Batman novoBatman;
		byte colisao = 0;
		//Para Beizer
		if(tipoCurva == 1){
			novoBatman = controleBatman.translacaoBezier(batman, novoPonto);
			colisao = controleBatman.colisaoBezier(novoBatman);
			if(colisao == 0 ) batman = novoBatman;
			if(!anima) controleBatman.desenhaBezier(batman, Color.yellow);
		}
		//Para Hermite
		if(tipoCurva == 2){
			novoBatman = controleBatman.translacaoHermite(batman, novoPonto);
			colisao = controleBatman.colisaoHermite(novoBatman);
			if(colisao == 0) batman = novoBatman;
			if(!anima) controleBatman.desenhaHermite(batman, Color.yellow);
		}
	}
	
	//*******************************************************************************************
	
	//Metodo para rotação Batman Bezier e Hermite
	public void rotacaoBatman(int angulo){
		//Para Bezier
		if(tipoCurva == 1){
			batman = controleBatman.rotacaoBezier(batman, angulo);
			controleBatman.desenhaBezier(batman, Color.yellow);
		}
		//Para Hermite
		if(tipoCurva == 2){
			batman = controleBatman.rotacaoHermite(batman, angulo);
			controleBatman.desenhaHermite(batman, Color.yellow);
		}
	}
	
	//*******************************************************************************************
	
	//Metodo para animar batman de Bezier
	private Runnable animarBezier = new Runnable(){
		public void run(){
			Batman oldBatman;
			Point novoPonto;
			int x = passo, y = passo;
			byte colisao;
			int angulo = 1;
			para = true;
			
			while(para){
				oldBatman = batman;
				novoPonto = new Point(batman.getCentro().x() + x, batman.getCentro().y() + y);
				
				batman = controleBatman.translacaoBezier(batman, novoPonto);
				batman = controleBatman.rotacaoBezier(batman, angulo);
				
				colisao = controleBatman.colisaoBezier(batman);
				if(colisao != 0){
					if(angulo == 1) 
						angulo = -1;
					else
						angulo = 1;
				}
				
				if(colisao == 0){
					if(anima) controleBatman.desenhaBezier(batman, Color.yellow);
				}
				if(colisao == 1){
					y = passo;
					batman = oldBatman;
					colisao = 0;
				}
				if(colisao == 2){
					x = -passo;
					batman = oldBatman;
					colisao = 0;
				}
				if(colisao == 3){
					y = -passo;
					batman = oldBatman;
					colisao = 0;
				}
				if(colisao == 4){
					x = passo;
					batman = oldBatman;
					colisao = 0;
				}
				
				try {
					Thread.sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	//*******************************************************************************************
	
	//Metodo para animar batman de Bezier
	private Runnable animarHermite = new Runnable(){
		public void run(){
			Batman oldBatman;
			Point novoPonto;
			int x = passo, y = passo;
			byte colisao;
			int angulo = 1;
			para = true;
			
			while(para){
				oldBatman = batman;
				novoPonto = new Point(batman.getCentro().x() + x, batman.getCentro().y() + y);
				
				batman = controleBatman.translacaoHermite(batman, novoPonto);
				batman = controleBatman.rotacaoHermite(batman, angulo);
				
				colisao = controleBatman.colisaoHermite(batman);
				if(colisao != 0){
					if(angulo == 1) 
						angulo = -1;
					else
						angulo = 1;
				}
				
				
				if(colisao == 0){
					if(anima) controleBatman.desenhaHermite(batman, Color.yellow);
				}
				if(colisao == 1){
					y = passo;
					batman = oldBatman;
					colisao = 0;
				}
				if(colisao == 2){
					x = -passo;
					batman = oldBatman;
					colisao = 0;
				}
				if(colisao == 3){
					y = -passo;
					batman = oldBatman;
					colisao = 0;
				}
				if(colisao == 4){
					x = passo;
					batman = oldBatman;
					colisao = 0;
				}
				try {
					Thread.sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
		
	//*******************************************************************************************
	
	//Metodo para animação do Batman de Bezier e Hermite
	public void animarBatman(){
		//Para Bezier
		if(tipoCurva == 1){
			new Thread(animarBezier).start();
		}
		//Para Hermite
		if(tipoCurva == 2){
			new Thread(animarHermite).start();
		}
	}
	
	//*******************************************************************************************
	
	//trata evento quando o usuário arrasta o mouse com o botão pressionado
	public void mouseDragged(MouseEvent event) {
		int x, y;
		x = event.getX();
		y = event.getY();
		
		//Ponto para Tranlação de Bezier ou Hermite
		if(tipoCurva != 0) translacaoBatman(new Point(x,y));
		
		//Status mouse
		barraStatus = pnCenario.statusBar();
		barraStatus.setText("Arrastado em [" + x + ", " + y + "]");
	}
	
	//*******************************************************************************************
	
	//trata evento quando o usuário movimenta o mouse
	public void mouseMoved(MouseEvent event) {
		//Status mouse
		barraStatus = pnCenario.statusBar();
		barraStatus.setText("Movido em [" + event.getX() + ", " + event.getY() + "]");
		
	}
	
	//*******************************************************************************************
	
	//trata evento quando o mouse é liberado imediatamente após ser pressionado
	public void mouseClicked(MouseEvent event) {
		
		//Status mouse
		barraStatus = pnCenario.statusBar();
		barraStatus.setText("Clicado em [" + event.getX() + ", " + event.getY() + "]");
		
	}
	
	//*******************************************************************************************

	//trata evento quando o mouse entra na área da janela na tela
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//*******************************************************************************************
	
	//trata evento quando o mouse sai da área da janela na tela
	public void mouseExited(MouseEvent e) {
		//Status mouse
		barraStatus = pnCenario.statusBar();
		barraStatus.setText("Mouse fora janela");		
	}
	
	//*******************************************************************************************
	
	//trata evento quando o mouse é pressionado
	public void mousePressed(MouseEvent event) {
		int x, y;
		
		//Ponto
		x = event.getX();
		y = event.getY();
		
		
		//Pontos para desenhar uma curva de Bezier
		if(tipoCurva == 3)desenhaBezier(x, y);
		//Ponto para desenhar uma curva de Hermite
		if(tipoCurva == 4)desenhaHermite(x, y);
		
		//Status mouse
		barraStatus = pnCenario.statusBar();
		barraStatus.setText("Pressionado em [" + x + ", " + y + "]");		
	}
	
	//*******************************************************************************************
	
	//trata evento quando o mouse é liberado após ser arrastado
	public void mouseReleased(MouseEvent event) {
		//Status mouse
		barraStatus = pnCenario.statusBar();
		barraStatus.setText("Liberado em [" + event.getX() + ", " + event.getY() + "]");
	}
	
	//*******************************************************************************************
}
