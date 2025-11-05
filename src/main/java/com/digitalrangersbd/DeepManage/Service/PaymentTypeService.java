package com.digitalrangersbd.DeepManage.Service;

import com.digitalrangersbd.DeepManage.Authorization.RoleAuthorization;
import com.digitalrangersbd.DeepManage.Dto.PaymentTypeDto;
import com.digitalrangersbd.DeepManage.Entity.PaymentType;
import com.digitalrangersbd.DeepManage.JWT.UserContext;
import com.digitalrangersbd.DeepManage.Repository.PaymentTypeRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private final RoleAuthorization roleAuthorization;

    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository, RoleAuthorization roleAuthorization) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.roleAuthorization = roleAuthorization;
    }

    //Create Payment Type
    public PaymentType createPaymentType(PaymentTypeDto dto){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasCreatePaymentTypePermission(userId)){
            throw new SecurityException("User do not have the permission to create payment");
        }
        if(paymentTypeRepository.existsByName(dto.getName())){
            throw new RuntimeException("Payment name already exists.");
        }
        else{
            PaymentType paymentType = new PaymentType();
            paymentType.setName(dto.getName());
            paymentType.setCreated_date(LocalDate.now());
            paymentType.setCreated_time(LocalTime.now());

            return paymentTypeRepository.save(paymentType);
        }
    }

    //Get Payment type
    public List<PaymentType> getAllPaymentType(){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasViewPaymentTypePermission(userId)){
            throw new SecurityException("User do not have the permission to view payment");
        }
        else{
            return paymentTypeRepository.findAll();
        }
    }

    //Delete Payment
    public Boolean deletePayment(Long id){

        String userId = UserContext.getUserId();
        if(!roleAuthorization.hasDeletePaymentTypePermission(userId)){
            throw new SecurityException("User do not have the permission to delete payment");
        }
        else{
            if(paymentTypeRepository.existsById(id)){
                paymentTypeRepository.deleteById(id);
                return true;
            }
            else {
                return false;
            }
        }
    }

}
