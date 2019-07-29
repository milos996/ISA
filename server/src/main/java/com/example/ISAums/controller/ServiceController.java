package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateServiceRequest;
import com.example.ISAums.dto.request.UpdateServiceRequest;
import com.example.ISAums.dto.response.CreateServiceResponse;
import com.example.ISAums.dto.response.DeleteServiceResponse;
import com.example.ISAums.dto.response.GetServiceResponse;
import com.example.ISAums.dto.response.UpdateServiceResponse;
import com.example.ISAums.model.Service;
import com.example.ISAums.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.ServiceConverter.*;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<GetServiceResponse>> get() {
        List<Service> services = serviceService.getServices();
        return ResponseEntity.ok(toGetServicesResponseFromListServices(services));
    }

    @PostMapping
    public ResponseEntity<CreateServiceResponse> create(CreateServiceRequest request) {
        Service service = serviceService.createService(request);
        return ResponseEntity.ok(toCreateServiceResponseFromService(service));
    }

    @PutMapping
    public ResponseEntity<UpdateServiceResponse> update(UpdateServiceRequest request) {
        Service Service = serviceService.updateService(request);
        return ResponseEntity.ok(toUpdateServiceResponseFromService(Service));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteServiceResponse> delete(@PathVariable(name = "id") UUID serviceId) {
        Boolean isDeleted = serviceService.deleteService(serviceId);
        return  ResponseEntity.ok(toDeleteServiceResponseFromDeleteServiceRequest(serviceId, isDeleted));
    }

}
