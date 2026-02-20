# OBRS Backend Deployment (Render + Docker)

## 1) Push this repo to GitHub
Render will build from your GitHub repo.

## 2) Create a Web Service in Render
1. Open Render dashboard.
2. Click `New +` -> `Web Service`.
3. Select your GitHub repo.
4. Render should detect `render.yaml` automatically.

## 3) Set required environment variables
In Render service settings, set:

- `SPRING_PROFILES_ACTIVE=prod`
- `DB_URL=jdbc:mysql://<host>:3306/<db_name>?useSSL=true&requireSSL=true`
- `DB_USERNAME=<db_user>`
- `DB_PASSWORD=<db_password>`
- `JWT_KEY=<long-random-secret>`
- `cloudinary_cloudname=<cloudinary_name>`
- `cloudinary_apikey=<cloudinary_api_key>`
- `cloudinary_apisk=<cloudinary_api_secret>`

Notes:
- `PORT` is set by Render and already supported by `server.port=${PORT:8000}`.
- Use a cloud-hosted MySQL DB (publicly reachable by Render).

## 4) Deploy
1. Click `Manual Deploy` -> `Deploy latest commit`.
2. Wait for build + start logs.

## 5) Verify
- Health URL: `/books/getbooks`
- Example:
  - `https://<your-service>.onrender.com/books/getbooks`

## 6) Resume-ready links
Add these in your resume:
- Live API base URL
- Swagger URL (if enabled in prod)
- GitHub repository URL

## Local Docker test (optional)
Build:

```bash
docker build -t obrs-backend .
```

Run:

```bash
docker run --rm -p 8000:8000 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL="jdbc:mysql://<host>:3306/<db_name>" \
  -e DB_USERNAME="<db_user>" \
  -e DB_PASSWORD="<db_password>" \
  -e JWT_KEY="<jwt_secret>" \
  -e cloudinary_cloudname="<cloud_name>" \
  -e cloudinary_apikey="<api_key>" \
  -e cloudinary_apisk="<api_secret>" \
  obrs-backend
```
