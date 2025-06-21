# 🧠 Decision Support System (DSS)

[![Java](https://img.shields.io/badge/Java-17+-red.svg?style=flat&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=flat&logo=spring)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?style=flat&logo=mysql)](https://www.mysql.com/)

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
![image](https://github.com/user-attachments/assets/77a76851-4084-4356-92aa-894f83a67cb7)

![image](https://github.com/user-attachments/assets/7e781688-441f-4738-9541-dd353085a5cb)

![image](https://github.com/user-attachments/assets/680eb74a-c3bf-4d44-afbb-4c75eff7e22c)

![image](https://github.com/user-attachments/assets/932d68da-5700-4316-b2ed-63896eaf7995)

![image](https://github.com/user-attachments/assets/8099dc1a-f4b3-4aea-90fb-1497b56d686a)

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

