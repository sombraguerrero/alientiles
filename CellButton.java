import javax.swing.*;
import java.awt.*;

public final class CellButton extends JButton {
	private int rowIndex;
	private int columnIndex;
        private int colorPos = 0;
        public final static Color[] validColors = {Color.LIGHT_GRAY, Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};
        private Color currentColor = Color.LIGHT_GRAY;
	
	public CellButton() { setBackground(currentColor); }
	public CellButton(int r,int c) {
		rowIndex = r;
		columnIndex = c;
		setBackground(currentColor);
	}
	public void toggle(boolean reset) {
		if (colorPos == validColors.length - 1 || reset == true) {
                    colorPos = 0;
                }
		else {
                    colorPos++;
		}
                currentColor = validColors[colorPos];
		setBackground(currentColor);
	}
	
	
	public int getRow() { return rowIndex; }
	public int getColumn() { return columnIndex; }
	public void setCurrentColor(Color c) { currentColor = c; }
	public int getColorPos() { return colorPos; }
	public void setColorPos(int pos) { colorPos = pos; }
        @Override
        public void setBackground(Color c) {
            super.setBackground(c);
        }
}
