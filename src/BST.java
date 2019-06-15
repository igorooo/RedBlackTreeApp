import javax.swing.*;
import java.awt.*;

public class  BST {

    Node root;


    public enum TColor{
        RED,
        BLACK
    }



    public class Node{

        Node left,right,parent;
        int key,level;
        int spaces;
        TColor color;

        public Node(Node parent,int key,int level){
            this.key = key;
            this.parent = parent;
            this.left = this.right = null;
            this.level = level;
            this.color = TColor.RED;

        }

        public void setLeft(Node left){
            this.left = left;
        }

        public void setRight(Node right){
            this.right = right;
        }

        public void setParent(Node parent){
            this.parent = parent;
        }

        public void setKey(int key){
            this.key = key;
        }

        public void setSpaces(int spaces){
            this.spaces = spaces;
        }

        public void setLevel(int level){
            this.level = level;
        }

        public void setColor(TColor color){
            this.color = color;
        }


        @Override
        public String toString() {
            return Integer.toString(key);
        }
    }


    public BST(){
        root = null;
    }

    public void addRBNode(Node node){

        if(node == root){
            node.setColor(TColor.BLACK);
            return;
        }


        if(node.parent.color == TColor.RED){

            Node parent = node.parent;
            Node grandparent = parent.parent;
            Node uncle;


            if( parent == grandparent.right){

                uncle = grandparent.left;

                if(uncle != null){

                    if(uncle.color == TColor.RED){  // CASE 1  (uncle red)

                        uncle.setColor(TColor.BLACK);
                        parent.setColor(TColor.BLACK);
                        grandparent.setColor(TColor.RED);

                        addRBNode(grandparent);

                        return;
                    }

                    if(uncle.color == TColor.BLACK){


                        if( node == parent.left){   // CASE 2  (uncle black, node is left son)

                            RightRotate(parent);

                            addRBNode(parent);

                            return;
                        }

                        if( node == parent.right){  // CASE 3 (uncle black, node is right son)

                            LeftRotate(grandparent);

                            grandparent.setColor(TColor.RED);
                            parent.setColor(TColor.BLACK);

                        }
                    }

                }

                if(uncle == null){

                    if( node == parent.left){   // CASE 2  (uncle black, node is left son)

                        RightRotate(parent);

                        addRBNode(parent);

                        return;
                    }

                    if( node == parent.right){  // CASE 3 (uncle black, node is right son)

                        LeftRotate(grandparent);

                        grandparent.setColor(TColor.RED);
                        parent.setColor(TColor.BLACK);

                    }


                }


            }


            if( parent == grandparent.left){    // MIRRORED CASE

                uncle = grandparent.right;

                if(uncle != null){

                    if(uncle.color == TColor.RED){  // CASE 1  (uncle red)

                        uncle.setColor(TColor.BLACK);
                        parent.setColor(TColor.BLACK);
                        grandparent.setColor(TColor.RED);

                        addRBNode(grandparent);

                        return;
                    }

                    if(uncle.color == TColor.BLACK){


                        if( node == parent.right){   // CASE 2  (uncle black, node is right son)

                            LeftRotate(parent);

                            addRBNode(parent);

                            return;
                        }

                        if( node == parent.left){  // CASE 3 (uncle black, node is left son)

                            RightRotate(grandparent);

                            grandparent.setColor(TColor.RED);
                            parent.setColor(TColor.BLACK);

                        }
                    }
                }

                if(uncle == null){

                    if( node == parent.right){   // CASE 2  (uncle black, node is right son)

                        LeftRotate(parent);

                        addRBNode(parent);

                        return;
                    }

                    if( node == parent.left){  // CASE 3 (uncle black, node is left son)

                        RightRotate(grandparent);

                        grandparent.setColor(TColor.RED);
                        parent.setColor(TColor.BLACK);

                    }
                }
            }
        }
    }



    public void addNode(int key){

        if(root == null){
            root = new Node(null, key,0);
            //root.setSpaces(256);
            System.out.println("node "+key+" root, level 0");
            addRBNode(root);
            return;
        }

        int level = 0;
        Node x = root;
        Node y = root;



        while(x != null){

            level++;
            y = x;

            if(key >= x.key){
                x = x.right;
            }

            else{
                x = x.left;
            }
        }

        Node node = new Node(y,key,level);


        if(node.key < y.key){
            y.setLeft(node);
            //node.setSpaces(y.spaces - (int)(256/Math.pow(2,level)));
        }

        if(node.key >= y.key){
            y.setRight(node);
            // node.setSpaces(y.spaces + (int)(256/Math.pow(2,level)));
        }

        System.out.println("node "+node.key+" parent: "+node.parent.key + " level: " + level);


        addRBNode(node);

    }


