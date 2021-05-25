package com.emsi.demo2.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.emsi.demo2.entities.Patient;

public interface PatientRepos extends JpaRepository<Patient,Long> {
public Page<Patient> findByNomContains(String kw,Pageable p);
}



