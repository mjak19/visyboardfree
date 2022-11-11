import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TheTypeVisyBoardApp extends TheTypeFrame implements ComponentListener, WindowListener, ActionListener {

	final TheTypeArrayList<TheTypeButton> TheMemberAllButtons = new TheTypeArrayList<>();
	Font TheMemberFont = null;
	String TheMemberFname = null;
	TheTypeVisyBoardApp TheMemberThis=this;
	TheTypeVisyBoard.Keyboard TheMembervisyboard = new TheTypeVisyBoard.Keyboard();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TheTypeVisyBoardApp TheLocalwindow = new TheTypeVisyBoardApp();
				TheLocalwindow.setVisible(true);
			}
		});
	}

	public TheTypeVisyBoardApp() {

		initialize();

	}

	JMenuBar TheMemJmenubar = new JMenuBar();

	JMenu theMemJmenuHelp = new JMenu("Help");
	JMenuItem TheMemJmenuItemUpgrade = new JMenuItem("Upgrade");
	JMenuItem TheMemJmenuItemCheckForUpdate = new JMenuItem("check for updade");
	JMenuItem TheMemJmenuItemWebsite = new JMenuItem("website");
	JMenuItem TheMemJmenuItemfeedback = new JMenuItem("feedback / repoert a bug / etc");
	JMenuItem TheMemJmenuItemAbout = new JMenuItem("About");
	
	JMenu theMemJmenuUtilitiies = new JMenu("Utilities");
	JMenuItem TheMemJmenuItemScreenShot = new JMenuItem("AppScreenShot");

	private void initialize() {
		this.getContentPane().setBackground(new Color(122, 138, 153));
		setAlwaysOnTop(true);
		setFocusableWindowState(false);
		setLocationByPlatform(true);
		setState(Frame.NORMAL);
		setTitle("visyboard free");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.addComponentListener(this);
		this.addWindowListener(this);
		
		this.setJMenuBar(TheMemJmenubar);
		
		TheMemJmenubar.add(theMemJmenuUtilitiies);
		TheMemJmenubar.add(theMemJmenuHelp);
		
		theMemJmenuHelp.add(TheMemJmenuItemUpgrade);
		theMemJmenuHelp.add(TheMemJmenuItemCheckForUpdate);
		theMemJmenuHelp.add(TheMemJmenuItemWebsite);
		//theMemJmenuHelp.add(TheMemJmenuItemfeedback);
		theMemJmenuHelp.add(TheMemJmenuItemAbout);
		

		
		theMemJmenuUtilitiies.add(TheMemJmenuItemScreenShot);
		
		TheMemJmenuItemUpgrade.addActionListener(this);
		TheMemJmenuItemCheckForUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"please check the website to update the free version if available",
						"version", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		TheMemJmenuItemAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(TheMemberThis,
						"visyboard free testing 0.3.0.1\n"
						+ "visit visyboard website\n"
						+ "https://sites.google.com/view/visyboardfree\n"
						,
						"version", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		TheMemJmenuItemWebsite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					//specify the protocol along with the URL
					URI oURL = new URI(
							"https://sites.google.com/view/visyboardfree/");
					desktop.browse(oURL);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		TheMemJmenuItemScreenShot.addActionListener(this);
		/*
		 * JButton TheLocalButton =new JButton(); getContentPane().add(TheLocalButton);
		 * TheLocalButton.setBounds(10,10,100,100);
		 */
	}
	Desktop desktop = java.awt.Desktop.getDesktop();
	boolean TheMemNotAdedYet = true;

	private void renderButtons() {

		float 
		buttonX = 0
		,
		buttonY = 0
		,
		buttonHeight =
				
				(this.getContentPane().getHeight()-buttonX)
				/ 
				TheMembervisyboard.items.size()
				
		,
		buttonWidth = 
				(this.getContentPane().getWidth()-buttonY)
				/
				TheMembervisyboard.items.get(0).items.size()		
		;

		for (TheTypeVisyBoard.Keyboard.Row TheLocalRow : TheMembervisyboard.items) {
			buttonX = 0;
			for (TheTypeVisyBoard.Keyboard.Row.Button TheLocalVisyButton : TheLocalRow.items) {
				TheTypeButton TheLocalButton = TheLocalVisyButton.getTheGUIButton();
				if (TheMemberFont == null) {
					TheMemberFont = TheLocalButton.getFont();
					TheMemberFname = TheMemberFont.getName();
				}
				if (TheMemNotAdedYet) {
					getContentPane().add(TheLocalButton);
					this.TheMemberAllButtons.add(TheLocalButton);
				}
				TheLocalButton.setBounds(buttonX, buttonY, buttonWidth * TheLocalVisyButton.getSpan(), buttonHeight);
				buttonX += (buttonWidth * TheLocalVisyButton.getSpan());
			}
			buttonY += buttonHeight;
		}
		TheMemNotAdedYet = false;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		renderButtons();

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		windowClosing(null);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		windowClosing(null);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

		for (TheTypeButton button : this.TheMemberAllButtons) {
			button.TheMemVirtualFingerDown = false;
			
			button.mouseReleased(null);

		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		windowClosing(null);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		windowClosing(null);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		windowClosing(null);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		windowClosing(null);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		windowClosing(null);
	}
	JFileChooser TheMemFileChooser=new JFileChooser();
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(TheMemJmenuItemUpgrade))
			JOptionPane.showMessageDialog(null,
					"upgrade to full/paid version will be available in the future",
					"Upgrade to full/paid version", JOptionPane.INFORMATION_MESSAGE);
		else

		{
			BufferedImage screenshotImage = new BufferedImage(this.getBounds().width, this.getBounds().height,
					BufferedImage.TYPE_INT_RGB);
			this.paint(screenshotImage.getGraphics());
			try {
				int TheLocSelection=TheMemFileChooser.showSaveDialog(this);
				if (TheLocSelection == JFileChooser.APPROVE_OPTION)
					ImageIO.write(screenshotImage, "png", TheMemFileChooser.getSelectedFile());
			} catch (IOException ex) {
				System.err.println("ImageIsuues");
			}
		}
	}
	
}
