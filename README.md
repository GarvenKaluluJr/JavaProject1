Step 1: Basic REST API with In-Memory Storage

- Implemented TaskController, UserController, and NotificationController with required endpoints.
- Tasks are stored in-memory using a List or Map (no database).
- Tasks support creation, soft-delete (marked as deleted), and retrieval (all and pending).
- Users can register and "login" to see their tasks and notifications.
- Notifications support GET endpoints for all and pending notifications.
- All endpoints return JSON responses with proper HTTP codes.
- Classes are separated per OOP best practices.