    private void changeLevel(Node node, int level){

        if(node == null) return;

        node.setLevel(level);

        changeLevel(node.left, level+1);
        changeLevel(node.right,level +1);
    }

    public void changeLEVEL(){

        changeLevel(root,0);
    }

    public void LeftRotate(Node x){

        Node y = x.right;
        x.right = y.left;

        if( y.left != null){
            y.left.setParent(x);
        }

        y.setParent(x.parent);

        if( x.parent == null){
            root = y;
        }

        if( x.parent != null){

            if(x == x.parent.left){
                x.parent.left = y;
            }

            else{
                x.parent.right = y;
            }
        }

        y.left = x;
        x.parent = y;

        changeLEVEL();

    }

    public void RightRotate(Node x){

        Node y = x.left;
        x.left = y.right;

        if( y.right != null){
            y.right.setParent(x);
        }

        y.setParent(x.parent);

        if( x.parent == null){
            root = y;
        }

        if( x.parent != null){

            if(x == x.parent.left){
                x.parent.left = y;
            }

            else{
                x.parent.right = y;
            }
        }

        y.right = x;
        x.parent = y;

        changeLEVEL();

    }



    private void in_Order(Node node){

        if(node == null) return;

        in_Order(node.left);
        System.out.println(node.key);
        in_Order(node.right);
    }

    public void inOrder(){
        System.out.println("begin of in order");
        in_Order(root);
        System.out.println("end of in order");
    }

    private void in_Order(Node node, JTextArea field){

        if(node == null) return;

        in_Order(node.left,field);
        field.setText(field.getText()+ " " + Integer.toString(node.key));
        in_Order(node.right,field);
    }

    public void inOrder(JTextArea field){
        field.setText("");
        in_Order(root,field);
    }

    private void pre_Order(Node node){

        if(node == null) return;

        System.out.println(node.key);
        pre_Order(node.left);
        pre_Order(node.right);
    }

    public void preOrder(){
        System.out.println("begin of pre order");
        pre_Order(root);
        System.out.println("end of pre order");
    }

    private void pre_Order(Node node, JTextArea field){

        if(node == null) return;

        field.setText(field.getText()+ " " + Integer.toString(node.key));
        pre_Order(node.left,field);
        pre_Order(node.right,field);
    }

    public void preOrder(JTextArea field){
        field.setText("");
        pre_Order(root,field);
    }

    private void post_Order(Node node){

        if(node == null) return;

        pre_Order(node.left);
        pre_Order(node.right);
        System.out.println(node.key);
    }

    public void postOrder(){
        System.out.println("begin of post order");
        post_Order(root);
        System.out.println("end of post order");
    }

    private void post_Order(Node node, JTextArea field){

        if(node == null) return;

        post_Order(node.left,field);
        post_Order(node.right,field);
        field.setText(field.getText()+ " " + Integer.toString(node.key));
    }

    public void postOrder(JTextArea field){
        field.setText("");
        post_Order(root,field);
    }

    public Node findNode(int key){

        if(root == null){
            System.out.println("Tree is empty");
            return null;
        }

        Node x = root;
        Node y = root;

        String a = "";



        while(x != null){



            if(x.key == key){

                if(x == root){
                    System.out.println("Found node with key: "+x.key + ", he is root");
                    return root;
                }

                if(x == y.right){
                    a = "right";
                }

                if(x == y.left){
                    a = "left";
                }

                System.out.println("Found node with key: "+x.key + ", he is " + a + " son of " + x.parent.key );
                return x;
            }

            y = x;

            if(key >= x.key){
                x = x.right;
            }

            else{
                x = x.left;
            }
        }



        System.out.println("There is no such a node");
        return null;

    }

    public void findNode(int key, JTextArea field){

        if(root == null){
            field.setText("Tree is empty");
            return;
        }

        Node x = root;
        Node y = root;

        String a = "";



        while(x != null){



            if(x.key == key){

                if(x == root){
                    field.setText("Found node with key: "+x.key + ", he is root");
                    return;
                }

                if(x == y.right){
                    a = "right";
                }

                if(x == y.left){
                    a = "left";
                }

                field.setText("Found node with key: "+x.key + ", he is " + a + " son of " + x.parent.key );
                return;
            }

            y = x;

            if(key >= x.key){
                x = x.right;
            }

            else{
                x = x.left;
            }
        }



        field.setText("There is no such a node");

    }

