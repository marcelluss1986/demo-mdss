package com.mdss.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mdss.demo.dto.CreateUserDTO;
import com.mdss.demo.dto.UserDTO;
import com.mdss.demo.entities.User;
import com.mdss.demo.repositories.UserRepository;
import com.mdss.demo.services.exceptions.DatabaseException;
import com.mdss.demo.services.exceptions.ResourceNotFoundException;


@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	ModelMapper mapper;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	public UserDTO findByEmail(String email) {
		User user = repository.findByEmail(email);
		UserDTO dto = mapper.map(user, UserDTO.class);
		return dto;
	}
	
	
	public CreateUserDTO findUserByEmail(String email) {
		User user = repository.findByEmail(email);
		CreateUserDTO dto = mapper.map(user, CreateUserDTO.class);
		return dto;
	}
		

	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void delete(String email) {
		try {
			repository.deleteByEmail(email);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(email);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}


	public User update(Long id, User obj) {
		try {
			User entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}catch(EntityNotFoundException e) {
		throw new ResourceNotFoundException(id);
	}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());

	}
}
