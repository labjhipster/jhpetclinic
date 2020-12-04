package org.jhipster.petclinic.service.impl;

import org.jhipster.petclinic.service.PetService;
import org.jhipster.petclinic.domain.Pet;
import org.jhipster.petclinic.repository.PetRepository;
import org.jhipster.petclinic.service.dto.PetDTO;
import org.jhipster.petclinic.service.mapper.PetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Pet}.
 */
@Service
@Transactional
public class PetServiceImpl implements PetService {

    private final Logger log = LoggerFactory.getLogger(PetServiceImpl.class);

    private final PetRepository petRepository;

    private final PetMapper petMapper;

    public PetServiceImpl(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    @Override
    public PetDTO save(PetDTO petDTO) {
        log.debug("Request to save Pet : {}", petDTO);
        Pet pet = petMapper.toEntity(petDTO);
        pet = petRepository.save(pet);
        return petMapper.toDto(pet);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pets");
        return petRepository.findAll(pageable)
            .map(petMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PetDTO> findOne(Long id) {
        log.debug("Request to get Pet : {}", id);
        return petRepository.findById(id)
            .map(petMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pet : {}", id);
        petRepository.deleteById(id);
    }
}
