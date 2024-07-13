package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.service.dto.SpendRecordDto;
import com.amaap.unusualspends.service.UnusualSpendService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

public class UnusualSpendController {
    UnusualSpendService unusualSpendService;

    @Inject
    public UnusualSpendController(UnusualSpendService unusualSpendService) {
        this.unusualSpendService = unusualSpendService;
    }

    public Map<Integer, List<SpendRecordDto>> evaluateSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return unusualSpendService.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
    }

    public Response sendEmail(Map<Integer, List<SpendRecordDto>> spendRecord) {
        boolean isSent = unusualSpendService.sendEmail(spendRecord);
        if (isSent) {
            return new Response(HttpStatus.OK, "Email sent successfully");
        }
        return new Response(HttpStatus.BAD_REQUEST, "Error sending Email");

    }
}