    public Node min(Node node){
        if(node == null){
            return null;
        }

        Node x = node;

        while(x.left != null){
            x = x.left;
        }

        return x;
    }

    public void MIN(){
        System.out.println("Min: "+ min(root));
    }

    public void MIN(JTextArea field){
        field.setText("Min: "+ min(root));
    }



    public Node max(Node node){
        if(node == null){
            return null;
        }

        Node x = node;

        while(x.right != null){
            x = x.right;
        }

        return x;
    }

    public void MAX(){
        System.out.println("Max: "+ max(root));
    }

    public void MAX(JTextArea field){
        field.setText("Max: "+ max(root));
    }


    private class LVL{

        int level;

        protected LVL(){
            level = 0;
        }

        public void changeLVL(int level){
            this.level = level;
        }

        public void upLVL(){
            level++;
        }

    }

    /*private void heigh_(Node node, LVL lvl){

        if(node == null) return;

        if(node.level > lvl.level){
            lvl.changeLVL(node.level);
        }

        heigh_(node.left,lvl);
        heigh_(node.right,lvl);

    } */

    public int Heigh(Node node){

        if(node == null) return 0;

        int left = Heigh(node.left);
        int right = Heigh(node.right);

        return 1 + Math.max(left,right);
    }

    public int heigh(){

        int h = Heigh(root);

        System.out.println("Heigh of tree is: "+ h);
        return h;

    }

    public void heigh(JTextArea field){

        int h = Heigh(root);

        field.setText("Heigh of tree is: "+ h);

    }

    private void Internal_Nodes(Node node, LVL lvl){

        if(node == null) return;

        lvl.upLVL();

        Internal_Nodes(node.left,lvl);
        Internal_Nodes(node.right,lvl);

    }

    public int InternalNodes(){

        LVL lvl = new LVL();

        Internal_Nodes(root,lvl);

        System.out.println("Tree has "+ lvl.level + " internal nodes");
        return lvl.level;

    }

    public void InternalNodes(JTextArea field){

        LVL lvl = new LVL();

        Internal_Nodes(root,lvl);

        field.setText("Tree has "+ lvl.level + " internal nodes");

    }

    private void External_Nodes(Node node, LVL lvl){

        if(node == null){
            lvl.upLVL();
            return;
        }

        External_Nodes(node.left,lvl);
        External_Nodes(node.right,lvl);

    }

    public int ExternalNodes(){

        LVL lvl = new LVL();

        External_Nodes(root,lvl);

        System.out.println("Tree has "+ lvl.level + " external nodes");
        return lvl.level;

    }

    public void ExternalNodes(JTextArea field){

        LVL lvl = new LVL();

        External_Nodes(root,lvl);

        field.setText("Tree has "+ lvl.level + " external nodes");

    }

    private void Leafs_(Node node, LVL lvl){

        if(node == null){
            return;
        }

        if(node.left == null && node.right == null){
            lvl.upLVL();
        }

        Leafs_(node.left,lvl);
        Leafs_(node.right,lvl);

    }

    public int Leafs(){

        LVL lvl = new LVL();

        Leafs_(root,lvl);

        System.out.println("Tree has "+ lvl.level + " leafs");
        return lvl.level;

    }

    public void Leafs(JTextArea field){

        LVL lvl = new LVL();

        Leafs_(root,lvl);

        field.setText("Tree has "+ lvl.level + " leafs");

    }

    /** ZAD 1B
     *
     *
     * DISPLAYING TREE
     * DISPLAYING LEVEL BY LEVEL
     * NEXT AND PREVIOUS ELEMENT
     *
     *
     * **/



    private class LEVELS{

        int h;

        LinkQueue<Node> array[] = new LinkQueue[32];

        public LEVELS(){

            h = heigh();

           // array[] = new LinkQueue[h];

            for(int i = 0; i <= h; i++){
                array[i] = new LinkQueue<Node>();
            }

        }

        private void addNodeOnLvl(Node node){

            array[node.level].enqueue(node);

        }

        public void displayLevels(){

            for(int i = 0; i <= h; i++){
                System.out.print("Level (" + i + "): ");

                while(array[i].size() > 0){
                    System.out.print(array[i].dequeue() + " ");
                }

                System.out.println();

            }
        }

        public void displayLevels(JTextArea field){

            field.setText("");

            for(int i = 0; i <= h; i++){
                //System.out.print("Level (" + i + "): ");
                field.setText(field.getText() + "Level " + Integer.toString(i)+ " : ");

                while(array[i].size() > 0){
                    //System.out.print(array[i].dequeue() + " ");
                    field.setText(field.getText()+array[i].dequeue() + " ");
                }

                field.setText(field.getText()+"\n");

            }
        }

