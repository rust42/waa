package edu.miu.restdemo.service.impl;

import edu.miu.restdemo.dto.ProductDto;
import edu.miu.restdemo.entity.Product;
import edu.miu.restdemo.repository.ProductRepo;
import edu.miu.restdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

//    @Autowired
//    public ProductServiceImpl(ProductRepo productRepo) {
//        this.productRepo = productRepo;
//    }

    @Override
    public List<ProductDto> findAll() {
        var data = productRepo.findAll();
        var result = new ArrayList<ProductDto>();

        data.forEach(entity ->  {
            var dto = new ProductDto(entity.getName(), entity.getPrice(), entity.getCategory());
            result.add(dto);
        });
        return result;
    }

    @Override
    public void save(ProductDto productDto) {
        // convert to entity
        var entity = new Product();

        productRepo.save(entity);
    }
}
