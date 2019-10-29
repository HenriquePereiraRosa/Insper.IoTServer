package com.insper.iotserver.controller;

import com.insper.iotserver.model.SelectColor;
import com.insper.iotserver.repository.DeviceRepository;
import com.insper.iotserver.repository.SelectColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/select_colors")
public class SelectColorController {

	@Autowired
	private DeviceRepository deviceRepo;

	@Autowired
	private SelectColorRepository repo;

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
	@GetMapping
	public List<SelectColor> get() {
		return repo.findAll();
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
	@GetMapping("/last")
	public SelectColor getLast() {
		List<SelectColor> list = repo.findAll();
		return list.get(list.size() - 1);
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
	@GetMapping("/{id}/last")
	public List<SelectColor> getLastItem(@PathVariable Long id) {
		List<SelectColor> lastItem = repo.getLastByDeviceId(id);
		return lastItem;
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
	@PostMapping
	public ResponseEntity<SelectColor> post(@RequestBody SelectColor obj, HttpServletResponse response) {
		if (deviceRepo.findById(obj.getDevice().getId()) != null) {
			SelectColor objSaved = repo.save(obj);

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
	@PutMapping("/last")  // Designed to Embbeded Systems
	public ResponseEntity<SelectColor> updateLastColor(@RequestBody SelectColor obj,
			HttpServletResponse response) {

		List<SelectColor> list = repo.getLastByDeviceId(obj.getDevice().getId());
		SelectColor dbObj = list.get(0);
		if ((deviceRepo.findById(obj.getDevice().getId()) != null)
				&& (dbObj != null) && (obj != null)) {

			obj.setDateTime(LocalDateTime.now());
			SelectColor objSaved = repo.save(copyObj(obj, dbObj));

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
	private SelectColor copyObj(SelectColor obj, SelectColor dbObj) {
		if (obj.getColor() != null) {
			dbObj.setColor(obj.getColor());
		}

		if (obj.getDateTime() != null) {
			dbObj.setDateTime(obj.getDateTime());
		}

		return dbObj;
	}
}
