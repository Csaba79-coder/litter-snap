package com.csaba79coder.littersnap.model.litter.persistence;

import com.csaba79coder.littersnap.model.litter.entity.Litter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LitterRepository extends JpaRepository<Litter, UUID> {
}
