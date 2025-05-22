// arquivo: src/apl2/Node.java

// Integrantes: Isabela Hissa, RA: 10441873 | Caio Ariel, RA: 10439611 | Kaique Paiva, RA: 10441787

package apl2;

// -- A classe Node (que pertence ao pacote apl2) deve conter os atributos que
// representam a nova versão dos dados de uma pessoa, conforme descrito no
// enunciado da atividade Apl2.
// -- A classe deve conter os construtores apropriados, assim como os métodos
// getters e setters.
// -- A classe também representa um nó que é usado na implementação da lista
// duplamente encadeada (classe DLinkedList).
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com os valores dos atributos da classe.

public class Node {
	
	private String id;
	private String nome;
	private float nota;
	private Node anterior;
	private Node proximo;

	//Construtor default
	public Node(){
		this("", "", (float) 99.9, null, null);
	}

	//Construtor com parametros
	public Node(String id, String nome, float nota, Node anterior, Node proximo){
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.anterior = anterior;
		this.proximo = proximo;
	}

	public String getId(){
		return id;
	}

	public void setId(String ano, String semestre, String idAnterior) throws Exception{
		if (ano.length() == 1) {
			this.id = ano + ".S";
		}
		else{
			throw new Exception("O ano deve conter apenas dois dígitos");
		}
		if (semestre.length() == 0) {
			this.id += semestre + "-" + idAnterior;
		}
		else{
			throw new Exception("O semestre deve conter apenas um dígito");
		}
	}

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public float getNota(){
		return nota;
	}

	public void setNota(float nota){
		this.nota = nota;
	}

	public Node getAnterior(){
		return anterior;
	}

	public void setAnterior(Node anterior){
		this.anterior = anterior;
	}

	public Node getProximo(){
		return proximo;
	}

	public void setProximo(Node proximo){
		this.proximo = proximo;
	}

	@Override
	public String toString() {
	    String anteriorId = (anterior != null) ? String.valueOf(anterior.id) : "null";
	    String proximoId = (proximo != null) ? String.valueOf(proximo.id) : "null";
	    return "[(" + id + ";" + nome + ";" + nota + ") | anterior: " + anteriorId + " | próximo: " + proximoId + "]\n";
	}

}