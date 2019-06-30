package trees;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this program add/delete/edit all roots and elements in tree
public class SimpleTreeFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 200;

    private DefaultTreeModel model;
    private JTree tree;

    public SimpleTreeFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        TreeNode root = makeSampleTree();
        model = new DefaultTreeModel(root);
        tree = new JTree(model);

        tree.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane, BorderLayout.CENTER);

        makeButtons();
    }

    public TreeNode makeSampleTree() {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
        DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
        root.add(country);
        DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
        country.add(state);
        DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
        state.add(city);
        city = new DefaultMutableTreeNode("Cupertino");
        state.add(city);
        state = new DefaultMutableTreeNode("Michigan");
        country.add(state);
        city = new DefaultMutableTreeNode("Ann Arbor");
        state.add(city);
        country = new DefaultMutableTreeNode("Germany");
        root.add(country);
        state = new DefaultMutableTreeNode("Schleswing-Holstein");
        country.add(state);
        city = new DefaultMutableTreeNode("Kiel");
        state.add(city);
        return root;
    }

    public void makeButtons() {
        JPanel panel = new JPanel();
        JButton addSiblingButton = new JButton("Add Sibling");
        addSiblingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode =
                        (DefaultMutableTreeNode)
                         tree.getLastSelectedPathComponent();

                if (selectedNode == null) return;

                DefaultMutableTreeNode parent =
                        (DefaultMutableTreeNode) selectedNode.getParent();

                if (parent == null) return;

                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New");

                int selectedIndex = parent.getIndex(selectedNode);
                model.insertNodeInto(newNode, parent, selectedIndex + 1);

                TreeNode[] nodes = model.getPathToRoot(newNode);
                TreePath path = new TreePath(nodes);
                tree.scrollPathToVisible(path);
            }
        });
        panel.add(addSiblingButton);
        JButton addChildButton = new JButton("Add Child");
        addChildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode =
                        (DefaultMutableTreeNode)
                         tree.getLastSelectedPathComponent();
                        if (selectedNode == null) return;

                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New");
                model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());

                TreeNode[] nodes = model.getPathToRoot(newNode);
                TreePath path = new TreePath(nodes);
                tree.scrollPathToVisible(path);
            }
        });
        panel.add(addChildButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
                        tree.getLastSelectedPathComponent();

                if (selectedNode != null && selectedNode.getParent() != null)
                    model.removeNodeFromParent(selectedNode);
            }
        });
        panel.add(deleteButton);
        add(panel, BorderLayout.SOUTH);
    }
}
