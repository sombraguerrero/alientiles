import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TileFrame extends JFrame {
	private int numberOfCells = 7;
	private final CellButton[][] cells;
	private final JPanel board = new JPanel();
	private final TileAction a = new TileAction();
	private final JButton[] ctrl = new JButton[4];
        private final JLabel winner = new JLabel();
	private final String[] btnInfo = {"Start Over","Undo","Board Size","Quit"};
	private final Stack ActionStack;
	
	public TileFrame(String title) {
		super(title);
		ActionStack = new Stack();
		cells = new CellButton[numberOfCells][];
		board.setLayout(new GridLayout(numberOfCells,numberOfCells,7,7));
		for (int i=0; i<numberOfCells; i++) {
			cells[i] = new CellButton[numberOfCells];
			
			for (int j=0; j<cells[i].length; j++) {
				cells[i][j] = new CellButton(i,j);
				board.add(cells[i][j]);
				cells[i][j].addActionListener(a); 
			}
		}
		
		JPanel ctrlPanel = new JPanel(new FlowLayout());
		
		for (int i=0; i<btnInfo.length; i++) {
			ctrl[i] = new JButton(btnInfo[i]);
			ctrlPanel.add(ctrl[i]);
		}
                
                //Let the computer randomly choose which color is the goal.
                //Random rng = new Random();
                //winner.setForeground(CellButton.validColors[rng.nextInt(CellButton.validColors.length - 1) + 1]);
                
                //The original form of the problem.
                winner.setForeground(Color.BLUE);
                winner.setText("This color wins!");
                ctrlPanel.add(winner);
		
		ctrl[0].addActionListener(new ResetAction());
		ctrl[1].addActionListener(new UndoAction());
		ctrl[2].addActionListener(new SizeAction());
		ctrl[3].addActionListener(new QuitAction());
		
		setLayout(new BorderLayout());
		add(ctrlPanel,BorderLayout.SOUTH);
		add(board,BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		TileFrame frame = new TileFrame("Alien Tiles");
		frame.setSize(800,800);
		frame.setLocation(300,300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	class TileAction implements ActionListener {
                @Override
		public void actionPerformed(ActionEvent e) {
			CellButton theCell = (CellButton)(e.getSource());
			ActionStack.push(theCell);
			int rowAt = theCell.getRow();
			int colAt = theCell.getColumn();
			for (int i=0; i<=rowAt; i++) {
				cells[i][colAt].toggle(false);
			}
			for (int i=rowAt+1; i<numberOfCells; i++) {
				cells[i][colAt].toggle(false);
			}
			for (int i=0; i<colAt; i++) {
				cells[rowAt][i].toggle(false);
			}
			for (int i=colAt+1; i<numberOfCells; i++) {
				cells[rowAt][i].toggle(false);
			}
		}
		
	}
	
	class ResetAction implements ActionListener {
                @Override
		public void actionPerformed(ActionEvent e) {
			if (JOptionPane.showConfirmDialog(null,"Start a new game?","Reset",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
			for (int a=0; a<numberOfCells; a++) {
                            for (CellButton cell : cells[a]) {
                                cell.toggle(true);
                            }
			}
		    }
		}
	}
   
   	class QuitAction implements ActionListener {
                @Override
		public void actionPerformed(ActionEvent e) {
			dispose();
	}
   }
   
   class UndoAction implements ActionListener {
                @Override
		public void actionPerformed(ActionEvent e) {
			if (!ActionStack.empty()) {
			
			CellButton lastCell = (CellButton)ActionStack.pop();
			
			int prevRowAt = lastCell.getRow();
			int prevColAt = lastCell.getColumn();
			
			for (int i=0; i<=prevRowAt; i++) {
				cells[i][prevColAt].toggle(false);
			}
			for (int i=prevRowAt+1; i<numberOfCells; i++) {
				cells[i][prevColAt].toggle(false);
			}
			for (int i=0; i<prevColAt; i++) {
				cells[prevRowAt][i].toggle(false);
			}
			for (int i=prevColAt+1; i<numberOfCells; i++) {
				cells[prevRowAt][i].toggle(false);
			}
		}
	}
   }
   
   	class SizeAction implements ActionListener {
                @Override
		public void actionPerformed(ActionEvent e) {
			board.removeAll();
			
			String n = JOptionPane.showInputDialog("Enter the number of cells:");
			numberOfCells = Integer.parseInt(n);
			if (numberOfCells <= 7) {
                            for (int i=0; i<numberOfCells; i++) {
                                    for (int j=0; j<numberOfCells; j++) {
                                            board.add(cells[i][j]);
                                    }
                            }
                            repaint();
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Board cannot be resized to greater than 7 square tiles.", "Notice", JOptionPane.OK_CANCEL_OPTION);
                        }
                }
            }
}
