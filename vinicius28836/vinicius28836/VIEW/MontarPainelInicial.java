package vinicius28836.VIEW;

	import java.awt.*;
	import javax.swing.*;
	import vinicius28836.CONTROL.ControlarAplicativo;

public class MontarPainelInicial {

	private JFrame  baseFrame;
	private JPanel  basePanel;
	private JPanel  outputPanel;
	private JLabel 	statusBar;
	
	private Graphics desenho;
	
	//*******************************************************************************************
	public MontarPainelInicial( ControlarAplicativo controlePrograma )
	{
		JPanel  buttonPanel;
		JPanel  titlePanel;
		JButton btEnd, btLimpar,bt;

		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout( new BoxLayout( baseFrame.getContentPane(), BoxLayout.Y_AXIS) );

		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // FITS PANEL TO THE ACTUAL MONITOR SIZE
		baseFrame.setUndecorated(true);  // TURN OFF ALL THE PANEL BORDERS 

		basePanel = new JPanel();
		basePanel.setLayout( new BorderLayout() );

		// TITLE PANEL
		titlePanel = new JPanel();
		titlePanel.setPreferredSize( new Dimension ( 0, 50 ) );
		titlePanel.setBackground( Color.gray );

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout( new BorderLayout() );
		outputPanel.setBackground( Color.black );

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize( new Dimension ( 0, 40 ) );
		buttonPanel.setBackground( Color.gray );

		// PANEL TITLE
		JLabel titulo;
		titulo = new JLabel( "Batman");
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Dialog", Font.BOLD, 25));
		titlePanel.add(titulo);

		// ADDING BUTTONS
		bt = addAButton ( "Bezier", "cbezier", Color.blue, buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Hermite", "chermite", Color.blue,buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Batman Bezier", "bezier", Color.green, buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Batman Hermite", "hermite", Color.green, buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Zoom out", "out", Color.cyan, buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Zoom in", "in", Color.cyan, buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Rotação horario", "horario", Color.magenta, buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Rotação anti-horario", "antihorario", Color.magenta, buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Animar", "animar", Color.orange, buttonPanel );
		bt.addActionListener( controlePrograma );
		bt = addAButton ( "Para de Animar", "para", Color.orange, buttonPanel );
		bt.addActionListener( controlePrograma );
		btLimpar = addAButton ( "Limpar Tela", "limpar", Color.white, buttonPanel );
		btLimpar.addActionListener( controlePrograma );
		btEnd = addAButton ( "Fim", "fechar", Color.red, buttonPanel );
		btEnd.addActionListener( controlePrograma );
		
		// Barra status
		statusBar = new JLabel();
		
		// OUVINTES DO MOUSE
		outputPanel.addMouseListener(controlePrograma);
		outputPanel.addMouseMotionListener(controlePrograma);

		// VISIBLE PANELS
		baseFrame.add(basePanel);
		baseFrame.add( statusBar, BorderLayout.SOUTH );

		basePanel.add( titlePanel, BorderLayout.PAGE_START );
		basePanel.add( outputPanel, BorderLayout.CENTER );
		basePanel.add( buttonPanel, BorderLayout.PAGE_END );
		
		//baseFrame.setSize(1000,700);
		baseFrame.setVisible(true);
	}

	//*******************************************************************************************
	// METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA

	private JButton addAButton( String    textoBotao,
                                String    textoControle,
                                Color cor,
                                Container container
                              ) 
	{
		JButton botao;

		botao = new JButton( textoBotao );
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
		botao.setBackground(cor);
		container.add(botao);

		botao.setActionCommand( textoControle );

		return ( botao );
	}

	//*******************************************************************************************
	// METODO PARA MOSTRAR O FRAME BASICO

	public void showPanel() 
	{
		basePanel.setVisible(true);
	}
	//*******************************************************************************************
	
	public JLabel statusBar(){
		return statusBar;
	}
	
	//*******************************************************************************************
	public Graphics iniciarGraphics()
	{

		desenho = outputPanel.getGraphics();

		return ( desenho );
	}

	//*******************************************************************************************
	
	public Dimension Outputtamanho(){
		return outputPanel.getSize();
	}
	
	//*******************************************************************************************
}
