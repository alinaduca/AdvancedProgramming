package com.example.demo.api.controller;

import com.example.demo.api.model.Player;
import com.example.demo.service.PlayerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@RestController
public class PlayerGroupController {
    private PlayerGroupService playerGroupService;

    @Autowired
    public PlayerGroupController() {
        this.playerGroupService = new PlayerGroupService();
    }

    @GetMapping("/minimal-group")
    public ResponseEntity<Set<Player>> getMinimalPlayerGroup() {
        return new ResponseEntity<>(new HashSet<>(playerGroupService.minimalGroup()), new HttpHeaders(), HttpStatus.OK);
    }
}
