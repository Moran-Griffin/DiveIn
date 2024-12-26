package diving.gui;
import javax.swing.*;

import diving.DifficultyTable1mS;
import scoring.CompositeScore;
import scoring.DropRule;
import scoring.WeightedTotalSystem;

import java.awt.*;
import java.awt.event.*;

/**
 * Creates a DiveInWindow GUI that provides the user with the opportunity to add various Scores
 * the GUI allows for Scores to be added line by line and it automatically 
 * calculates the weighted total of the scores along with displaying raw scores for each dive.
 * 
 * @author Griffin Moran
 * @version 1.0
 */
public class DiveInWindow extends JFrame implements ActionListener, KeyListener
//ask about actionPreformed methods
{
  private static final long serialVersionUID = 1L;
  JTextArea text;
  JLabel points;
  DiveEditorDialog de;
  private String[] judges;
  private DifficultyTable1mS dt;
  private String contents = "";
  private CompositeScore totalScores;
  private final String nA = "N/A";
  private double weightedTotal = 0.0;
  private final String exit = "Exit";
  private final String reset = "Reset";
  private final String add = "Add";
  
  
  /**
   * Creates a DiveInWindow and sets up the display.
   * 
   * @param judges The list of judges to use
   * @param dt The table containing score values
   */
  public DiveInWindow(final String[] judges, final DifficultyTable1mS dt) 
  {
    this.judges = judges;
    this.dt = dt;
    setup();
  }
  
  /**
   * Helper method that sets up the entire GUI adding the menu with its various menu items
   * along with the output display.
   */
  public void setup() 
  {
    setTitle("DiveIn by Moran2gc@dukes.jmu.edu");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setFocusable(true);
    
    addKeyListener(this);

    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    
    JMenuBar menu = new JMenuBar();
    JMenu file, edit;
    JMenuItem exitB, addB, resetB;
    
    file = new JMenu("File");
    menu.add(file);  
    
    edit = new JMenu("Edit");
    menu.add(edit);  
    
    exitB = new JMenuItem(exit);
    file.add(exitB);
    
    exitB.addActionListener(this);
    
    addB = new JMenuItem(add);
    edit.add(addB);
    
    addB.addActionListener(this);
    
    resetB = new JMenuItem(reset);
    edit.add(resetB);
    
    resetB.addActionListener(this);
    
    contentPane.add(menu, BorderLayout.NORTH);
    
    text = new JTextArea();
    text.setForeground(Color.BLUE);
    text.setEditable(false);
    JScrollPane scroll = new JScrollPane(text);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    contentPane.add(scroll, BorderLayout.CENTER);
    
    JPanel total = new JPanel();
    total.setPreferredSize(new Dimension(300, 40));
    total.setLayout(new BorderLayout());
    
    JLabel label = new JLabel("Total Points", SwingConstants.LEFT);
    points = new JLabel(nA, SwingConstants.LEFT);
    points.setForeground(Color.BLUE);
    
    total.add(label, BorderLayout.NORTH);
    total.add(points, BorderLayout.CENTER);
    
    contentPane.add(total, BorderLayout.SOUTH);
    
    setResizable(false);
    setVisible(true);
  }
  
  /**
   * Creates a DiveEditorDialog and displays that to take in information entered
   * by the user about the dive.
   * If the value returned by the getValue() method is null nothing is changed on the output screen.
   */
  public void add() 
  {
    de = new DiveEditorDialog(judges, dt);
    de.ok.addActionListener(this);   
  }
  
  /**
   * Resets the GUI to its initial state before any scores were added.
   */
  public void clear() 
  {
    contents = "";
    text.setText(contents);
    points.setText(nA); 
    weightedTotal = 0.0;
  }

  @Override
  public void actionPerformed(final ActionEvent ae)
  {
    String command = ae.getActionCommand();
    
    if(command.equals(reset)) 
    {
      clear();
    }
    if(command.equals(add)) 
    {
      add();
    }
    
    if(command.equals(exit)) 
    {
      System.exit(0);
    }
    
    if(command.equals("OK")) 
    {
      CompositeScore cs = de.getValue();
      if(cs == null) 
      {
        return;
      }
      
      contents += (cs.getKey() + " " + Double.toString(cs.getValue()) + "\n");
      text.setText(contents);
      
      totalScores = new CompositeScore(cs.getKey(),
          new DropRule(false, false), new WeightedTotalSystem(dt));
      totalScores.add(cs);
      weightedTotal += totalScores.getValue();
      
      points.setText(String.format("%.1f",weightedTotal));
      
      de.dispose();
    }
  }
  
  @Override
  public void keyPressed(final KeyEvent e) 
  {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
    {
      System.exit(0);
    }
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    
  }
}
