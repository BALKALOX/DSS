# 🧠 Decision Support System (DSS)

[![Java](https://img.shields.io/badge/Java-17+-red.svg?style=flat&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=flat&logo=spring)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?style=flat&logo=mysql)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

A web-based **Decision Support System** (DSS) for expert-driven decision making in multi-criteria environments. It supports monitoring and analysis across ecological, economic, energy, and legal domains, providing a platform for task creation, evaluation, and optimal decision selection using AHP, TOPSIS, and ELECTRE methods.

---

## 🌟 Features

- 🧮 Multi-criteria decision analysis (MCDA) with AHP, TOPSIS, ELECTRE
- 👨‍🔬 Expert evaluation of alternatives and decision scoring
- 🔐 Role-based user system (Admin, Analyst, Expert)
- 🧠 Automatic method recommendation based on task parameters
- 🌐 REST API support for integration and expansion
- 🎨 Responsive UI using Thymeleaf, Tailwind CSS, and vanilla JS

---

## 🛠 Technologies Used

| Layer        | Technology                               |
|--------------|------------------------------------------|
| Backend      | Java, Spring Boot, Spring Security       |
| Frontend     | Thymeleaf, Tailwind CSS, JavaScript      |
| Database     | MySQL + Spring Data JPA                  |
| Build Tool   | Maven                                    |
| Versioning   | Git                                      |

---

## 🚀 Getting Started

1. **Clone the project**:
   ```bash
   git clone https://github.com/your-username/dss.git
   cd dss
   ```

2. **Create MySQL database**:
   ```sql
   CREATE DATABASE dss;
   ```

3. **Configure DB connection**  
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/dss
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   ```

4. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Open in browser**:  
   [http://localhost:8080](http://localhost:8080)

---

## 📸 Screenshots

_Coming soon..._

---

## 📚 Future Enhancements

- Visualization of MCDA results
- Export reports (PDF/Excel)
- Notifications for experts
- Integration with external datasets and APIs

---

## 👤 Author

**Anton Misiura**  
Full-stack Java developer, passionate about intelligent systems and expert decision support.

---

