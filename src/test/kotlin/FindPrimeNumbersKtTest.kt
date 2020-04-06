import com.natpryce.hamkrest.assertion.assertThat
import org.junit.jupiter.api.Test

val PRIME_NUMBERS = listOf(
        2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L, 61L, 67L, 71L)

val NON_PRIME_NUMBERS = listOf(
        4L, 6L, 8L, 12L, 14L, 18L, 20L, 24L, 30L, 32L, 38L, 42L, 44L, 48L, 54L, 60L, 62L, 68L, 72L)

val MIXED_NUMBERS = listOf(
        2L, 3L, 5L, 7L, 11L, 14L, 18L, 20L, 24L, 30L, 32L, 38L, 42L, 44L, 47L, 53L, 59L, 61L, 67L, 71L)

val MIXED_NUMBERS_RESULT = listOf(2L, 3L, 5L, 7L, 11L, 47L, 53L, 59L, 61L, 67L, 71L)

internal class FindPrimeNumbersKtTest {

    @Test
    fun `all numbers are prime`()  {
        val actual = PRIME_NUMBERS.filter { isPrimeNumber(it) }

        assertThat(actual, PRIME_NUMBERS::containsAll)
    }

    @Test
    fun `all numbers are not prime`()  {
        val actual = NON_PRIME_NUMBERS.filter { isPrimeNumber(it) }

        assertThat(actual, emptyList<Long>()::equals)
    }

    @Test
    fun `only prime numbers are true`()  {
        val actual = MIXED_NUMBERS.filter { isPrimeNumber(it) }

        assertThat(actual, MIXED_NUMBERS_RESULT::containsAll)
    }

}