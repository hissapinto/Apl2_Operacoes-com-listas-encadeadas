// arquivo: src/apl2/DLinkedList.java

// Integrantes: Isabela Hissa, RA: 10441873 | Caio Ariel, RA : XXXXXXXX | Kaique Paiva, RA: XXXXXXXX

package apl2;

// -- A classe DLinkedList (que pertence ao pacote apl2) deve implementar uma
// lista duplamente encadeada. Os nós dessa lista são do tipo [da classe] Node.
// -- A classe deve possuir dois nós especiais, head e tail, que são
// referências para o primeiro e último nó da lista, respectivamente.
// -- A classe deve possuir um contador de quantos nós existem na lista.
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com o conteúdo da lista.
// -- A classe deve implementar as operações a seguir, respeitando o
// comportamento descrito em cada operação.

public class DLinkedList {
	
	private Node head;
	private Node tail;
	private int count;

// Construtor
	public DLinkedList() {
		head = null;
		tail = null;
		count = 0;
	}

// Novo nó no início da lista
	public void insert(String id, String nome, float nota) {
		Node node = new Node(id, nome, nota, null, head);
		if (isEmpty()){ //se vazia, o novo nó também é a cauda
			tail = node;
		}
		head = node; //define novo nó como inicio
		count ++;
	}

// Novo nó no final da lista
	public void append(String id, String nome, float nota) {
		Node node = new Node(id, nome, nota, tail, null);
		if(isEmpty()){
			head = node;
		}
		tail = node;
		count ++;
	}

// Remove o nó do início da lista e retorna a referência do nó removido ou null se vazia
	public Node removeHead() {
		if(isEmpty()){ //se vazia retorna null
			return null;
		}
		Node pAux = head; //cria auxiliar apontando pra head
		head = pAux.getProximo(); // head agora aponta pro próximo do auxiliar
		head.setAnterior(null); //remove link da nova head com o nó removido
		count--;

		if(isEmpty()){ //se a lista agora ficou vazia, corrige tail para null
			tail = null;
		}

		pAux.setProximo(null); //remove link de pAux com a lista
		return pAux;
	}


// Remove o nó do fim da lista e retorna a referência do nó removido ou null se vazia
	public Node removeTail() {
		if(isEmpty()){ //se vazia retorna null
			return null;
		}
		Node pAux = tail; //cria auxiliar apontando para tail
		tail = pAux.getAnterior(); //tail agora é o nó anterior de pAux
		tail.setProximo(null); //remove link da nova tail com o nó removido
		count--;

		if(isEmpty()){ //Se a lista agora ficou vazia, corrige head para null
			head = null;
		}

		pAux.setAnterior(null); //remove link de pAux com a lista
		return pAux;
	}

// Remove o nó que contém o <ID da pessoa> da lista e retorna-o ou retorna null se não achar.
	public Node removeNode(String id) {
		if(isEmpty()){
			return null;
		}

		Node pAux = head;
		while (pAux != null && !pAux.getId().equals(id)) {
			pAux = pAux.getProximo();
		}

		if (pAux == null){ //Não achou o id na lista
			return null;
		}
	
		count--; //Se achou já diminui o contador

		if (pAux == head){
			return removeHead();
		}

		if (pAux == tail){
			return removeTail();
		}
		
		//Caso genérico: id no meio da lista
		pAux.getAnterior().setProximo(pAux.getProximo()); //liga o nó anterior do aux ao proximo do aux
		pAux.getProximo().setAnterior(pAux.getAnterior()); //liga o nó posterior do aux ao anterior do aux	
		return pAux;
	}

	// Retorna head ou null se vazia
	public Node getHead() {
		if(isEmpty()){
			return null;
		}
		return head;
	}

// Retorna tail ou null se vazia
	public Node getTail() {
		if(isEmpty()){
			return null;
		}
		return tail;
	}


// Retorna uma referência para o nó que contém o <ID da pessoa> ou null caso não ache na lista
	public Node getNode(String id) {
		if(isEmpty()){
			return null;
		}

		Node pAux = head;
		while (pAux != null && !pAux.getId().equals(id)) {
			pAux = pAux.getProximo();
		}

		return pAux; //retorna o nó ou null caso percorra tudo
	}

// Retorna a quantidade de nós da lista.
	public int count() {
		return count;
	}

// Retorna true se a lista estiver vazia ou false, caso contrário.
	public boolean isEmpty() {
		return head == null;
	}

// Esvazia a lista, liberando a memória de todos os nós da lista.
	public void clear() {
		if(isEmpty()){
			return;
		}

		Node pAux = head;
		while (pAux != null) {
			Node pProx = pAux.getProximo();
			pAux.setAnterior(null);
			pAux.setProximo(null);
			pAux = pProx;
		}

		head = null;
		tail = null;
		count = 0;
	}


// OPERAÇÃO:		toString()
// COMPORTAMENTO:	Retorna uma string com o conteúdo da lista (caso queira, use o
//					exemplo do método toString() da classe LinkedListOriginal).
	@Override
	public String toString() {
		// TODO: Implementar o método e remover o lançamento de exceção abaixo.
		throw new UnsupportedOperationException("Método ainda não implementado.");
	}

}