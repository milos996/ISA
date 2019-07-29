package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateServiceRequest;
import com.example.ISAums.dto.request.UpdateServiceRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public com.example.ISAums.model.Service createService(CreateServiceRequest request) {
        if (serviceRepository.existsByName(request.getName())) {
            throw new EntityAlreadyExistsException(request.getName());
        }

        com.example.ISAums.model.Service service = com.example.ISAums.model.Service.builder()
                .name(request.getName())
                .build();

        return  serviceRepository.save(service);
    }


    @Transactional(rollbackFor = Exception.class)
    public com.example.ISAums.model.Service updateService(UpdateServiceRequest request) {
        Optional<com.example.ISAums.model.Service> service = serviceRepository.findById(request.getId());

        if (service.get() == null) {
            throw new EntityWithIdDoesNotExist("Service", request.getId());
        }

        if (serviceRepository.existsByName(request.getName())) {
            throw new CustomException("Entity with this name" + request.getName() + " already exists!");
        }

        service.get().setName(request.getName());
        return serviceRepository.save(service.get());
    }


    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteService(UUID serviceId) {
        Optional<com.example.ISAums.model.Service> service = serviceRepository.findById(serviceId);

        if (service.get() == null) {
            throw new EntityWithIdDoesNotExist("Service", serviceId);
        }

        serviceRepository.delete(service.get());

        return true;
    }


    @Transactional(readOnly = true)
    public List<com.example.ISAums.model.Service> getServices() {
        return serviceRepository.findAll();
    }
}
