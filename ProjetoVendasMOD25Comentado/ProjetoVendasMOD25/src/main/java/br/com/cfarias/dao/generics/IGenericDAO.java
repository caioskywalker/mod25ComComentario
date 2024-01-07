package br.com.cfarias.dao.generics;
import java.io.Serializable;
import java.util.Collection;

import br.com.cfarias.domain.Persistente;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;


public interface IGenericDAO <T extends Persistente, E extends Serializable> {
	/*"T é um tipo genérico que estende Persistente, indicando que qualquer tipo que seja utilizado como T deve ser uma subclasse ou implementação da interface Persistente.
E é outro tipo genérico que estende Serializable, significando que qualquer tipo usado como E deve ser serializável.
	 * 
	 */
	
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;

    public void excluir(E valor);

    public void alterar(T entity) throws TipoChaveNaoEncontradaException;

    public T consultar(E valor);

    public Collection<T> buscarTodos();
    
    /*
     * Um tipo serializável em Java refere-se a um tipo de objeto que pode ser convertido em uma sequência de bytes. A serialização é o processo de converter um objeto em uma sequência de bytes para armazenamento ou transmissão, e a desserialização é o processo reverso, onde os bytes são usados para reconstruir o objeto original.

A interface Serializable em Java é uma marcação (marker interface), o que significa que ela não possui métodos para serem implementados. A presença dessa interface em uma classe é usada pelo Java para indicar que os objetos dessa classe podem ser serializados e desserializados.

Quando uma classe implementa a interface Serializable, ela está concordando em seguir as regras e permitir que suas instâncias sejam convertidas em uma sequência de bytes. Isso é útil em situações em que você precisa armazenar objetos em arquivos, enviar objetos através da rede ou persistir objetos em um banco de dados, entre outras operações que envolvem a conversão de objetos em uma forma que possa ser transportada ou armazenada.

Em resumo, um tipo serializável é aquele que pode ser convertido em uma sequência de bytes por meio do processo de serialização, geralmente marcado pela implementação da interface Serializable em Java
     */

}
