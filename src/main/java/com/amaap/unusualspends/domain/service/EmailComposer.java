package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.service.dto.SpendRecordDto;

import java.util.List;

public class EmailComposer {
    public static String composeEmail(String name, List<SpendRecordDto> record) {
        StringBuilder body = new StringBuilder("\n Hello " + name + "!\n\n We have detected unusually high spending on your card in these categories:\n ");
        double totalUnusualSpend = 0;
        for (SpendRecordDto spend : record) {
            body.append("\n * You spent " + spend.unusualSpendAmount + " on " + spend.category + "\n");
            totalUnusualSpend += spend.unusualSpendAmount;
        }
        System.out.println("\n");
        body.append("Thanks,\n" +
                "\n" +
                "The Credit Card Company");
        body.insert(0, "Unusual spending of " + totalUnusualSpend +" detected!\n");
        return body.toString();
    }
}
