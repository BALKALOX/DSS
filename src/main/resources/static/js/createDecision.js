const token = localStorage.getItem('token');
const urlParams = new URLSearchParams(window.location.search);
const taskId = urlParams.get('taskId');

// Додаємо сценарій
document.getElementById('add-scenario').addEventListener('click', () => {
    const container = document.getElementById('scenarios-container');
    const scenarioBlock = document.createElement('div');
    scenarioBlock.className = 'bg-gray-50 p-4 rounded-lg shadow-sm flex flex-wrap items-center gap-3 scenario-block';

    scenarioBlock.innerHTML = `
        <input type="text" placeholder="Назва сценарію" class="scenario-title flex-1 border rounded px-2 py-1" />
        <input type="text" placeholder="Опис сценарію" class="scenario-description flex-1 border rounded px-2 py-1" />
        <input type="number" step="0.01" min="0" max="1" placeholder="Ймовірність (0-1)" class="scenario-possibility w-32 border rounded px-2 py-1" />
        <button type="button" class="remove-scenario bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded">X</button>
    `;

    // Видалити сценарій
    scenarioBlock.querySelector('.remove-scenario').addEventListener('click', () => {
        scenarioBlock.remove();
    });

    container.appendChild(scenarioBlock);
});

// Сабміт форми
document.getElementById('create-decision-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const description = document.getElementById('description').value;
    const title = document.getElementById('title').value;
    const decisionCategory = document.getElementById('task-category').value;

    const parameters = [];
    document.querySelectorAll('.parameter-block').forEach(block => {
        parameters.push({
            taskParameterId: parseInt(block.querySelector('.task-parameter-id').value),
            value: parseFloat(block.querySelector('.param-value').value),
            comment: block.querySelector('.param-comment').value
        });
    });

    const scenarios = [];
    document.querySelectorAll('.scenario-block').forEach(block => {
        scenarios.push({
            title: block.querySelector('.scenario-title').value,
            description: block.querySelector('.scenario-description').value,
            possibility: parseFloat(block.querySelector('.scenario-possibility').value)
        });
    });

    const decisionData = {
        decisionCategory: decisionCategory,
        title: title,
        description: description,
        decisionStatus: "PROPOSED",
        taskId: parseInt(taskId),
        decisionParameterDtoList: parameters,
        scenariosDto: scenarios
    };

    fetch('http://localhost:8080/api/v1/decisions', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(decisionData)
    })
        .then(response => {
            if (response.ok) {
                alert('Рішення успішно створено!');
                window.location.href = '/tasks/' + taskId;
            } else {
                alert('Помилка створення рішення!');
            }
        })
        .catch(error => console.error('Помилка:', error));
});
