package com.insper.iotserver.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="color")
public class SelectColor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String msg;
	private String color;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@Column(name = "desired_color")
	private String desiredColor;
	
	@Column(name = "desired_date_time")
	private LocalDateTime desiredDateTime;
	
	@ManyToOne
	@JoinColumn(name = "id_device")
	@NotNull
	private Device device;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getDesiredColor() {
		return desiredColor;
	}

	public void setDesiredColor(String desiredColor) {
		this.desiredColor = desiredColor;
	}

	public LocalDateTime getDesiredDateTime() {
		return desiredDateTime;
	}

	public void setDesiredDateTime(LocalDateTime desiredDateTime) {
		this.desiredDateTime = desiredDateTime;
	}
	
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelectColor other = (SelectColor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
