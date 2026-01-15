package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.SaleDto;
import com.digitalrangersbd.DeepManage.Dto.SaleUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.*;
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
    private final UserRepository userRepository;
    private final OutletRepository outletRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    public SaleService(SaleRepository saleRepository, RoleAuthorization roleAuthorization, ProductRepository productRepository, UserRepository userRepository, OutletRepository outletRepository, PaymentTypeRepository paymentTypeRepository) {
        this.saleRepository = saleRepository;
        this.roleAuthorization = roleAuthorization;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.outletRepository = outletRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }
    
    //Create new sale
    public Sale createSale(SaleDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreateSalePermission(userId)){
            throw new SecurityException("User does not have permission to create sale.");
        }
        else{
            Sale sale = new Sale();

            sale.setReference(dto.getReference());
            sale.setSaleDate(dto.getSaleDate());

            sale.setCreated_date(LocalDate.now());
            sale.setCreated_time(LocalTime.now());
            sale.setUpdated_date(LocalDate.now());
            sale.setUpdated_time(LocalTime.now());
            sale.setCreated_by_id(userId);

            if(dto.getCustomer() != null){
                User customer = userRepository.findById(dto.getCustomer())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                sale.setCustomer(customer);
            }

            if(dto.getSoldBy() != null){
                User soldBy = userRepository.findById(dto.getSoldBy())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                sale.setSoldBy(soldBy);
            }

            if(dto.getOutlet() != null){
                Outlet outlet = outletRepository.findById(dto.getOutlet())
                        .orElseThrow(() -> new RuntimeException("Outlet not found"));
                sale.setOutlet(outlet);
            }

            if(dto.getPaymentType() != null){
                PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentType())
                        .orElseThrow(() -> new RuntimeException("Payment type not found"));
                sale.setPaymentType(paymentType);
            }

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
    public List<Sale> getAllSale(){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewSalePermission(userId)){
            throw new SecurityException("User does not have the permission to view sale");
        }
        else{
            return saleRepository.findByDeletedFalse();
        }
    }

    //View sale by id
    public Optional<Sale> getSaleById(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewSalePermission(userId)){
            throw new SecurityException("User does not have the permission to view sale.");
        }
        else{
            return saleRepository.findById(id);
        }
    }

    //Update sale
    public Sale updateSale(Long id, SaleUpdateDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateSalePermission(userId)){
            throw new SecurityException("User does not have the permission to update sale");
        }
        else{

            return saleRepository.findById(id)
                    .map(sale -> {
                        if(dto.getReference() != null) sale.setReference(dto.getReference());
                        if(dto.getSaleDate() != null) sale.setSaleDate(dto.getSaleDate());
                        sale.setUpdated_date(LocalDate.now());
                        sale.setUpdated_time(LocalTime.now());
                        sale.setUpdated_by_id(userId);

                        if(dto.getCustomer() != null){
                            User customer = userRepository.findById(dto.getCustomer())
                                    .orElseThrow(() -> new RuntimeException("User not found"));
                            sale.setCustomer(customer);
                        }

                        if(dto.getSoldBy() != null){
                            User soldBy = userRepository.findById(dto.getSoldBy())
                                    .orElseThrow(() -> new RuntimeException("User not found"));
                            sale.setSoldBy(soldBy);
                        }

                        if(dto.getOutlet() != null){
                            Outlet outlet = outletRepository.findById(dto.getOutlet())
                                    .orElseThrow(() -> new RuntimeException("Outlet not found"));
                            sale.setOutlet(outlet);
                        }

                        if(dto.getPaymentType() != null){
                            PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentType())
                                    .orElseThrow(() -> new RuntimeException("Payment type not found"));
                            sale.setPaymentType(paymentType);
                        }

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
    public Sale deleteSale(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeleteSalePermission(userId)){
            throw new SecurityException("User do not have the permission to delete Sale");
        }
        return saleRepository.findById(id)
                .map(sale -> {
                    sale.setDeleted(true);
                    sale.setDeletedById(userId);
                    sale.setDeletedDate(LocalDate.now());
                    sale.setDeletedTime(LocalTime.now());

                    return saleRepository.save(sale);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));


    }


}

