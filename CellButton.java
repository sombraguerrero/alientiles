import javax.swing.*;
import java.awt.*;

public final class CellButton extends JButton {
	private int rowIndex;
	private int columnIndex;
        private int colorPos = 0;
        
        //A priming color is needed if we randomize the solution color.
        //public final static Color[] validColors = {Color.LIGHT_GRAY, Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};
        public final static Color[] validColors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};
        private Color currentColor = validColors[colorPos];
	
	public CellButton() { setBackground(currentColor); }
	public CellButton(int r,int c) {
		rowIndex = r;
		columnIndex = c;
		setBackground(currentColor);
	}
	public void toggle(boolean reset) {
                /***
		if (colorPos == validColors.length - 1 && !reset) {
                    colorPos = 1;
                }
                else if (reset) {
                    colorPos = 0;
                }
                * **/
                if (colorPos == validColors.length - 1 || reset) {
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
