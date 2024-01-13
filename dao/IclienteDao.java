package carlosguilhermeatividade.dao;

import carlosguilhermeatividade.domain.Cliente;

import java.util.Collection;

public interface IclienteDao {
    public Boolean cadastrar(Cliente cliente);
    public void excluir(Long cpf);
    public void alterar(Cliente cliente);
    public Cliente consultar(Long cpf);
    public Collection<Cliente> buscartodos();

}
