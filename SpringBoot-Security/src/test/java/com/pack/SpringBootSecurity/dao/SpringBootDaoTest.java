package com.pack.SpringBootSecurity.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.pack.SpringBootSecurity.model.Product;
import com.pack.SpringBootSecurity.repository.ProductRepsitory;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDaoTest {
	
	@Autowired
	private ProductRepsitory productRepo;
	
	@Test
	public void testSaveProduct(){
        Product product = new Product(10l,"TV","LG","China",20000.0);
        Product savedInDb = productRepo.save(product);
        Optional<Product> data = productRepo.findById(savedInDb.getId());
        Product getFromDb = (Product)data.get();
        assertThat(getFromDb).isEqualTo(savedInDb);
	}

}
