package b_Money;

/**
 * Represents a currency with a name and exchange rate.
 */
public class Currency {
	private String name;
	private Double rate;

	/**
	 * Constructs a new Currency object.
	 *
	 * @param name The name of this Currency.
	 * @param rate The exchange rate of this Currency.
	 *             The rate argument of each currency indicates that Currency's "universal" exchange rate.
	 *             Imagine that we define the rate of each currency in relation to some universal currency.
	 *             This means that the rate of each currency defines its value compared to this universal currency.
	 */
	Currency(String name, Double rate) {
		this.name = name;
		this.rate = rate;
	}

	/**
	 * Converts an amount of this Currency to its value in the general "universal currency."
	 * (As mentioned in the documentation of the Currency constructor)
	 *
	 * @param amount An amount of cash of this currency.
	 * @return The value of amount in the "universal currency."
	 */
	public Integer universalValue(Integer amount) {
		return (int) (amount * rate);
	}

	/**
	 * Gets the name of this Currency.
	 *
	 * @return The name of Currency.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the rate of this Currency.
	 *
	 * @return The rate of this Currency.
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * Sets the rate of this currency.
	 *
	 * @param rate New rate for this Currency.
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}

	/**
	 * Converts an amount from another Currency to an amount in this Currency.
	 *
	 * @param amount        Amount of the other Currency.
	 * @param othercurrency The other Currency.
	 * @return The value of amount in this Currency.
	 */
	public Integer valueInThisCurrency(Integer amount, Currency othercurrency) {
		return (int) (amount * othercurrency.getRate() / this.rate);
	}
}
