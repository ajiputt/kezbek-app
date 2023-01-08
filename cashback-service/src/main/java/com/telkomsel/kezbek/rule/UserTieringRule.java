package com.telkomsel.kezbek.rule;

import java.time.LocalDate;

import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import com.telkomsel.kezbek.model.UserTier;

public class UserTieringRule extends CoRRuleBook<Double> {

	@Override
	public void defineRules() {

		// check first buyer
		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(UserTier.class)
				.when(facts -> facts.isEmpty())
				.then((facts, result) -> result
						.setValue(UserTier.builder()
								.email(facts.getOne().getEmail())
								.lastTransactionDate(LocalDate.now())
								.partnerId(facts.getOne().getPartnerId())
								.totalTransaction(1)
								.tierId("B")
								.build()))
				.stop()
				.build());

		// check last transaction
		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(UserTier.class)
				.when(facts -> facts.getOne().getLastTransactionDate().compareTo(LocalDate.now()) > 29)
				.then((facts, result) -> {
					facts.getOne().setLastTransactionDate(LocalDate.now());
					facts.getOne().setTotalTransaction(1);
					facts.getOne().setTierId("B");
					result.setValue(facts.getOne());
				})
				.stop()
				.build());

		// check bronze tier increment
		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(UserTier.class)
				.when(facts -> facts.getOne().getTierId().equals("B") && facts.getOne().getTotalTransaction() < 7)
				.then((facts, result) -> {
					facts.getOne().setLastTransactionDate(LocalDate.now());
					facts.getOne().setTotalTransaction(facts.getOne().getTotalTransaction() + 1);
					result.setValue(facts.getOne());
				})
				.stop()
				.build());

		// check bronze tier level up
		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(UserTier.class)
				.when(facts -> facts.getOne().getTierId().equals("B") && facts.getOne().getTotalTransaction() == 7)
				.then((facts, result) -> {
					facts.getOne().setLastTransactionDate(LocalDate.now());
					facts.getOne().setTotalTransaction(1);
					facts.getOne().setTierId("S");
					result.setValue(facts.getOne());
				})
				.stop()
				.build());

		// check silver tier increment
		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(UserTier.class)
				.when(facts -> facts.getOne().getTierId().equals("S") && facts.getOne().getTotalTransaction() < 7)
				.then((facts, result) -> {
					facts.getOne().setLastTransactionDate(LocalDate.now());
					facts.getOne().setTotalTransaction(facts.getOne().getTotalTransaction() + 1);
					result.setValue(facts.getOne());
				})
				.stop()
				.build());

		// check silver tier level up
		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(UserTier.class)
				.when(facts -> facts.getOne().getTierId().equals("S") && facts.getOne().getTotalTransaction() == 7)
				.then((facts, result) -> {
					facts.getOne().setLastTransactionDate(LocalDate.now());
					facts.getOne().setTotalTransaction(1);
					facts.getOne().setTierId("G");
					result.setValue(facts.getOne());
				})
				.stop()
				.build());

		// check gold tier increment
		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(UserTier.class)
				.when(facts -> facts.getOne().getTierId().equals("G") && facts.getOne().getTotalTransaction() < 7)
				.then((facts, result) -> {
					facts.getOne().setLastTransactionDate(LocalDate.now());
					facts.getOne().setTotalTransaction(facts.getOne().getTotalTransaction() + 1);
					result.setValue(facts.getOne());
				})
				.stop()
				.build());

		// check gold tier max
		addRule(RuleBuilder.create().withFactType(UserTier.class).withResultType(UserTier.class)
				.when(facts -> facts.getOne().getTierId().equals("G") && facts.getOne().getTotalTransaction() == 7)
				.then((facts, result) -> {
					facts.getOne().setLastTransactionDate(LocalDate.now());
					result.setValue(facts.getOne());
				})
				.stop()
				.build());
	}

}
