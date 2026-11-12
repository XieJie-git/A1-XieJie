/**
 * Represents a fixed time slot in the clinic's daily schedule.
 * The clinic operates from 08:00 to 16:00 in 30-minute intervals.
 * Using an enum ensures that only valid slots can be used and makes
 * the schedule easy to modify in one place.
 */
public enum TimeSlot {
    SLOT_08_00("08:00"),
    SLOT_08_30("08:30"),
    SLOT_09_00("09:00"),
    SLOT_09_30("09:30"),
    SLOT_10_00("10:00"),
    SLOT_10_30("10:30"),
    SLOT_11_00("11:00"),
    SLOT_11_30("11:30"),
    SLOT_12_00("12:00"),
    SLOT_12_30("12:30"),
    SLOT_13_00("13:00"),
    SLOT_13_30("13:30"),
    SLOT_14_00("14:00"),
    SLOT_14_30("14:30"),
    SLOT_15_00("15:00"),
    SLOT_15_30("15:30");

    private final String label;

    TimeSlot(String label) {
        this.label = label;
    }

    /**
     * Returns the human-readable label of this time slot.
     * @return the label, e.g. "09:30"
     */
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
