package com.csaba79coder.littersnap.model.litter.persistence;

import com.csaba79coder.littersnap.model.litter.entity.Litter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * This class contains the litter repository.
 */
@Repository
public interface LitterRepository extends JpaRepository<Litter, UUID> {
}
