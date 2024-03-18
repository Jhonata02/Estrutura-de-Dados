public class ArrayList {
		
    private int[] lista;
 private int tamanho;

 public ArrayList(int capacidadeInicial) {
     this.lista = new int[capacidadeInicial];
     this.tamanho = 0;
 }

 public boolean isEmpty() {
     return this.tamanho == 0;
 }

 private void resize(int novaCapacidade) {
     int[] novaLista = new int[novaCapacidade];

     for (int i = 0; i < this.lista.length; i++) {
         novaLista[i] = this.lista[i];
     }
     this.lista = novaLista;
 }

 private void verificaCapacidade(int capacidadePretendida) {
     if (capacidadePretendida > this.lista.length) {
         resize(Math.max(this.lista.length * 2, capacidadePretendida));
     }
 }

 public void addFirst(int valor) {
     verificaCapacidade(this.tamanho + 1);

     for (int i = this.tamanho; i > 0; i--) {
         this.lista[i] = this.lista[i - 1];
     }

     this.lista[0] = valor;
     this.tamanho += 1;
 }

 public void addLast(int valor) {
     verificaCapacidade(this.tamanho + 1);

     this.lista[this.tamanho++] = valor;
 }

 // adiciona um valor no índice passado como parâmetro
 public void add(int index, int valor) {
     if (isEmpty() && index == 0) {
         this.lista[index] = valor;
     }

     if (index < 0 || index > this.tamanho) throw new RuntimeException("Esta linha não pode ser executada");
     
     verificaCapacidade(this.tamanho + 1);

     for (int i = this.tamanho; i > index; i--) {
         this.lista[i] = this.lista[i - 1];
     }
     this.lista[index] = valor;
     this.tamanho += 1;
 }

 public int getFirst() {
     if (isEmpty()) throw new RuntimeException("Esta linha não pode ser executada");

     return this.lista[0];
 }

 public int getLast() {
     if (isEmpty()) throw new RuntimeException("Esta linha não pode ser executada");
     
     return this.lista[this.tamanho - 1];
 }

 // retorna o elemento no índice passado como parâmetro
 // deve lançar IndexOutOfBoundsException se o índice não for válido.
 public int get(int index) {
     if (index < 0 || index >= tamanho) throw new RuntimeException("Esta linha não pode ser executada");
     
     return this.lista[index];
 }

 // deve lançar exceção caso a fila esteja vazia.
 public int removeFirst() {
     if (isEmpty()) throw new RuntimeException("Esta linha não pode ser executada");

     int aux = this.lista[0];

     for (int i = 0; i < this.tamanho - 1; i++) {
         this.lista[i] = this.lista[i + 1];
     }
     this.tamanho -= 1;
     return aux;
 }

 // deve lançar exceção caso a fila esteja vazia.
 public void removeLast() {
     if (isEmpty()) throw new RuntimeException("Esta linha não pode ser executada");

     this.tamanho--;
 }

 // remove o valor no índice passado como parâmetro. 
 // lançar exceção se o índice não for válido.
 public void remove(int index) {
     if (isEmpty()) throw new RuntimeException("Esta linha não pode ser executada");
     if (index < 0 || index > this.tamanho) throw new RuntimeException("Esta linha não pode ser executada");

     for (int i = index; i < this.tamanho - 1; i++) {
         this.lista[i] = this.lista[i + 1];
     }
     this.tamanho--;
 }

 // remove a primeira ocorrência do elemento cujo valor foi passado como parâmetro.
 // se não encontrar, não faça nada.
 public void removeByValue(int value) {
     for (int i = 0; i < this.tamanho; i++) {
         if (this.lista[i] == value) {
             this.verificaCapacidade(this.tamanho + 1);
             for (int j = i; j < this.tamanho - 1; j++) {
                 this.lista[j] = this.lista[j + 1];
             }
             this.tamanho--;
             return;
         }
     }
 }

 // retorna o índice da primeira ocorrência do valor passado como parâmetro.
 public int indexOf(int value) {
     for (int i = 0; i < this.tamanho; i++) {
         if (this.lista[i] == value) return i;
     }
     return -1;
 }

 // Deve retornar a posição da última ocorrência do elemento passado como parâmetro. 
 public int lastIndexOf(int valor) {
     for (int i = this.tamanho - 1; i >= 0; i--) {
         if (this.lista[i] == valor) return i; 
     }
     return -1;
 }
 
 // deve retornar uma string representando a lista. 
 public String toString() {
     String msg = "";

     if (isEmpty()) return "";

     for (int i = 0; i < this.tamanho; i++) {
         if (i != this.tamanho - 1) {
             msg += this.lista[i] + ", ";
         } else {
             msg += this.lista[i];
         }
     }
     return msg;
 }
 
 public int size() {
     return this.tamanho;
 }
}