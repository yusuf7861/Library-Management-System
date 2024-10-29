// Tab switching functionality
const tabs = document.querySelectorAll('.tab');
const sections = document.querySelectorAll('[id^="section-"]');

tabs.forEach(tab => {
    tab.addEventListener('click', () => {
        tabs.forEach(t => t.classList.remove('tab-active'));
        tab.classList.add('tab-active');
        sections.forEach(section => {
            section.classList.add('hidden');
            if (section.id === `section-${tab.id.split('-')[1]}`) {
                section.classList.remove('hidden');
            }
        });
    });
});

// Author search functionality
const searchAuthorForm = document.getElementById('searchAuthorForm');
const authorTableContainer = document.getElementById('authorTableContainer');

searchAuthorForm.addEventListener('submit', (e) => {
    e.preventDefault();
    // Simulate a search request (replace with actual AJAX call in a real application)
    setTimeout(() => {
        authorTableContainer.classList.remove('hidden');
    }, 500);
});