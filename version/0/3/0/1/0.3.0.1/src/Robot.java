

import java.awt.AWTException;

public class Robot  {

	java.awt.Robot rb=new java.awt.Robot();
	public Robot() throws AWTException {
		super();
		// TODO Auto-generated constructor stub
	}
	public void keyPress(TheTypeButton vk)
	{
		rb.keyPress(vk.TheMemberVisyBoardButton.getVK_CODE());
		vk.TheMethVK_StateDownOrUp(true);
	}
	public void keyRelease(TheTypeButton vk)
	{
		if(vk.TheMethVK_StateDownOrUp())
			rb.keyRelease(vk.TheMemberVisyBoardButton.getVK_CODE());
		vk.TheMethVK_StateDownOrUp(false);
	}
}
