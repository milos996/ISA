package com.example.ISAums.converter;

import com.example.ISAums.dto.response.CreateServiceResponse;
import com.example.ISAums.dto.response.DeleteServiceResponse;
import com.example.ISAums.dto.response.GetServiceResponse;
import com.example.ISAums.dto.response.UpdateServiceResponse;
import com.example.ISAums.model.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ServiceConverter {
    public static List<GetServiceResponse> toGetServicesResponseFromListServices(List<Service> services) {
        return services.stream()
            .map(service -> GetServiceResponse.builder()
                .id(service.getId())
                .name(service.getName())
                .build())
            .collect(Collectors.toList());
    }

    public static CreateServiceResponse toCreateServiceResponseFromService(Service service) {
        return CreateServiceResponse.builder()
            .id(service.getId())
            .build();
    }

    public static UpdateServiceResponse toUpdateServiceResponseFromService(Service service){
        return UpdateServiceResponse.builder()
            .id(service.getId())
            .build();
    }


    public static DeleteServiceResponse toDeleteServiceResponseFromDeleteServiceRequest(UUID serviceId, Boolean isDeleted) {
        return DeleteServiceResponse.builder()
            .id(serviceId)
            .isDeleted(isDeleted)
            .build();
    }
}
