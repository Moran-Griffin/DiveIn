package diving.gui;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import diving.DifficultyTable1mS;
import scoring.*;

/**
 * The DiveInWindow class creates a GUI that allows the user to
 * select the a dive from a dropdown menu and enter corresponding scores.
 * 
 * If the score is invalid nothing changes.
 * 
 * @author Griffin Moran
 * @version 1.0
 */
public class DiveEditorDialog extends JDialog implements ActionListener
{
  private static final long serialVersionUID = 1L;
  CompositeScore weighted;
  Double total;
  JButton ok;
  private String[] judges;
  private DifficultyTable1mS dt;
  private Container contentPane = getContentPane();
  private JComboBox<String> box;
  private JTextField[] fields;
  private String cancel = "Cancel";
  
  /**
   * The DiveInWindow constructor creates a DiveInWindow that adjusts judge boxes
   * based on how many judges there are for the specified contest.
   * 
   * @param judges The list of Judges to use.
   * @param dt The diffiuclty table to initialize the dropdown menu.
   */
  public DiveEditorDialog(final String[] judges, final DifficultyTable1mS dt) 
  {
    super();
    this.judges = judges;
    this.dt = dt; 
    contentPane.setLayout(new BorderLayout());
    setup();
  }
  
  /**
   * Sets up the visuals for the DiveInWindow, the JDialog is not resizable
   * and it disposes when it is closed.
   * 
   */
  public void setup() 
  {
    setSize(400, 175);
    setTitle("Enter the Scores");
    
    box = new JComboBox<String>();
    box.setForeground(Color.BLUE);
    for(String dive: dt.keySet()) 
    {
      box.addItem(dive);
    }
    
    contentPane.add(box, BorderLayout.NORTH);
    addJudges();
    
    addButtons();
    
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
  }
  
  /**
   * Helper method for the setup, adds the OK and Cancel buttons
   * to the DiveEditorDialog and sets up their corresponding actions.
   * 
   * OK calls the getValue() method
   * Cancel disposes the DiveEditorDialog
   */
  private void addButtons() 
  {
    JPanel buttons = new JPanel();
    JPanel align = new JPanel();
    
    buttons.setLayout(new FlowLayout());
    align.setLayout(new BorderLayout());
    
    this.ok = new JButton("OK");  
    JButton cancelB = new JButton(cancel);
    
    buttons.add(ok);   
    buttons.add(cancelB);
    
    cancelB.addActionListener(this);
    
    align.add(buttons, BorderLayout.EAST);
    
    contentPane.add(align, BorderLayout.SOUTH);
  }
  
  /**
   * Helper method for the setup, adds the countries of the judges
   * along with the textboxes for the user to enter corresponding scores.
   */
  private void addJudges() 
  {
    JPanel entries = new JPanel();
    entries.setLayout(new FlowLayout());
    
    this.fields = new JTextField[judges.length];
    int index =0;
    for(String judge: judges)
    {
      JPanel cur = new JPanel();
      cur.setPreferredSize(new Dimension(70, 50));
      
      JLabel label = new JLabel(judge, SwingConstants.CENTER);
      JTextField tf = new JTextField();
      tf.setForeground(Color.BLUE);
      tf.setHorizontalAlignment(SwingConstants.CENTER);
      
      this.fields[index] = tf;
      index++;
      
      cur.add(label);
      cur.add(tf);
      cur.setLayout(new BoxLayout(cur, BoxLayout.Y_AXIS));
      entries.add(cur);
    }
    contentPane.add(entries, BorderLayout.CENTER);
  }
  
  /**
   * Returns a new CompositeScore created from the selected Dive and entered scores.
   * @return A new CompositeScore if the information is valid, null if othersise.
   */
  public CompositeScore getValue()
  {
    Rule rule = new DropRule(true, true);
    ScoringSystem sys = new WeightedTotalSystem(dt);
    CompositeScore cs = new CompositeScore((String)box.getSelectedItem(), rule, sys);
    for(int i = 0; i < judges.length; i++)
    {
      try 
      {      
        Score sc;
        String value = this.fields[i].getText();
        if(value.equals(""))
        {
          sc = new LeafScore(judges[i], 0.0);
        }
        else
        {
          sc = new LeafScore(judges[i],
              Missing.doubleValue(Double.parseDouble(this.fields[i].getText())));
        }
        cs.add(sc);
      } catch(NullPointerException | NumberFormatException np)
      {
        return null;
      }
    }
    return cs;
  }
  
  @Override
  public void actionPerformed(final ActionEvent ae)
  {
    String command = ae.getActionCommand();
    
    if(command.equals(cancel)) 
    {
      dispose();
    }
  }
}
