package seedu.clinkedin.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_validRating_success() {
        String validRating = "4";
        assertEquals(new Rating(validRating).getClass(), Rating.class);
    }

    @Test
    public void equals_sameRating_success() {
        String rating = "4";
        assertEquals(new Rating(rating), new Rating(rating));
    }

    @Test
    public void equals_differentRating_success() {
        String rating1 = "3";
        String rating2 = "5";
        assertEquals(new Rating(rating1).equals(new Rating(rating2)), false);
    }

    @Test
    public void equals_differentRatingSameValues_success() {
        String rating1 = "6";
        String rating2 = "6";
        assertEquals(new Rating(rating1).equals(new Rating(rating2)), true);
    }

    @Test
    public void value_success() {
        String rating = "1";
        assertEquals(new Rating(rating).toString(), rating);
    }

    @Test
    public void validRating() {
        assertTrue(Rating.isValidRatingStr("0"));
    }

}
