package com.telkomsel.kezbek.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.telkomsel.kezbek.model.UserTier;
import com.telkomsel.kezbek.repository.UserTierRepository;

@ExtendWith(MockitoExtension.class)
class UserTierServiceImplTest {

	@Mock
	private UserTierRepository userTierRepository;

	@Autowired
	private UserTierServiceImpl userTierServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
			userTierServiceImpl = new UserTierServiceImpl(userTierRepository);
	}

	@Test
	void shouldReturn_UserTier() {
			UserTier userTier = userTierServiceImpl.processUserTier("test@email.com", "PARTNER-TEST");

			Assert.assertNotNull(userTier);
	}

}
