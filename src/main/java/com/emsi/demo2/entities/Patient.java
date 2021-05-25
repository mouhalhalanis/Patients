package com.emsi.demo2.entities;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name="Patients")
public class Patient {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
@NotNull

	private String nom;
@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date dNaiss;
	private boolean malade;
	
}
