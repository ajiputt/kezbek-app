package com.telkomsel.kezbek.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deliveredtechnologies.rulebook.Fact;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.telkomsel.kezbek.model.UserTier;
import com.telkomsel.kezbek.repository.UserTierRepository;
import com.telkomsel.kezbek.rule.UserTieringRule;
import com.telkomsel.kezbek.service.UserTierService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserTierServiceImpl implements UserTierService {

        private final UserTierRepository userTierRepository;

        @Override
        public UserTier processUserTier(String email, String partnerId) {

                Optional<UserTier> optinonalUserTier = userTierRepository.findByEmailAndPartnerId(email,
                                partnerId);

                UserTier defaultUser = UserTier.builder()
                                .email(email)
                                .lastTransactionDate(LocalDate.now())
                                .partnerId(partnerId)
                                .totalTransaction(1)
                                .tierId("B")
                                .build();

                NameValueReferableMap<UserTier> facts = new FactMap<>();
                RuleBook<UserTier> userTieringRule = RuleBookBuilder.create(UserTieringRule.class)
                                .withResultType(UserTier.class).withDefaultResult(defaultUser).build();

                optinonalUserTier.ifPresent(userTier -> facts.put(new Fact<>(userTier)));

                userTieringRule.run(facts);

                UserTier resp = userTieringRule.getResult().map(Result::getValue).orElse(defaultUser);

                userTierRepository.save(resp);

                return resp;
        }
}