        public void SetSpaces(Node node){

            if(node == null){
                return;
            }

            if(node == root){
                node.setSpaces((int)Math.pow(2,heigh()+2));

            }

            if(node != root){

                if(node == node.parent.left){

                    node.setSpaces(node.parent.spaces - (int)(root.spaces/Math.pow(2,node.level)));
                }

                if(node == node.parent.right){

                    node.setSpaces(node.parent.spaces + (int)(root.spaces/Math.pow(2,node.level)));
                }

            }

            SetSpaces(node.left);
            SetSpaces(node.right);

        }



        public void displaytree(JTextArea field){

            field.setText("");
            Node temp;

            SetSpaces(root);

            Font font = new Font("Verdana", Font.BOLD, 12);
            field.setFont(font);
            field.setForeground(Color.BLACK);



            for(int i = 0; i <= h; i++){

                int sp = 0; //spaces

                while(array[i].size() > 0){

                    temp = array[i].dequeue();

                    while(temp.spaces > sp){
                        sp++;
                        field.setText(field.getText() + " ");
                    }

                    if(temp.color == TColor.RED){
                        field.setText(field.getText() + "{" + temp + "}" );
                    }

                    if(temp.color == TColor.BLACK){
                        field.setText(field.getText() + "[" + temp + "]" );
                    }



                    if(temp.key < 10){
                        sp += 3;
                    }

                    else if(temp.key < 100){
                        sp += 4;
                    }

                    else{
                        sp += 5;
                    }

                }

                field.setText(field.getText()+"\n\n");

            }

            field.setText(field.getText()+"[NODE] = BLACK NODE, {NODE} = RED NODE \n");
        }

    }

    public void DisplayByLevels(){

        LEVELS lvl = new LEVELS();
        addNodesOnLevel(root,lvl);

        lvl.displayLevels();

    }

    public void DisplayByLevels(JTextArea field){

        LEVELS lvl = new LEVELS();
        addNodesOnLevel(root,lvl);

        lvl.displayLevels(field);

    }

    public void DisplayTree(JTextArea field){

        LEVELS lvl = new LEVELS();
        addNodesOnLevel(root,lvl);

        lvl.displaytree(field);

    }

    private void addNodesOnLevel(Node node, LEVELS lvl){

        if(node == null) return;

        lvl.addNodeOnLvl(node);

        addNodesOnLevel(node.left,lvl);
        addNodesOnLevel(node.right,lvl);

    }

    public Node ElemBefore(Node node){

        if(node.left != null){
            return max(node.left);
        }

        if(node.left == null){

            Node x = node;

            while(x.parent != null){

                if( x == x.parent.right){
                    return x.parent;
                }

                x = x.parent;
            }
        }

        System.out.println("Node have minimal key");
        return null;

    }

    public void ElemBefore(Node node, JTextArea field){

        if(node.left != null){
            Node a = max(node.left);
            field.setText("Node before is: " + Integer.toString(a.key));
            return;

        }

        if(node.left == null){

            Node x = node;

            while(x.parent != null){

                if( x == x.parent.right){
                    field.setText("Node before is: " + Integer.toString(x.parent.key));
                    return;
                }

                x = x.parent;
            }
        }

        field.setText("Node have minimal key");

    }

    public Node ElemNext(Node node){

        if(node.right != null){
            return min(node.right);
        }

        if(node.right == null){

            Node x = node;

            while(x.parent != null){

                if( x == x.parent.left){
                    return x.parent;
                }

                x = x.parent;
            }
        }

        System.out.println("Node have maximal key");
        return null;

    }

    public void ElemNext(Node node, JTextArea field){

        if(node.right != null){
            Node a = min(node.right);
            field.setText("Next node is: " + Integer.toString(a.key));
            return;
        }

        if(node.right == null){

            Node x = node;

            while(x.parent != null){

                if( x == x.parent.left){
                    field.setText("Next node is: " + Integer.toString(x.parent.key));
                    return;
                }

                x = x.parent;
            }
        }

        field.setText("Node have maximal key");

    }

