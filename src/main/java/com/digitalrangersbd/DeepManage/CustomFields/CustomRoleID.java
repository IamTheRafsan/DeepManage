package com.digitalrangersbd.DeepManage.CustomFields;

import com.digitalrangersbd.DeepManage.Global.GlobalVariables;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRoleID implements IdentifierGenerator{
    @Autowired
    private GlobalVariables shopName;

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        String prefix = shopName.getShopName();
        String query = "SELECT role_id FROM role ORDER BY role_id DESC LIMIT 1";
        String lastNumber = (String) session.createNativeQuery(query).uniqueResult();

        int nextNumber = 1;

        if(lastNumber != null && lastNumber.startsWith(prefix)){

            String numberPart =  lastNumber.replace(prefix, "");

            nextNumber = Integer.parseInt(numberPart)+1;
        }

        return prefix + String.format("%04d", nextNumber);
    }
}
