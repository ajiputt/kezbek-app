package com.telkomsel.kezbek.service.impl;

import org.springframework.stereotype.Service;

import com.deliveredtechnologies.rulebook.Fact;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.telkomsel.kezbek.model.UserTier;
import com.telkomsel.kezbek.request.CashbackRequest;
import com.telkomsel.kezbek.rule.CashbackQuantityRule;
import com.telkomsel.kezbek.rule.CashbackTieringRule;
import com.telkomsel.kezbek.service.CalucalteCashbackService;
import com.telkomsel.kezbek.service.UserTierService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculateCashbackServiceImpl implements CalucalteCashbackService {

	private final UserTierService userTierService;

	@Override
	public Double calculateCashbackTier(CashbackRequest cashbackRequest) {
		NameValueReferableMap<UserTier> facts = new FactMap<>();
		RuleBook<Double> cashbackTieringRule = RuleBookBuilder.create(CashbackTieringRule.class)
				.withResultType(Double.class).withDefaultResult(Double.valueOf(0)).build();

		facts.put(new Fact<>(
			userTierService.processUserTier(cashbackRequest.getEmail(), cashbackRequest.getPartnerId())));

		cashbackTieringRule.run(facts);

		return cashbackTieringRule.getResult().map(Result::getValue).orElse(Double.valueOf(0));
	}

	@Override
	public Double calculateCashbackQuantity(CashbackRequest cashbackRequest) {
		RuleBook<Double> cashbackQuantityRule = RuleBookBuilder.create(CashbackQuantityRule.class)
				.withResultType(Double.class).withDefaultResult(1.2).build();

		NameValueReferableMap<CashbackRequest> facts = new FactMap<>();
		facts.put(new Fact<>(cashbackRequest));

		cashbackQuantityRule.run(facts);
		return cashbackQuantityRule.getResult().map(Result::getValue).orElse(Double.valueOf(0))
				* cashbackRequest.getTotalAmount() / 100;
	}

}
