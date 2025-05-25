
// Integrantes: Isabela Hissa, RA: 10441873 | Caio Ariel, RA: 10439611 | Kaique Paiva, RA: 10441787

import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.Node;
import apl2.Operation;
import apl2.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainApl2 {
	
	public static void main(String[] args) {
		//Cria lista dados originais
		LinkedListOriginal list = new LinkedListOriginal();
		
		// um nó na LinkedListOriginal list.
		try {
			String dados = Data.loadTextFileToString("dados.txt");
			
			String[] linhas = dados.split("\n");
			
			for (String linha : linhas) {
				String[] listaDados = linha.split("#");
				
				int id = Integer.parseInt(listaDados[0]);
				int notaInteiro = Integer.parseInt(listaDados[2]);
				int notaDecimal = Integer.parseInt(listaDados[3]);
				
				list.append(id, listaDados[1], notaInteiro, notaDecimal);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			e.printStackTrace();
		} catch (IOException e) { // Se erro
			System.out.println("Erro ao ler o arquivo.");
			e.printStackTrace();
		}
		
		//Converte dados e salva em string
		DLinkedList fixedList = Operation.map(list);
		String contents = Operation.mapToString(fixedList);
		//Cria a lista de notas filtradas e ja faz a media
		DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
		float average = Operation.reduce(filteredGradedList);
		
		//Menu
		Scanner scanner = new Scanner(System.in);
		int opcaoUsuario;

		do{
			System.out.println("Sistema Conversor de Notas");
			System.out.println("1) Dados originais: lê arquivo dados.txt e apresenta todos os dados do Sistema de Notas Legado");
			System.out.println("2) Dados convertidos: gera arquivo dados.csv e apresenta todos os dados do Sistema de Notas Atualizado");
			System.out.println("3) Lista notas filtradas válidas: apresenta os dados somente das notas válidas filtradas");
			System.out.println("4) Lista notas filtradas inválidas: apresenta os dados somente das notas filtradas pela “ausência de notas”");
			System.out.println("5) Média de notas válidas: apresenta a média das notas válidas filtradas");			
			System.out.println("6) Notas acima da média: apresenta os dados para as notas acima da média");
			System.out.println("7) Lista mapeada para uma única string: apresenta a String contendo os dados mapeados");
			System.out.println("8) Sair do programa\n");
			opcaoUsuario = scanner.nextInt();

			switch (opcaoUsuario){
				case 1: //le dados originais e printa
					System.out.println(list + "\n");
					break;
				case 2: //Converte pra csv
					try {
 						Data.saveStringToTextFile("dados.csv", contents);
						System.out.println("Arquivo csv gerado com sucesso.\n");
 		 			} catch(FileNotFoundException e) {
 						System.out.println("Arquivo não encontrado!\n");
						e.printStackTrace();
  					} catch(IOException e) {
  						System.out.println("Erro ao salvar o arquivo.\n");
  						e.printStackTrace();
  					}
					break;
				case 3: //Lista de notas validas
					System.out.println(filteredGradedList + "\n");
					break;
				case 4: //Lista notas invalidas
					DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
					System.out.println(filteredNonGradedList + "\n");
					break;
				case 5: //Media de notas
					System.out.println(average + "\n");
					break;
				case 6: //Notas acima da media
					DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
					System.out.println(aboveAverageList + "\n");
					break;
				case 7: //Mapeia notas para string
					System.out.println(contents + "\n");
					break;
				default:
					if (opcaoUsuario == 1 || opcaoUsuario == 2 || opcaoUsuario == 3 || opcaoUsuario == 4 || opcaoUsuario == 5 || opcaoUsuario == 6 || opcaoUsuario == 7 || opcaoUsuario == 8){
						break;
					} 
					else {
						System.out.println("Opção inválida.\n");
						break;
					}
			}
		} while (opcaoUsuario != 8); //Sai do programa
		scanner.close();
	}
}
/*
 * System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
 * System.out.println(list);
 * System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n"
 * );
 * 
 * DLinkedList fixedList = Operation.map(list);
 * System.out.
 * println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>"
 * );
 * System.out.println(fixedList);
 * System.out.
 * println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n"
 * );
 * 
 * DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
 * System.out.
 * println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
 * System.out.println(filteredGradedList);
 * System.out.
 * println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
 * 
 * DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
 * System.out.
 * println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
 * System.out.println(filteredNonGradedList);
 * System.out.
 * println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n"
 * );
 * 
 * float average = Operation.reduce(filteredGradedList);
 * System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
 * System.out.println(average);
 * System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
 * 
 * DLinkedList aboveAverageList =
 * Operation.filterRemoveBelowAverage(filteredGradedList, average);
 * System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
 * System.out.println(aboveAverageList);
 * System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
 * 
 * String contents = Operation.mapToString(fixedList);
 * System.out.
 * println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
 * System.out.println(contents);
 * System.out.
 * println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
 * 
 * // TOD O: Salvar o conteúdo da String contents em um arquivo chamado
 * "dados.csv".
 * 
 * try {
 * Data.saveStringToTextFile("dados.csv", contents);
 * } catch(FileNotFoundException e) {
 * System.out.println("Arquivo não encontrado!");
 * e.printStackTrace();
 * } catch(IOException e) {
 * System.out.println("Erro ao salvar o arquivo.");
 * e.printStackTrace();
 * }
 * 
 * Node test1 = fixedList.getNode("25.S3-999");
 * System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 +
 * "\n<<<<<<<<<< test1 <<<<<<<<<<\n");
 * 
 * Node test2 = fixedList.removeNode("25.S3-999");
 * System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 +
 * "\n<<<<<<<<<< test2 <<<<<<<<<<\n");
 * 
 * Node test3 = fixedList.getNode("25.S3-999");
 * System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 +
 * "\n<<<<<<<<<< test3 <<<<<<<<<<\n");
 * 
 * aboveAverageList.clear();
 * System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" +
 * aboveAverageList + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");
 * 
 * DLinkedList testList = new DLinkedList();
 * // Inserir um nó no início da lista testList com os dados ("ABC", "John Doe",
 * 4.7f).
 * testList.insert("ABC", "John Doe", 4.7f);
 * // Inserir um nó no final da lista testList com os dados ("XYZ", "Jane Doe",
 * 9.9f).
 * testList.append("XYZ", "Jane Doe", 9.9f);
 * // Inserir um nó no início da lista testList com os dados ("321", "Test",
 * 2.3f).
 * testList.insert("321", "Test", 2.3f);
 * // Inserir um nó no final da lista testList com os dados ("Nothing",
 * "Yada yada yada", 99.9f).
 * testList.append("Nothing", "Yada yada yada", 99.9f);
 * 
 * System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList +
 * "\n<<<<<<<<<< testList <<<<<<<<<<\n");
 * System.out.println("testList.getHead(): " + testList.getHead());
 * System.out.println("testList.getTail(): " + testList.getTail());
 * System.out.println("testList.removeHead(): " + testList.removeHead());
 * System.out.println("testList.removeTail(): " + testList.removeTail() + '\n');
 * System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList +
 * "\n<<<<<<<<<< testList <<<<<<<<<<\n");
 * System.out.println("testList.getHead(): " + testList.getHead());
 * System.out.println("testList.getTail(): " + testList.getTail());
 * System.out.println("testList.removeNode(\"ABC\"): " +
 * testList.removeNode("ABC") + '\n');
 * System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList +
 * "\n<<<<<<<<<< testList <<<<<<<<<<\n");
 * System.out.println("testList.getHead(): " + testList.getHead());
 * System.out.println("testList.getTail(): " + testList.getTail() + '\n');
 * 
 * // Inserir um nó no início da lista testList com os dados ("qwerty",
 * "QWERTY", 1.2f).
 * testList.insert("qwerty", "QWERTY", 1.2f);
 * // Inserir um nó no final da lista testList com os dados ("WASD", "wasd",
 * 3.4f).
 * testList.append("WASD", "wasd", 3.4f);
 * // Inserir um nó no início da lista testList com os dados ("ijkl", "IJKL",
 * 5.6f).
 * testList.insert("ijkl", "IJKL", 5.6f);
 * // Inserir um nó no final da lista testList com os dados ("1234",
 * "Um Dois Tres Quatro", 7.8f).
 * testList.append("1234", "Um Dois Tres Quatro", 7.8f);
 * 
 * System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList +
 * "\n<<<<<<<<<< testList <<<<<<<<<<\n");
 * testList.clear();
 * System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList +
 * "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");
 */
