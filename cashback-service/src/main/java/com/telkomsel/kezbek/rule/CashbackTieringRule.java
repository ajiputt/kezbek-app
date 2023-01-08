package com.telkomsel.kezbek.rule;

import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import com.telkomsel.kezbek.model.UserTier;

public class CashbackTieringRule extends CoRRuleBook<Double> {

	@Override
	public void defineRules() {

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("B") && facts.getOne().getTotalTransaction() == 2)
				.then((facts, result) -> result
						.setValue(result.getValue() + 15000))
				.stop()
				.build());

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("B") && facts.getOne().getTotalTransaction() == 4)
				.then((facts, result) -> result
						.setValue(result.getValue() + 25000))
				.stop()
				.build());

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("B") && facts.getOne().getTotalTransaction() == 6)
				.then((facts, result) -> result
						.setValue(result.getValue() + 35000))
				.stop()
				.build());

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("S") && facts.getOne().getTotalTransaction() == 2)
				.then((facts, result) -> result
						.setValue(result.getValue() + 17500))
				.stop()
				.build());

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("S") && facts.getOne().getTotalTransaction() == 4)
				.then((facts, result) -> result
						.setValue(result.getValue() + 28500))
				.stop()
				.build());

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("S") && facts.getOne().getTotalTransaction() == 6)
				.then((facts, result) -> result
						.setValue(result.getValue() + 37500))
				.stop()
				.build());

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("G") && facts.getOne().getTotalTransaction() == 2)
				.then((facts, result) -> result
						.setValue(result.getValue() + 18000))
				.stop()
				.build());

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("G") && facts.getOne().getTotalTransaction() == 4)
				.then((facts, result) -> result
						.setValue(result.getValue() + 29000))
				.stop()
				.build());

		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(Double.class)
				.when(facts -> facts.getOne().getTierId().equals("G") && facts.getOne().getTotalTransaction() == 6)
				.then((facts, result) -> result
						.setValue(result.getValue() + 38000))
				.stop()
				.build());
	}

}
