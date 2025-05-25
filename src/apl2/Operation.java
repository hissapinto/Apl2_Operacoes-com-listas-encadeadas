
// Integrantes: Isabela Hissa, RA: 10441873 | Caio Ariel, RA: 10439611 | Kaique Paiva, RA: 10441787

package apl2;

public class Operation {

	public static DLinkedList map(final LinkedListOriginal original) {
		DLinkedList novaLista = new DLinkedList();
		
		NodeOriginal atual = original.getHead();
		
		while(atual != null) {
			int idOriginal = atual.getId();
			String nome = atual.getNome();
			int parteInteira = atual.getInteiro();
			int parteDecimal = atual.getDecimo();
			
			// o .format("%03d"... serve para quando vir um id do tipo '1', '7' e etc.
			// ele converta em '001', '007'.
			String novoId = "25.S3-" + String.format("%03d", idOriginal);
			
			float nota;
			if(parteInteira == -1 || parteDecimal == -1) {
				nota = 99.9f;
			} else {
				nota = parteInteira + (parteDecimal / 10.0f);
			}
			
			novaLista.append(novoId, nome, nota);
			
			atual = atual.getNext();
		}
		
		return novaLista;
	}

	public static DLinkedList filterRemoveNonGraded(final DLinkedList data) {
		DLinkedList listaNotasValidas = new DLinkedList();
		
		Node node = data.getHead();

		do {
			if(node.getNota() != 99.9f) {
				listaNotasValidas.append(node.getId(), node.getNome(), node.getNota());
			}
			
			node = node.getProximo();
		} while(node != data.getHead());
		
		return listaNotasValidas;
	}

	public static DLinkedList filterRemoveGraded(final DLinkedList data) {
		DLinkedList listaNotasInvalidas = new DLinkedList();
		Node node = data.getHead();

		if(node != null){
			do{
				if(node.getNota() == 99.9f){
					listaNotasInvalidas.append(node.getId(), node.getNome(), node.getNota());
				}
				node = node.getProximo();
			} while(node != data.getHead());
		}

		return listaNotasInvalidas;
	}

	public static DLinkedList filterRemoveBelowAverage(final DLinkedList data, float average) {
		DLinkedList acimaMedia = new DLinkedList();
		Node node = data.getHead();

		if(node != null){
			do{
				if(node.getNota() > average){
					acimaMedia.append(node.getId(), node.getNome(), node.getNota());
				}
				node = node.getProximo();
			} while(node != data.getHead());
		}
		return acimaMedia;
	}
	
	public static float reduce(final DLinkedList data) {		
		float total = 0.0f;
		int contador = 0;
		
		Node node = data.getHead();
		
		if(node != null) {
			do {
				total += node.getNota();
				contador++;					
				
				node = node.getProximo();
			} while(node != data.getHead());			
		}
		
		if(contador == 0) {
			return 0.0f;
		}
		
		return total / contador;
	}

	//Metodo toString direto da dlinkedlist
	public static String mapToString(final DLinkedList data) { //final = variavel imutavel (tipo let)
		StringBuilder sb = new StringBuilder();

		if (data.getHead() == null) {
   			return "Lista vazia";
		}
		
		sb.append("(" + data.count() + ") \n");
		
		Node node = data.getHead();
		int qtdAux = 0;
		while (qtdAux < data.count() && node != null) {
			sb.append(node.getId())
			.append(";")
			.append(node.getNome())
			.append(";")
			.append(node.getNota())
			.append("\n");
			node = node.getProximo();
			qtdAux++;
		}
		return sb.toString();
	}
}