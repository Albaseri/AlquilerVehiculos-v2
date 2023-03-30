package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;

public class Clientes implements IClientes {
	private List<Cliente> coleccionClientes;

	// constructor por defecto
	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}

	// método get
	@Override
	public List<Cliente> get() {
		return coleccionClientes;
	}

	// método getCantidad
	@Override
	public int getCantidad() {
		return coleccionClientes.size();
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		if (!coleccionClientes.contains(cliente)) {
			coleccionClientes.add(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		int indiceCliente = coleccionClientes.indexOf(cliente); // declaro variable e inicializo
		if (indiceCliente != -1) { //si el índice de Cliente es diferente de null (-1), se mete dentro de la colección y lo almacena.
			cliente = coleccionClientes.get(indiceCliente); // almaceno el cliente encontrado de la colección de Clientes.
		}else {
			cliente = null;
		}
		return cliente;
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if (coleccionClientes.contains(cliente)) {
			coleccionClientes.remove(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) { //compruebo si cliente pasado por parámetro es nulo
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}
		Cliente clienteE = buscar(cliente); // busca y encuentra cliente. ClienteE es cliente encontrado
		
		if (clienteE == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		if (nombre != null && !nombre.isBlank()) {
			clienteE.setNombre(nombre); //modifico el nombre
		}

		if (telefono != null && !telefono.isBlank()) {
			clienteE.setTelefono(telefono);
		}
	}
}
