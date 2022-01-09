package in.learnjavaskills.multipledatabaseconfigure.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learnjavaskills.multipledatabaseconfigure.customer.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
