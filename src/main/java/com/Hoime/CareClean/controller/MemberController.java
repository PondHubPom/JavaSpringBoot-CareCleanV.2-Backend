package com.Hoime.CareClean.controller;

import com.Hoime.CareClean.model.domain.*;
import com.Hoime.CareClean.model.wrapper.*;
import com.Hoime.CareClean.services.MembersService;
import com.Hoime.CareClean.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import java.io.*;
import java.nio.file.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/member")
public class MemberController {


    @Autowired
    private MembersService membersService;
    @Autowired
    private UtilService utilService;

    @PostMapping("/login")
    public MemberDomain login(
            @RequestBody(required = false) MemberWrapper w) {
        return membersService.login(w);
    }

    @PostMapping("/register")
    public MemberDomain register(
            @RequestBody(required = false) MemberWrapper w) {

        return membersService.register(w);
    }
    @GetMapping("/get-personal-data")
    public PersonalDataDomain getPersonalData(HttpServletRequest request) {
        String id = request.getAttribute("id").toString();
        return membersService.getPersonalData(id);
    }
    @PutMapping("/edit-personal-data")
    public UtilDomain editPersonalData(@RequestBody EditPersonalWrapper wrapper, HttpServletRequest res) {
        String id = res.getAttribute("id").toString();
        return membersService.editPersonalData(id, wrapper);
    }
    @GetMapping("/get-history-booking")
    public List<HistoryBookingDomain> getHistoryBookingDomain(
            HttpServletRequest request) {

        String id = request.getAttribute("id").toString();

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return membersService.getHistoryBookingDomain(id);
    }
    @PutMapping("/set-to-admin")
    public UtilDomain toAdmin(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getAttribute("id").toString();
        return membersService.toAdmin(id);
    }
    @GetMapping("/get-location")
    public LocationDomain getLocationData(HttpServletRequest request) {
        String id = request.getAttribute("id").toString();
        return membersService.getLocationData(id);
    }
    @PutMapping("/reset-password")
    public UtilDomain resetPassword(
            @RequestBody ResetPasswordWrapper resetPasswordWrapper,
            HttpServletRequest request) {

        String id = request.getAttribute("id").toString();
        return membersService.resetPassword(id,resetPasswordWrapper);
    }

    @DeleteMapping("/delete")
    public UtilDomain removeAccount(@RequestBody IdWrapper w, HttpServletRequest req, HttpServletResponse res) {
        String id = req.getAttribute("id").toString();
        return membersService.removeAccount(id,w);
    }

    @PutMapping("/retrieve")
    public UtilDomain retrieveAccount(@RequestBody IdWrapper w, HttpServletRequest req, HttpServletResponse res) {
        String id = req.getAttribute("id").toString();
        return membersService.retrieveAccount(id,w);
    }

}