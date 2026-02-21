<h1 align="center">📚 Online Book Reselling System – Backend</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange" />
  <img src="https://img.shields.io/badge/SpringBoot-Backend-brightgreen" />
  <img src="https://img.shields.io/badge/Security-JWT-blue" />
  <img src="https://img.shields.io/badge/Database-PostgreSQL-blue" />
  <img src="https://img.shields.io/badge/Database-MySQL-lightblue" />
  <img src="https://img.shields.io/badge/Cloud-Cloudinary-purple" />
  <img src="https://img.shields.io/badge/Deployment-Render-black" />
</p>

<p align="center">
  A <b>Spring Boot RESTful backend</b> that allows users to buy and sell books online
  with secure authentication, role-based access control, image upload support, and cloud deployment.
</p>

<p align="center">
  🔗 <b>Live API:</b><br/>
  <a href="https://onlinebookresellingsystem-fc.onrender.com" target="_blank">
    https://onlinebookresellingsystem-fc.onrender.com
  </a>
</p>

<hr/>

<h2>🚀 Features</h2>

<ul>
  <li>✅ JWT-based Authentication (60 min expiry)</li>
  <li>✅ Role-Based Authorization (USER / ADMIN)</li>
  <li>✅ Secure RESTful APIs</li>
  <li>✅ Book Image Upload (Cloudinary Integration)</li>
  <li>✅ Pagination & Sorting</li>
  <li>✅ Structured Error Handling</li>
  <li>✅ Environment-Based Configuration (dev / prod)</li>
  <li>✅ Logging with Logback</li>
  <li>✅ Production Deployment on Render</li>
</ul>

<hr/>

<h2>🏗️ Architecture</h2>

<pre>
Client → Controller → Service → Repository → Database
                ↓
           Spring Security (JWT)
                ↓
           Cloudinary (Image Storage)
</pre>

<ul>
  <li>Stateless REST architecture</li>
  <li>Controller–Service–Repository pattern</li>
  <li>BCrypt password hashing</li>
  <li>Profile-based configuration</li>
</ul>

<hr/>

<h2>🛠️ Tech Stack</h2>

<h3>🔹 Backend</h3>
<ul>
  <li>Java 17</li>
  <li>Spring Boot</li>
  <li>Spring Security (JWT)</li>
  <li>JPA / Hibernate</li>
</ul>

<h3>🔹 Database</h3>
<ul>
  <li>MySQL (Local Development)</li>
  <li>PostgreSQL (Production)</li>
</ul>

<h3>🔹 Cloud & Deployment</h3>
<ul>
  <li>Render (Cloud Hosting)</li>
  <li>Cloudinary (Image Storage)</li>
</ul>

<h3>🔹 Tools</h3>
<ul>
  <li>Maven</li>
  <li>Git & GitHub</li>
  <li>Postman</li>
</ul>

<hr/>

<h2>🔐 Authentication & Authorization</h2>

<h3>🔑 Login</h3>
<pre>
POST /auth/login
</pre>
<p>Returns JWT token.</p>

<h3>👤 Roles</h3>
<ul>
  <li><b>USER</b> → Add / Update Books</li>
  <li><b>ADMIN</b> → Approve Books & Manage Users</li>
</ul>

<h3>📌 Protected Routes Example</h3>

<pre>
POST   /books/addbook        (USER)
GET    /admin/getbooks       (ADMIN)
PATCH  /admin/update_book/{id}
</pre>

<p><b>Authorization Header:</b></p>

<pre>
Authorization: Bearer &lt;jwt_token&gt;
</pre>

<hr/>

<h2>📦 API Endpoints</h2>

<h3>🔹 Auth</h3>

<table>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Access</th>
</tr>
<tr>
<td>POST</td>
<td>/auth/login</td>
<td>Public</td>
</tr>
</table>

<h3>🔹 Users</h3>

<table>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Access</th>
</tr>
<tr>
<td>POST</td>
<td>/api/register</td>
<td>Public</td>
</tr>
<tr>
<td>PATCH</td>
<td>/api/update_user/me</td>
<td>USER</td>
</tr>
<tr>
<td>GET</td>
<td>/api/get_users</td>
<td>ADMIN</td>
</tr>
</table>

<h3>🔹 Books</h3>

<table>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Access</th>
</tr>
<tr>
<td>POST</td>
<td>/books/addbook</td>
<td>USER</td>
</tr>
<tr>
<td>GET</td>
<td>/books/getbooks</td>
<td>Public</td>
</tr>
<tr>
<td>PATCH</td>
<td>/books/updatebook</td>
<td>Owner</td>
</tr>
</table>

<hr/>

<h2>🗄️ Data Model</h2>

<h3>👤 User</h3>
<ul>
  <li>user_id (PK)</li>
  <li>userName</li>
  <li>email (unique)</li>
  <li>password (BCrypt)</li>
  <li>roles (ADMIN / USER)</li>
</ul>

<h3>📘 Book</h3>
<ul>
  <li>bookId (PK)</li>
  <li>bookName</li>
  <li>authorName</li>
  <li>description</li>
  <li>price</li>
  <li>imgUrl</li>
  <li>status (PENDING / ACTIVE / REJECTED)</li>
  <li>user (Owner)</li>
</ul>

<p><b>Book Approval Flow:</b></p>

<pre>
USER adds book → status = PENDING
ADMIN approves → status = ACTIVE
</pre>

<hr/>

<h2>⚙️ Environment Configuration</h2>

<pre>
SPRING_PROFILES_ACTIVE=prod
PORT=10000
DB_URL=
DB_USERNAME=
DB_PASSWORD=
JWT_KEY=
cloudinary_cloudname=
cloudinary_apikey=
cloudinary_apisk=
</pre>

<hr/>

<h2>▶️ Run Locally</h2>

<pre>
git clone &lt;your-repo-url&gt;
cd OnlineBookResellingSystem
./mvnw spring-boot:run
</pre>

<p>App runs at:</p>

<pre>
http://localhost:8000
</pre>

<hr/>

<h2>🧪 Testing</h2>

<ul>
  <li>API tested using Postman</li>
  <li>Authentication & Role access verified</li>
  <li>Image upload validation tested</li>
</ul>

<hr/>

<h2>🔮 Future Improvements</h2>

<ul>
  <li>Refresh Tokens</li>
  <li>Rate Limiting</li>
  <li>Flyway / Liquibase Migration</li>
  <li>Unit & Integration Tests</li>
  <li>Caching</li>
  <li>Metrics & Monitoring</li>
</ul>

<hr/>

<h2>👨‍💻 Creator</h2>

<p>
  <b>Sai Ganesh</b><br/>
  Java FullStack Developer<br/><br/>
  <a href="https://www.linkedin.com/in/saiganesh17072003" target="_blank">
    LinkedIn Profile
  </a>
</p>

<hr/>

<p align="center">
  ⭐ If you like this project, consider giving it a star!
</p>
