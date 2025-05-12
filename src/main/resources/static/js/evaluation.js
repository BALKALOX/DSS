let selectedDecisionId = null;

function openRateModal(decisionId) {
    selectedDecisionId = decisionId;
    const modal = document.getElementById('rate-modal');
    modal.classList.remove('hidden');
    modal.classList.add('flex');

    document.getElementById('rate-score').value = '';
    document.getElementById('rate-comment').value = '';

    fetch(`/api/v1/decisions/${decisionId}/evaluation`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.status === 204) return null;
            return response.json();
        })
        .then(data => {
            if (data) {
                document.getElementById('rate-score').value = data.score;
                document.getElementById('rate-comment').value = data.comment || '';
            }
        })
        .catch(error => {
            console.error('Помилка при завантаженні попередньої оцінки:', error);
        });
}

function closeRateModal() {
    selectedDecisionId = null;
    const modal = document.getElementById('rate-modal');
    modal.classList.add('hidden');
    modal.classList.remove('flex');
}

function submitRate() {
    const score = parseFloat(document.getElementById('rate-score').value);
    const comment = document.getElementById('rate-comment').value;

    if (!selectedDecisionId) {
        alert('ID рішення не визначено');
        return;
    }

    if (isNaN(score) || score < 0 || score > 10) {
        alert('Будь ласка, введіть оцінку від 0 до 10.');
        return;
    }

    fetch(`/api/v1/decisions/${selectedDecisionId}/rate`, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ score, comment, decisionId: selectedDecisionId })
    })
        .then(response => {
            if (!response.ok) throw new Error('Помилка при оцінюванні');
            return response.json();
        })
        .then(() => {
            alert('Оцінку збережено!');
            closeRateModal();
            location.reload();
        })
        .catch(error => {
            console.error('Помилка при збереженні оцінки:', error);
            alert('Не вдалося зберегти оцінку');
        });
}