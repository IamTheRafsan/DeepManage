package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.SaleDto;
import com.digitalrangersbd.DeepManage.Dto.SaleUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.Product;
import com.digitalrangersbd.DeepManage.Entity.Sale;
import com.digitalrangersbd.DeepManage.Entity.SaleItem;
import com.digitalrangersbd.DeepManage.Repository.ProductRepository;
import com.digitalrangersbd.DeepManage.Repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final RoleAuthorization roleAuthorization;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, RoleAuthorization roleAuthorization, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.roleAuthorization = roleAuthorization;
        this.productRepository = productRepository;
    }
    
    //Create new sale
    public Sale createSale(String userId, SaleDto dto){
        if(!roleAuthorization.hasCreateSalePermission(userId)){
            throw new SecurityException("User does not have permission to create sale.");
        }
        else{
            Sale sale = new Sale();

            sale.setReference(dto.getReference());
            sale.setSaleDate(dto.getSaleDate());
            sale.setOutlet(dto.getOutlet());
            sale.setSoldBy(dto.getSoldBy());

            sale.setCreated_date(LocalDate.now());
            sale.setCreated_time(LocalTime.now());
            sale.setUpdated_date(LocalDate.now());
            sale.setUpdated_time(LocalTime.now());

            List<SaleItem> saleItem = dto.getSaleItem();
            if(saleItem != null && !saleItem.isEmpty()){

                for(SaleItem item: saleItem ){
                    item.setSale(sale);

                    sale.getSaleItem().add(item);

                }
            }

            return saleRepository.save(sale);

        }
    }

    //View sale data
    public List<Sale> getAllSale(String userId){
        if(!roleAuthorization.hasViewSalePermission(userId)){
            throw new SecurityException("User does not have the permission to view sale");
        }
        else{
            return saleRepository.findAll();
        }
    }

    //View sale by id
    public Optional<Sale> getSaleById(String userId, Long id){
        if(!roleAuthorization.hasViewSalePermission(userId)){
            throw new SecurityException("User does not have the permission to view sale.");
        }
        else{
            return saleRepository.findById(id);
        }
    }

    //Update sale
    public Sale updateSale(String userId, Long id, SaleUpdateDto dto){

        if(!roleAuthorization.hasUpdateSalePermission(userId)){
            throw new SecurityException("User does not have the permission to update sale");
        }
        else{

            return saleRepository.findById(id)
                    .map(sale -> {
                        if(dto.getReference() != null) sale.setReference(dto.getReference());
                        if(dto.getSaleDate() != null) sale.setSaleDate(dto.getSaleDate());
                        if(dto.getOutlet() != null) sale.setOutlet(dto.getOutlet());
                        if(dto.getSoldBy() != null) sale.setSoldBy(dto.getSoldBy());
                        sale.setUpdated_date(LocalDate.now());
                        sale.setUpdated_time(LocalTime.now());

                        if(dto.getSaleItem() != null){
                            sale.getSaleItem().clear();

                            for(SaleItem item: dto.getSaleItem()){
                                Product product = productRepository.findById(item.getProduct().getId())
                                        .orElseThrow(() -> new RuntimeException("Product with ID " + item.getProduct().getId() + " not found."));

                                item.setSale(sale);
                                item.setProduct(product);
                                sale.getSaleItem().add(item);
                            }
                        }
                        return saleRepository.save(sale);

                    })
                    .orElseThrow(() -> new RuntimeException("Sale id not found"+id ));
        }

    }

    //Delete Sale
    public Boolean deleteSale(String userId, Long id){
        if(!roleAuthorization.hasDeleteSalePermission(userId)){
            throw new SecurityException("User do not have the permission to delete Sale");
        }
        else{
            if(saleRepository.existsById(id)){
                saleRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }

    }


}

