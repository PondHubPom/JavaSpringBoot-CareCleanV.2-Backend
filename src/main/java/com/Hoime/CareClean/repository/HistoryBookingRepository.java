package com.Hoime.CareClean.repository;

import com.Hoime.CareClean.model.entity.BookingEntity;
import com.Hoime.CareClean.model.entity.HistoryBookingEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryBookingRepository extends ElasticsearchRepository<HistoryBookingEntity,String>{
    List<HistoryBookingEntity> findByAccountId(String accountId);
    HistoryBookingEntity findByBookingId(String bookingId);
//    Optional<HistoryBookingEntity> find(String accountId);
//
//    List<HistoryBookingEntity> findWithAccountId(String accountId);
}
