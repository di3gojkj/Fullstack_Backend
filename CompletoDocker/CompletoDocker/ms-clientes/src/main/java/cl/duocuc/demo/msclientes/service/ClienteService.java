package cl.duocuc.demo.msclientes.service;

import cl.duocuc.demo.msclientes.dto.ClienteDTO;
import cl.duocuc.demo.msclientes.model.Cliente;
import cl.duocuc.demo.msclientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ClienteService - Logica de Negocio
 * Patron identico a ProductoService.
 */
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private ClienteDTO toDTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setApellido(c.getApellido());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());
        return dto;
    }

    private Cliente toEntidad(ClienteDTO dto) {
        Cliente c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setApellido(dto.getApellido());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        return c;
    }

    public List<ClienteDTO> obtenerTodos() {
        List<Cliente> entidades = clienteRepository.findAll();
        List<ClienteDTO> lista = new ArrayList<>();
        for (Cliente c : entidades) {
            lista.add(toDTO(c));
        }
        return lista;
    }

    public Optional<ClienteDTO> obtenerPorId(Long id) {
        Optional<Cliente> op = clienteRepository.findById(id);
        if (op.isPresent()) {
            return Optional.of(toDTO(op.get()));
        }
        return Optional.empty();
    }

    public ClienteDTO crear(ClienteDTO dto) {
        return toDTO(clienteRepository.save(toEntidad(dto)));
    }

    public Optional<ClienteDTO> actualizar(Long id, ClienteDTO dto) {
        Optional<Cliente> op = clienteRepository.findById(id);
        if (op.isEmpty()) {
            return Optional.empty();
        }
        Cliente existente = op.get();
        existente.setNombre(dto.getNombre());
        existente.setApellido(dto.getApellido());
        existente.setEmail(dto.getEmail());
        existente.setTelefono(dto.getTelefono());
        return Optional.of(toDTO(clienteRepository.save(existente)));
    }

    public boolean eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }

}
