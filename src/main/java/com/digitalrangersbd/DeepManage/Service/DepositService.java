package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.DepositDto;
import com.digitalrangersbd.DeepManage.Dto.DepositUpdateDto;
import com.digitalrangersbd.DeepManage.Entity.*;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    private final DepositRepository depositRepository;
    private final RoleAuthorization roleAuthorization;
    private final DepositCategoryRepository depositCategoryRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final WarehouseRepository warehouseRepository;
    private final OutletRepository outletRepository;
    private final UserRepository userRepository;

    public DepositService(DepositRepository depositRepository, RoleAuthorization roleAuthorization, DepositCategoryRepository depositCategoryRepository, PaymentTypeRepository paymentTypeRepository, WarehouseRepository warehouseRepository, OutletRepository outletRepository, UserRepository userRepository) {
        this.depositRepository = depositRepository;
        this.roleAuthorization = roleAuthorization;
        this.depositCategoryRepository = depositCategoryRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.warehouseRepository = warehouseRepository;
        this.outletRepository = outletRepository;
        this.userRepository = userRepository;
    }


    //Create new deposit
    public Deposit createDeposit(DepositDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreateDepositPermission(userId)){
            throw new SecurityException("User does not have the persmission to create deposit.");
        }
        else{
            Deposit deposit = new Deposit();
            deposit.setName(dto.getName());
            deposit.setStatus(dto.getStatus());
            deposit.setAmount(dto.getAmount());
            deposit.setDescription(dto.getDescription());

            deposit.setCreated_date(LocalDate.now());
            deposit.setCreated_time(LocalTime.now());
            deposit.setUpdated_date(LocalDate.now());
            deposit.setUpdated_time(LocalTime.now());

            if(dto.getCategory() != null){
                DepositCategory depositCategory = depositCategoryRepository.findById(dto.getCategory())
                        .orElseThrow(() -> new RuntimeException("Deposit category does not exists"));
                deposit.setCategory(depositCategory);
            }

            if(dto.getPaymentType() != null){
                PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentType())
                        .orElseThrow(() -> new RuntimeException("Payment type does not exists"));
                deposit.setPaymentType(paymentType);
            }

            if(dto.getWarehouseId() != null){
                Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                        .orElseThrow(() -> new RuntimeException("Warehouse does not exists"));
                deposit.setWarehouse(warehouse);
            }

            if(dto.getOutletId() != null){
                Outlet outlet = outletRepository.findById(dto.getOutletId())
                        .orElseThrow(() -> new RuntimeException("Outlet does not exists"));
                deposit.setOutlet(outlet);
            }

            if(dto.getUserId() != null){
                if(!userRepository.existsById(dto.getUserId())){
                    throw new RuntimeException("User id does not exists");
                }
                else{
                    deposit.setUserId(dto.getUserId());
                }
            }

            return depositRepository.save(deposit);

        }

    }

    //View all deposit
    public List<Deposit> getAllDeposit(){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewDepositPermission(userId)){
            throw new SecurityException("User does not have the persmission to view deposit.");
        }
        else{
            return depositRepository.findAll();
        }
    }

    //View deposit by id
    public Optional<Deposit> getDepositById(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewDepositPermission(userId)){
            throw new SecurityException("User does not have the persmission to view deposit.");
        }
        else{
            return depositRepository.findById(id);
        }
    }

    //Update deposit
    public Deposit updateDeposit(Long id, DepositUpdateDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasUpdateDepositPermission(userId)){
            throw new SecurityException("User does not have the persmission to update deposit.");
        }
        else{
            return depositRepository.findById(id)
                    .map(deposit -> {
                        if(dto.getName() != null) deposit.setName(dto.getName());
                        if(dto.getStatus() != null) deposit.setStatus(dto.getStatus());
                        if(dto.getAmount() != null) deposit.setAmount(dto.getAmount());
                        if(dto.getDescription() != null) deposit.setDescription(dto.getDescription());

                        deposit.setUpdated_date(LocalDate.now());
                        deposit.setUpdated_time(LocalTime.now());

                        if(dto.getCategory() != null){
                            DepositCategory depositCategory = depositCategoryRepository.findById(dto.getCategory().getId())
                                    .orElseThrow(() -> new RuntimeException("Deposit category does not exists"));
                            deposit.setCategory(depositCategory);
                        }

                        if(dto.getPaymentType() != null){
                            PaymentType paymentType = paymentTypeRepository.findById(dto.getPaymentType().getId())
                                    .orElseThrow(() -> new RuntimeException("Payment type does not exists"));
                            deposit.setPaymentType(paymentType);
                        }

                        return depositRepository.save(deposit);

                    })
                    .orElseThrow(() -> new RuntimeException("Deposit does not exist."));
        }
    }

    //Delete deposit
    public Boolean deleteDeposit(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeleteDepositPermission(userId)){
            throw new SecurityException("User does not have the persmission to delete deposit.");
        }
        else{
            if(depositRepository.existsById(id)){
                depositRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
