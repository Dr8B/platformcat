class PcUpload extends HTMLElement {
    static get observedAttributes() {
        return ['label', 'accept', 'multiple', 'disabled'];
    }

    constructor() {
        super();
        this.attachShadow({mode: 'open'});
    }

    connectedCallback() {
        this.render();
    }

    attributeChangedCallback() {
        if (this.shadowRoot.childElementCount) {
            this.render();
        }
    }

    render() {
        const label = this.getAttribute('label') || 'Выберите файл';
        const accept = this.getAttribute('accept') || '';
        const multiple = this.hasAttribute('multiple');
        const disabled = this.hasAttribute('disabled');
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: inline-flex; align-items: center; gap: .75em; }
                button {
                    font: inherit; cursor: pointer; padding: .55em 1em; border-radius: 8px;
                    border: 1px dashed var(--pc-border, #94a3b8); background: var(--pc-surface, #eef2f7);
                    color: var(--pc-text, #1f2937); display: inline-flex; align-items: center; gap: .5em;
                }
                button[disabled] { opacity: .5; cursor: not-allowed; }
                svg { width: 1.1em; height: 1.1em; }
                span.files { font-size: .9rem; color: var(--pc-muted, #6b7280); }
                input { display: none; }
            </style>
            <button type="button" ${disabled ? 'disabled' : ''}>
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M12 16V4M7 9l5-5 5 5"/><path d="M4 16v2a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2v-2"/>
                </svg>
                <span>${label}</span>
            </button>
            <span class="files"></span>
            <input type="file" accept="${accept}" ${multiple ? 'multiple' : ''}>
        `;
        const button = this.shadowRoot.querySelector('button');
        const input = this.shadowRoot.querySelector('input');
        const files = this.shadowRoot.querySelector('.files');
        button.addEventListener('click', () => input.click());
        input.addEventListener('change', () => {
            files.textContent = Array.from(input.files).map((f) => f.name).join(', ');
        });
    }
}

customElements.define('pc-upload', PcUpload);
