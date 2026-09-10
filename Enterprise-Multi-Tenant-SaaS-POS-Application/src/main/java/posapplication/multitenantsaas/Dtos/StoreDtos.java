package posapplication.multitenantsaas.Dtos;

import posapplication.multitenantsaas.ModelClass.StoreContact;
import posapplication.multitenantsaas.ModelClass.StoreStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class StoreDtos {
    private UUID id;
    private String brand;
    private UserDtos storeAdmin;
    private String storeType;
    private StoreStatus storeStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private StoreContact storeContact;
}
