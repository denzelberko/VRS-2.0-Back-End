package com.example.vrs.model.repository;

import com.example.vrs.model.entity.RequestEdit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestEditRepository extends JpaRepository<RequestEdit, Long> {
}
