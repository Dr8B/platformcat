class PcSpinner extends HTMLElement {
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
        const label = this.getAttribute('label') || '';
        const dim = {small: '1.25rem', medium: '2rem', large: '3rem'}[size] || '2rem';
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: inline-flex; flex-direction: column; align-items: center; gap: .5em; }
                .spinner {
                    width: ${dim}; height: ${dim}; border-radius: 50%;
                    border: .18em solid var(--pc-surface, #e5e7eb);
                    border-top-color: var(--pc-primary, #2563eb);
                    animation: pc-spin .7s linear infinite;
                }
                span { font-size: .85rem; color: var(--pc-muted, #6b7280); }
                @keyframes pc-spin { to { transform: rotate(360deg); } }
            </style>
            <div class="spinner" role="status" aria-label="${label || 'Загрузка'}"></div>
            ${label ? `<span>${label}</span>` : ''}
        `;
    }
}

customElements.define('pc-spinner', PcSpinner);
