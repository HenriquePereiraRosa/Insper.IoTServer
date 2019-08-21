package com.insper.iotserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "state")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean state;
	
	@Column(name = "msg_code")
	private String msgCode;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;

	@Column(name = "desired_state")
	private Boolean desiredState;
	
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

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getDesiredState() {
		return desiredState;
	}

	public void setDesiredState(Boolean desiredState) {
		this.desiredState = desiredState;
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
		State other = (State) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
