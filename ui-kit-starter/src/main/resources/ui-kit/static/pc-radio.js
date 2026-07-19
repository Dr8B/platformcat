class PcRadio extends HTMLElement {
    static get observedAttributes() {
        return ['label', 'name', 'checked', 'disabled'];
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
        const name = this.getAttribute('name') || '';
        const checked = this.hasAttribute('checked');
        const disabled = this.hasAttribute('disabled');
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: inline-block; }
                label { display: inline-flex; align-items: center; gap: .55em; font: inherit;
                    cursor: pointer; color: var(--pc-text, #1f2937); }
                label[data-disabled] { opacity: .5; cursor: not-allowed; }
                input { width: 1.1em; height: 1.1em; accent-color: var(--pc-primary, #2563eb); cursor: inherit; }
            </style>
            <label ${disabled ? 'data-disabled' : ''}>
                <input type="radio" name="${name}" ${checked ? 'checked' : ''} ${disabled ? 'disabled' : ''}>
                ${label ? `<span>${label}</span>` : ''}
            </label>
        `;
        const input = this.shadowRoot.querySelector('input');
        input.addEventListener('change', () => {
            if (!input.checked) {
                return;
            }
            this.setAttribute('checked', '');
            this.getRootNode().querySelectorAll(`pc-radio[name="${name}"]`).forEach((el) => {
                if (el !== this) {
                    el.removeAttribute('checked');
                }
            });
        });
    }
}

customElements.define('pc-radio', PcRadio);
