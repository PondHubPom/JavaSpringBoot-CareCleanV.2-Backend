package com.Hoime.CareClean.controller;

import com.Hoime.CareClean.fileUploadUtil.FileUploadUtil;
import com.Hoime.CareClean.model.domain.*;
import com.Hoime.CareClean.model.entity.BookingEntity;
import com.Hoime.CareClean.model.wrapper.*;
import com.Hoime.CareClean.services.BookingService;
import com.Hoime.CareClean.services.MembersService;
import com.Hoime.CareClean.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/service")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private MembersService membersService;
    @Autowired
    private UtilService utilService;

//    @PostMapping("/booking")
//    public BookingDomain booking(@RequestBody BookingWrapper w, HttpServletRequest res) {
//        String id = res.getAttribute("id").toString();
//        return bookingService.booking(w,id);
//    }
    @PostMapping(value = "/booking", consumes = "multipart/form-data")
    public BookingDomain booking(
            @RequestParam("file") MultipartFile file,
                                 @RequestParam("bookingDate") String bookingDate,
                                 @RequestParam("timeslot") String timeslot,
                                 @RequestParam("select") String select,
                                 @RequestParam("amount") String amount,
                                 @RequestParam("payment") String payment,
                                 @RequestParam("title") String title,
                                 @RequestParam("select_payment") String select_payment,
                                 @RequestParam("price") String price,

                                 HttpServletRequest res) throws IOException {
        BookingWrapper w = new BookingWrapper();

        w.setBookingDate(bookingDate.trim());
        w.setTimeslot(timeslot.trim());
        w.setSelect(select.trim());
        w.setAmount(amount.trim());
        w.setPayment(payment.trim());
        w.setSelect_payment(select_payment.trim());
        w.setTitle(title.trim());
        w.setPrice(price);

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        String uploadDir =  (Math.random()) + ".jpg";
        w.setPath(uploadDir);

        new FileUploadUtil().saveFile(uploadDir, fileName, file);

        String id = res.getAttribute("id").toString();
        return bookingService.booking(w,id);
    }
    @PutMapping("/set-to-success")
    public BookingSuccessDomain toSuccess(@RequestBody BookingSuccessWrapper w, HttpServletRequest req, HttpServletResponse res) {
        String id = req.getAttribute("id").toString();
        return bookingService.bookingSuccess(id,w);
    }
    @PutMapping("/set-to-cancel")
    public BookingSuccessDomain toCancel(@RequestBody BookingSuccessWrapper w, HttpServletRequest req, HttpServletResponse res) {
        String id = req.getAttribute("id").toString();
        return bookingService.bookingCancel(id,w);
    }


}
