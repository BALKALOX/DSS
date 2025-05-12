const token = localStorage.getItem('token');
const decisionId = document.getElementById('decisionId').value;

// Модальне вікно
const modal = document.getElementById('confirm-modal');
const modalTitle = document.getElementById('modal-title');
const modalMessage = document.getElementById('modal-message');
const modalConfirm = document.getElementById('modal-confirm');
const modalCancel = document.getElementById('modal-cancel');

// Показати модальне вікно
function showModal(title, message, confirmAction) {
    modalTitle.innerText = title;
    modalMessage.innerText = message;
    modal.classList.remove('hidden');

    modalConfirm.onclick = () => {
        confirmAction();
        modal.classList.add('hidden');
    };

    modalCancel.onclick = () => {
        modal.classList.add('hidden');
    };
}

// Додати новий сценарій
function addScenario() {
    const container = document.getElementById('scenarios-container');
    const scenarioBlock = document.createElement('div');
    scenarioBlock.className = 'bg-gray-50 p-4 rounded-lg shadow-sm flex flex-wrap items-center gap-3 scenario-block mt-2';

    scenarioBlock.innerHTML = `
        <input type="text" class="scenario-title w-1/3 border rounded px-2 py-1" placeholder="Назва сценарію">
        <input type="text" class="scenario-description w-1/3 border rounded px-2 py-1" placeholder="Опис">
        <input type="number" step="0.01" min="0" max="1" class="scenario-possibility w-28 border rounded px-2 py-1" placeholder="Ймовірність">
        <button type="button" class="remove-scenario bg-red-500 hover:bg-red-600 text-white py-1 px-3 rounded">✕</button>
    `;

    container.appendChild(scenarioBlock);
    attachRemoveScenarioListeners();
}

// Прив'язати обробник видалення сценарію
function attachRemoveScenarioListeners() {
    document.querySelectorAll('.remove-scenario').forEach(button => {
        button.onclick = function () {
            this.closest('.scenario-block').remove();
        };
    });
}

// Зберегти рішення
function saveDecision() {
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;
    const decisionCategory = document.getElementById('decisionCategory').value;

    const parameters = Array.from(document.querySelectorAll('.parameter-block')).map(block => ({
        taskParameterId: parseInt(block.querySelector('.task-parameter-id').value),
        value: parseFloat(block.querySelector('.param-value').value),
        comment: block.querySelector('.param-comment').value
    }));

    const scenarios = Array.from(document.querySelectorAll('.scenario-block')).map(block => ({
        title: block.querySelector('.scenario-title').value.trim(),
        description: block.querySelector('.scenario-description').value.trim(),
        possibility: parseFloat(block.querySelector('.scenario-possibility').value)
    })).filter(scenario => scenario.title && scenario.description && !isNaN(scenario.possibility));

    const decisionData = {
        title,
        description,
        decisionCategory,
        decisionStatus: "PROPOSED",
        decisionParameterDtoList: parameters,
        scenariosDto: scenarios
    };

    console.log(JSON.stringify(decisionData, null, 2));

    fetch(`http://localhost:8080/api/v1/decisions/${decisionId}`, {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(decisionData)
    })
        .then(response => {
            if (response.ok) {
                window.history.back();
            } else {
                alert('Помилка при оновленні рішення!');
            }
        })
        .catch(error => console.error('Помилка при оновленні:', error));
}

// Видалити рішення
function deleteDecision() {
    fetch(`http://localhost:8080/api/v1/decisions/${decisionId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                window.location.href = '/tasks';
            } else {
                alert('Помилка при видаленні рішення!');
            }
        })
        .catch(error => console.error('Помилка при видаленні:', error));
}

// Обробники кнопок
document.getElementById('add-scenario').addEventListener('click', addScenario);
document.getElementById('save-decision').addEventListener('click', () => {
    showModal("Підтвердити збереження", "Ви впевнені, що хочете зберегти зміни?", saveDecision);
});
document.getElementById('delete-decision').addEventListener('click', () => {
    showModal("Підтвердити видалення", "Ви точно хочете видалити рішення?", deleteDecision);
});

// Підключити обробники для існуючих сценаріїв при завантаженні
attachRemoveScenarioListeners();
