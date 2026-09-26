#Security-gateway-prototype
A spring boot API gateway component built as a part of a larger academic project "A Provenence-aware Security Gateway for 
detecting prompt injection , Tool misuse and PII exfiltration in Multi Agent LLMs".
This repository contains the "gateway layer" of that system - the component responsible for routing requests to a detection
service to a detection service , enforcing basic auth , validating input and persisting an auditable record of every decision made.
## What this component does
- Receives content submitted for security evaluation
- Routes it to an external ML detection service over REST
- **Falls back to a simple rule-based check** if the ML service is unreachable,
  so the gateway degrades gracefully instead of failing
- Persists every decision (allowed/blocked, source, timestamp) to PostgreSQL
  via Spring Data JPA, creating an audit trail
- Validates incoming requests and rejects malformed/empty input
- Enforces basic API-key authentication on all gateway endpoints
- Returns clean, structured JSON error responses instead of raw stack traces
## Tech stack
- Java, Spring Boot
- Spring Web (REST API)
- Spring Data JPA + PostgreSQL
- Maven
## Architecture context
This gateway is one piece of a larger polyglot microservices system. The full
architecture also includes:
- Python/LangGraph AI agents generating traffic (built by teammates)
- A Python ML detection service (rule-based baseline + planned fine-tuned
  classifier + Presidio for PII detection) that this gateway calls
- This gateway sits between them, enforcing auth, routing, and logging
This repo covers only the Spring Boot gateway piece.
**Body:**
json
{ "content": "some text to evaluate" }
**Response:**
json
{ "decision": "ALLOW", "source": "ml-service" }
source is "ml-service" if the ML detection service responded, or "fallback-mock" if it was unreachable and the
rule-based fallback ran instead.
