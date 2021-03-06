/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nuvemapp.hibernatesearch;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.nuvemapp.hibernatesearch.model.Produto;
import br.com.nuvemapp.hibernatesearch.util.FullTextEntityManagerProducer;
import br.com.nuvemapp.hibernatesearch.util.JPAUtil;

public class Main {

	/**
	 * @param args
	 * the command line arguments
	 */
	public static void main(String[] args) throws InterruptedException {

		// INCIA A CLASSE UTILITARIA QUE RETORNA UMA CONEXAO COM O BANCO
		JPAUtil jPAUtil = new JPAUtil();
		// RETORNA A ENTIDADE
		EntityManager entityManager = jPAUtil.getEntityManager();

		// INCIA A CLASSE UTILITARIA QUE RETORNA UM HIBERNATE SEARCH
		FullTextEntityManagerProducer fullTextEntityManagerProducer = new FullTextEntityManagerProducer();
		// INCIIA A PESQUISA E INDEXACAO
		fullTextEntityManagerProducer.inicia(entityManager);

		// PESQUISA, RETONANDO UMA LISTA DE PRODUTOS COM O TEXTO ENVIADO.
		List<Produto> pesquisaProduto = fullTextEntityManagerProducer.pesquisaProduto("Brastemp");

		System.err.println("Retornou : " + pesquisaProduto.size());
		for (Produto produto : pesquisaProduto) {
			System.out.println("Encontrados : " + produto.getNome());
		}

		// ISSO TAMBEM FECHA O ENTITYMANAGER PASSADO NA INICIALIZACAO DO MESMO.
		fullTextEntityManagerProducer.para();

		System.exit(0);

	}

}
