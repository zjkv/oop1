package company.rental;

import company.BaseTest;
import company.Subscription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PriceCalculatorTest extends BaseTest {

    @Test
    void shouldApplyDiscountToCalculatedPriceBasedOnSubscriptionWhenRidesAmountMatchesValueInSubscription() {
        //given
        final var scooterData = new Object[]{"not_fast", 1.0f, 2.0f, 3.0f, 4.0f};
        final var isImmediatePayment = Boolean.TRUE;
        final var minutes = new UsageTime(1);
        final var priceAmountClientMultiplicationFactor = 0.9f;

        //when
        //using zero_to_nine rents subscription - multiplier of discount * 1
        final var regularPrice = PriceCalculator.calculate(scooterData, isImmediatePayment, minutes, priceAmountClientMultiplicationFactor, Subscription.ZERO_TO_NINE, new RidesAmount(8));
        //using ten_to_nineteen rents subscription - multiplier of discount * 0.9
        final var priceWith0Point9Multiplier = PriceCalculator.calculate(scooterData, isImmediatePayment, minutes, priceAmountClientMultiplicationFactor, Subscription.TEN_TO_NINETEEN, new RidesAmount(12));
        assertEquals(0.9f, priceWith0Point9Multiplier / regularPrice, 0.0001f);

    }

    @Test
    void shouldApplyDefaultDiscountToCalculatedPriceBasedOnSubscriptionWhenRidesAmountDoesNotMatchValueInSubscription() {
        //given
        final var scooterData = new Object[]{"not_fast", 1.0f, 2.0f, 3.0f, 4.0f};
        final var isImmediatePayment = Boolean.TRUE;
        final var minutes = new UsageTime(1);
        final var priceAmountClientMultiplicationFactor = 0.9f;

        //when
        //using ten_to_nineteen rents subscription - multiplier of discount * 0.9
        final var tenToNineteenSubscriptionPrice = PriceCalculator.calculate(scooterData, isImmediatePayment, minutes, priceAmountClientMultiplicationFactor, Subscription.TEN_TO_NINETEEN, new RidesAmount(3));
        //using twenty_to_twenty_nine rents subscription - multiplier of discount * 0.8
        final var twentyToTwentyNineSubscriptionPrice = PriceCalculator.calculate(scooterData, isImmediatePayment, minutes, priceAmountClientMultiplicationFactor, Subscription.TWENTY_TO_TWENTY_NINE, new RidesAmount(8));
        //both subscriptions discounts wont be applied, because number of rides dont match required amount in given subscription
        assertEquals(1, tenToNineteenSubscriptionPrice / twentyToTwentyNineSubscriptionPrice, 0.0001f);

    }
}