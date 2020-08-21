package tdas;

import java.util.ArrayDeque;
import java.util.Deque;

import java.util.Stack;

public class BinaryTree<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = new BinaryNode<>();
    }

    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
    }
    
    public BinaryTree(BinaryNode<T> root){
        this.root =root;
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }

    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft = this.root.getLeft().countLeavesRecursive();
            }
            if (this.root.getRight() != null) {
                leavesRight = this.root.getRight().countLeavesRecursive();
            }
            return leavesLeft + leavesRight;
        }
    }

    public int countLeavesIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                }
                if (subtree.root.getRight() != null) {
                    stack.push(subtree.root.getRight());
                }
                if (subtree.isLeaf()) {
                    count++;
                }
            }
        }
        return count;
    }

    public BinaryNode<T> recursiveSearch(T content) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (this.root.getContent().equals(content)) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().recursiveSearch(content);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().recursiveSearch(content);
                    }
                }
                return tmp;
            }
        }
    }

    public BinaryNode<T> iterativeSearch(T content) {
        Stack<BinaryTree<T>> sbt = new Stack();

        if (this.isEmpty()) {
            return null;
        } else {
            sbt.push(this);
            while (!sbt.empty()) {
                BinaryTree<T> sTree = sbt.pop();
                if (sTree.root.getContent().equals(content)) {
                    return sTree.root;
                } else {
                    if (sTree.root.getLeft() != null) {
                        sbt.push(sTree.root.getLeft());
                    }
                    if (sTree.root.getRight() != null) {
                        sbt.push(sTree.root.getRight());
                    }
                }
            }
            return null;
        }
    }

    public void printRecursiveInordenBinary(BinaryTree node) {
        if (node != null) {
            printRecursiveInordenBinary(node.getLeft());
            System.out.print(node.root.getContent() + "  ");
            printRecursiveInordenBinary(node.getRight());
        }
    }

public void printIterativeInordenBinary(){
//        if (!this.isEmpty()) {
//            Stack<BinaryTree<T>> sTrees = new Stack<>();
//            sTrees.push(this);
//            while (!sTrees.isEmpty()) {
//                BinaryTree<T> trees = sTrees.pop();
//                System.out.print(trees.getRoot().getLeft().getRoot().getContent()+ "  ");
//                if (trees.root.getRight() != null) {
//                    sTrees.push(trees.root.getRight());
//                }           
//                if (trees.root.getLeft() != null) {
//                    sTrees.push(trees.root.getLeft());
//                }
//            }
//        }
    }

