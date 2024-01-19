package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Bank class.
 */
public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	/**
	 * Test for the getName method.
	 */
	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	/**
	 * Test for the getCurrency method.
	 */
	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	/**
	 * Test for the openAccount method.
	 */
	@Test
	public void testOpenAccount() {
		try {
			SweBank.openAccount("Bob");
			fail("Expected AccountExistsException");
		} catch (AccountExistsException e) {
			// Expected exception, test passes
		}
	}

	/**
	 * Test for the deposit method.
	 */
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(10000, SEK));
		assertEquals(Integer.valueOf(10000), SweBank.getBalance("Bob"));
	}

	/**
	 * Test for the withdraw method.
	 */
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Bob", new Money(5000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Bob"));
	}

	/**
	 * Test for the getBalance method.
	 */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
	}

	/**
	 * Test for the transfer method.
	 */
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Bob", DanskeBank, "Gertrud", new Money(2000, SEK));
		assertEquals(Integer.valueOf(2000), DanskeBank.getBalance("Gertrud"));
		assertEquals(Integer.valueOf(-2000), SweBank.getBalance("Bob"));
	}

	/**
	 * Test for the timedPayment method.
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob", "payment1", 1, 0, new Money(500, SEK), DanskeBank, "Gertrud");
		SweBank.tick();
		assertEquals(Integer.valueOf(500), DanskeBank.getBalance("Gertrud"));
		assertEquals(Integer.valueOf(-500), SweBank.getBalance("Bob"));
	}
}
