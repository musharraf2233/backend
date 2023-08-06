package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.dao.ProductDao;
import com.youtube.ecommerce.entity.ImageModel;
import com.youtube.ecommerce.entity.Product;
import com.youtube.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDao productDao;
	
	

	@PreAuthorize("hasRole('Admin')")
	@PostMapping(value = { "/addNewProduct" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public Product addNewProduct(@RequestPart("product") Product product,
			@RequestPart("imageFile") MultipartFile[] file) {
		try {
			Set<ImageModel> images = uploadImage(file);
			product.setProductImages(images);
			return productService.addNewProduct(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageModel> imageModels = new HashSet<>();

		for (MultipartFile file : multipartFiles) {
			ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			imageModels.add(imageModel);
		}

		return imageModels;
	}

	@GetMapping({ "/getAllProducts" })
	public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "8") int pageSize,
			@RequestParam(defaultValue = "") String searchKey) {
		List<Product> result = productService.getAllProducts(pageNumber,pageSize, searchKey);
		System.out.println("Result size is " + result.size());
		return result;
	}
	
	@GetMapping({"/getAllBrands"})
	public Set<String> getAllBrands(){
		List<Product> prod=productDao.findAll();
		Set<String> brands=new HashSet<>();
		for(Product p:prod) {
			brands.add(p.getProductName());
		}
		System.out.println(brands.size());
		return brands;
		
	}
	
	@GetMapping("/getProductByName/{productName}")
	public List<Product> getProductsByName(@PathVariable("productName") String productName){
		return productService.getProductDetailsByName(productName);
	}
	
	@PutMapping("/updateBestSeller/{id}")
	public Product updateBestSeller(@PathVariable("productId") int produtId,@RequestPart("product") Product product) {
		Product update=productDao.findById(produtId).get();
		update.setBestSeller(product.getBestSeller());
		return productService.addNewProduct(update);
	}

	@GetMapping("/getByBestSeller")
	public List<Product> getProductByBestSeller(){
		return productService.getProductByBestSeller();
	}
	
	@GetMapping("/getByTrends")
	public List<Product> getProductByTrends(){
		return productService.getProductByTrendPefume();
	}
	
    @GetMapping({"/getProductDetailsByMen/{type}"})
	public List<Product> getProductDetailsByType(@PathVariable("type") String type,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "") String searchKey){
    	List<Product> byType= productService.getProductsType(pageNumber,pageSize, searchKey, type);
    	System.out.println(byType.size());
    	return byType;
	}

	@GetMapping({ "/getProductDetailsById/{productId}" })
	public Product getProductDetailsById(@PathVariable("productId") Integer productId) {
		return productService.getProductDetailsById(productId);
	}

	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping({ "/deleteProductDetails/{productId}" })
	public void deleteProductDetails(@PathVariable("productId") Integer productId) {
		productService.deleteProductDetails(productId);
	}

	@PreAuthorize("hasRole('User')")
	@GetMapping({ "/getProductDetails/{isSingleProductCheckout}/{productId}" })
	public List<Product> getProductDetails(
			@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
			@PathVariable(name = "productId") Integer productId) {
		return productService.getProductDetails(isSingleProductCheckout, productId);
	}
}
