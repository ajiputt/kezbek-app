package com.telkomsel.kezbek.rule;

import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import com.telkomsel.kezbek.request.CashbackRequest;

public class CashbackQuantityRule extends CoRRuleBook<Double> {
	
	

	@Override
	public void defineRules() {
		
		addRule(RuleBuilder.create().withFactType(CashbackRequest.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getQuantity() == 1 && facts.getOne().getTotalAmount() < 500000)
				.then((facts, result) -> result
				.setValue(result.getValue() + 0.55))
				.stop()
				.build());
		
		addRule(RuleBuilder.create().withFactType(CashbackRequest.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getQuantity() == 1 && facts.getOne().getTotalAmount() < 1000000)
				.then((facts, result) -> result
				.setValue(result.getValue() + 1.1))
				.stop()
				.build());
		
		addRule(RuleBuilder.create().withFactType(CashbackRequest.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getQuantity() == 2 && facts.getOne().getTotalAmount() < 1000000)
				.then((facts, result) -> result
				.setValue(result.getValue() + 1.25))
				.stop()
				.build());
		
		addRule(RuleBuilder.create().withFactType(CashbackRequest.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getQuantity() == 2 && facts.getOne().getTotalAmount() < 1500000)
				.then((facts, result) -> result
				.setValue(result.getValue() + 1.55))
				.stop()
				.build());
		
		addRule(RuleBuilder.create().withFactType(CashbackRequest.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getQuantity() == 2 && facts.getOne().getTotalAmount() >= 1500000)
				.then((facts, result) -> result
				.setValue(result.getValue() + 1.75))
				.stop()
				.build());
		
		addRule(RuleBuilder.create().withFactType(CashbackRequest.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getQuantity() >= 3 && facts.getOne().getTotalAmount() >= 1500000)
				.then((facts, result) -> result
				.setValue(result.getValue() + 2.15))
				.stop()
				.build());
	}
}
