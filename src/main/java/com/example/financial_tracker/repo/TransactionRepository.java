package com.example.financial_tracker.repo;

import com.example.financial_tracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // උඹේ Transaction එන්ටිටි එකේ තියෙන්නේ private User user; නම්, උඹ මෙතඩ් එක ලියද්දී findBy + User (එන්ටිටි වේරියබල් එකේ නම) + Id
    List<Transaction>findByUserId(int userId);

}
