package in.learnjavaskills.multipledatabaseconfigure.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learnjavaskills.multipledatabaseconfigure.vendor.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {}
