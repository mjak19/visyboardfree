

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;

public class TheTypeButton extends JButton implements ActionListener, MouseListener, ComponentListener {

	
	TheTypeVisyBoard.Keyboard.Row.Button TheMemberVisyBoardButton;
	private int TheMemberVK_Code;
	 static Robot TheMemberRobot;
	private float TheMemDivFactorHz = 3;
	private float TheMemDivFactorVr = 1.0f;
	private Color TheMemOriginalForeColor;
	private Color TheMemOriginalBackColor;
	 boolean TheMemVirtualFingerDown;
	 static Popup TheMemberPopup;
	 static JLabel TheMemberLabelOfPopup = new JLabel("Here is my popup!");
	 static
	 {
		
		TheMemberLabelOfPopup.setFont(new Font("Dialog",1,21));;
	 }
	public TheTypeButton() {
		super();
		init();
	}

	public TheTypeButton(String text) {

		super(text);
		init();
	}

	public TheTypeButton(TheTypeVisyBoard.Keyboard.Row.Button btn) {
		TheMemberVisyBoardButton = btn;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		if (TheMemberRobot == null)
			try {
				TheMemberRobot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.addMouseListener(this);
		this.addComponentListener(this);
		this.setMargin(new Insets(0, 0, 0, 0));
		setDoubleBuffered(true);
		String text = "";
		TheMemberVK_Code = TheMemberVisyBoardButton.getVK_CODE();
		TheMemOriginalBackColor =Color.gray;

		if 
		(
				TheMemberVK_Code >= KeyEvent.VK_F1
				&&
				TheMemberVK_Code <= KeyEvent.VK_F12
		)
		{
			//OriginalBackColor = new Color((int)((Color.yellow.getRGB()+Color.gray.getRGB())/2));
			TheMemDivFactorHz = 3;
		}

		if 
		(
				TheMemberVK_Code >= KeyEvent.VK_A
				&& 
				TheMemberVK_Code <= KeyEvent.VK_Z
		) 
		{
			//OriginalBackColor = (Color.orange);
			text = 
					(
							TheMemberVisyBoardButton.items.get(0).toString()
					);
			TheMemDivFactorHz = 1.5f;
		}
		else
		{
			for (TheTypeVisyBoard.Keyboard.Row.Button.Text txt : this.TheMemberVisyBoardButton.items) 
			{
				text += txt.toString() + "\n";
			}
			if (text.endsWith("\n")) 
			{
				text = text.substring(0, text.length() - 1);
			}
		}
		if 
		(
				TheMemberVK_Code == KeyEvent.VK_DELETE 
				|| TheMemberVK_Code == KeyEvent.VK_SHIFT
				|| TheMemberVK_Code == KeyEvent.VK_CAPS_LOCK
				|| TheMemberVK_Code == KeyEvent.VK_TAB
				|| TheMemberVK_Code == KeyEvent.VK_BACK_QUOTE 
				|| TheMemberVK_Code == KeyEvent.VK_DEAD_TILDE
				|| TheMemberVK_Code == KeyEvent.VK_OPEN_BRACKET
				|| TheMemberVK_Code == KeyEvent.VK_CLOSE_BRACKET
				|| TheMemberVK_Code == KeyEvent.VK_COLON 
				|| TheMemberVK_Code == KeyEvent.VK_QUOTE
				|| TheMemberVK_Code == KeyEvent.VK_COMMA 
				|| TheMemberVK_Code == KeyEvent.VK_PERIOD
				|| TheMemberVK_Code == KeyEvent.VK_SEMICOLON 
				|| TheMemberVK_Code == KeyEvent.VK_SLASH
				|| TheMemberVK_Code == KeyEvent.VK_BACK_SLASH
				|| 	(
						TheMemberVK_Code >= KeyEvent.VK_0 
						&& 
						TheMemberVK_Code <= KeyEvent.VK_9
					)
				|| TheMemberVK_Code == KeyEvent.VK_EQUALS 
				|| TheMemberVK_Code == KeyEvent.VK_MINUS
		)
		{
			//OriginalBackColor = (Color.green);
			TheMemDivFactorHz = 2.5f;
		}
		
		
		
		
			
		
		TheMemOriginalForeColor = getForeground();
		setBackground(TheMemOriginalBackColor);
		setText(text);

		// setOpaque(true);
	}
	public boolean TheMethVK_StateDownOrUp()
	{
		return TheMemVK_StateDownOrUp;
	}
	private boolean TheMemVK_StateDownOrUp;
	public void TheMethVK_StateDownOrUp(boolean val)
	{
		 TheMemVK_StateDownOrUp=val;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		
			TheMemberRobot.keyPress(this);

			setForeground(pressForeColor());
			if (isHoldable()) {
				virtualFingerDown(!virtualFingerDown());
			}
		
	}

	private boolean virtualFingerDown() {
		// TODO Auto-generated method stub
		return TheMemVirtualFingerDown;
	}

	

	private void virtualFingerDown(boolean b) {
		// TODO Auto-generated method stub
		TheMemVirtualFingerDown = b;
		
	}

	private Color pressForeColor() {
		// TODO Auto-generated method stub
		return Color.red;
	}

	private Color enterBackColor() {
		// TODO Auto-generated method stub
		return Color.lightGray;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		if (isHoldable()) {
			if (virtualFingerDown())
				return;
		}
		TheMemberRobot.keyRelease(this);
		setForeground(TheMemOriginalForeColor);
	}

	private boolean isHoldable() {
		// TODO Auto-generated method stub
		return 
				(
						TheMemberVK_Code == KeyEvent.VK_ALT
						|| 
						TheMemberVK_Code == KeyEvent.VK_CONTROL
						|| 
						TheMemberVK_Code == KeyEvent.VK_SHIFT
						|| 
						TheMemberVK_Code == KeyEvent.VK_WINDOWS
				);
	}

	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		TheMemberLabelOfPopup.setText(KeyEvent.getKeyText(TheMemberVK_Code));
		
		TheMemberPopup = PopupFactory.getSharedInstance().getPopup(this, TheMemberLabelOfPopup,
				this.getX() + this.getWidth() + SwingUtilities.windowForComponent(this).getX(),
				this.getY() + this.getHeight() + SwingUtilities.windowForComponent(this).getY());

		setBackground(enterBackColor());
		TheMemberPopup.show();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		TheMemberPopup.hide();
		setBackground(TheMemOriginalBackColor);
	}

	public void setSize(float f, float buttonHeight) {
		// TODO Auto-generated method stub
		super.setSize((int) f, (int) buttonHeight);
	}

	public void setBounds(float buttonX, float buttonY, float f, float buttonHeight) {
		// TODO Auto-generated method stub
		super.setBounds((int) buttonX, (int) buttonY, (int) f, (int) buttonHeight);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("resized" + this.getText());
		int h = getHeight();
		int w = getWidth();
		
		this.setFont(getFont().deriveFont((float) Math.min( w / (TheMemDivFactorHz*TheMemberVisyBoardButton.getSpan()),h/TheMemDivFactorVr)));
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
	}
}
