package br.com.cfarias.dao.generics;

import java.util.HashMap;
import java.util.Map;

public class SingletonMap {
	
	 private static SingletonMap singletonMap;

	    /**
	     * Contém todos os registros da aplicação.
	     * Simula o banco de dados
	     */
	    protected Map<Class, Map<?, ?>> map; /**Um Map que a chave é a classe e o valor é um outro mapa, 
	    uma estrutura de mapa onde as chaves são classes e os valores são mapas que têm chaves e valores de tipos desconhecidos. 
		*/
	    private SingletonMap() { //Construtor privado que inicializa o map anterior como uma instancia HashMap vazia
	        map = new HashMap<>();
	    }
	    /**
	     * Método que garante o retorno de apenas uma instância desse objeto
	     *
	     * @return SingletonMap
	     */
	    public static SingletonMap getInstance() {//método que verifica se o SingletonMap já foi utilizado.
	        if (singletonMap == null) {//Se for vazio, inicializa uma instância singletonMap() com um novo hashMap
	            singletonMap = new SingletonMap();
	        }
	        return singletonMap; //caso o singletonMap não seja null, apenas retorna a instância com o map
	    }
	    

	    public Map<Class, Map<?, ?>> getMap() { /* um getter para o mapa da instancia, que simula um DB*/
	        return this.map;
	    }

}
