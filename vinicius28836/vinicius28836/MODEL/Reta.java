package vinicius28836.MODEL;


public class Reta {

	private Point  pontoInicial;
	private Point  pontoFinal;

	//****************************************************************************************	
	public Reta ( Point pInicial, Point pFinal )
	{
		pontoInicial = pInicial;
		pontoFinal   = pFinal;
	}
	
	public Reta( float p1X, float p1Y, float p2X, float p2Y ){
		
		pontoInicial = new Point(p1X, p1Y);
		pontoFinal = new Point(p2X, p2Y);
	}
	//****************************************************************************************	
	public Point getPontoInicial()
	{
		return ( pontoInicial );
	}

	//****************************************************************************************	
	public Point getPontoFinal()
	{
		return ( pontoFinal );
	}

	//****************************************************************************************	
}
