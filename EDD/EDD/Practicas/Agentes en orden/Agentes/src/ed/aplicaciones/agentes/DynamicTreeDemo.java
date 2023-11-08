/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * 
 * Modified for the purposes of the course "Programación II"
 */ 

package ed.aplicaciones.agentes;

/*
 * This code is based on an example provided by Richard Stanford, 
 * a tutorial reader.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;

/**
 * Main panel for a graphic interface that displays a binary balanced search tree
 * on the left panel, and its contents in the correct order on the right panel.
 * @author VeronicaArriola
 */
public class DynamicTreeDemo extends JPanel 
                             implements ActionListener {
    private static String ADD_COMMAND = "add";
    private static String REMOVE_COMMAND = "remove";
    private static String CLEAR_COMMAND = "clear";
    private static String SAVE_COMMAND = "save";
    private static String LOAD_COMMAND = "load";
    
    private JFrame mainFrame;
    private JPanel mainPanel;
    private DynamicTreePanel<String> treePanel;
    private HTMLDocument htmlDocument;
    private Element treeBox;
    
    private final JFileChooser fileChooser = new JFileChooser();

    /**
     * Constructor that receives a reference to the main frame to use as
     * parent container when needed
     * (adding the menu bar, showing dialogues, etc.).
     * @param mainFrame Frame that contains <code>this</code> panel.
     */
    public DynamicTreeDemo(JFrame mainFrame) {
        super();
        
        this.mainFrame = mainFrame;
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        //Add menu bar
        addMenuBar();
        
        //Create the components.
        treePanel = new DynamicTreePanel<>();
        populateTree(treePanel);

        JButton addButton = new JButton("Add");
        addButton.setActionCommand(ADD_COMMAND);
        addButton.addActionListener(this);
        
        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeButton.addActionListener(this);
        
        JButton clearButton = new JButton("Clear");
        clearButton.setActionCommand(CLEAR_COMMAND);
        clearButton.addActionListener(this);

        //Lay everything out.
        treePanel.setPreferredSize(new Dimension(300, 350));
        mainPanel.add(treePanel, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(0,3));
        panel.add(addButton);
        panel.add(removeButton); 
        panel.add(clearButton);
		mainPanel.add(panel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        JEditorPane p = new JEditorPane();
        p.setPreferredSize(new Dimension(400, 375));
        p.setContentType("text/html");
        p.setText("<html>\n" +
        "   <head>\n" +
        "     <title>An example HTMLDocument</title>\n" +
        "     <style type=\"text/css\">\n" +
        "       div { background-color: silver; padding: 10px; }\n" +
        "     </style>\n" +
        "   </head>\n" +
        "   <body>\n" +
        "     <div id=\"TREEBOX\">\n" +
        "       <p>Paragraph 1</p>\n" +
        "       <p>Paragraph 2</p>\n" +
        "     </div>\n" +
        "   </body>\n" +
        " </html>");
        htmlDocument = (HTMLDocument)p.getDocument();
        treeBox = htmlDocument.getElement("TREEBOX");
        
        add(p);
        
        try{
            htmlDocument.setInnerHTML(treeBox, treePanel.treeToHtml());
        } catch( BadLocationException | IOException ble){
            Logger.getLogger(DynamicTreeDemo.class.getName()).log(Level.SEVERE, null, ble);
        }
    }
    
    /**
     * Creates the menu bar with the commands to manipulate the tree
     * and sets it to the container frame.
     */
    protected void addMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);
        
        JMenuItem menuItem = new JMenuItem("Save...", KeyEvent.VK_S);
        menuItem.setActionCommand(SAVE_COMMAND);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Load...", KeyEvent.VK_L);
        menuItem.setActionCommand(LOAD_COMMAND);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        mainFrame.setJMenuBar(menuBar);
    }

    /**
     * For demonstrating purposes it populates the tree with default values.
     * @param treePanel 
     */
    public void populateTree(DynamicTreePanel treePanel) {
        String[] agents = {"James Bond", "Solid Snake", "Austin Powers", "Hanna",
        "Jason Bourne", "Evelyn Salt", "Maxwell Smart"};
        for(int i = 0; i < agents.length; i++){
            treePanel.addObject(agents[i]);
        }
    }
    
    /**
     * Channels the actions ordered by the user through the interface buttons
     * to the objects and methods that can address them.
     * @param e Details of the event received.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (ADD_COMMAND.equals(command)) {
            //Add button clicked
            String s = (String)JOptionPane.showInputDialog(this,
                    "Ingrese el nombre de un agente:",
                    "Registro",
                    JOptionPane.PLAIN_MESSAGE);
            if(s != null && !s.equals("")){
                treePanel.addObject(s);
            }
        } else if (REMOVE_COMMAND.equals(command)) {
            //Remove button clicked
            treePanel.removeCurrentNode();
        } else if (CLEAR_COMMAND.equals(command)) {
            //Clear button clicked.
            treePanel.clear();
        } else if (SAVE_COMMAND.equals(command)) {
            //Save menu clicked
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    //This is where the application opens the file.
                    treePanel.saveTree(file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Failed to save file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                return;
            }

        } else if (LOAD_COMMAND.equals(command)) {
            // Load menu clicked
			int result = JOptionPane.showConfirmDialog(this,
					"Loading a tree will delete current tree.  Do you wish to proceed?",
					"alert", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result != JOptionPane.OK_OPTION) {
				return;
			}
			
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    //This is where a real application would open the file.
                    treePanel.loadTree(file);
                } catch (FileNotFoundException fne){
                    JOptionPane.showMessageDialog(mainFrame, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Failed to save file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                return;
            }
        }
        
        try{
            htmlDocument.setInnerHTML(treeBox, treePanel.treeToHtml());
        } catch( BadLocationException | IOException ble){
            Logger.getLogger(DynamicTreeDemo.class.getName()).log(Level.SEVERE, null, ble);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Register of Special Agents");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DynamicTreeDemo newContentPane = new DynamicTreeDemo(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Entry point for the application that launches the graphic interface to
     * display a binary balanced search tree.
     * @param args 
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
