package com.hospital.lab.controller;

import com.hospital.lab.entity.Ward;
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

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable String id) {
        Optional<Ward> ward = wardService.getWardById(id);
        return ward.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/beds")
    public ResponseEntity<Ward> updateWardBeds(
            @PathVariable String id,
            @RequestParam int numberOfBeds) {
        try {
            Ward updatedWard = wardService.updateWardBeds(id, numberOfBeds);
            return ResponseEntity.ok(updatedWard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}