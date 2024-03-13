package src;

public class Pilha {
	
	private int topo;
	private String[] pilha;
	
	public Pilha(int size) {
		this.topo = -1;
		this.pilha = new String[size];
	}
	
	public void push(String e) {
		if (isFull()) throw new RuntimeException("A pilha está cheia");
		
		this.pilha[++this.topo] = e;
	}
	
	public String pop() {
		if (isEmpty()) throw new RuntimeException("A pilha já está vazia");
		
		return this.pilha[this.topo --];
	}
	
	public String peek() {
		if (isEmpty()) throw new RuntimeException("A pilha está vazia");
		return this.pilha[this.topo];
	}
	
	public boolean isEmpty() {
		return this.topo == -1;
	}
	
	public boolean isFull() {
		return this.topo + 1 == this.pilha.length;
	}

}
