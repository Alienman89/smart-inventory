# 📦 Smart Inventory - Warehouse Management System (WMS) API

System zarządzania magazynem i śledzenia ruchów magazynowych zbudowany w oparciu o **Java 21**, **Spring Boot 3** oraz **PostgreSQL**.

## 🚀 Technologie

* **Java 21**
* **Spring Boot 3.4** (Spring Data JPA, Spring Web, Validation)
* **PostgreSQL**
* **Lombok**
* **OpenAPI / Swagger UI** (dokumentacja REST API)
* **JUnit 5 & Mockito** (testy jednostkowe)
* **Docker & Docker Compose**

## 💡 Funkcjonalności

- 📦 **Zarządzanie Produktami:** Pełny CRUD, unikalne kody SKU, walidacja stanów i cen.
- 🏷️ **Kategorie:** Klasyfikacja produktów w relacji jeden-do-wielu (`@ManyToOne`).
- 🔄 **Ruchy Magazynowe:** Rejestrowanie historii przyjęć (`IN`) i wydań (`OUT`) z automatycznym przeliczaniem ilości towaru.
- 🛡️ **Globalna Obsługa Błędów:** Czytelne komunikaty błędów w formacie JSON (`@RestControllerAdvice`).
- 📑 **Interaktywna Dokumentacja:** Swagger UI dostępny pod adresem URL.

## 🛠️ Uruchomienie projektu

### Za pomocą Docker Compose (Zalecane)

Upewnij się, że masz zainstalowanego i uruchomionego w tle Dockera, a następnie wykonaj polecenie w katalogu głównym:

```bash
docker compose up --build