    public void DeleteNode(Node node){




        if(node.left == null && node.right == null){

            if(node.parent.left == node){
                node.parent.setLeft(null);
            }

            if(node.parent.right == node){
                node.parent.setRight(null);
            }

            return;

        }

        if(node.left != null && node.right == null){

            node.left.setParent(node.parent);
            node = node.left;

            if(node == node.parent.right){

                node.parent.right = node.right;
            }

            if(node == node.parent.left){

                node.parent.left = node.left;
            }

            deleteRBNode(node);
            return;


        }

        if(node.left == null && node.right != null){

            node.right.setParent(node.parent);

            node.parent.right = node.right;

            node = node.right;

            deleteRBNode(node);
            return;

        }

        Node next = ElemNext(node);

        node.setKey(next.key);

        DeleteNode(next);

        deleteRBNode(node);
    }

    public void deleteRBNode(Node node){


        if(node == root){
            node.setColor(TColor.BLACK);
            return;
        }


        Node parent = node.parent;
        Node bro;

        if( node == parent.left){

            bro = parent.right;

            if(bro != null ){

                if( bro.color == TColor.RED){      // CASE 1, bro is red

                    LeftRotate(parent);
                    parent.setColor(TColor.RED);
                    bro.setColor(TColor.BLACK);

                    deleteRBNode(node);
                    return;
                }

                if( bro.color == TColor.BLACK){


                    if(bro.right != null){   // if bro right son is not null  (avoid NullPointerException)  (better would be Nil[T] )

                        if(bro.right.color == TColor.RED){     //CASE 4, bro is black, and his right son is red

                            LeftRotate(parent);
                            bro.setColor(parent.color);
                            parent.setColor(TColor.BLACK);

                            return;
                        }
                    }

                    if(bro.left != null){

                        if(bro.left.color == TColor.RED){  //CASE 3, bro is black, and his left son is red, while his right son is black or null

                            bro.left.setColor(TColor.BLACK);
                            RightRotate(bro);
                            bro.setColor(TColor.RED);
                            deleteRBNode(node);

                            return;
                        }
                    }

                    else{                          // CASE 2, bro is black, both his sons are black, (including both his sons are black or null)
                        bro.color = TColor.RED;
                        deleteRBNode(parent);
                        return;

                    }
                }
            }
        }


        if( node == parent.right){      // MIRRORED CASE

            bro = parent.left;

            if(bro != null ){

                if( bro.color == TColor.RED){      // CASE 1, bro is red

                    RightRotate(parent);
                    parent.setColor(TColor.RED);
                    bro.setColor(TColor.BLACK);

                    deleteRBNode(node);
                    return;
                }

                if( bro.color == TColor.BLACK){


                    if(bro.left != null){   // if bro left son is not null  (avoid NullPointerException)  (better would be Nil[T] )

                        if(bro.left.color == TColor.RED){     //CASE 4, bro is black, and his left son is red

                            RightRotate(parent);
                            bro.setColor(parent.color);
                            parent.setColor(TColor.BLACK);

                            return;
                        }
                    }

                    if(bro.right != null){

                        if(bro.right.color == TColor.RED){  //CASE 3, bro is black, and his right son is red, while his left son is black or null

                            bro.right.setColor(TColor.BLACK);
                            LeftRotate(bro);
                            bro.setColor(TColor.RED);
                            deleteRBNode(node);

                            return;
                        }
                    }

                    else{                          // CASE 2, bro is black, both his sons are black, (including both his sons are black or null)
                        bro.color = TColor.RED;
                        deleteRBNode(parent);
                        return;

                    }
                }
            }
        }
    }































    public void LeftTreeHeigh(Node node,JTextArea field){

        if(node.left != null){

            field.setText("Heigh of left sub tree: " + Heigh(node.left));

        }

        else{
            field.setText("There is no left sub tree ");
        }


    }

    public void RightTreeHeigh(Node node,JTextArea field){

        if(node.right != null){

            field.setText("Heigh of right sub tree: " + Heigh(node.right));

        }

        else{
            field.setText("There is no right sub tree ");
        }


    }

    public int CountNodes(Node node){

        if(node == null) return 0;

        return 1 + CountNodes(node.left) + CountNodes(node.right);
    }

    public void countNodes(Node node,JTextArea field){
        field.setText("Amount of Nodes for " + node + " : "+CountNodes(node));
    }

    public void LcountNodes(Node node,JTextArea field){
        if(root.left != null){
            field.setText("Amount of Nodes in left sub tree: "+CountNodes(node.left));
        }
        else{
            field.setText("There is no left sub tree ");
        }
    }

    public void RcountNodes(Node node,JTextArea field){
        if(root.right != null){
            field.setText("Amount of Nodes in right sub tree: "+CountNodes(node.right));
        }
        else{
            field.setText("There is no right sub tree ");
        }
    }







}
