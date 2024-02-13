package com.magazzino.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magazzino.model.Product;
import com.magazzino.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/product")
	public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
		Page<Product> findAll = productService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
    

    @GetMapping(path = "/product/id:{id}")
	public ResponseEntity<Product> findById(@PathVariable(required = true) Long id) {
		Optional<Product> find = productService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

        
	}

    @GetMapping(path = "/product/code:{code}")
	public ResponseEntity<Product> findByCode(@PathVariable(required = true) String code) {
		Optional<Product> find = productService.findByCode(code);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}

    @PostMapping(path = "/product")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		Product save = productService.save(product);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}


    @DeleteMapping(path = "/product/id:{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		productService.deleteById(id);
		return new ResponseEntity<>("Product deleted", HttpStatus.OK);

	}

    @DeleteMapping(path = "/product/code:{code}")
	public ResponseEntity<String> delete(@PathVariable String code) {
		productService.deleteByCode(code);
		return new ResponseEntity<>("Product deleted", HttpStatus.OK);

	}


    @PutMapping(path = "/product/id:{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) throws Exception {
		Product save = productService.updateById(id, product);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

    @PutMapping(path = "/product/code:{code}")
	public ResponseEntity<Product> update(@PathVariable String code, @RequestBody Product product) throws Exception {
		Product save = productService.updateByCode(code, product);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
}
