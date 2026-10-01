# Min Journal – Backend

Backend för "My Journal", en app där användare kan skriva anteckningar taggade med humör och se statistik över dem. Byggd med Quarkus och H2.

## Teknikstack

- **Java 21**
- **Quarkus 3.39.4**
- **H2** (in-memory-databas)
- **Hibernate ORM + Panache**
- **Basic Auth** via `quarkus-security-jpa`, med BCrypt-hashade lösenord

## Kom igång

### Krav

- Java 21 (JDK)
- Maven (medföljer via `./mvnw`, ingen separat installation behövs)

### Starta servern

```bash
./mvnw quarkus:dev
```

Servern startar på `http://localhost:8080`. H2 är in-memory och byggs upp från grunden varje gång servern startar, så ingen separat databasinstallation krävs.

### Swagger UI (API-dokumentation)

När servern kör, öppna `http://localhost:8080/q/swagger-ui` för att se och testa alla endpoints direkt i webbläsaren.

## API – översikt

| Metod | Endpoint | Beskrivning | Kräver inloggning |
|---|---|---|---|
| POST | `/api/auth/register` | Skapa nytt konto | Nej |
| GET | `/api/posts` | Hämta inloggad användares inlägg | Ja |
| POST | `/api/posts` | Skapa nytt inlägg | Ja |
| GET | `/api/posts/statistics?startDate=...&endDate=...` | Statistik för vald period | Ja |

Autentisering sker med **Basic Auth**: `Authorization: Basic <base64(username:password)>` på varje skyddat anrop.

## Datamodell

```
User
├── id (Long) PK
├── username (String, unikt)
├── password (String, BCrypt-hashat)
└── role (String)

Post
├── id (Long)
├── note (String)
├── mood (enum: HAPPY, SAD, MOTIVATED, ANGRY, SUSPICIOUS)
├── createdAt (LocalDateTime, satt automatiskt av servern)
└── user (relation till User, @ManyToOne) FK
```

## Arkitektur

Projektet är uppdelat i lager enligt Quarkus-konventioner:

```
resource/   REST-endpoints (JAX-RS), tar emot requests och skickar svar
service/    "BusinessLogic" (t.ex. statistikberäkning, registrering)
repository/ Dataåtkomst mot databasen (Panache)
entity/     JPA-entiteter, motsvarar databastabeller
dto/        Data Transfer Objects, formen på det som skickas till/från frontend
```

## Lösta problem under utvecklingen

**Dubbla användarnamn.** Databasen tillät från början flera konton med samma användarnamn, vilket gjorde att om man skapat två användare med samma username gick det inte att logga in på någon av dem. Löst genom att lägga till `@Column(unique = true)` på `username`-fältet, samt en kontroll i `AuthService` som kastar en `WebApplicationException` med statuskod `409 Conflict` om namnet redan finns, innan något sparas.

