# ArsenalSync

**Short pitch:** ArsenalSync is an MVP Android app that pairs with an ESP32-equipped firearm module to prevent misuse, enable secure remote locking/unlocking, and track the device’s location — helping keep firearms out of the wrong hands.

---

## Key features (MVP — short & crisp)

* **Secure user sign-in** via Supabase (email/password).
* **Pair & control** the gun module over **Bluetooth** or **Wi-Fi** to lock/unlock remotely.
* **Geo-tracking**: reports gun location to the platform.
* **Auto-lock timer**: if unlocked for *X* minutes, the gun auto-locks.
* **Geofencing**: gun only remains unlocked inside user-defined “safe areas”; leaving the area auto-locks.
* **Verification flow**: users submit gun details for verification before full access.

---

## How it works (high level)

1. User registers / logs in via Supabase email/password.
2. User submits gun details; system verifies and stores verification status.
3. App connects to the gun’s ESP32 module using **Bluetooth (local)** or **Wi-Fi (local/internet)**.
4. After verification, user can lock/unlock remotely. Locking state and location updates are persisted to Supabase.
5. App enforces time policy and geofence rules locally and via server-side checks where applicable.

---

## Tech stack & architecture

* **Android (Kotlin)** — App platform
* **UI:** Jetpack Compose
* **Architecture:** MVVM + Clean Code / Clean Architecture principles
* **Backend / DB:** Supabase (authentication + database)
* **Device:** ESP32 on the firearm (Bluetooth & Wi-Fi)

---

## Important notes — security & ethics

* This project targets **safety-first** use cases: preventing theft, accidental discharge by minors, and unauthorized access.
* **Legal compliance:** firearm laws vary by jurisdiction. Ensure installations, usage, and distribution comply with local, state, and national laws.
* **Do not use** the app to bypass safety mechanisms or regulations. Always follow manufacturer guidance and applicable laws.
* The MVP is **not** a substitute for physical safety practices (safe storage, trigger locks, training).

---

## MVP limitations

* Single-gun support per account (multi-gun support planned).
* Only Supabase email/password auth implemented (no social logins or 2FA yet).
* No installation/build instructions included (MVP repo-focused).
* No external contributor workflow or open license provided in this stage.

---

## Future roadmap (coming soon)

* Multi-gun accounts & device management.
* Two-factor authentication and stronger identity verification.
* Admin dashboard for verification & audit logs.
* Push notifications / alerts for geofence breaches or theft.
* Audit/forensics mode (time-stamped events, tamper alerts).
* Expand supported connectivity options and fail-safe behaviors.

---

## Developer notes (implementation hints)

* Keep core device logic in the domain layer (Clean Architecture) so rules (auto-lock, geofence) run reliably even with intermittent connectivity.
* Use Jetpack libraries (ViewModel, StateFlow/LiveData) for reactive UI.
* Securely store minimal device pairing credentials (use Android Keystore where needed).
* For Bluetooth/Wi-Fi comms, define a concise command set and handshake protocol on the ESP32 side to prevent accidental commands.

---

## Testing & verification (MVP)

* Simulate ESP32 responses during early dev using a mock endpoint / test harness.
* Run geofence/time policy tests across edge cases (connectivity loss, app backgrounded, device reboot).
* Log events with timestamps to Supabase for later auditing.

---