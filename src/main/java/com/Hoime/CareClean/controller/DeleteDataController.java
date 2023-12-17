package com.Hoime.CareClean.controller;

import com.Hoime.CareClean.model.domain.UtilDomain;
//import com.Hoime.CareClean.services.DeleteService;
import com.Hoime.CareClean.services.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/secure/data")
public class DeleteDataController {
    @Autowired
    MembersService memberService;
//    @DeleteMapping("/delete")
//    public UtilDomain remove(@RequestParam("id") String id) {
//        return memberService.removeAccount(id);
//    }
}
