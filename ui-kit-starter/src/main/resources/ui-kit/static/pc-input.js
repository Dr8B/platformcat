class PcInput extends HTMLElement {
    static get observedAttributes() {
        return ['label', 'placeholder', 'type', 'value', 'disabled'];
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
        const label = this.getAttribute('label') || '';
        const placeholder = this.getAttribute('placeholder') || '';
        const type = this.getAttribute('type') || 'text';
        const value = this.getAttribute('value') || '';
        const disabled = this.hasAttribute('disabled');
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: inline-block; }
                label { display: flex; flex-direction: column; gap: .35em; font: inherit; }
                span { font-size: .85rem; color: var(--pc-muted, #6b7280); }
                input {
                    font: inherit; padding: .55em .75em; border-radius: 8px;
                    border: 1px solid var(--pc-border, #cbd5e1); background: var(--pc-field, #fff);
                    color: var(--pc-text, #1f2937); min-width: 220px; transition: border-color .15s, box-shadow .15s;
                }
                input:focus { outline: none; border-color: var(--pc-primary, #2563eb);
                    box-shadow: 0 0 0 3px color-mix(in srgb, var(--pc-primary, #2563eb) 20%, transparent); }
                input:disabled { background: #f1f5f9; opacity: .7; cursor: not-allowed; }
            </style>
            <label>
                ${label ? `<span>${label}</span>` : ''}
                <input type="${type}" placeholder="${placeholder}" value="${value}" ${disabled ? 'disabled' : ''}>
            </label>
        `;
    }
}

customElements.define('pc-input', PcInput);
