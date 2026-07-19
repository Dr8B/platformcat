class PcModalClose extends HTMLElement {
    static get observedAttributes() {
        return ['size', 'label'];
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
        const size = this.getAttribute('size') || 'medium';
        const label = this.getAttribute('label') || 'Закрыть';
        const dim = {small: '1.6rem', medium: '2rem', large: '2.5rem'}[size] || '2rem';
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: inline-block; }
                button {
                    width: ${dim}; height: ${dim}; display: inline-flex; align-items: center;
                    justify-content: center; border-radius: 50%; cursor: pointer;
                    border: 1px solid var(--pc-border, #e2e8f0); background: var(--pc-surface, #f1f5f9);
                    color: var(--pc-text, #334155); transition: background .15s;
                }
                button:hover { background: var(--pc-border, #e2e8f0); }
                svg { width: 55%; height: 55%; }
            </style>
            <button type="button" aria-label="${label}" title="${label}">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.2" stroke-linecap="round">
                    <path d="M6 6l12 12M18 6L6 18"/>
                </svg>
            </button>
        `;
        this.shadowRoot.querySelector('button').addEventListener('click', () => {
            this.dispatchEvent(new CustomEvent('pc-close', {bubbles: true, composed: true}));
        });
    }
}

customElements.define('pc-modal-close', PcModalClose);
