
package com.example.fleet_management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {
}
