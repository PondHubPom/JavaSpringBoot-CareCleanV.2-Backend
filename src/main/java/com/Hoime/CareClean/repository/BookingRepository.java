package com.Hoime.CareClean.repository;

import com.Hoime.CareClean.model.entity.BookingEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends ElasticsearchRepository<BookingEntity,String>{
    List<BookingEntity> findByAccountId(String accountId);
    BookingEntity findByBookingDate(String bookingDate);
    BookingEntity findByTimeslot(String timeslot);
//    Optional<BookingEntity> find(String accountId);

}
