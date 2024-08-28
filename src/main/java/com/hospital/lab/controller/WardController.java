package com.hospital.lab.controller;

import com.hospital.lab.entity.Ward;
import com.hospital.lab.entity.WardId;
import com.hospital.lab.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/wards")
public class WardController {

    @Autowired
    private WardService wardService;

    @PostMapping
    public ResponseEntity<Ward> saveWard(@RequestBody Ward ward) {
        Ward savedWard = wardService.saveWard(ward);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWard);
    }

    // Get method to retrieve a ward by its composite ID
    @GetMapping("/{departmentCode}/{wardNumber}")
    public ResponseEntity<Ward> getWardById(
            @PathVariable Long departmentCode,
            @PathVariable int wardNumber) {

        WardId wardId = new WardId(wardNumber, departmentCode);
        Optional<Ward> ward = wardService.getWardById(wardId);

        if (ward.isPresent()) {
            return ResponseEntity.ok(ward.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{departmentCode}/{wardNumber}/beds")
    public ResponseEntity<Ward> updateWardBeds(
            @PathVariable Long departmentCode,
            @PathVariable int wardNumber,
            @RequestParam int numberOfBeds) {

        WardId wardId = new WardId(wardNumber, departmentCode);

        try {
            Ward updatedWard = wardService.updateWardBeds(wardId, numberOfBeds);
            return ResponseEntity.ok(updatedWard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/{departmentCode}/{wardNumber}/beds")
//    public ResponseEntity<Ward> updateWardBeds(
//            @PathVariable Long departmentCode,
//            @PathVariable int wardNumber,
//            @RequestParam int numberOfBeds) {
//
//        WardId wardId = new WardId(wardNumber, departmentCode);
//        Ward updatedWard = wardService.updateWardBeds(wardId, numberOfBeds);
//        return ResponseEntity.ok(updatedWard);
//    }

}

