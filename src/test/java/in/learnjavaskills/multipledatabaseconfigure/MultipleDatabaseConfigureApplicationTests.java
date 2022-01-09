package in.learnjavaskills.multipledatabaseconfigure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.learnjavaskills.multipledatabaseconfigure.customer.entity.Customer;
import in.learnjavaskills.multipledatabaseconfigure.customer.repository.CustomerRepository;
import in.learnjavaskills.multipledatabaseconfigure.vendor.entity.Vendor;
import in.learnjavaskills.multipledatabaseconfigure.vendor.repository.VendorRepository;

@SpringBootTest
class MultipleDatabaseConfigureApplicationTests 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@Test
	void addCustomer()
	{
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Henry");
		customerRepository.save(customer);
	}
	
	@Test
	void addVendor()
	{
		Vendor vendor = new Vendor();
		vendor.setFirstName("Bruno");
		vendor.setLastName("wonder");
		vendorRepository.save(vendor);
	}
}