public void printRecursivePreordenBinary(BinaryTree node) {
        if (node != null) {
            System.out.print(node.root.getContent() + "  ");
            printRecursivePreordenBinary(node.getLeft());
            printRecursivePreordenBinary(node.getRight());
        }
    }

    public void printIterativePreordenBinary() {
        if (!this.isEmpty()) {
            Stack<BinaryTree<T>> sTrees = new Stack<>();
            sTrees.push(this);
            while (!sTrees.isEmpty()) {
                BinaryTree<T> tree = sTrees.pop();
                System.out.print(tree.getRoot().getContent() + "  ");
                if (tree.root.getRight() != null) {
                    sTrees.push(tree.root.getRight());
                }
                if (tree.root.getLeft() != null) {
                    sTrees.push(tree.root.getLeft());
                }
            }
        }
    }

    public void printRecursivePostordenBinary(BinaryTree node) {
        if (node != null) {
            printRecursivePostordenBinary(node.getLeft());
            printRecursivePostordenBinary(node.getRight());
            System.out.print(node.root.getContent() + "  ");
        }
    }

    public void printIterativePostordenBinary() {
        if (!this.isEmpty()) {
            Stack<BinaryTree<T>> sTrees = new Stack<>();
            Stack<BinaryTree<T>> sContents = new Stack<>();
            sTrees.push(this);
            while (!sTrees.isEmpty()) {
                BinaryTree<T> tree = sTrees.pop();
                sContents.push(tree);
                if (tree.root.getLeft() != null) {
                    sTrees.push(tree.root.getLeft());
                }
                if (tree.root.getRight() != null) {
                    sTrees.push(tree.root.getRight());
                }
            }
            while (!sContents.isEmpty()) {
                System.out.print(sContents.pop().getRoot().getContent() + "  ");
            }
        }
    }
    
    //----------------------------------------------------HOMEWORK----------------------------------------------------------------------------------------
    
    //1. Implemente el método getMin que, dado un árbol de enteros, 
    //   encuentre el nodo que almacena el menor de los números.
    //Recursive
    public BinaryNode<T> getMinRecursive() {
        if (!this.isEmpty()) {
            BinaryNode<T> minValueNode = root;
            if (root.getLeft() != null || root.getRight() != null) {
                BinaryNode<T> currentNode = null;
                if (root.getLeft() != null) {
                    if (root.getLeft().root.compareTo(minValueNode) == -1) 
                        minValueNode = this.root.getLeft().root; 
                    if (!root.getLeft().isLeaf()) 
                        currentNode = this.root.getLeft().getMinRecursive();
                    if (currentNode != null) {
                        if (currentNode.compareTo(minValueNode) == -1) 
                            minValueNode = currentNode;   
                    }
                }
                if (root.getRight() != null) {
                    if (root.getRight().root.compareTo(minValueNode) == -1) 
                        minValueNode = this.root.getRight().root;         
                    if (!root.getRight().isLeaf()) 
                        currentNode = root.getRight().getMinRecursive();
                    if (currentNode != null) {
                        if (currentNode.compareTo(minValueNode) == -1) 
                            minValueNode = currentNode;
                    }
                }
            }
            return minValueNode;
        } else {
            return null;
        }

    }
    //Iterative
    public BinaryNode<T> getMinIterative() {
        if (!this.isEmpty()) {
            Deque<BinaryTree<T>> deck = new ArrayDeque<>();
            deck.push(this);
            BinaryNode<T> min = this.root;
            while (!deck.isEmpty()) {
                BinaryTree<T> currentTree = deck.pop();
                if (currentTree.root.compareTo(min) == -1) {
                    min = currentTree.root;
                } else {
                    if (currentTree.root.getLeft() != null) 
                        deck.push(currentTree.root.getLeft());
                    if (currentTree.root.getRight() != null) 
                        deck.push(currentTree.root.getRight());    
                }
            }
            return min;
        }
        else{
            return null;
        }
    }
    
    // 2. Implemente el método countLevels que calcule el número de niveles de árbol.
    //Considere que un árbol vacío tiene 0 niveles, mientras que un árbol hoja tiene 1 solo nivel.
   // Recursive
    public int countLevelsRecursive() {
        if (!isEmpty()) {
            int count = 1;
            int countLeft = 0;
            int countRight = 0;
            if (root.getLeft() != null ||root.getRight() != null) {
                if (root.getLeft() != null) 
                    countLeft = countLeft + root.getLeft().countLevelsRecursive();
                if (root.getRight() != null) 
                    countRight = countRight + root.getRight().countLevelsRecursive();
                if (countRight > countLeft) {
                    count = count + countRight;
                } else {
                    count = count + countLeft;
                }
            }
            return count;
        } else {
            return 0;
        }
    }
    //Iterative
        public int iterativeCountLevels(){
        Deque<BinaryTree<T>> deck = new ArrayDeque();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            deck.push(this);
            while (!deck.isEmpty()) {
                count++;
                BinaryTree<T> trees = deck.pop();
                if (trees.root.getLeft() != null) {
                    deck.push(trees.root.getLeft());
                }
                if (trees.root.getRight() != null) {
                    count--;
                    deck.push(trees.root.getRight());
                }
            }
        }
        return count;
    }
       //3. Se dice que un árbol binario es zurdo si el árbol está vacío o es una hoja, 
        //   o bien si sus hijos son ambos zurdos y tiene a más de la mitad de sus descendientes 
        //  en el hijo izquierdo. Implementar el método isLefty que indique si un árbol binario es zurdo o no.
        public int size() {
            int size = 1;
            if (this.root.getLeft() != null) {
                size += this.root.getLeft().size();
            }
            if (this.root.getRight() != null) {
                size += this.root.getRight().size();
            }
            return size;
        }
        
           public boolean isLeftyIterative(){
        if(this.isEmpty() || this.isLeaf() )
            return true;
        else {
            Deque<BinaryNode<T>> deck = new ArrayDeque<>();
            while(!this.root.getLeft().isLeaf()){
                deck.push(this.getRoot());
            
            }  
            return this.countLevelsRecursive() == 1;
        }
    }
        //4. Implemente el método isIdenticalRecursive que, dado un segundo árbol binario, 
        //retorne true o false indicando sizeDeck dicho árbol es igual al que invoca el método (this).
        // Recursive
        public boolean isIdenticalRecursive(BinaryTree<T> n) {
        boolean isEqualToLeft = false;
        boolean isEqualToRight = false;
        if (this.isEmpty() && n.isEmpty()) {
            return true;
        } else if (!(root.getContent().equals(n.getRoot().getContent()))) {
            return false;
        } else {
            if (!isLeaf() || !n.isLeaf()) {
                if (this.getRoot().getLeft() != null && n.getRoot().getLeft() != null) {
                    isEqualToLeft = root.getLeft().isIdenticalRecursive(n.getRoot().getLeft());
                } else {
                    if (root.getLeft() == null && n.getRoot().getLeft() == null) {
                        isEqualToLeft = true;
                    }
                }
                if (getRoot().getRight() != null && n.getRoot().getRight() != null) {
                    isEqualToRight = root.getRight().isIdenticalRecursive(n.getRoot().getRight());
                } else {
                    if (root.getRight() == null && n.getRoot().getRight() == null) {
                        isEqualToRight = true;
                    }
                }
            } else {
                return root.getContent().equals(n.getRoot().getContent());
            }
        }
        boolean identical=isEqualToLeft && isEqualToRight;
        return identical;
        } 
        //5. Encontrar el valor más grande de cada nivel del árbol. 
        //El método largestValueOfEachLevel debe imprimir el mayor valor presente 
        //en cada nivel de un árbol binario cuyos nodos contienen números enteros.
        //Recursive
        public Deque<BinaryNode<T>> largestValueOfEachLevelRecursive(){
        Deque<BinaryNode<T>> deck= new ArrayDeque<>();
        if(this.isEmpty()){
            return null;
        }else{
           if(!this.isLeaf()){
               deck.add(root);
               Deque<BinaryNode<T>> deckLeft= new ArrayDeque<>(); 
               Deque<BinaryNode<T>> deckRight= new ArrayDeque<>();
               if(this.root.getLeft()!=null)
                   deckLeft=this.root.getLeft().largestValueOfEachLevelRecursive();
               else
                   deckLeft.add(new BinaryNode(0));
               if(this.root.getRight()!=null)
                   deckRight=this.root.getRight().largestValueOfEachLevelRecursive();
               else
                   deckRight.add(new BinaryNode(0));
               if(deckLeft.size()>deckRight.size()){
                   int sizeDeck=deckLeft.size()-deckRight.size();
                   for(int i=0;i<sizeDeck;i++)
                      deckRight.add(new BinaryNode(0));
               }else{
                   int tam=deckRight.size()-deckLeft.size();
                   for(int i=0;i<tam;i++){
                      deckLeft.add(new BinaryNode(0));
                   }
               }
               int size=deckLeft.size();
               for(int i=0;i<size;i++){
                    if(deckLeft.element().compareTo(deckRight.element())==1){
                        deck.add(deckLeft.remove());
                        deckRight.remove();
                    }
                    else{
                        deck.add(deckRight.remove());
                        deckLeft.remove();
                    }
               }
           }else{
               deck.add(root);
               return deck;
           }
        }
        return deck;
    }  
    //6. El método countNodesWithOnlyChildRecursive debe retornar el número de nodos de un árbol que
    //   tienen un solo hijo. 
    
    //Recursive
    public int countNodesWithOnlyChildRecursive() {
        if (!this.isEmpty()) {
            int count = 0;
            if (!isLeaf()) {
                if (!(root.getLeft() != null && root.getRight() != null)) {
                    count++;
                    if (root.getLeft() != null) {
                        count = count + this.root.getLeft().countNodesWithOnlyChildRecursive();
                    }
                    if (root.getRight() != null) {
                        count = count + this.root.getRight().countNodesWithOnlyChildRecursive();
                    }
                } else {
                    if (this.root.getRight() != null) {
                        count = count + this.root.getRight().countNodesWithOnlyChildRecursive();
                    }
                    if (this.root.getLeft() != null) {
                        count = count + this.root.getLeft().countNodesWithOnlyChildRecursive();
                    }
                }
            } else {
                return count;
            }
            return count;
        } else {
            return 0;
        }
    }
    //Iterative
    public int countNodesWithOnlyChildIterative() {
        if (!this.isEmpty()) {
            int count = 0;
            Deque<BinaryTree<T>> deck = new ArrayDeque<>();
            deck.push(this);
            while (!deck.isEmpty()) {
                BinaryTree<T> trees = deck.pop();
                if (!trees.isLeaf()) {
                    if ((trees.getLeft() != null && trees.getRight() == null) || (trees.getLeft() == null && trees.getRight() != null)) {
                        count++;
                    } else {
                        deck.push(trees.getLeft());
                        deck.push(trees.getRight());
                    }
                }
            }
            return count;
        } else {
            return 0;
        }
    }
    // 7. El método isHeightBalanced debe retornar si un árbol binario está balanceado en altura o
    //  no. Un árbol vacío está siempre balanceado en altura. Un árbol binario no vacío está
    //  balanceado si y solo si:
    //  1) Su subárbol izquierdo está balanceado,
    //  2) Su subárbol derecho está balanceado, y
    //  3) La diferencia entre las alturas de sus subárboles izquierdo y derecho es menor que 1.
    public boolean isHeighBalancedRecursive(){
        if(this.isEmpty())
            return true;
        else{                
            if(!(this.getLeft().size() == 1 && this.getRight().size() == 1)){
                this.getLeft().isHeighBalancedRecursive();
                this.getRight().isHeighBalancedRecursive();
            }else{
                int dif = Math.abs(this.getLeft().size() - this.getRight().size());
                if(dif > 1){
                    return false;
                }
            }     
         }
        return true;
    }
}
