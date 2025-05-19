// arquivo: src/apl2/DLinkedList.java

// Integrantes: Isabela Hissa, RA: 10441873 | Caio Ariel, RA: 10439611 | Kaique Paiva, RA: 10441787

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
			head = tail = node;
			head.setProximo(node); //no -> no
			head.setAnterior(node); //no <- no
		}
		node.setProximo(head); //no -> head
    	node.setAnterior(tail); //tail <- no
        head.setAnterior(node); //no <- head
        tail.setProximo(node); //tail -> no
		head = node; //define novo nó como head
		count ++;
	}

// Novo nó no final da lista
	public void append(String id, String nome, float nota) {
		Node node = new Node(id, nome, nota, tail, head);
		if(isEmpty()){
			head = tail = node;
			node.setProximo(node); //no -> no
        	node.setAnterior(node); //no <- no
		}
		node.setAnterior(tail); //tail <- no
        node.setProximo(head); //no -> head
        tail.setProximo(node); //tail -> no
        head.setAnterior(node); //head -> no
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
		tail.setProximo(head); //Atualiza link tail -> head
		head.setAnterior(tail); //Atualiza link tail <- head
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
		tail.setProximo(head); //Atualiza link tail -> head
		head.setAnterior(tail); //Atualiza link tail <- head
		return pAux;
	}

// Remove o nó que contém o <ID da pessoa> da lista e retorna-o ou retorna null se não achar.
	public Node removeNode(String id) {
	    if(isEmpty()){
	        return null;
	    }

	    // Caso especial: se o nó a ser removido é o head
	    if (head.getId().equals(id)) {
	        return removeHead();
	    }
	    
	    // Caso especial: se o nó a ser removido é o tail
	    if (tail.getId().equals(id)) {
	        return removeTail();
	    }

	    // Procurar o nó no meio da lista
	    Node pAux = head.getProximo(); // Começamos do segundo nó
	    while (pAux != head) { // Verificamos se completamos o ciclo
	        if (pAux.getId().equals(id)) {
	            // Remover o nó do meio da lista
	            pAux.getAnterior().setProximo(pAux.getProximo());
	            pAux.getProximo().setAnterior(pAux.getAnterior());
	            
	            // Desconectar o nó da lista
	            pAux.setAnterior(null);
	            pAux.setProximo(null);
	            
	            // Decrementar o contador
	            count--;
	            
	            return pAux;
	        }
	        pAux = pAux.getProximo();
	    }

	    return null; // Nó não encontrado
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
	    // Em uma lista circular, precisamos verificar se já voltamos ao início
	    do {
	        if(pAux.getId().equals(id)) {
	            return pAux;
	        }
	        pAux = pAux.getProximo();
	    } while (pAux != head); // Paramos quando voltamos ao início da lista

	    return null; // Não encontrou o nó com o id especificado
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

// Retorna uma string com o conteúdo da lista
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (head == null) {
   			return "Lista vazia";
		}
		
		sb.append("(" + count + ") \n");
		
		Node node = this.head;
		int qtdAux = 0;
		while (qtdAux < this.count && node != null) {
			sb.append("(")
			.append(node.getId())
			.append(";")
			.append(node.getNome())
			.append(";")
			.append(node.getNota())
			.append(") -> \n");
			node = node.getProximo();
			qtdAux++;
		}
		sb.append("head");
		
		return sb.toString();
	}

}