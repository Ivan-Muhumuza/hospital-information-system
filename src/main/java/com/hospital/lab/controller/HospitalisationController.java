package com.hospital.lab.controller;

import com.hospital.lab.entity.Hospitalisation;
import com.hospital.lab.service.HospitalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospitalisations")
public class HospitalisationController {
    @Autowired
    private HospitalisationService hospitalisationService;

    @PostMapping
    public ResponseEntity<Hospitalisation> addHospitalisation(@RequestBody Hospitalisation hospitalisation) {
        Hospitalisation savedHospitalisation = hospitalisationService.createHospitalisation(hospitalisation);
        return ResponseEntity.ok(savedHospitalisation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospitalisation> getHospitalisationById(@PathVariable String id) {
        return hospitalisationService.getHospitalisationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospitalisation(@PathVariable String id) {
        hospitalisationService.deleteHospitalisation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospitalisation> updateHospitalisation(
            @PathVariable String id,
            @RequestBody Hospitalisation hospitalisation) {
        if (!id.equals(hospitalisation.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Hospitalisation updatedHospitalisation = hospitalisationService.updateHospitalisation(hospitalisation);
        return ResponseEntity.ok(updatedHospitalisation);
    }
}