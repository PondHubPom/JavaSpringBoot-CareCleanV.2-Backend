package com.Hoime.CareClean.controller;

import com.Hoime.CareClean.model.domain.*;
import com.Hoime.CareClean.model.wrapper.*;
import com.Hoime.CareClean.services.MembersService;
import com.Hoime.CareClean.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    @Autowired
    private MembersService membersService;
    @Autowired
    private UtilService utilService;

    @GetMapping("/get-all-member")
    public List<MemberListDomain> getMemberListDomain(
            HttpServletRequest request) {

        String id = request.getAttribute("id").toString();

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return membersService.getMemberListDomain(id);
    }
    @GetMapping("/get-all-booking")
    public List<BookingListDomain> getBookingListDomain(
            HttpServletRequest request) {

        String id = request.getAttribute("id").toString();

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return membersService.getBookingListDomain(id);
    }
}
