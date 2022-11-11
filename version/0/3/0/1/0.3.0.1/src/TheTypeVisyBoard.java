
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class TheTypeVisyBoard {
	public static class WithCollectionOf<T> {
		public final ArrayList<T> items = new ArrayList<T>();
	}

	public static class Keyboard extends WithCollectionOf<TheTypeVisyBoard.Keyboard.Row> {
		public static final Object to = new Object();

		public Keyboard() {
			Row[] rows = {
					new Row().AppendOne(KeyEvent.VK_ESCAPE, new String[] { "␛" }).AppendRangeFromTo("F1", "F12")
							.AppendOne(KeyEvent.VK_BACK_SPACE, new String[] { "⌫" }),
					new Row().AppendOne(new String[] { "`", "~" }).AppendRangeFromTo("1", "9")
							.AppendOne(new String[] { "0", ")" }).AppendOne(new String[] { "-", "_" })
							.AppendOne(new String[] { "=", "+" }).AppendOne(KeyEvent.VK_DELETE, new String[] { "⌦" }),
					new Row().AppendOne(KeyEvent.VK_TAB, new String[] { "⭾" }, 2).AppendMultiple("QWERTYUIOP".split(""))
							.AppendOne(new String[] { "[", "{" }).AppendOne(new String[] { "]", "}" }),
					new Row().AppendOne(KeyEvent.VK_CAPS_LOCK, new String[] { "⇪" }, 2)
							.AppendMultiple("ASDFGHJKL".split("")).AppendOne(new String[] { ";", ":" })
							.AppendOne(new String[] { "'", "\"" }).AppendOne(KeyEvent.VK_ENTER, new String[] { "⏎" }),
					new Row().AppendOne(KeyEvent.VK_SHIFT, new String[] { "⇧" }, 2).AppendMultiple("ZXCVBNM".split(""))
							.AppendOne(new String[] { ",", "<" }).AppendOne(new String[] { ".", ">" })
							.AppendOne(KeyEvent.VK_UP, new String[] { "↑" }).AppendOne(new String[] { "/", "?" })
							.AppendOne(new String[] { "\\", "|" }),
					new Row().AppendOne(KeyEvent.VK_CONTROL, new String[] { "CTRL" })
							.AppendOne(KeyEvent.VK_WINDOWS, new String[] { "❖" }).AppendOne(new String[] { "ALT" })
							.AppendOne(KeyEvent.VK_SPACE, new String[] { "SPACE" }, 6)
							.AppendOne(KeyEvent.VK_CONTEXT_MENU, new String[] { "☰" })
							.AppendOne(KeyEvent.VK_LEFT, new String[] { "←" })
							.AppendOne(KeyEvent.VK_DOWN, new String[] { "↓" })
							.AppendOne(KeyEvent.VK_RIGHT, new String[] { "→" })
							.AppendOne(KeyEvent.VK_PRINTSCREEN, new String[] { "⎙" }) };
			items.addAll(Arrays.asList(rows));
		}

		public static class Row extends WithCollectionOf<TheTypeVisyBoard.Keyboard.Row.Button> {
			public TheTypeVisyBoard.Keyboard.Row AppendMultiple(String[] strings) {
				for (String str : strings) {
					this.AppendOne(new String[] { str.toUpperCase(), str.toLowerCase() });
				}
				return this;
			}

			public final String[] NumAlternatives = new String[] { ")", "!", "@", "#", "$", "%", "^", "&", "*", "(" };

			public TheTypeVisyBoard.Keyboard.Row AppendRangeFromTo(String fromStr, String ToStr) {
				try {
					Integer from = Integer.parseInt(fromStr), to = Integer.parseInt(ToStr);

					for (Integer i = from; i <= to; i++) {
						this.AppendOne(new String[] { i.toString(), NumAlternatives[i] });
					}
				} catch (Exception ex) {
					Integer from = Integer.parseInt(fromStr.replace("F", "").replace("f", "")),
							to = Integer.parseInt(ToStr.replace("F", "").replace("f", ""));
					for (Integer i = from; i <= to; i++) {
						this.AppendOne(new String[] { "F" + i.toString() });
					}
				}
				return this;
			}

			public Row AppendOne(int vk_code, String[] strings, int span) {
				Button bt = new Button();
				bt.init(vk_code, strings, span);
				items.add(bt);
				return this;
			}

			public Row AppendOne(int vk_code, String[] strings) {
				return AppendOne(vk_code, strings, 1);
			}

			public Row AppendOne(String[] strings) {
				Button bt = new Button();
				bt.init(strings);
				this.items.add(bt);
				return this;
			}

			public static class Button extends WithCollectionOf<TheTypeVisyBoard.Keyboard.Row.Button.Text> {
				private int Span;
				private int VK_CODE;

				public Integer getSpan() {
					return this.Span;
				}

				public int getVK_CODE() {
					return this.VK_CODE;
				}

				public Button init(int VK_CODE, String[] strings, int span) {
					this.Span = span;
					this.VK_CODE = VK_CODE;
					Text[] texts = new Text[strings.length];
					for (int i = 0; i < strings.length; i++) {
						texts[i] = new Text(strings[i]);
					}
					items.addAll(Arrays.asList(texts));
					return this;
				}

				public Button init(String[] strings) {
					int code = 1;
					try {
						code = KeyEvent.class.getField("VK_" + strings[0]).getInt(null);
					} catch (Exception ex) {
						code = KeyEvent.getExtendedKeyCodeForChar(strings[0].charAt(0));
					}
					return init(code, strings, 1);
				}

				public static class Text {
					private final String coreStr;

					public Text(String strText) {
						this.coreStr = strText;
					}

					public String toString() {
						return this.coreStr;
					}
				}
				TheTypeButton GUIButton;
				public TheTypeButton getTheGUIButton() {
					// TODO Auto-generated method stub
					if(GUIButton==null)
						GUIButton=new TheTypeButton(this);
					return GUIButton;
				}
			}
		}
	}
}
