package com.Hoime.CareClean.controller;

//import com.Homie.Home_NotPro.model.entity.BuyMenuEntity;

import com.Hoime.CareClean.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/util")
public class UtilController {
//    @Autowired
//    private SaveFileService saveFileService;
//    @Value("${app.image.directory}")
//    private String imageDirectoryPath;

//    @GetMapping(value = "/image/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<byte[]> getImage(@PathVariable(required = false) String filename) throws IOException {
//
//        if (filename == null) {
//            filename = "auto";
//        }
//        return saveFileService.getImages(filename);
//    }

//    @Autowired
//    private BuyMenuRepository buyMenuRepository;
//    @Autowired
//    private HistoryTransferRepository historyTransferRepository;
//    @Autowired
//    private LockerIdRepository lockerIdRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private HistoryBookingRepository historyBookingRepository;
//    @Autowired
//    private StatisticsMenuRepository statisticsMenuRepository;
//    @Autowired
//    private StoreRepository storeRepository;

    @DeleteMapping("/all-delete")
    public String delete() {

        memberRepository.deleteAll();
        bookingRepository.deleteAll();
        historyBookingRepository.deleteAll();

        return "Success";
    }
}