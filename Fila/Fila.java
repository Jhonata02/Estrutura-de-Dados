public class Fila {
	
	private int[] fila;
	private int tail;
	private int head;
	private int size;

    // sua fila deve seguir a abordagem circular que vimos em sala de aula.
    // isso implica em dizer quer adições e remoções são O(1).
    public Fila(int capacidade) {
        this.fila = new int[capacidade];
		this.head = -1;
		this.tail = -1;
		this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == -1 && this.tail == -1;
    }

    public boolean isFull() {
        return ((this.tail + 1)) % this.fila.length == this.head;
    }

    // deve lançar exceção caso a fila esteja cheia.
    public void addLast(int valor) {
        if (isFull()) 
			throw new RuntimeException("Esta linha não pode ser executada.");

		if (isEmpty()) 
			this.head = 0;
		
		this.size += 1;
		this.tail = (this.tail + 1) % this.fila.length;
		this.fila[tail] = valor;
    }

    // deve lançar exceção caso a fila esteja vazia.
    public int removeFirst() {
        if (isEmpty())
			throw new RuntimeException("Esta linha não pode ser executada.");
        
		int e = this.fila[this.head];

		if (this.head == this.tail) {
			this.head = -1;
			this.tail = -1;
		} else {
			this.head = (this.head + 1) % this.fila.length;
		}

		this.size -= 1;
		return e;
    }

    // deve lançar exceção caso a fila esteja vazia. apenas retorna o primeiro da fila, sem
    // remover;
    public int getFirst() {
        if (isEmpty())
			throw new RuntimeException("Esta linha não pode ser executada.");

		return this.fila[this.head];
    }

    // deve lançar exceção caso a fila esteja vazia. apenas retorna o último da fila, sem
    // remover;
    public int getLast() {
        if (isEmpty())
            throw new RuntimeException("Esta linha não pode ser executada.");

		return this.fila[this.tail];
    }

    // deve retornar uma string representando a fila. 
    public String toString() {
        String msg = "";
		
		if (isEmpty()) return msg;

		int i = this.head;
		int j = -1;
		while (j < this.size - 1) {
			if (j == this.size -2) {
				msg += this.fila[i];
			} else {
				msg += this.fila[i] + ", ";
			}
			
			i = (i + 1) % this.fila.length;
			j++;
		}

		return msg;
    }
    
    // Deve retornar a posição da primeira ocorrência do elemento passado como parâmetro. 
    public int indexOf(int valor) {
        if (isEmpty()) return -1;

		int i = this.head;
		int j = -1;
		while (j < this.size - 1) {
			if (this.fila[i] == valor) return i;

			i = (i + 1) % this.fila.length;
			j++;
		}

		return -1;
    }

    // Deve retornar a posição da última ocorrência do elemento passado como parâmetro. 
    public int lastIndexOf(int valor) {
        if (isEmpty()) return -1;

		int i = this.size - 1;
		int j = -1;

		while (j < this.size - 1) {
			if (this.fila[i] == valor) return i;

			i = (i - 1) % this.fila.length;
			j++;
		}

		return -1;
    }
    
    public int size() {
        return this.size;
    }
}
