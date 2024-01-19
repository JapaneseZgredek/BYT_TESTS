package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}

	@Test
	public void testAddRemoveTimedPayment() {
		// Test adding and removing a timed payment
		testAccount.addTimedPayment("payment1", 5, 2, new Money(100, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("payment1"));

		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment1"));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// Test timed payment execution
		testAccount.addTimedPayment("payment2", 2, 1, new Money(50, SEK), SweBank, "Alice");

		// Wait for one tick
		testAccount.tick();
		// Check if the timed payment was executed
		assertEquals(new Money(9999950, SEK).getAmount(), testAccount.getBalance().getAmount());
	}

	@Test
	public void testAddWithdraw() {
		// Test deposit and withdrawal
		testAccount.deposit(new Money(500, SEK));
		assertEquals(new Money(10000500, SEK).getAmount(), testAccount.getBalance().getAmount());

		testAccount.withdraw(new Money(200, SEK));
		assertEquals(new Money(10000300, SEK).getAmount(), testAccount.getBalance().getAmount());
	}

	@Test
	public void testGetBalance() {
		// Test getting the balance
		assertEquals(new Money(10000000, SEK).toString(), testAccount.getBalance().toString());
	}
}
