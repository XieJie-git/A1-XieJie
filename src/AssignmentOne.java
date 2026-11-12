import java.util.List;

/**
 * The main entry point for the PROG2004 A1 appointment system.
 * Demonstrates:
 *  - Part 3: creating and displaying health professional objects
 *  - Part 6: end-to-end operation of the appointment system
 */
public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("  HEALTH SERVICE APPOINTMENT SYSTEM (A1)");
        System.out.println("============================================");

        // ===== PART 3: Working with health professional objects =====
        System.out.println("\n--- PART 3: Health Professionals ---");

        // Create a mix of health professional objects
        GeneralPractitioner gp1 = new GeneralPractitioner("1001",
                "Dr. Sarah Lee",
                new String[]{"Monday", "Wednesday", "Friday"}, true);
        GeneralPractitioner gp2 = new GeneralPractitioner("1002",
                "Dr. James Wong",
                new String[]{"Tuesday", "Thursday"}, true);
        GeneralPractitioner gp3 = new GeneralPractitioner("1003",
                "Dr. Emily Chen");

        Specialist sp1 = new Specialist("2001",
                "Dr. Michael Torres",
                new String[]{"Monday", "Tuesday"}, "Cardiology");
        Specialist sp2 = new Specialist("2002",
                "Dr. Lisa Park", "Paediatrics");

        Nurse nurse1 = new Nurse("3001",
                "Nurse Amy Wilson",
                new String[]{"Monday", "Wednesday", "Friday"}, true);
        Nurse nurse2 = new Nurse("3002",
                "Nurse Robert Brown", false);

        Dietitian diet1 = new Dietitian("4001",
                "Dietitian Karen Zhang",
                new String[]{"Tuesday", "Thursday"}, true);
        Dietitian diet2 = new Dietitian("4002",
                "Dietitian Tom Miller", false);

        // Store all professionals in a single common-type list (polymorphism)
        List<HealthProfessional> professionals = List.of(
                gp1, gp2, gp3, sp1, sp2, nurse1, nurse2, diet1, diet2
        );

        for (HealthProfessional p : professionals) {
            System.out.println(p);
        }

        // ===== PART 6: Working with appointments =====
        System.out.println("\n--- PART 6: Appointment System Demo ---");

        AppointmentManager manager = new AppointmentManager();

        // Create patients
        Patient pat1 = new Patient("Alice Green", "0412345601");
        Patient pat2 = new Patient("Bob White", "0412345602");
        Patient pat3 = new Patient("Charlie Black", "0412345603");
        Patient pat4 = new Patient("Diana Blue", "0412345604");
        Patient pat5 = new Patient("Eve Red", "0412345601");

        // Add several appointments across different professional types
        System.out.println("\n[Adding appointments]");
        System.out.println(manager.addAppointment(
                new Appointment(pat1, gp1, TimeSlot.SLOT_09_00)));
        System.out.println(manager.addAppointment(
                new Appointment(pat2, sp1, TimeSlot.SLOT_10_00)));
        System.out.println(manager.addAppointment(
                new Appointment(pat3, nurse1, TimeSlot.SLOT_11_30)));
        System.out.println(manager.addAppointment(
                new Appointment(pat4, diet1, TimeSlot.SLOT_14_00)));
        System.out.println(manager.addAppointment(
                new Appointment(pat5, gp2, TimeSlot.SLOT_15_00)));

        // Attempt a double-booking (gp1 at 09:00 is already taken)
        System.out.println(manager.addAppointment(
                new Appointment(pat2, gp1, TimeSlot.SLOT_09_00)));

        // Display all appointments sorted by time
        System.out.println("\n[All appointments sorted by time]");
        for (Appointment a : manager.getAllSorted()) {
            System.out.println(a);
        }

        // Filter by professional ID
        System.out.println("\n[Filter: appointments with professional ID 1001]");
        for (Appointment a : manager.filterByProfessionalId("1001")) {
            System.out.println(a);
        }

        // Filter by patient mobile
        System.out.println("\n[Filter: appointments for mobile 0412345601]");
        for (Appointment a : manager.filterByPatientMobile("0412345601")) {
            System.out.println(a);
        }

        // Cancel an existing appointment (gp1 at 09:00)
        System.out.println("\n[Cancelling appointment]");
        System.out.println(manager.cancelByProfessionalAndTime(
                "1001", TimeSlot.SLOT_09_00));

        // Attempt to cancel a non-existent appointment
        System.out.println(manager.cancelByProfessionalAndTime(
                "9999", TimeSlot.SLOT_08_00));

        // Cancel by patient mobile
        System.out.println(manager.cancelByPatientMobile("0412345604"));

        // Attempt to cancel a non-existent patient mobile
        System.out.println(manager.cancelByPatientMobile("0499999999"));

        // Display remaining appointments after cancellation
        System.out.println("\n[Remaining appointments after cancellations]");
        for (Appointment a : manager.getAllSorted()) {
            System.out.println(a);
        }

        System.out.println("\nTotal appointments on record: "
                + manager.count());
        System.out.println("\n--- Demo complete ---");
    }
}
