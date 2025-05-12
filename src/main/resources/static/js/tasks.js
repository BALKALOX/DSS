const token = localStorage.getItem('token'); // беремо токен з localStorage

fetch('http://localhost:8080/api/v1/auth/user', {
    method: 'GET',
    headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
    }
})
    .then(response => response.json())
    .then(user => {
        console.log(user);
        const isAnalyst = user.roles.some(role => role.name === "ANALYST");

        if (isAnalyst) {
            // Додаємо кнопку "Створити задачу"
            const createButton = document.createElement('a');
            createButton.href = '/tasks/new';
            createButton.className = "inline-block bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded mb-8";
            createButton.textContent = "Створити задачу";

            // Додаємо кнопку перед блоком з тасками
            const container = document.querySelector('.max-w-7xl');
            container.insertBefore(createButton, container.children[7]);

            // ТІЛЬКИ ДЛЯ АНАЛІТИКА додаємо кнопки редагувати/видалити
            document.querySelectorAll('.task-card').forEach(card => {
                const taskId = card.getAttribute('data-task-id');

                const editButton = document.createElement('a');
                editButton.href = `/tasks/${taskId}/edit`;
                editButton.className = "bg-yellow-500 hover:bg-yellow-600 text-white font-semibold py-1 px-3 rounded ml-2";
                editButton.textContent = 'Редагувати';

                const deleteButton = document.createElement('button');
                deleteButton.className = "bg-red-500 hover:bg-red-600 text-white font-semibold py-1 px-3 rounded ml-2";
                deleteButton.textContent = 'Видалити';
                deleteButton.onclick = () => deleteTask(taskId, card);

                card.querySelector('.buttons-container').appendChild(editButton);
                card.querySelector('.buttons-container').appendChild(deleteButton);
            });
        }
        // Інакше — нічого не додаємо!
    })
    .catch(error => {
        console.error('Помилка отримання користувача:', error);
    });

// Функція для видалення таски
function deleteTask(taskId, card) {
    if (confirm('Ви точно хочете видалити цю задачу?')) {
        fetch(`http://localhost:8080/api/v1/tasks/${taskId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                card.remove();
            } else {
                alert('Помилка при видаленні задачі!');
            }
        }).catch(error => {
            console.error('Помилка:', error);
        });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.toggle-parameters').forEach(button => {
        button.addEventListener('click', () => {
            const paramList = button.nextElementSibling;
            if (paramList.classList.contains('hidden')) {
                paramList.classList.remove('hidden');
                button.textContent = 'Сховати параметри';
            } else {
                paramList.classList.add('hidden');
                button.textContent = 'Показати параметри';
            }
        });
    });
});