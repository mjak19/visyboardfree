
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TheTypeLabel extends JLabel implements MouseListener {

	public static void main(String[] args) throws Exception {
		TheTypeLabel label = new TheTypeLabel("Some text");
		label.setBackground(Color.cyan);
		label.addMouseListener(label);
		JFrame frame = new JFrame("Resize label font");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(label);

		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private int baseFontSize = 0;
	private int baseWidth = 0;
	private int baseHeight = 0;
	Font TheMemberlFont = getFont();

	public TheTypeLabel(String text) {
		super(text);
		init();
	}

	protected void init() {
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setAlignmentX(0);
		setDoubleBuffered(true);

		setOpaque(true);
	}

	public void resizeFont() {
		// TODO Auto-generated method stub
		Dimension TheLocalSize = getSize();
		int TheLocalTextLen = getText().length();
		setFont(TheMemberlFont.deriveFont(TheMemberlFont.getStyle(),
				Math.abs(Math.min((TheLocalSize.width / (TheLocalTextLen)), (TheLocalSize.height / (2))))));

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		setFont(TheMemberlFont.deriveFont(TheMemberlFont.getStyle(), getFont().getSize() + 2));
		System.out.println(getFont());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}