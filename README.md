# A1-XieJ — Health Service Appointment System

**Unit:** PROG2004 Object Oriented Programming
**Assessment:** Assessment 1 — Portfolio/programming (40%)
**Student:** XieJie

A Java class library that models the backbone of a health service appointment
system. It contains no user interface, menus, login, or persistence; instead,
`AssignmentOne.main` runs a scripted simulation that creates objects and
exercises their behaviour, printing the results to the console.

---

## How to run

Requires JDK 11 or later.

```bash
cd src
javac *.java
java AssignmentOne
```

---

## Class overview

| Class | Responsibility |
|---|---|
| `HealthProfessional` | Abstract base class. Holds the data common to every professional: numeric ID, name, and the fixed set of days worked each week. |
| `GeneralPractitioner` | Primary-care doctor; adds whether the doctor may prescribe medication. |
| `Specialist` | Doctor with a named specialty (e.g. Cardiology, Paediatrics). |
| `Nurse` | Nursing staff; adds whether the nurse is certified to collect blood samples. |
| `Dietitian` | Nutrition professional; adds whether they specialise in paediatric nutrition. |
| `Patient` | Holds the patient name and mobile number required for a booking. |
| `TimeSlot` | Enumeration of the clinic's fixed daily slots, 08:00–15:30 at 30-minute intervals. |
| `Appointment` | Links one patient, one professional, and one time slot. Implements `Comparable` so appointments sort earliest to latest. |
| `AppointmentManager` | Owns the collection of appointments and provides add, filter, sort, and cancel operations with double-booking protection. |
| `AssignmentOne` | Entry point. Demonstrates Parts 3 and 6 of the brief. |

---

## Design decisions

**Abstract base class, not a concrete one.** A "plain" health professional with
no specialty has no meaning in this system, so `HealthProfessional` is declared
`abstract`. That removes the possibility of an untyped professional being booked.
Because `getType()` is abstract, every subclass is forced by the compiler to
declare what kind of professional it is — the base class cannot be extended
without supplying that behaviour.

**Encapsulation with validation at the boundary.** All fields are `private` and
`final`. The constructor rejects an ID that is not digits-only, and rejects blank
patient names or mobile numbers. Because the values are set once and never
exposed for mutation, an object cannot be driven into an invalid state after
construction. `getWorkingDays()` returns a defensive copy of the array so external
code cannot alter the professional's roster.

**Two constructors on several classes.** The subclass constructors that omit the
roster delegate to a default of Monday–Friday, so callers who do not care about
the schedule are not forced to supply one. Each subclass also offers a
shorter constructor for the same reason.

**`TimeSlot` as an enum.** The clinic schedule is a fixed, closed set of values,
which is exactly what an enum models. Booking a time that is not in the set is
then a compile-time error rather than a runtime check. The enum's declaration
order matches chronological order, so `compareTo` gives correct time ordering for
free, and the whole schedule can be changed in one file without touching
`Appointment` or `AppointmentManager`.

**`Comparable` on `Appointment`.** Sorting a day's appointments is a natural
requirement, and `Comparable` lets `Collections.sort` order a whole collection
without the manager knowing anything about how appointments compare.

**Equality by ID.** `HealthProfessional.equals` compares IDs, which is the
identity the clinic actually uses. `AppointmentManager` relies on this to decide
whether an incoming booking collides with an existing one, so a duplicate
`HealthProfessional` object representing the same doctor is still recognised as
the same person.

**`ArrayList` for the collection.** Appointment counts are small and the
operations are add, iterate, and filter — sequential access dominates. An
`ArrayList` gives cheap appends and iteration, and because the ordering is
supplied by `Comparable` there is no need for a sorted structure.

**Feedback as returned messages.** Every `AppointmentManager` operation returns a
`String` beginning with `SUCCESS:` or `WARNING:`. This keeps the manager free of
console output, so it stays testable and the caller decides how to display the
result.

---

## Assumptions

- Bookings are for the current day only, so no date is stored. Ordering within a
  day is sufficient.
- Two appointments at the same slot for different professionals may appear in
  either order; `compareTo` returns 0 for them and the sort is stable.
- Cancelling by mobile number removes every appointment belonging to that
  patient, which is the more useful reading of "cancel by patient phone number".
- A professional's working days are stored and displayed but not enforced against
  a booking, since the brief does not ask for roster validation in Part 5.

---

## The four pillars of OOP in this code

| Pillar | Where |
|---|---|
| Encapsulation | Private final fields plus constructor validation in `HealthProfessional`, `Patient`, `Appointment`; defensive copy in `getWorkingDays()`. |
| Inheritance | `GeneralPractitioner`, `Specialist`, `Nurse`, `Dietitian` all `extend HealthProfessional` and reuse its fields, constructor, and `toString`. |
| Polymorphism | `List<HealthProfessional>` in `AssignmentOne` holds all nine professionals; the loop calls `toString()` and each subclass contributes its own output. Dynamic dispatch on `getType()` is what makes that possible. |
| Abstraction | `HealthProfessional` is abstract and declares abstract `getType()`; callers work through the base type and never need to know the concrete class. |

---

## GenAI declaration

See the submission comments for the formal declaration.
