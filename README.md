# memory_project

Aplikacja „Gra Memory” jest projektem gry typu Klient – Serwer. W
projekcie zastosowaliśmy wielowątkowość. Dane gromadzone są w
bazie danych. Za interakcje z użytkownikiem odpowiada interfejs
graficzny napisany w JavaFX.
Gra daje możliwość rozgrywki wielu gier dzięki zastosowaniu wątków.
Komunikacja serwera z klientami jest możliwa dzięki zastosowaniu
Socket’ów. Przepływ informacji pomiędzy klientem a serwerem
obsługiwany jest za pomocą BufferedReader’a oraz PrintWriter’a.


# Funkcjonalności:
* Równoczesna rozgrywka wielu użytkowników
* Zapis stanu rozgrywki do bazy
* Licznik punktów

* Doliczenie kary za zbyt długi czas wyboru
* Oczekiwanie na gracza
* Wskazane zwycięzcy oraz przegranego
* Wskazanie obecnego ruchu
