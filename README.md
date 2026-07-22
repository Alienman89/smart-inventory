# 📦 Smart Inventory - Warehouse Management System (WMS) API

System zarządzania magazynem i śledzenia ruchów magazynowych zbudowany w oparciu o **Java 21**, **Spring Boot 3** oraz **PostgreSQL**.

---

## 🚀 Technologie

* **Java 21**
* **Spring Boot 3.4** (Spring Data JPA, Spring Web, Validation)
* **PostgreSQL**
* **Lombok**
* **OpenAPI / Swagger UI** (dokumentacja REST API)
* **JUnit 5 & Mockito** (testy jednostkowe)
* **Docker & Docker Compose**

---

## 💡 Funkcjonalności

* 📦 **Zarządzanie Produktami:** Pełny CRUD, unikalne kody SKU, walidacja stanów i cen.
* 🏷️ **Kategorie:** Klasyfikacja produktów w relacji jeden-do-wielu (`@ManyToOne`).
* 🔄 **Ruchy Magazynowe:** Rejestrowanie historii przyjęć (`IN`) i wydań (`OUT`) z automatycznym przeliczaniem ilości towaru.
* 🛡️ **Globalna Obsługa Błędów:** Czytelne komunikaty błędów w formacie JSON (`@RestControllerAdvice`).
* 📑 **Interaktywna Dokumentacja:** Swagger UI dostępny bezpośrednio pod adresem URL.

---

## 🛠️ Uruchomienie projektu (Docker)

### 1. Start aplikacji

Upewnij się, że masz uruchomiony program **Docker Desktop**, a następnie wykonaj w terminalu w głównym katalogu projektu polecenie:

```bash
docker compose up --build
```

### 2. Dostęp do API i Dokumentacji

Po pomyślnym uruchomieniu kontenerów aplikacja oraz interaktywna dokumentacja są od razu gotowe do testowania:

* 📑 **Swagger UI (Dokumentacja API):** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* 🔗 **Endpoint bazowy API:** [http://localhost:8080/api/products](http://localhost:8080/api/products)

---

### 🛑 Zatrzymanie aplikacji

Aby zatrzymać i wyczyścić kontenery po zakończeniu pracy, wpisz w terminalu:

```bash
docker compose down
```

---

## 🧪 Testy jednostkowe

Aby uruchomić pakiet automatycznych testów jednostkowych lokalnie, wykonaj komendę:

```bash
./mvnw test
```
