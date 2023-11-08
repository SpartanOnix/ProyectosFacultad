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
 * Modified for the purposes of the course "Programación II"
 */ 

package ed.aplicaciones.agentes;

/*
 * This code is based on an example provided by Richard Stanford, 
 * a tutorial reader.
 */

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Panel that displays the binary balanced search tree.
 * @author VeronicaArriola
 */
public class DynamicTreePanel<C extends Comparable<C>> extends JPanel {
    /**
     * The actual tree.
     */
    protected AVLTreeModel<C> treeModel;
    
    /**
     * The graphic element to display the tree.
     */
    protected JTree tree;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    /**
     * Constructor that creates the tree and displays it on the screen.
     */
    public DynamicTreePanel() {
        super(new GridLayout(1,0));
        
        treeModel = new AVLTreeModel<>();
        /// TODO BONUS: See MyTreeModelListener at the end of this class.
		//treeModel.addTreeModelListener(new MyTreeModelListener());
        tree = new JTree(treeModel);
        tree.setEditable(false); // TODO BONUS: make true
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);

        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);
    }

    /** Removes all nodes. */
    public void clear() {
        treeModel.clear();
    }

    /** Removes the currently selected node and rebalances the tree. */
    public void removeCurrentNode() {
        TreePath currentSelection = tree.getSelectionPath();
        if (currentSelection != null) {
            AVLTreeNode currentNode = (AVLTreeNode)
                         (currentSelection.getLastPathComponent());
            AVLTreeNode parent = (AVLTreeNode)(currentNode.getParent());
            treeModel.removeNodeFromParent(currentNode);
            return;
        } 

        // There was no selection.
        toolkit.beep();
    }

    /**
     * Adds a child node to the tree, with the indicated <code>Comparable</code>
     * inside and rebalance.
     * @param child Object to be inserted.
     * @return The newly created node.
     */
    public AVLTreeNode addObject(C child) {
        
        AVLTreeNode childNode = treeModel.addObject(child);
        
        //Make sure the user can see the lovely new node.
        Object[] tp = childNode.getPath();
        tree.scrollPathToVisible(new TreePath(tp));
        return childNode;
    }
    
    /**
     * Returns a string containing all the elements of the AVLTree in order
     * with html format.
     * @return The html string.
     */
    public String treeToHtml(){
        return treeModel.toHtml();
    }
    
    /**
     * Saves the tree to the indicated file.
     * @param file Object with access to the file where the tree will be saved.
     * @throws IOException 
     */
    public void saveTree(File file) throws IOException{
        treeModel.saveTree(file);
    }
    
    /**
     * Loads the tree from the indicated file.
     * @param file Object with access to the file where the tree is stored.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadTree(File file) throws FileNotFoundException, IOException{
        treeModel.loadTree(file);
    }
    
    //    class MyTreeModelListener implements TreeModelListener {
//        @Override
//        public void treeNodesChanged(TreeModelEvent e) {
//            ///
//            /// TODO BONUS: Tree must be sorted again if the content of a node
//            ///             is modified.
//            DefaultMutableTreeNode node;
//            node = (DefaultMutableTreeNode)(e.getTreePath().getLastPathComponent());
//
//            /*
//             * If the event lists children, then the changed
//             * node is the child of the node we've already
//             * gotten.  Otherwise, the changed node and the
//             * specified node are the same.
//             */
//
//                int index = e.getChildIndices()[0];
//                node = (DefaultMutableTreeNode)(node.getChildAt(index));
//
//            System.out.println("The user has finished editing the node.");
//            System.out.println("New value: " + node.getUserObject());
//        }
//        @Override
//        public void treeNodesInserted(TreeModelEvent e) {
//        }
//        @Override
//        public void treeNodesRemoved(TreeModelEvent e) {
//        }
//        @Override
//        public void treeStructureChanged(TreeModelEvent e) {
//        }
//    }

}
