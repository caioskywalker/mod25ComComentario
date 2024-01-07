package br.com.cfarias.dao.generics;

import anotacao.TipoChave;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;
import br.com.cfarias.domain.Persistente;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Classe genérica que implementa interface genérica com os métodos de CRUD
 */

public abstract class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T,E> {
	// A chave tem que implementar a interface Persistente, que no caso é a classe Cliente
	// Em resumo, um tipo serializável é aquele que pode ser convertido em uma sequência de 
	//	bytes por meio do processo de serialização, Serializable indica apenas que é um objeto
	// obs: Serializable não possui métodos, é uma interface marcação;
	//geralmente marcado pela implementação da interface Serializable em Java.
	 //protected Map<Class, Map<Long, T>> map = new HashMap<>();

    /**
     * Necessário utilizar Singleton para ter apenas um MAP no sistema
     */
    private SingletonMap singletonMap;//Instancia o SingletonMap na DAO
    public GenericDAO() {
        this.singletonMap = SingletonMap.getInstance();//método getInstance para utilizar o map do 
        //SingletonMap
       
    }

    public abstract Class<T> getTipoClasse();
    

    public abstract void atualiarDados(T entity, T entityCadastrado);
    
    /*Há dois métodos abstratos getTipoClasse() e atualiarDados(T entity, T entityCadastrado) 
     * que devem ser implementados pelas subclasses. 
     * O primeiro retorna a classe do tipo T e o
     *  segundo é responsável por atualizar os dados de uma entidade.
     * 
     */
    

   

    public E getChave(T entity) throws TipoChaveNaoEncontradaException {
        Field[] fields = entity.getClass().getDeclaredFields();
        /*armazena o Campo de getDeclaredFields no obj field*/ 
        E returnValue = null;
        //objeto do Tipo E returnValue,inicializado como nulo
        for (Field field : fields) {
        	//intera o fields com o getDeclaredFields
            if (field.isAnnotationPresent(TipoChave.class)) {
            	//Verifica se a anotação TipoChave está sendo utilizada
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                //cria um obj tipo chave e armazena getAnotation do campo no obj
                String nomeMetodo = tipoChave.value();
                //Armazena o valor da chave em uma String nomeMetodo
                try {
                    Method method = entity.getClass().getMethod(nomeMetodo);
                    //armazena o método no method
                    returnValue = (E) method.invoke(entity);
                    //invoca o método
                    return returnValue;
                    //retorna o método invocado
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    //Criar exception de negócio TipoChaveNaoEncontradaException
                    e.printStackTrace();
                    throw new TipoChaveNaoEncontradaException("Chave principal do objeto " + entity.getClass() + " não encontrada", e);
                }
            }
        }
        if (returnValue == null) {
            String msg = "Chave principal do objeto " + entity.getClass() + " não encontrada";
            System.out.println("**** ERRO ****" + msg);
            throw new TipoChaveNaoEncontradaException(msg);
        }
        return null;
    }
    /*	getChave()
     *  Este método utiliza reflexão para obter a chave de uma entidade T marcada com a anotação @TipoChave.
     *   Ele busca um método específico definido pela anotação e invoca esse método na entidade 
     *   para obter a chave, que é uma classe.
     */

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
        //Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
        Map<E, T> mapaInterno = getMapa();//utiliza o getMap do SingletonMap e o aloca para o mapaInterno
        E chave = getChave(entity);// A chave é uma classe
        if (mapaInterno.containsKey(chave)) {
            return false;
        }

        mapaInterno.put(chave, entity);
        return true;
    }

	private Map<E, T> getMapa() {
		Map<E, T> mapaInterno = (Map<E, T>) this.singletonMap.getMap().get(getTipoClasse());
		if (mapaInterno == null) {
			mapaInterno = new HashMap<>();
			this.singletonMap.getMap().put(getTipoClasse(), mapaInterno);
		}
		return mapaInterno;
	}

    @Override
    public void excluir(E valor) {
        //Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
        Map<E, T> mapaInterno = getMapa();
        T objetoCadastrado = mapaInterno.get(valor);
        if (objetoCadastrado != null) {
            mapaInterno.remove(valor, objetoCadastrado);
        }
    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {
        Map<E, T> mapaInterno = getMapa();
        E chave = getChave(entity);
        T objetoCadastrado = mapaInterno.get(chave);
        if (objetoCadastrado != null) {
            atualiarDados(entity, objetoCadastrado);
        }
    }

    @Override
    public T consultar(E valor) {
        //Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.get(valor);
    }

    @Override
    public Collection<T> buscarTodos() {
        Map<E, T> mapaInterno = getMapa();
        return mapaInterno.values();
    }

}
