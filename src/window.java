import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class window extends JFrame {

    BST bst = new BST();

    private JLabel msg;
    private JButton addNodeButton;
    private JButton deleteNodeButton;
    private JButton displayTreeButton;
    private JButton findNodeWithGivenButton;
    private JButton findNodeWithMinButton;
    private JButton findNodeWithMaxButton;
    private JButton heighOfTreeButton;
    private JButton amOfInternalNodesButton;
    private JButton amOfExternalNodesButton;
    private JButton amOfLeafsButton;
    private JButton displayByLevelsButton;
    private JButton displayInOrderButton;
    private JButton displayPreOrderButton;
    private JButton displayPostOrderButton;
    private JButton findNextNodeAfterButton;
    private JButton findNodeBeforeButton;
    private JTextField NextNode;
    private JTextField BeforeNode;
    private JTextField FindNode;
    private JTextField AddNode;
    private JTextField DeleteNode;
    private JTextArea text;
    private JButton clearButton;
    private JButton infosButton;
    private JPanel RootPanel;
    private JButton leftHButton;
    private JButton rightHButton;
    private JButton lAmountButton;
    private JButton rAmountButton;
    private JButton totalAmountOfNodesButton;
    private JTextField given;
    private JButton RESTARTButton;
    private JTextPane txt;

    public window() {

        super("RED BLACK TREE");



        // 5 2 7 1 4 6 12 3 9 8
        // 1 2 3 4 5 6 7 8 9 10 11 12

      /*  bst.addNode(6);
        bst.addNode(8);
        bst.addNode(3);
        bst.addNode(4);
        bst.addNode(2);
        bst.addNode(10);
        bst.addNode(7);

        BST.Node node = bst.findNode(6);
       // bst.LeftRotate(node);
        //bst.RightRotate(node);
        bst.DisplayTree(text);  */



      given.setText("root");

        addNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int key,l;
                String[] a = AddNode.getText().split(" ");
                l = a.length;

                try{

                    for(int i = 0; i < l; i++){

                        key = Integer.parseInt(a[i]);
                        bst.addNode(key);

                    }

                    //key = Integer.parseInt(AddNode.getText());
                   // bst.addNode(key);
                    AddNode.setText("");

                }

                catch(Exception q){
                    q.printStackTrace();
                    AddNode.setText("Wrong key number");

                }

            }
        });
        deleteNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int key;

                try{
                    key = Integer.parseInt(DeleteNode.getText());
                    BST.Node node = bst.findNode(key);

                    if(node != null){
                        bst.DeleteNode(node);
                    }
                    DeleteNode.setText("");

                }

                catch(Exception a){
                    a.printStackTrace();
                    DeleteNode.setText("Wrong key number");

                }

            }
        });
        displayByLevelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.DisplayByLevels(text);
            }
        });
        displayInOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.inOrder(text);
            }
        });
        displayPreOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.preOrder(text);
            }
        });
        displayPostOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.postOrder(text);
            }
        });
        findNodeWithGivenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int key;

                try{
                    key = Integer.parseInt(FindNode.getText());
                    bst.findNode(key, text);
                    FindNode.setText("");

                }

                catch(Exception a){
                    FindNode.setText("Wrong key number");

                }
            }
        });
        findNodeWithMinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.MIN(text);
            }
        });
        findNodeWithMaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.MAX(text);
            }
        });
        heighOfTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.heigh(text);
            }
        });
        amOfInternalNodesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.InternalNodes(text);
            }
        });
        amOfExternalNodesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.ExternalNodes(text);
            }
        });
        amOfLeafsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.Leafs(text);
            }
        });
        findNextNodeAfterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int key;
                BST.Node node;

                try{
                    key = Integer.parseInt(NextNode.getText());
                    node = bst.findNode(key);
                    bst.ElemNext(node,text);
                    NextNode.setText("");
                }

                catch(Exception a){
                    NextNode.setText("Wrong key number");

                }

            }
        });
        findNodeBeforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int key;
                BST.Node node;

                try{
                    key = Integer.parseInt(BeforeNode.getText());
                    node = bst.findNode(key);
                    bst.ElemBefore(node,text);
                    BeforeNode.setText("");
                }

                catch(Exception a){
                    BeforeNode.setText("Wrong key number");

                }

            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");
            }
        });
        infosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame2 = new JFrame("Informations");
                frame2.setSize(500,150);
                frame2.setLocation(300,300);
                frame2.setLayout(new GridBagLayout());
                JLabel label = new JLabel("Program made by \n\nIgor Czeczot   ");
                JLabel label2 = new JLabel("Test RED-BLACK tree with integer values");
                frame2.add(label);
                frame2.add(label2);
                frame2.setVisible(true);

            }
        });
        displayTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst.DisplayTree(text);
            }
        });
        totalAmountOfNodesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int key;
                BST.Node node;

                if(given.getText().contains("root")){

                    bst.countNodes(bst.root,text);
                }

                else{

                    try{
                        key = Integer.parseInt(given.getText());
                        node = bst.findNode(key);
                        bst.countNodes(node,text);

                    }

                    catch(Exception a){
                        given.setText("Wrong key number");

                    }

                }
            }
        });
        rAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int key;
                BST.Node node;

                if(given.getText().contains("root")){

                    bst.RcountNodes(bst.root,text);
                }

                else{

                    try{
                        key = Integer.parseInt(given.getText());
                        node = bst.findNode(key);
                        bst.RcountNodes(node,text);

                    }

                    catch(Exception a){
                        given.setText("Wrong key number");

                    }

                }
            }
        });
        lAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int key;
                BST.Node node;

                if(given.getText().contains("root")){

                    bst.LcountNodes(bst.root,text);
                }

                else{

                    try{
                        key = Integer.parseInt(given.getText());
                        node = bst.findNode(key);
                        bst.LcountNodes(node,text);

                    }

                    catch(Exception a){
                        given.setText("Wrong key number");

                    }

                }
            }
        });
        rightHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int key;
                BST.Node node;

                if(given.getText().contains("root")){

                    bst.RightTreeHeigh(bst.root,text);
                }

                else{

                    try{
                        key = Integer.parseInt(given.getText());
                        node = bst.findNode(key);
                        bst.RightTreeHeigh(node,text);
                    }

                    catch(Exception a){
                        given.setText("Wrong key number");
                    }

                }
            }
        });
        leftHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int key;
                BST.Node node;

                if(given.getText().contains("root")){

                    bst.LeftTreeHeigh(bst.root,text);
                }

                else{

                    try{
                        key = Integer.parseInt(given.getText());
                        node = bst.findNode(key);
                        bst.LeftTreeHeigh(node,text);
                    }

                    catch(Exception a){
                        given.setText("Wrong key number");
                    }

                }
            }
        });
        RESTARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bst = new BST();
                text.setText("restarting.");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args){

        JFrame frame = new JFrame("RED BLACK TREE");
        frame.setContentPane(new window().RootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
