import java.util.NoSuchElementException;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void addFirst(int valor) {
        Node newNode = new Node(valor);

        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }

        size += 1;
    }

    public void addLast(int valor) {
        Node newNode = new Node(valor);

        if (isEmpty()) {
            this.head = newNode;
            this.tail = head;
        } else {
            this.tail.next = newNode;
            newNode.prev = tail;
            this.tail = newNode;
        }
        this.size += 1;
    }

    // adiciona um valor na posição passada como parâmetro
    public void add(int index, int valor) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        Node newNode = new Node(valor);

        if (index == 0) {
            this.addFirst(valor);

        } else if (index == size) {
            this.addLast(valor);

        } else {
            Node aux = this.head;

            for (int i = 0; i < index - 1; i++)
                aux = aux.next;

            newNode.next = aux.next;
            aux.next = newNode;

            size += 1;
        }
    }

    public int getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return this.head.value;
    }

    public int getLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        return this.tail.value;
    }

    // retorna o elemento na posição passada como parâmetro
    // deve lançar IndexOutOfBoundsException se o índice não for válido.
    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node aux = this.head;

        for (int i = 0; i < index; i++)
            aux = aux.next;

        return aux.value;
    }

    // deve lançar exceção caso a fila esteja vazia.
    public int removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        int v = this.head.value;

        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }

        size -= 1;
        return v;
    }

    // deve lançar exceção caso a fila esteja vazia.
    public int removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        int v = this.tail.value;

        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }

        size -= 1;
        return v;
    }

    // remove a primeira ocorrência do elemento cujo valor foi passado como
    // parâmetro.
    // se não encontrar, não faça nada. true se remover, false se não remover.
    public boolean removeByValue(int value) {
        Node aux = this.head;
        for (int i = 0; i < this.size; i++) {
            if (aux.value == value) {
                if (i == 0)
                    removeFirst();
                else if (i == size - 1)
                    removeLast();
                else {
                    aux.prev.next = aux.next;
                    aux.next.prev = aux.prev;
                    size -= 1;
                }

                return true;
            }
            aux = aux.next;
        }

        return false;
    }

    // retorna a posição da primeira ocorrência do valor passado como parâmetro.
    public int indexOf(int value) {
        Node aux = this.head;
        int index = 0;
        while (aux != null) {
            if (aux.value == value)
                return index;
            aux = aux.next;
            index += 1;
        }

        return -1;
    }

    public boolean contain(int v) {
        return indexOf(v) != -1;
    }

    // Deve retornar a posição da última ocorrência do elemento passado como
    // parâmetro.
    public int lastIndexOf(int valor) {
        if (isEmpty())
            return -1;

        int last = -1;
        Node aux = this.head;
        int i = -1;
        while (aux != null) {
            i += 1;
            if (aux.value == valor)
                last = i;
            aux = aux.next;
        }

        return last;
    }

    // deve retornar uma string representando a lista.
    public String toString() {
        if (isEmpty())
            return "";

        Node aux = this.head;
        String out = "";
        while (aux != null) {
            out += aux.value + ", ";
            aux = aux.next;
        }
        return out.substring(0, out.length() - 2);
    }

    public int size() {
        return this.size;
    }

    // remove o valor no índice passado como parâmetro. retorna o valor removido.
    // lançar exceção se o índice não for válido.
    public int remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        if (index == 0)
            return removeFirst();
        if (index == size - 1)
            return removeLast();

        Node aux = this.head;
        for (int i = 0; i < index; i++)
            aux = aux.next;

        aux.prev.next = aux.next;
        aux.next.prev = aux.prev;
        size -= 1;
        return aux.value;
    }

    private Node getNode(int index) {

        Node aux = this.head;

        for (int i = 0; i < index; i++)
            aux = aux.next;

        return aux;
    }

    // troca o nó na posição indexA pelo nó na posição indexB. Você não pode trocar
    // os valores,
    // tem que manipular as referências para efetuar a troca. Pode assumir que
    // indexA e indexB são
    // índices válidos.
    // dica 1: primeiro faça um método getNode(int index) que retorna o nó que você
    // está procurando.
    // dica 2: Fique atento para o caso de indexA ou indexB for os extremos,
    // pois você vai ter que atualizar head e/ou tail se forem.
    public void swap(int indexA, int indexB) {
        Node aux1 = getNode(indexA);
        Node aux2 = getNode(indexB);
        Node aux1Prev = aux1.prev;
        Node aux2Next = aux2.next;

        aux2.next = aux1.next;
        aux1.next.prev = aux2;
        aux1.prev = aux2.prev;
        aux2.prev.next = aux1;
        aux2.prev = aux1Prev;
        aux1.next = aux2Next;

        if (indexA == 0 && indexB == this.size - 1) {
            this.head = aux2;
            this.tail = aux1;
        } else if (indexA == 0) {
            this.head = aux2;
            aux1.next.prev = aux1;
        } else if (indexB == this.size - 1) {
            this.tail = aux1;
            aux2.prev.next = aux2;
        } else {
            aux1.next.prev = aux1;
            aux2.prev.next = aux2;
        }
    }

    // coloca o nó da posição indexA no início da lista. Deve ser O(1).
    public void moveToHead(int index) {
        if (isEmpty() || index == 0)
            return;

        Node auxNode2 = getNode(index);

        if (index == this.size - 1) {
            auxNode2.prev.next = null;
            this.head.prev = auxNode2;
            auxNode2.next = this.head;
            this.head = auxNode2;
            this.tail = auxNode2.prev;
            this.head.prev = null;
        } else {
            auxNode2.prev.next = auxNode2.next;
            auxNode2.next.prev = auxNode2.prev;
            this.head.prev = auxNode2;
            auxNode2.next = this.head;
            auxNode2.prev = null;
            this.head = auxNode2;
        }

    }

    public void moveToTail(Node node) {
        if (isEmpty() || (node.prev == null && node.next == null)) return;

        if (node.prev == null) {
            node.prev = this.tail;
            this.tail.next = node;
            this.head = node.next;
            this.tail = node;
            this.tail.next.prev = null;
            this.tail.next = null;
        } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            this.tail.next = node;
            node.prev = this.tail;
            node.next = null;
            this.tail = node;
        }
    }

}

class Node {

    int value;
    Node prev;
    Node next;

    Node(int v) {
        this.value = v;
    }

}