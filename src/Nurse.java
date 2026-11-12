/**
 * Represents a Nurse — a health professional who provides patient care,
 * takes vital signs, and may collect blood samples.
 * <p>
 * In addition to the common health professional attributes, a Nurse has
 * a flag indicating whether they hold a blood-collection certificate.
 */
public class Nurse extends HealthProfessional {

    private final boolean hasBloodCollectionCert;

    /**
     * Constructs a Nurse with all attributes.
     *
     * @param id                     the numeric ID (digits only)
     * @param name                   the full name
     * @param workingDays           the working days of the week
     * @param hasBloodCollectionCert true if the nurse can collect blood
     */
    public Nurse(String id, String name, String[] workingDays,
                 boolean hasBloodCollectionCert) {
        super(id, name, workingDays);
        this.hasBloodCollectionCert = hasBloodCollectionCert;
    }

    /**
     * Constructs a Nurse with a default Monday–Friday schedule.
     *
     * @param id                     the numeric ID (digits only)
     * @param name                   the full name
     * @param hasBloodCollectionCert true if the nurse can collect blood
     */
    public Nurse(String id, String name, boolean hasBloodCollectionCert) {
        super(id, name);
        this.hasBloodCollectionCert = hasBloodCollectionCert;
    }

    /**
     * Returns whether this nurse can collect blood samples.
     *
     * @return true if blood collection is certified
     */
    public boolean canCollectBlood() {
        return hasBloodCollectionCert;
    }

    @Override
    public String getType() {
        return "Nurse";
    }

    @Override
    public String toString() {
        return super.toString() + " (Blood Collection Certified: "
                + hasBloodCollectionCert + ")";
    }
}
