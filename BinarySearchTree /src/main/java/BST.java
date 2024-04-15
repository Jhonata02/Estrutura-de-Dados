import java.util.ArrayList;

public class BST {

    private Node root;
    private int size;

    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Implementação iterativa da adição de um elemento em uma árvore binária de pequisa.
     * @param element o valor a ser adicionado na árvore.
     */
    public void add(int element) {
        this.size += 1;
        if (isEmpty())
            this.root = new Node(element);
        else {

            Node aux = this.root;

            while (aux != null) {

                if (element < aux.value) {
                    if (aux.left == null) {
                        Node newNode = new Node(element);
                        aux.left = newNode;
                        newNode.parent = aux;
                        return;
                    }

                    aux = aux.left;
                } else {
                    if (aux.right == null) {
                        Node newNode = new Node(element);
                        aux.right = newNode;
                        newNode.parent = aux;
                        return;
                    }

                    aux = aux.right;
                }
            }
        }

    }


    /**
     * Busca o nó cujo valor é igual ao passado como parâmetro. Essa é a implementação
     * iterativa clássica da busca binária em uma árvore binária de pesquisa.
     * @param element O elemento a ser procurado.
     * @return O nó contendo o elemento procurado. O método retorna null caso
     * o elemento não esteja presente na árvore.
     */
    public Node search(int element) {
        Node aux = this.root;
        while (aux != null) {
            if (aux.value == element) {
                return aux;
            }else {
                if (element > aux.value) {
                    aux = aux.right;
                }else {
                    aux = aux.left;
                }
            }
        }
        return null;
    }


    /**
     * Retorna a altura da árvore.
     */
    public int height() {
        return height(this.root);
    }

    private int height(Node node) {
        if (node == null) return -1;
        else return 1 + Math.max(height(node.left), height(node.right));
    }


    public boolean equals(BST outra) {
        ArrayList<Integer> aux1 = new ArrayList<>();
        this.preOrder(aux1, root);
        ArrayList<Integer> aux2 = new ArrayList<>();
        outra.preOrder(aux2, outra.root);
        if (aux1.equals(aux2)) return true;
        else return false;
    }

    //Funciona como um "Hash da Arvore"
    private void preOrder(ArrayList out,Node node) {
        if (node != null) {
            out.add(node.value);
            preOrder(out, node.left);
            preOrder(out, node.right);
        }
        return;
    }

    /**
     * Retorna o número de folhas da árvore.
     */
    public int contaFolhas() {
        return contaFolhas(this.root);
    }

    private int contaFolhas(Node node) {
        if (node == null) return 0;
        else if (node.isLeaf()) return 1;
        else return contaFolhas(node.left) + contaFolhas(node.right);
    }



    /**
     * @return o tamanho da árvore.
     */
    public int size() {
        return this.size;
    }

}

class Node {

    int value;
    Node left;
    Node right;
    Node parent;

    Node(int v) {
        this.value = v;
    }

    public boolean isLeaf() {
        if (this.left == null && this.right == null) return true;
        else return false;
    }

}