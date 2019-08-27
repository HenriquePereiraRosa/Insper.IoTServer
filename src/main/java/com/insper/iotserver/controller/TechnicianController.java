package com.insper.iotserver.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.insper.iotserver.model.Technician;
import com.insper.iotserver.repository.TechnicianRepository;

@RestController
@CrossOrigin
@RequestMapping("/technicians")
public class TechnicianController {

	//@Autowired
	//sprivate DeviceRepository deviceRepo;

	@Autowired
	private TechnicianRepository repo;

	@GetMapping
	public List<Technician> get() {
		return repo.findAll();
	}

	@GetMapping("/last")
	public Technician getLast() {
		List<Technician> list = repo.findAll();
		return list.get(list.size() - 1);
	}

	@GetMapping("/{id}/last")
	public List<Technician> getLastItem(@PathVariable Long id) {
		List<Technician> lastItem = repo.getLastByDeviceId(id);
		return lastItem;
	}

	@PostMapping
	public ResponseEntity<Technician> post(@RequestBody Technician obj, HttpServletResponse response) {
		
		if (repo.findById(obj.getId()) != null) {
			Technician objSaved = repo.save(obj);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(objSaved.getId())
					.toUri();
			response.setHeader("Location", uri.toASCIIString());

			return ResponseEntity.status(HttpStatus.CREATED).body(objSaved);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	

	/**
	 * Returns an RenponseEntity object that is the result of an  
	 *  object updated in DB.
	 * This method always returns immediately, whether or not the 
	 * image exists. When this applet attempts to draw the image on
	 * the screen, the data will be loaded. The graphics primitives 
	 * that draw the image will incrementally paint on the screen. 
	 *
	 * @param  @RequestBody Color object with the attributes to update.
	 * @return      The updated object
	 * @see         ...
	 */
	@PutMapping  // Designed to Embbeded Systems
	public ResponseEntity<Technician> update(@RequestBody Technician obj,
			HttpServletResponse response) {

		Technician dbObj = repo.getOne(obj.getId());
		if ((repo.findById(obj.getId()) != null)
				&& (dbObj != null) && (obj != null)) {

			Technician objSaved = repo.save(copyObj(obj, dbObj));

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(objSaved.getId())
					.toUri();
			response.setHeader("Location", uri.toASCIIString());

			return ResponseEntity.status(HttpStatus.OK).body(objSaved);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	

	/**
	 * Returns an Technician Obj updated by Obj param.
	 * All non null attributes will be updated. 
	 *
	 * @param  An updated Technician obj.
	 * @param  A Technician dbObj saved on DB whose need to be updated.
	 * @return The updated object
	 */
	private Technician copyObj(Technician obj, Technician dbObj) {
		if (obj.getName() != null) {
			dbObj.setName(obj.getName());
		}

		if (obj.getEmail() != null) {
			dbObj.setEmail(obj.getEmail());
		}

		if (obj.getLab() != null) {
			dbObj.setLab(obj.getLab());
		}

		if (obj.getBio() != null) {
			dbObj.setBio(obj.getBio());
		}

		if (obj.getLabel() != null) {
			dbObj.setLabel(obj.getLabel());
		}

		return dbObj;
	}
}
