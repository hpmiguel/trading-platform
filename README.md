# Trading Platform
Example Trading Platform with Hexagonal Architecture.

How to Run:

1. Run Postgres container. 
> docker run --name pg_local -p 5432:5432 -e POSTGRES_USER=test -e POSTGRES_PASSWORD=test -e POSTGRES_DB=tradingdb -d postgres

2. Run "TradingSpringBootApplication"