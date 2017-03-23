package minesweeper.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import minesweeper.Board;
import minesweeper.Bot;
import minesweeper.Logic;
import minesweeper.Mouse;
import minesweeper.Window;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnOpenMinesweeper = new JButton("Open Minesweeper");
		btnOpenMinesweeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window.setMinesweeperSizeAndPosition();
				Window.setMinesweeperToForeground();
			}
		});
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(btnOpenMinesweeper);
		
		JSeparator separator = new JSeparator();
		contentPane.add(separator);
		
		JButton btnSolve = new JButton("Solve");
		btnSolve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				setAlwaysOnTop(false);
				Window.setMinesweeperToForeground();
				Bot.initiate();
				
//				// The middle square
				int startingSquare = 225;
				Mouse.leftClickSquare(startingSquare);
				
				Board.initializeBoard();
				Board.fillBoard();
				Board.printBoard();
				
				int count = 0;
				int lastCount = -1;
				int runs = 0;
				while (Window.gameNotOver()){

					Board.fillBoard();
					count = Logic.SolveBasic();
					count += Logic.solveAdvanced();
					
					if( count == 0 && lastCount == 0 )
					{

						return;
//						System.exit(0);
//						Logic.guess();
//						count = 1;
					}
					lastCount = count;
					runs++;
					if( runs > 100 )
					{
						break;
					}
				}

				setAlwaysOnTop(true);
			}
		});
		contentPane.add(btnSolve);
	}

}
